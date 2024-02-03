package dev.florbo.command;

import dev.florbo.FlorboMod;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;

/**
 * An example command implementing the Command api of OneConfig.
 * Registered in ExampleMod.java with `CommandManager.INSTANCE.registerCommand(new ExampleCommand());`
 *
 * @see Command
 * @see Main
 * @see FlorboMod
 */
@Command(value = FlorboMod.MODID, description = "Access the " + FlorboMod.NAME + " GUI.")
public class ExampleCommand {
    @Main
    private void handle() {
        FlorboMod.INSTANCE.config.openGui();
    }
}