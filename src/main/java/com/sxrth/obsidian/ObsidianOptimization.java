package com.sxrth.obsidian;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public final class ObsidianOptimization implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        FabricLoader loader = FabricLoader.getInstance();

        System.out.println("[ObsidianOptimization] 1.6.6 loaded.");
        System.out.println("[ObsidianOptimization] Sodium=" + loader.isModLoaded("sodium"));
        System.out.println("[ObsidianOptimization] Iris=" + loader.isModLoaded("iris"));
        System.out.println("[ObsidianOptimization] Lithium=" + loader.isModLoaded("lithium"));
        System.out.println("[ObsidianOptimization] ImmediatelyFast=" + loader.isModLoaded("immediatelyfast"));
    }
}
