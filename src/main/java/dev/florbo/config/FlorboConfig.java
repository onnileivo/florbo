package dev.florbo.config;


import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.core.OneColor;
import cc.polyfrost.oneconfig.config.core.OneKeyBind;
import dev.florbo.FlorboMod;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import cc.polyfrost.oneconfig.config.data.OptionSize;
import dev.florbo.features.hypixel.farming.ToggleAutoBreak;
import dev.florbo.hud.CounterHud;
import dev.florbo.hud.TimerHud;
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

    /*-----------------------STARRED MOB ESP-----------------------*/
    @Switch(
            name = "starred mob esp toggle",
            size = OptionSize.DUAL,
            category = "Render", // Render
            subcategory = "starred mob esp"
    )
    public static boolean mobEsp = false;
    @cc.polyfrost.oneconfig.config.annotations.Color( //wtf, not sigma
            name = "starred mob esp color",
            category = "Render",
            subcategory = "starred mob esp"
    )
    public static OneColor mobEspColor = new OneColor(255, 255, 255, 255);


    @Slider(
            name = "starred mob esp line width",
            min = 1, max = 10,

            step = 1,
            category = "Render",
            subcategory = "starred mob esp"
    )
    public static int starredMobEspLineWidth = 1;

    @Slider(
            name = "starred mob esp box height (can be neg)",
            category = "Render",
            subcategory = "starred mob esp",
            min = -10f,
            max = 10f
    )
    public static float starredMobEspBoxHeight = 2f;

    @Slider(
            name = "starred mob esp box width (can be neg)",
            category = "Render",
            subcategory = "starred mob esp",
            min = -10,
            max = 10f
    )
    public static float starredMobEspBoxWidth = 1f;

    /*----------------------FELS ESP------------------*/
    @Switch(
            name = "fels mob esp",
            size = OptionSize.DUAL,
            category = "Render",
            subcategory = "fels esp"
    )
    public static boolean felsEsp = false;
    @cc.polyfrost.oneconfig.config.annotations.Color( //wtf, not sigma
            name = "fels mob esp color",
            category = "Render",
            subcategory = "fels esp"
    )
    public static OneColor felsEspColor = new OneColor(255, 255, 255, 255);

    @Slider(
            name = "fels mob esp box width",
            min = -10f,
            max = 10f,
            subcategory = "fels esp"
    )
    public static float felsEspBoxWidth = 2f;

    @Slider(
            name = "fels mob esp box heigth",
            min = -10f,
            max = 10f,
            subcategory = "fels esp"
    )
    public static float felsEspBoxHeight = 2f;


    // INVIS SHADOW ASSSIN

    @Switch(
            name = "Invis SA esp",
            size = OptionSize.DUAL,
            category = "Render",
            subcategory = "Sa esp"
    )
    public static boolean saEsp = false;
    @cc.polyfrost.oneconfig.config.annotations.Color( //wtf, not sigma
            name = "SA esp color",
            category = "Render",
            subcategory = "Sa esp"
    )
    public static OneColor saEspColor = new OneColor(255, 255, 255, 255);

    @Slider(
            name = "SA mob esp box width",
            min = -10f,
            max = 10f,
            subcategory = "Sa esp"
    )
    public static float saEspBoxWidth = 2f;

    @Slider(
            name = "sa mob esp box heigth",
            min = -10f,
            max = 10f,
            subcategory = "Sa esp"
    )
    public static float saEspBoxHeight = 2f;












    @Switch(
            name = "autobreak",
            description = "breaks blocks and moves with the arrowkeys",
            size = OptionSize.DUAL,
            category = "QOL",
            subcategory = "OldAutoBreak"
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
            subcategory = "OldAutoBreak"
    )
    public static OneKeyBind autoBreakKeybind = new OneKeyBind(Keyboard.KEY_P);

    @Switch(
            name = "funny main menu",
            category = "funny",
            subcategory = "gui"
    )
    public static boolean funnyMainMenu = true;

    @KeyBind(
            name = "left autobreak toggle",
            description = "tap it once to toggle going to the direction and break block th9ingy",
            category = "QOL",
            subcategory = "ToggleAutoBreak"
    )
    public static OneKeyBind toggleAutoBreakLeft = new OneKeyBind(Keyboard.KEY_N);

    @KeyBind(
            name = "right autobreak toggle",
            description = "tap it once to toggle going to the direction and break block th9ingy",
            category = "QOL",
            subcategory = "ToggleAutoBreak"
    )
    public static OneKeyBind toggleAutoBreakRight = new OneKeyBind(Keyboard.KEY_M);

    @HUD(
            name = "Counter hud",
            category = "Misc",
            subcategory = "Counter"
    )
    public CounterHud counterHud = new CounterHud();

    @KeyBind(
            name = "Decrement counter",
            category = "Misc",
            subcategory = "Counter"
    )
    public OneKeyBind decrementCounterBind = new OneKeyBind(Keyboard.KEY_K);

    @KeyBind(
            name = "Increment counter",
            category = "Misc",
            subcategory = "Counter"
    )
    public OneKeyBind incrementCounterBind = new OneKeyBind(Keyboard.KEY_L);

    @KeyBind(
            name = "Reset counter",
            category = "Misc",
            subcategory = "Counter"
    )
    public OneKeyBind resetCounterBind = new OneKeyBind(Keyboard.KEY_J);

    @Switch(
            name = "funny messages from admins",
            category = "Funny",
            subcategory = "useless xd"

    )
    public static boolean funnyMessagesFromAdmins = false;


    @HUD(
            name = "timer hud",
            category = "Misc",
            subcategory = "Timer"
    )
    public TimerHud timerHud = new TimerHud();

    @KeyBind(
            name = "pause / resume timer",
            category = "Misc",
            subcategory = "Timer"
    )
    public OneKeyBind pauseOrResumeTimerBind = new OneKeyBind(Keyboard.KEY_H);

    @KeyBind(
            name = "reset timer",
            category = "Misc",
            subcategory = "Timer"
    )
    public OneKeyBind resetTimerBind = new OneKeyBind(Keyboard.KEY_G);


    /*----DEBUGS----*/ // needed so debug last in list :DDDDDDDDDDD

    @Switch(
            name = "starred mob esp all entities",
            category = "DEBUG"
    )
    public static boolean starredMobEspAllEntities = false;

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
    public void toggleAutoBreak() {autoBreak = !autoBreak;}
    public FlorboConfig() {
        super(new Mod(FlorboMod.NAME, ModType.HYPIXEL, "/florbo/assets/icon.png"), FlorboMod.MODID + ".json");
        initialize();
        registerKeyBind(unGrabKeybind, KeyboardUtils::toggleGrab);
        registerKeyBind(autoBreakKeybind, this::toggleAutoBreak);
        registerKeyBind(toggleAutoBreakLeft, ToggleAutoBreak::onLeftPress);
        registerKeyBind(toggleAutoBreakRight, ToggleAutoBreak::onRightPress);
        registerKeyBind(resetCounterBind, counterHud::resetCounter);
        registerKeyBind(incrementCounterBind, counterHud::incrementCounter);
        registerKeyBind(decrementCounterBind, counterHud::decrementCounter);
        registerKeyBind(pauseOrResumeTimerBind, timerHud::pauseResumeTimer);
        registerKeyBind(resetTimerBind, timerHud::reset);
    }
}

