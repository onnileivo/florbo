package dev.florbo.features.hypixel.dungeons.helpers.impl;

import cc.polyfrost.oneconfig.config.core.OneColor;
import dev.florbo.config.FlorboConfig;
import dev.florbo.features.hypixel.dungeons.helpers.Renderable;

public class ShadowAssasin implements Renderable {

    @Override
    public float getBoxWidth() {
        return FlorboConfig.saEspBoxWidth;
    }

    @Override
    public float getBoxHeight() {
        return FlorboConfig.saEspBoxHeight;
    }

    @Override
    public OneColor getColor() {
        return FlorboConfig.saEspColor;
    }

    @Override
    public boolean getEnabled() {
        return FlorboConfig.saEsp;
    }
}
