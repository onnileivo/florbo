package dev.florbo.hud;

import cc.polyfrost.oneconfig.config.annotations.KeyBind;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.core.OneKeyBind;
import cc.polyfrost.oneconfig.hud.SingleTextHud;
import org.lwjgl.input.Keyboard;

public class CounterHud extends SingleTextHud {
    public static int count = 0;

    @Switch(
            name = "don't show if 0",
            category = "Misc",
            subcategory = "Counter"
    )
    public boolean dontshowif0 = true;
    public CounterHud() {
        super("", true);
    }

    @Override
    protected String getText(boolean example) {
        if (example) return "69 haha";
        if (count == 0 && dontshowif0) return "";
        return String.valueOf(count);
    }

    public void incrementCounter() {
        count++;
    }

    public void decrementCounter() {
        if (count >= 1) count--;
    }
    public void resetCounter() {
        count = 0;
    }
}
