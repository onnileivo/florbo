package dev.florbo.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import dev.florbo.FlorboMod;
import net.minecraft.client.Minecraft;

@Command(value="florbo", aliases = {"florboclient", "florboglorbo", "flopper", "florbbo", "florboglorbbo", "fc"})
public class FlorboCommand {

    @Main
    private void handle() {
        FlorboMod.config.openGui();
        System.out.println(Minecraft.getMinecraft().theWorld.loadedEntityList); //debugger, noone uses command anyway lmao
    }
}