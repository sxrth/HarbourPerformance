package com.obsidianoptimization;

import net.fabricmc.loader.api.FabricLoader;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Lightweight client-side performance policy.
 * Avoids renderer overwrites and is safe to disable automatically when
 * known render-stack mods are present.
 */
public final class PerformanceRuntime {
    private static final AtomicBoolean ACTIVE = new AtomicBoolean(true);

    private PerformanceRuntime() {}

    public static void initialize() {
        // Keep the mod conservative when a major renderer replacement is present.
        boolean sodium = FabricLoader.getInstance().isModLoaded("sodium");
        boolean iris = FabricLoader.getInstance().isModLoaded("iris");
        if (sodium || iris) {
            // Do not patch the renderer; coexist with the external renderer.
            ACTIVE.set(true);
        }
    }

    public static boolean isActive() {
        return ACTIVE.get();
    }
}
