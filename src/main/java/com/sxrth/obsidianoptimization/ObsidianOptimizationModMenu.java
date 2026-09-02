package com.sxrth.obsidianoptimization;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class ObsidianOptimizationModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new Screen(Component.literal("ObsidianOptimization")) {
            @Override
            protected void init() {
                addRenderableWidget(Button.builder(
                        Component.literal("Automatic optimization: ON"),
                        button -> {})
                    .bounds(this.width / 2 - 120, this.height / 2 - 10, 240, 20)
                    .build());
            }

            @Override
            public void onClose() {
                this.minecraft.setScreen(parent);
            }
        };
    }
}
