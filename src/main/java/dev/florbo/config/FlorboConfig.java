package dev.florbo.config;


import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneKeyBind;
import dev.florbo.FlorboMod;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import dev.florbo.util.KeyboardUtils;
import org.lwjgl.input.Keyboard;

import java.awt.Color;

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

    @KeyBind(
            name = "Mouse ungrab",
            category = "QOL",
            subcategory = "Misc"
    )
    public static OneKeyBind unGrabKeybind = new OneKeyBind(Keyboard.KEY_O);

    @KeyBind(
            name = "autobreak keybind",
            category = "QOL",
            subcategory = "Farming"
    )
    public static OneKeyBind autoBreakKeybind = new OneKeyBind(Keyboard.KEY_P);
    // getters
    public static Color getChestEspColor() {
        switch (chestEspColor) {
            case 1: return Color.BLUE;
            case 2: return Color.MAGENTA;
            default: return Color.RED;
            // if undefined red
            // or if case 0 (RED)
        }
    }
    public static Color getMobEspColor() {
        switch (mobEspColor) {
            case 1: return Color.BLUE;
            case 2: return Color.MAGENTA;
            default: return Color.RED;
            // if undefined red
            // or if case 0 (RED)
        }
    }
    public void toggleAutoBreak() {autoBreak = !autoBreak;}
    public FlorboConfig() {
        super(new Mod(FlorboMod.NAME, ModType.HYPIXEL, "/florbo/assets/icon.png"), FlorboMod.MODID + ".json");
        initialize();
        registerKeyBind(unGrabKeybind, KeyboardUtils::toggleGrab);
        registerKeyBind(autoBreakKeybind, this::toggleAutoBreak);
    }
}

