package com.sxrth.obsidian;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientEntrypoint implements ClientModInitializer {
    public static final String MOD_ID = "obsidianoptimization";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("ObsidianOptimization initialized successfully!");
    }
}
