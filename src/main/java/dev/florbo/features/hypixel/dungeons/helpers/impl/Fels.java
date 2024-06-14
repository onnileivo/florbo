package dev.florbo.features.hypixel.dungeons.helpers.impl;

import cc.polyfrost.oneconfig.config.core.OneColor;
import dev.florbo.config.FlorboConfig;
import dev.florbo.features.hypixel.dungeons.helpers.Renderable;

public class Fels implements Renderable {

    @Override
    public float getBoxWidth() {
        return FlorboConfig.felsEspBoxWidth;
    }

    @Override
    public float getBoxHeight() {
        return FlorboConfig.felsEspBoxHeight;
    }

    @Override
    public OneColor getColor() {
        return FlorboConfig.felsEspColor;
    }

    @Override
    public boolean getEnabled() {
        return FlorboConfig.felsEsp;
    }
}
