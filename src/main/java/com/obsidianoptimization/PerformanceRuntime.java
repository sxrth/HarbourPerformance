package com.obsidianoptimization;

import net.fabricmc.loader.api.FabricLoader;

public final class PerformanceRuntime {
    private static boolean active = true;

    private PerformanceRuntime() {}

    public static void initialize() {
        // Compatibility-first: detect common renderer/performance mods without modifying them.
        FabricLoader loader = FabricLoader.getInstance();
        boolean sodium = loader.isModLoaded("sodium");
        boolean iris = loader.isModLoaded("iris");
        boolean immediatelyFast = loader.isModLoaded("immediatelyfast");

        // Detection is intentionally read-only. No renderer or Mixin hooks are installed.
        active = true;
        System.out.println("[ObsidianOptimization] loaded"
                + " | Sodium=" + sodium
                + " | Iris=" + iris
                + " | ImmediatelyFast=" + immediatelyFast);
    }

    public static boolean isActive() {
        return active;
    }
}