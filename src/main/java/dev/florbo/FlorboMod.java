package dev.florbo;


import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import dev.florbo.command.FlorboCommand;
import dev.florbo.config.FlorboConfig;
import dev.florbo.features.funny.FakeAdminMsgLmao;
import dev.florbo.features.hypixel.dungeons.MobEsp;
import dev.florbo.features.hypixel.farming.AutoBreak;
import dev.florbo.features.hypixel.farming.ToggleAutoBreak;
import dev.florbo.features.visuals.ChestEsp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
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

    public static FlorboConfig config;


    xyz.yourboykyle.secretroutes.Main secretRoutes = new xyz.yourboykyle.secretroutes.Main();
    @Mod.Instance(MODID)
    public static FlorboMod INSTANCE;

    public static final Minecraft mc = Minecraft.getMinecraft();
    public static String splashText;
    public static final String[] customSplashes = {"florbo client best mod !!!!", "ABONGUS", "what the sigma", "skibidi toilet w rizz", "ono ono ono ono ono ono", "sponsored by suupan kebab oy", "i shitted", "HOP ON HYPIXEL NO DOWNTIME", "local e-kittens in your area !!!"};

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        Display.setTitle("FLORBOOO GLORBOOO version umm like " + VERSION + " or some shit");
        splashText = getRandomString(customSplashes);
        config = new FlorboConfig();

        CommandManager.INSTANCE.registerCommand(new FlorboCommand());

        MinecraftForge.EVENT_BUS.register(new ChestEsp());
        MinecraftForge.EVENT_BUS.register(new AutoBreak());
        MinecraftForge.EVENT_BUS.register(new ToggleAutoBreak());
        MinecraftForge.EVENT_BUS.register(new MobEsp());
        MinecraftForge.EVENT_BUS.register(new FakeAdminMsgLmao());

    }

    public static String getRandomString(String[] array) {
        return array[new Random().nextInt(array.length)];
    }
}