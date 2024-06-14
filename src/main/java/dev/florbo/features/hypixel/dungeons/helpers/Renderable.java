package dev.florbo.features.hypixel.dungeons.helpers;

import cc.polyfrost.oneconfig.config.core.OneColor;

public interface Renderable {
    float getBoxWidth();
    float getBoxHeight();
    OneColor getColor();
    boolean getEnabled();
}
