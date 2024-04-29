package dev.florbo.util;

import net.minecraft.util.MouseHelper;
import org.lwjgl.input.Mouse;

import static dev.florbo.features.hypixel.dungeons.MobEsp.mc;

public class KeyboardUtils {
    private static boolean mouseUngrabbed;
    private static MouseHelper oldMouseHelper;

    public static void ungrabMouse() {
        if (!Mouse.isGrabbed() || mouseUngrabbed) return;
        mc.gameSettings.pauseOnLostFocus = false;
        oldMouseHelper = mc.mouseHelper;
        oldMouseHelper.ungrabMouseCursor();
        mc.inGameHasFocus = true;
        mc.mouseHelper = new MouseHelper() {
            @Override
            public void mouseXYChange() {
            }

            @Override
            public void grabMouseCursor() {
            }

            @Override
            public void ungrabMouseCursor() {
            }
        };
        mouseUngrabbed = true;
    }
    public static void regrabMouse() {
        regrabMouse(false);
    }

    public static void regrabMouse(boolean force) {
        if (!mouseUngrabbed && !force) return;
        mc.mouseHelper = oldMouseHelper;
        if (mc.currentScreen == null || force) {
            mc.mouseHelper.grabMouseCursor();
        }
        mouseUngrabbed = false;
    }

    public static void toggleGrab() {
        if (mouseUngrabbed) {
            regrabMouse();
        } else {
            ungrabMouse();
        }
    }
}
