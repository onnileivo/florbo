package dev.florbo.features.hypixel.farming;

import dev.florbo.FlorboMod;
import dev.florbo.config.FlorboConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import javax.swing.text.JTextComponent;

public class AutoBreak {
    // SPHAGETTI UP AHEAD
    public static boolean shouldBreak = false;
    public static KeyBinding shouldBePressed;
    public Minecraft mc = Minecraft.getMinecraft();
    KeyBinding forward = FMLClientHandler.instance().getClient().gameSettings.keyBindForward;
    KeyBinding back = FMLClientHandler.instance().getClient().gameSettings.keyBindBack;
    KeyBinding right = FMLClientHandler.instance().getClient().gameSettings.keyBindRight;
    KeyBinding left = FMLClientHandler.instance().getClient().gameSettings.keyBindLeft;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (FlorboConfig.autoBreak && shouldBreak) {
            resetKeys();
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), true);
            KeyBinding.setKeyBindState(shouldBePressed.getKeyCode(), true);
        }
    }
    public void resetKeys() {
        KeyBinding.setKeyBindState(forward.getKeyCode(), false);
        KeyBinding.setKeyBindState(back.getKeyCode(), false);
        KeyBinding.setKeyBindState(right.getKeyCode(), false);
        KeyBinding.setKeyBindState(left.getKeyCode(), false);
    }
    @SubscribeEvent
    public void onPlayerKeyPress(InputEvent.KeyInputEvent event) {
        boolean keyUp = Keyboard.isKeyDown(Keyboard.KEY_UP);
        boolean keyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
        boolean keyLeft = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
        boolean keyRight = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);


        if (shouldBreak && !(keyUp || keyDown || keyLeft || keyRight)) {
            resetKeys();
            KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), false);
        }
        shouldBreak = keyUp || keyDown || keyLeft || keyRight;
        if (keyUp) {
            shouldBePressed = forward;
        } else if (keyLeft) {
            shouldBePressed = left;
        } else if (keyRight) {
            shouldBePressed = right;
        } else if (keyDown) {
            shouldBePressed = back;
        }
    }
}
