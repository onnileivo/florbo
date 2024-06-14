package dev.florbo.features.hypixel.dungeons.helpers.impl;

import cc.polyfrost.oneconfig.config.core.OneColor;
import dev.florbo.config.FlorboConfig;
import dev.florbo.features.hypixel.dungeons.helpers.Renderable;

public class StarredMob implements Renderable {

    @Override
    public float getBoxWidth() {
        return FlorboConfig.starredMobEspBoxWidth;
    }

    @Override
    public float getBoxHeight() {
        return FlorboConfig.starredMobEspBoxHeight;
    }

    @Override
    public OneColor getColor() {
        return FlorboConfig.mobEspColor;
    }

    @Override
    public boolean getEnabled() {
        return FlorboConfig.mobEsp;
    }
}
