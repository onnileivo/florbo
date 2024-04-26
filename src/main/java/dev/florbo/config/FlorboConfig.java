package dev.florbo.config;


import dev.florbo.FlorboMod;
import dev.florbo.hud.TestHud;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Dropdown;
import cc.polyfrost.oneconfig.config.annotations.HUD;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;

import java.awt.*;

/**
 * The main Config entrypoint that extends the Config type and inits the config options.
 * See <a href="https://docs.polyfrost.cc/oneconfig/config/adding-options">this link</a> for more config Options
 */
public class FlorboConfig extends Config {
    @Switch(
            name = "CHEST ESP VAMOS",
            size = OptionSize.DUAL,
            category = "Render",
            subcategory = "Mining"
    )
    public static boolean chestEsp = false;
    @Dropdown(
            name = "Chest esp color",
            options = { "Red","Blue","Purple" },
            category = "Render",
            subcategory = "Mining"
    )
    public static int chestEspColor = 0;
    @Switch(
            name = "MOB ESP VAMOS",
            size = OptionSize.DUAL,
            category = "BROKEN", // Render
            subcategory = "Dungeons"
    )
    public static boolean mobEsp = false;
    @Dropdown(
            name = "Mob esp color",
            options = { "Red","Blue","Purple" },
            category = "BROKEN", // Render
            subcategory = "Dungeons"
    )
    public static int mobEspColor = 0;

    @Switch(
            name = "autobreak",
            description = "breaks blocks and moves with the arrowkeys",
            size = OptionSize.DUAL,
            category = "QOL",
            subcategory = "Farming"
    )
    public static boolean autoBreak = true;


    public static Color getChestEspColor() {
        if (chestEspColor == 0) return Color.RED;
        if (chestEspColor == 1) return Color.BLUE;
        if (chestEspColor == 2) return Color.MAGENTA;
        return Color.RED;
    }
    public static Color getMobEspColor() {
        if (mobEspColor == 0) return Color.RED;
        if (mobEspColor == 1) return Color.BLUE;
        if (mobEspColor == 2) return Color.MAGENTA;
        return Color.RED;
    }
    public FlorboConfig() {
        super(new Mod(FlorboMod.NAME, ModType.HYPIXEL, "/florbo/assets/icon.png"), FlorboMod.MODID + ".json");
        initialize();
    }
}

