package dev.florbo;


import dev.florbo.command.FlorboCommand;
import dev.florbo.config.FlorboConfig;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import dev.florbo.features.hypixel.farming.AutoBreak;
import dev.florbo.features.hypixel.farming.ToggleAutoBreak;
import dev.florbo.features.visuals.ChestEsp;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.opengl.Display;

import java.util.Random;

/**
 * The entrypoint of the Example Mod that initializes it.
 *
 * @see Mod
 * @see InitializationEvent
 */
@Mod(modid = FlorboMod.MODID, name = FlorboMod.NAME, version = FlorboMod.VERSION, clientSideOnly = true)
public class FlorboMod {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    // Sets the variables from `gradle.properties`. See the `blossom` config in `build.gradle.kts`.
    @Mod.Instance(MODID)
    public static FlorboMod INSTANCE;
    public static FlorboConfig config;
    public Minecraft mc;
    public static String splashText;
    public String[] customSplashes = {"florbo client best mod !!!!", "ABONGUS", "what the sigma", "skibidi toilet w rizz", "ono ono ono ono ono ono", "sponsored by suupan kebab oy", "i shitted", "HOP ON HYPIXEL NO DOWNTIME", "local e-kittens in your area !!!"};
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        splashText = getRandomString(customSplashes);
        config = new FlorboConfig();
        CommandManager.INSTANCE.registerCommand(new FlorboCommand());
        mc = Minecraft.getMinecraft();
        Display.setTitle("FLORBOOO GLORBOOO version umm like " + VERSION + " or some shit");
        MinecraftForge.EVENT_BUS.register(new ChestEsp());
        MinecraftForge.EVENT_BUS.register(new AutoBreak());
        MinecraftForge.EVENT_BUS.register(new ToggleAutoBreak());
    }
    public static String getRandomString(String[] array) {
        return array[new Random().nextInt(array.length)];
    }
}