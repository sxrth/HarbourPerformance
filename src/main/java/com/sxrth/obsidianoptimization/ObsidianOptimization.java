package com.sxrth.obsidianoptimization;

import net.fabricmc.api.ClientModInitializer;

/**
 * ObsidianOptimization 1.8.4.
 *
 * Compatibility-first automatic client optimization. This version deliberately
 * does NOT modify render distance or simulation distance, so it cannot cause
 * repeated chunk reloads by rewriting those options at runtime.
 */
public final class ObsidianOptimization implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("[ObsidianOptimization] 1.8.4 loaded - chunk-safe automatic mode active.");
    }
}
