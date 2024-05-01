package dev.florbo.features.hypixel.farming;

import dev.florbo.config.FlorboConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.security.Key;
import java.util.Objects;

public class ToggleAutoBreak {
    static KeyBinding right = FMLClientHandler.instance().getClient().gameSettings.keyBindRight;
    static KeyBinding left = FMLClientHandler.instance().getClient().gameSettings.keyBindLeft;

    public enum breakState {
        NONE,
        MOVINGLEFT,
        MOVINGRIGHT
    }
    public static breakState currentState = breakState.NONE;
    public static breakState oldCurrentState = currentState;

    public static void resetKeys() {
        KeyBinding.setKeyBindState(right.getKeyCode(), false);
        KeyBinding.setKeyBindState(left.getKeyCode(), false);
    }

    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event) {
        if (Minecraft.getMinecraft().currentScreen != null) {
            resetKeys();
            currentState = breakState.NONE;
        }
        if ((oldCurrentState == breakState.NONE) && (currentState != breakState.NONE)) {
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), true);
        }
        if ((currentState == breakState.NONE) && (oldCurrentState != breakState.NONE)) {
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), false);
            oldCurrentState = currentState;
        }
        if (oldCurrentState != currentState) {
            switch (currentState) {
                case MOVINGRIGHT:
                    KeyBinding.setKeyBindState(right.getKeyCode(), true);
                    break;
                case MOVINGLEFT:
                    KeyBinding.setKeyBindState(left.getKeyCode(), true);
            }
        }
    }

    public static void onLeftPress() {
        oldCurrentState = currentState;
        resetKeys();
        if (currentState == breakState.MOVINGLEFT) {
            currentState = breakState.NONE;
        } else {
            currentState = breakState.MOVINGLEFT;
        }
    }

    public static void onRightPress() {
        oldCurrentState = currentState;
        resetKeys();
        if (currentState == breakState.MOVINGRIGHT) currentState = breakState.NONE;
        else currentState = breakState.MOVINGRIGHT;
    }
}
