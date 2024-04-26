package dev.florbo.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import dev.florbo.FlorboMod;

@Command(value="florbo", aliases = {"florboclient", "florboglorbo", "flopper", "florbbo", "florboglorbbo"})
public class FlorboCommand {

    @Main
    private void handle() {
        FlorboMod.config.openGui();
    }
}
