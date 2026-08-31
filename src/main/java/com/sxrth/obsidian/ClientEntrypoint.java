package com.sxrth.obsidian;

import net.fabricmc.api.ClientModInitializer;

public final class ClientEntrypoint implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ObsidianOptimization.initialize();
    }
}