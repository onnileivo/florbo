package dev.florbo.mixin;

import dev.florbo.FlorboMod;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.GuiModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.List;
@Mixin(GuiMainMenu.class)
public class MainMenuMixin extends GuiScreen {
    @Shadow
    private GuiButton realmsButton;
    @Shadow
    private String splashText = FlorboMod.splashText;
    @Shadow
    private static ResourceLocation minecraftTitleTextures = new ResourceLocation(FlorboMod.MODID, "/minecraftmenutitle.png");

    /**
     * @author me
     * @reason nuh uh
     */
    @Overwrite
    private void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_) {
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, p_73969_1_, "Lonely ass mf"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, p_73969_1_ + p_73969_2_ * 1, "onko tämä se moninpeli"));
        this.realmsButton = new GuiButton(14, this.width / 2 + 2, p_73969_1_ + p_73969_2_ * 2, 98, 20, "habaxel");
        this.buttonList.add(this.realmsButton);
        this.buttonList.add(new GuiButton(6, this.width / 2 - 100, p_73969_1_ + p_73969_2_ * 2, 98, 20, I18n.format("fml.menu.mods", new Object[0])));
    }
    /**
     * @author me
     * @reason nuh uh
     */
    @Overwrite
    protected void actionPerformed(GuiButton button) throws IOException {
        ISaveFormat isaveformat;
        WorldInfo worldinfo;
        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
        }
        if (button.id == 5) {
            this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
        }
        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiSelectWorld(this));
        }
        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiMultiplayer(this));
        }
        if (button.id == 14 && this.realmsButton.visible) {
            ServerData hypixel = new ServerData("Hypixel", "hypixel.net", false);
            FMLClientHandler.instance().connectToServer(new GuiMultiplayer(mc.currentScreen), hypixel);
        }
        if (button.id == 4) {
            this.mc.shutdown();
        }
        if (button.id == 6) {
            this.mc.displayGuiScreen(new GuiModList(this));
        }
        if (button.id == 11) {
            this.mc.launchIntegratedServer("Demo_World", "Demo_World", DemoWorldServer.demoWorldSettings);
        }
        if (button.id == 12 && (worldinfo = (isaveformat = this.mc.getSaveLoader()).getWorldInfo("Demo_World")) != null) {
            GuiYesNo guiyesno = GuiSelectWorld.makeDeleteWorldYesNo(this, worldinfo.getWorldName(), 12);
            this.mc.displayGuiScreen(guiyesno);
        }
    }

}
