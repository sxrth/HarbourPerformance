package com.sxrth.obsidianoptimization;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public final class ObsidianOptimization implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricLoader loader = FabricLoader.getInstance();

        System.out.println("[ObsidianOptimization] 1.6.3 loaded.");
        System.out.println("[ObsidianOptimization] Sodium=" + loader.isModLoaded("sodium"));
        System.out.println("[ObsidianOptimization] Iris=" + loader.isModLoaded("iris"));
        System.out.println("[ObsidianOptimization] Lithium=" + loader.isModLoaded("lithium"));
        System.out.println("[ObsidianOptimization] ImmediatelyFast=" + loader.isModLoaded("immediatelyfast"));
    }
}