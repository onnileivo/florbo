package dev.florbo.features.hypixel.dungeons;

import dev.florbo.config.FlorboConfig;
import dev.florbo.mixin.AccessorRenderManager;
import dev.florbo.mixin.MinecraftMixin;
import dev.florbo.util.BlockRenderer;
import dev.florbo.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glListBase;

public class MobEsp {
    public static Minecraft mc = Minecraft.getMinecraft();
    @SubscribeEvent
    public void onRenderLivingpre(RenderLivingEvent.Pre<?> event) {
        if (!FlorboConfig.mobEsp) return;
        if (event.entity != mc.thePlayer && event.entity instanceof EntityLivingBase) {
            String name = event.entity.getName();
            if (name.startsWith("§6✯") && name.endsWith("§c❤") && event.entity.hasCustomName() && event.entity.getAlwaysRenderNameTag()) {
                System.out.println("YO GUYS WE FOUND THAT THAANG BRUHb " + name);

                System.out.println(event.entity.posX);
                System.out.println(event.entity.posY);
                System.out.println(event.entity.posZ);
                RenderUtil.entityESPBox(event.entity, ((MinecraftMixin) mc).getTimer().renderPartialTicks, FlorboConfig.getMobEspColor());
            }
        }
    }
    // skytils yoink
    public static Double getRenderX() {
        return ((AccessorRenderManager)mc).getRenderX();
    }
    public static Double getRenderY() {
        return ((AccessorRenderManager)mc).getRenderY();
    }
    public static Double getRenderZ() {
        return ((AccessorRenderManager)mc).getRenderZ();
    }

}
