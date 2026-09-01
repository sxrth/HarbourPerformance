package com.sxrth.obsidian;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.ClientModInitializer;

public final class ObsidianOptimization implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricLoader loader = FabricLoader.getInstance();
        System.out.println("[ObsidianOptimization] 1.7.0 loaded for Minecraft 1.21.11");
        System.out.println("[ObsidianOptimization] Sodium=" + loader.isModLoaded("sodium"));
        System.out.println("[ObsidianOptimization] Iris=" + loader.isModLoaded("iris"));
        System.out.println("[ObsidianOptimization] Lithium=" + loader.isModLoaded("lithium"));
        System.out.println("[ObsidianOptimization] ImmediatelyFast=" + loader.isModLoaded("immediatelyfast"));
    }
}
