package dev.florbo;

import dev.florbo.command.ExampleCommand;
import dev.florbo.config.TestConfig;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import net.minecraftforge.fml.common.Mod;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = FlorboMod.MODID, name = FlorboMod.NAME, version = FlorboMod.VERSION)
public class FlorboMod {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    @Mod.Instance(MODID)
    public static FlorboMod INSTANCE; // Adds the instance of the mod, so we can access other variables.
    public static TestConfig config;

    // Register the config and commands.
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new TestConfig();
        CommandManager.INSTANCE.registerCommand(new ExampleCommand());
    }
}
