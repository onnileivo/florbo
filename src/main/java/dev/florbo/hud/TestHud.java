package dev.florbo.hud;

import cc.polyfrost.oneconfig.hud.SingleTextHud;
import dev.florbo.config.FlorboConfig;

/**
 * An example OneConfig HUD that is started in the config and displays text.
 *
 * @see FlorboConfig#hud
 */
public class TestHud extends SingleTextHud {
    public TestHud() {
        super("Test", false);
    }

    @Override
    public String getText(boolean example) {
        long l = System.currentTimeMillis();
        return "The time is " + Long.toString(l);
    }
}
