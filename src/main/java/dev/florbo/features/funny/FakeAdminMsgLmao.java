package dev.florbo.features.funny;

import dev.florbo.FlorboMod;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class FakeAdminMsgLmao {
    public static final String[] adminList = {
            "Hypixel"
            ,"Rezzus"
            ,"Plancke"
            ,"OrangeMarshall"
            ,"Jayavarmen"
            ,"TimeDeo"
            ,"xHascox"
            ,"aPunch"
            ,"Minikloon"
    }; // bad formatting because of magic cursor i think thats the name of the thingymajingy

    public static final String[] fakeMessageList = {
            "What the skibidi are you doing?",
            "sigmaaa",
            "skibidi broiler?",
            "based? based on what?",
            "will you be my e-kitten please",
            "what the skibidi",
            "skibidi toilet is my favourite show, want to watch it with me?"
    };
    // this is the greatest joke of all time lmao

    public static void showFakeMessageFromAdmin() {
        if (Minecraft.getMinecraft().thePlayer != null) {
            String name = FlorboMod.getRandomString(adminList);
            String message = FlorboMod.getRandomString(fakeMessageList);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("§dFrom §c[ADMIN] §c" + name + "§7:" + " §7" + message));
        }
    }
    @SubscribeEvent
    public void on(GuiOpenEvent e) {
        System.out.println("skibidi ");
        if (new Random().nextInt(300) == 69) {showFakeMessageFromAdmin(); } // 1 in 300 chance to do some funny business lmao todo: remove xd
    }
}
