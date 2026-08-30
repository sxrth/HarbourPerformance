package com.obsidianoptimization;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

/**
 * Safe, compatibility-first client performance helper.
 * It deliberately avoids renderer overwrites and aggressive mixins.
 */
public final class ObsidianOptimization implements ClientModInitializer {
    private static final String NAME = "ObsidianOptimization";
    private static boolean performanceMode;
    private static boolean adaptiveMode;
    private static int savedRenderDistance = -1;
    private static int savedSimulationDistance = -1;
    private static int savedFps = -1;
    private static int adaptiveTick;

    private static final KeyMapping TOGGLE = new KeyMapping(
            "key.obsidianoptimization.toggle", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F8,
            "category.obsidianoptimization");
    private static final KeyMapping ADAPTIVE = new KeyMapping(
            "key.obsidianoptimization.adaptive", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F9,
            "category.obsidianoptimization");

    @Override
    public void onInitializeClient() {
        PerformanceRuntime.initialize();
        KeyBindingHelper.registerKeyBinding(TOGGLE);
        KeyBindingHelper.registerKeyBinding(ADAPTIVE);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE.consumeClick()) togglePerformance(client);
            while (ADAPTIVE.consumeClick()) toggleAdaptive(client);
            if (performanceMode && adaptiveMode && client.level != null) adaptiveTick(client);
        });

        System.out.println("[" + NAME + "] loaded. F8=Performance, F9=Adaptive.");
        logDetectedMods();
    }

    private static void togglePerformance(Minecraft client) {
        Options o = client.options;
        if (!performanceMode) {
            savedRenderDistance = o.renderDistance().get();
            savedSimulationDistance = o.simulationDistance().get();
            savedFps = o.framerateLimit().get();

            // Conservative baseline: meaningful FPS gain without touching renderer internals.
            o.renderDistance().set(Math.min(savedRenderDistance, 8));
            o.simulationDistance().set(Math.min(savedSimulationDistance, 5));
            o.framerateLimit().set(260);
            performanceMode = true;
            adaptiveMode = true;
            adaptiveTick = 0;
            message(client, "§aHarbourPerformance §lON §7• Adaptive FPS aktif • F9 aç/kapat");
        } else {
            restore(o);
            performanceMode = false;
            adaptiveMode = false;
            message(client, "§cHarbourPerformance §lOFF");
        }
        o.save();
    }

    private static void toggleAdaptive(Minecraft client) {
        if (!performanceMode) {
            message(client, "§eÖnce §fF8 §eile Performance Mode'u aç.");
            return;
        }
        adaptiveMode = !adaptiveMode;
        message(client, adaptiveMode
                ? "§aAdaptive FPS §lON"
                : "§cAdaptive FPS §lOFF");
    }

    private static void adaptiveTick(Minecraft client) {
        // Evaluate roughly twice per second. This only changes render distance,
        // so it stays friendly with Sodium/Iris/ImmediatelyFast and similar mods.
        if (++adaptiveTick < 20) return;
        adaptiveTick = 0;

        Options o = client.options;
        int fps = client.getFps();
        int current = o.renderDistance().get();
        int target = current;

        if (fps > 165 && current < 12) target = current + 1;
        else if (fps < 75 && current > 5) target = current - 1;
        else if (fps < 50 && current > 4) target = current - 1;

        // Never exceed the user's original render distance while Performance Mode is active.
        if (savedRenderDistance > 0) target = Math.min(target, savedRenderDistance);
        target = Math.max(4, Math.min(target, 12));

        if (target != current) o.renderDistance().set(target);
    }

    private static void restore(Options o) {
        if (savedRenderDistance >= 0) o.renderDistance().set(savedRenderDistance);
        if (savedSimulationDistance >= 0) o.simulationDistance().set(savedSimulationDistance);
        if (savedFps >= 0) o.framerateLimit().set(savedFps);
    }

    private static void message(Minecraft client, String text) {
        if (client.gui != null) client.gui.getChat().addMessage(Component.literal(text));
    }

    private static void logDetectedMods() {
        String[] optional = {"sodium", "iris", "immediatelyfast", "entityculling", "lithium", "ferritecore", "indium"};
        StringBuilder found = new StringBuilder();
        for (String id : optional) {
            if (FabricLoader.getInstance().isModLoaded(id)) {
                if (found.length() > 0) found.append(", ");
                found.append(id);
            }
        }
        System.out.println("[" + NAME + "] compatible mods detected: " + (found.length() == 0 ? "none" : found));
    }
}
