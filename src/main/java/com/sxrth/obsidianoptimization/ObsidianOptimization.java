package com.sxrth.obsidianoptimization;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;

/**
 * Automatic, compatibility-first client optimization.
 * Does not replace the renderer and does not install F8/F9 keybinds.
 */
public final class ObsidianOptimization implements ClientModInitializer {
    private static final int TARGET_RENDER_DISTANCE = 10;
    private static final int TARGET_SIMULATION_DISTANCE = 6;
    private static final int LOW_FPS_RENDER_DISTANCE = 8;
    private static final int LOW_FPS_SIMULATION_DISTANCE = 5;

    private static boolean initialized;
    private static int originalRender = -1;
    private static int originalSimulation = -1;
    private static int tickCounter;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(ObsidianOptimization::onClientTick);
        System.out.println("[ObsidianOptimization] 1.8.0 automatic performance mode loaded.");
    }

    private static void onClientTick(Minecraft client) {
        if (client == null || client.options == null) return;

        if (!initialized) {
            initialized = true;
            captureOriginals(client.options);
            applyBaseline(client.options);
        }

        // Re-evaluate only twice per second to avoid needless option writes.
        if (++tickCounter >= 10) {
            tickCounter = 0;
            adaptiveTune(client);
        }
    }

    private static void captureOriginals(Options options) {
        originalRender = options.renderDistance().get();
        originalSimulation = options.simulationDistance().get();
    }

    private static void applyBaseline(Options options) {
        options.renderDistance().set(Math.min(originalRender, TARGET_RENDER_DISTANCE));
        options.simulationDistance().set(Math.min(originalSimulation, TARGET_SIMULATION_DISTANCE));
    }

    private static void adaptiveTune(Minecraft client) {
        int fps = client.getFps();
        Options options = client.options;

        if (fps > 0 && fps < 45) {
            options.renderDistance().set(Math.min(options.renderDistance().get(), LOW_FPS_RENDER_DISTANCE));
            options.simulationDistance().set(Math.min(options.simulationDistance().get(), LOW_FPS_SIMULATION_DISTANCE));
        } else if (fps >= 90) {
            options.renderDistance().set(Math.min(originalRender, TARGET_RENDER_DISTANCE));
            options.simulationDistance().set(Math.min(originalSimulation, TARGET_SIMULATION_DISTANCE));
        }
    }

    /** Used by the Mod Menu screen. */
    public static void resetPerformanceProfile() {
        Minecraft client = Minecraft.getInstance();
        if (client != null && client.options != null) {
            applyBaseline(client.options);
            if (client.player != null) {
                client.player.displayClientMessage(
                    Component.literal("ObsidianOptimization: automatic performance mode active"), false);
            }
        }
    }
}
