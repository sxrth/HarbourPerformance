package com.sxrth.obsidian;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public final class ClientEntrypoint implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricLoader loader = FabricLoader.getInstance();

        System.out.println("[ObsidianOptimization] 1.6.5 loaded.");
        System.out.println("[ObsidianOptimization] Sodium=" + loader.isModLoaded("sodium"));
        System.out.println("[ObsidianOptimization] Iris=" + loader.isModLoaded("iris"));
        System.out.println("[ObsidianOptimization] Lithium=" + loader.isModLoaded("lithium"));
        System.out.println("[ObsidianOptimization] ImmediatelyFast=" + loader.isModLoaded("immediatelyfast"));
    }
}
