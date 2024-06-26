package dev.florbo.mixin;

import com.google.common.collect.Lists;
import dev.florbo.FlorboMod;
import dev.florbo.config.FlorboConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.GuiModList;
import org.apache.commons.io.Charsets;
import org.spongepowered.asm.mixin.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Mixin(GuiMainMenu.class)
public abstract class MainMenuMixin extends GuiScreen {
    @Unique
    private static final Random florbo$RANDOM = new Random();
    // bruh theres got to be a better way to do this
    @Shadow
    private GuiScreen field_183503_M;
    @Shadow
    private boolean field_183502_L;
    @Shadow
    private GuiButton realmsButton;
    @Shadow
    private String splashText;
    @Final
    @Shadow
    private static ResourceLocation splashTexts;
    @Shadow
    private static ResourceLocation minecraftTitleTextures = new ResourceLocation(FlorboMod.MODID, "/minecraftmenutitle.png");
    @Shadow
    private DynamicTexture viewportTexture;
    @Shadow
    private ResourceLocation backgroundTexture;
    @Final
    @Shadow
    private Object threadLock;
    @Shadow
    private int field_92023_s;
    @Shadow
    private int field_92024_r;
    @Shadow
    private int field_92022_t;
    @Shadow
    private int field_92021_u;
    @Shadow
    private int field_92020_v;
    @Shadow
    private int field_92019_w;
    @Shadow
    private String openGLWarning1;
    @Final
    @Shadow
    public static String field_96138_a;
    @Shadow
    private String openGLWarning2 = field_96138_a;
    /**
     * @author me
     * @reason nuh uh
     */
    @Overwrite
    public void initGui() {
        this.splashText = FlorboMod.splashText;
        if (!FlorboConfig.funnyMainMenu) {
            minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");
            BufferedReader bufferedreader = null;
            try {
                String s;
                ArrayList<String> list = Lists.newArrayList();
                bufferedreader = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(splashTexts).getInputStream(), Charsets.UTF_8));
                while ((s = bufferedreader.readLine()) != null) {
                    if ((s = s.trim()).isEmpty()) continue;
                    list.add(s);
                }
                if (!list.isEmpty()) {
                    do {
                        this.splashText = (String)list.get(florbo$RANDOM.nextInt(list.size()));
                    } while (this.splashText.hashCode() == 125780783);
                }
            } catch (IOException ignored) {
            } finally {
                if (bufferedreader != null) {
                    try {
                        bufferedreader.close();
                    } catch (IOException ignored) {}
                }
            }
        }
        this.viewportTexture = new DynamicTexture(256, 256);
        if (FlorboConfig.funnyMainMenu) {
            this.backgroundTexture = new ResourceLocation("florbo", "kebabbackground.jpg");
        } else {
            this.backgroundTexture = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
        }



        int i = 24;
        int j = this.height / 4 + 48;
        addSingleplayerMultiplayerButtons(j, 24);

        if (FlorboConfig.funnyMainMenu) {
            this.buttonList.add(new GuiButton(0, this.width / 2 - 100, j + 72 + 12, 98, 20, "aseduksed"));
            this.buttonList.add(new GuiButton(4, this.width / 2 + 2, j + 72 + 12, 98, 20,"baibai"));

        } else {
            this.buttonList.add(new GuiButton(0, this.width / 2 - 100, j + 72 + 12, 98, 20, I18n.format("menu.options", new Object[0])));
            this.buttonList.add(new GuiButton(4, this.width / 2 + 2, j + 72 + 12, 98, 20, I18n.format("menu.quit", new Object[0])));
        }
        this.buttonList.add(new GuiButtonLanguage(5, this.width / 2 - 124, j + 72 + 12));
        Object object = this.threadLock;
        synchronized (object) {
            this.field_92023_s = this.fontRendererObj.getStringWidth(this.openGLWarning1);
            this.field_92024_r = this.fontRendererObj.getStringWidth(this.openGLWarning2);
            int k = Math.max(this.field_92023_s, this.field_92024_r);
            this.field_92022_t = (this.width - k) / 2;
            this.field_92021_u = ((GuiButton)this.buttonList.get((int)0)).yPosition - 24;
            this.field_92020_v = this.field_92022_t + k;
            this.field_92019_w = this.field_92021_u + 24;
        }
        this.mc.setConnectedToRealms(false);
        if (Minecraft.getMinecraft().gameSettings.getOptionOrdinalValue(GameSettings.Options.REALMS_NOTIFICATIONS) && !this.field_183502_L) {
            RealmsBridge realmsbridge = new RealmsBridge();
            this.field_183503_M = realmsbridge.getNotificationScreen(this);
            this.field_183502_L = true;
        }
        if (this.func_183501_a()) {
            this.field_183503_M.setGuiSize(this.width, this.height);
            this.field_183503_M.initGui();
        }
    }

    @Shadow
    protected abstract boolean func_183501_a();

    /**
     * @author me
     * @reason nuh uh
     */
    @Overwrite
    private void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_) {
        if (FlorboConfig.funnyMainMenu) {
            this.buttonList.add(new GuiButton(1, this.width / 2 - 100, p_73969_1_, "Lonely ass mf"));
            this.buttonList.add(new GuiButton(2, this.width / 2 - 100, p_73969_1_ + p_73969_2_, "onko tämä se moninpeli"));
            this.realmsButton = new GuiButton(14, this.width / 2 + 2, p_73969_1_ + p_73969_2_ * 2, 98, 20, "habaxel");
            this.buttonList.add(this.realmsButton);
            this.buttonList.add(new GuiButton(6, this.width / 2 - 100, p_73969_1_ + p_73969_2_ * 2, 98, 20, "forge modders"));
        } else {
            this.buttonList.add(new GuiButton(1, this.width / 2 - 100, p_73969_1_, I18n.format("menu.singleplayer", new Object[0])));
            this.buttonList.add(new GuiButton(2, this.width / 2 - 100, p_73969_1_ + p_73969_2_, I18n.format("menu.multiplayer", new Object[0])));
            this.realmsButton = new GuiButton(14, this.width / 2 + 2, p_73969_1_ + p_73969_2_ * 2, 98, 20, I18n.format("menu.online", new Object[0]).replace("Minecraft", "").trim());
            this.buttonList.add(this.realmsButton);
            this.buttonList.add(new GuiButton(6, this.width / 2 - 100, p_73969_1_ + p_73969_2_ * 2, 98, 20, I18n.format("fml.menu.mods", new Object[0])));
        }
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
