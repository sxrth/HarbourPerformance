package com.sxrth.obsidianoptimization;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public final class ObsidianOptimization implements ClientModInitializer {
    private static boolean enabled;
    private static boolean adaptive;
    private static KeyMapping toggleKey;
    private static KeyMapping adaptiveKey;
    private static int savedRenderDistance = -1;
    private static int savedSimulationDistance = -1;

    @Override
    public void onInitializeClient() {
        // Deliberately uses only loader/client classes; no Fabric API dependency.
        System.out.println("[ObsidianOptimization] 1.7.2 loaded.");
    }

    public static void toggle(Minecraft client) {
        enabled = !enabled;
        if (enabled) {
            saveAndApply(client.options);
            notify(client, "ObsidianOptimization: Performance ON");
        } else {
            restore(client.options);
            notify(client, "ObsidianOptimization: Performance OFF");
        }
    }

    public static void toggleAdaptive(Minecraft client) {
        adaptive = !adaptive;
        notify(client, "ObsidianOptimization: Adaptive " + (adaptive ? "ON" : "OFF"));
    }

    private static void saveAndApply(Options options) {
        if (savedRenderDistance < 0) savedRenderDistance = options.getEffectiveRenderDistance();
        if (savedSimulationDistance < 0) savedSimulationDistance = options.simulationDistance().get();
        options.renderDistance().set(Math.min(savedRenderDistance, 10));
        options.simulationDistance().set(Math.min(savedSimulationDistance, 6));
    }

    private static void restore(Options options) {
        if (savedRenderDistance >= 0) options.renderDistance().set(savedRenderDistance);
        if (savedSimulationDistance >= 0) options.simulationDistance().set(savedSimulationDistance);
        savedRenderDistance = -1;
        savedSimulationDistance = -1;
    }

    private static void notify(Minecraft client, String text) {
        if (client.player != null) {
            client.player.displayClientMessage(Component.literal(text), false);
        }
    }
}
