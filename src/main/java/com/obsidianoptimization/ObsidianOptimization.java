package com.obsidianoptimization;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public final class ObsidianOptimization implements ClientModInitializer {
    private static final String NAME = "ObsidianOptimization";

    private static boolean performanceMode;
    private static boolean adaptiveMode;

    private static final KeyBinding.Category CATEGORY = KeyBinding.Category.register(
            Identifier.of(NAME.toLowerCase(), "controls")
    );

    private static final KeyBinding PERFORMANCE = new KeyBinding(
            "key.obsidianoptimization.performance",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F8,
            CATEGORY
    );

    private static final KeyBinding ADAPTIVE = new KeyBinding(
            "key.obsidianoptimization.adaptive",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F9,
            CATEGORY
    );

    @Override
    public void onInitializeClient() {
        PerformanceRuntime.initialize();

        KeyBindingHelper.registerKeyBinding(PERFORMANCE);
        KeyBindingHelper.registerKeyBinding(ADAPTIVE);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (PERFORMANCE.wasPressed()) {
                performanceMode = !performanceMode;
                if (!performanceMode) adaptiveMode = false;
                message(client, "Performance Mode", performanceMode);
            }

            while (ADAPTIVE.wasPressed()) {
                if (!performanceMode) {
                    message(client, "Önce F8 ile Performance Mode'u aç.");
                } else {
                    adaptiveMode = !adaptiveMode;
                    message(client, "Adaptive Mode", adaptiveMode);
                }
            }
        });

        System.out.println("[" + NAME + "] ready. F8=Performance, F9=Adaptive.");
    }

    private static void message(MinecraftClient client, String name, boolean enabled) {
        if (client.player != null) {
            client.player.sendMessage(
                    Text.literal(NAME + ": " + name + " " + (enabled ? "ON" : "OFF")),
                    true
            );
        }
    }

    private static void message(MinecraftClient client, String text) {
        if (client.player != null) {
            client.player.sendMessage(Text.literal(NAME + ": " + text), true);
        }
    }

    public static boolean performanceEnabled() {
        return performanceMode;
    }

    public static boolean adaptiveEnabled() {
        return adaptiveMode;
    }
}