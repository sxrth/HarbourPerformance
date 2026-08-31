package com.sxrth.obsidian;

import net.fabricmc.loader.api.FabricLoader;

public final class ObsidianOptimization {
    private ObsidianOptimization() {}

    public static void initialize() {
        FabricLoader loader = FabricLoader.getInstance();

        System.out.println("[ObsidianOptimization] Compatibility-first build loaded.");
        System.out.println("[ObsidianOptimization] Sodium: " + loader.isModLoaded("sodium"));
        System.out.println("[ObsidianOptimization] Iris: " + loader.isModLoaded("iris"));
        System.out.println("[ObsidianOptimization] Lithium: " + loader.isModLoaded("lithium"));
        System.out.println("[ObsidianOptimization] ImmediatelyFast: " + loader.isModLoaded("immediatelyfast"));
    }
}