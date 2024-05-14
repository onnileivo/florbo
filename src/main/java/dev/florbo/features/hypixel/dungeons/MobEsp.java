package dev.florbo.features.hypixel.dungeons;

import dev.florbo.config.FlorboConfig;
import dev.florbo.mixin.AccessorRenderManager;
import dev.florbo.mixin.MinecraftMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class MobEsp {
    public static Minecraft mc = Minecraft.getMinecraft();

    private static final AxisAlignedBB MOB_BOX =
            new AxisAlignedBB(-0.5, 0, -0.5, 0.5, 1, 0.5);


    @SubscribeEvent
    public void onRenderWorldLastEvent(RenderWorldLastEvent event) {
        if (FlorboConfig.mobEsp) {

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glLineWidth(FlorboConfig.starredMobEspLineWidth);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_DEPTH_TEST);

            GL11.glPushMatrix();
            GL11.glTranslated(-getRenderX(),
                    -getRenderY(),
                    -getRenderZ());

            for (Entity e : mc.theWorld.loadedEntityList) {
                if (e == mc.thePlayer) {continue;}
                if (shouldRenderEntity(e)) {
                    double partialTicks = ((MinecraftMixin) mc).getTimer().renderPartialTicks;
                    GL11.glPushMatrix();
                    float r = (float) FlorboConfig.mobEspColor.getRed() / 255;
                    float g = (float) FlorboConfig.mobEspColor.getGreen() / 255;
                    float b = (float) FlorboConfig.mobEspColor.getBlue() / 255;
                    float a = (float) FlorboConfig.mobEspColor.getAlpha() / 255;
                    GL11.glColor4f(r, g, b, a);
                    GL11.glTranslated(
                            e.prevPosX
                                    + (e.posX - e.prevPosX) * partialTicks,
                            e.prevPosY
                                    + (e.posY - e.prevPosY) * partialTicks,
                            e.prevPosZ
                                    + (e.posZ - e.prevPosZ) * partialTicks);
                    double boxWidth = e.width + 0.5;
                    double boxHeight = e.height + 0.1;
                    GL11.glScaled(boxWidth, boxHeight, boxWidth);
                    drawOutlinedBox(MOB_BOX);
                    GL11.glPopMatrix();
                }
            }
            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }
    }
    public double getRenderX() {
        return ((AccessorRenderManager) mc.getRenderManager()).getRenderX();
    }

    public double getRenderY() {
        return ((AccessorRenderManager) mc.getRenderManager()).getRenderY();
    }

    public double getRenderZ() {
        return ((AccessorRenderManager) mc.getRenderManager()).getRenderZ();
    }
    private boolean shouldRenderEntity(Entity e) {
        if (FlorboConfig.starredMobEspAllEntities) { return true; }
        return e instanceof EntityArmorStand && e.hasCustomName() && e.getAlwaysRenderNameTag() && e.getName().startsWith("§6✯ ") && e.getName().endsWith("§c❤");
    }
    public static void drawOutlinedBox(AxisAlignedBB bb)
    {
        glBegin(GL_LINES);
        {
            glVertex3d(bb.minX, bb.minY, bb.minZ);
            glVertex3d(bb.maxX, bb.minY, bb.minZ);

            glVertex3d(bb.maxX, bb.minY, bb.minZ);
            glVertex3d(bb.maxX, bb.minY, bb.maxZ);

            glVertex3d(bb.maxX, bb.minY, bb.maxZ);
            glVertex3d(bb.minX, bb.minY, bb.maxZ);

            glVertex3d(bb.minX, bb.minY, bb.maxZ);
            glVertex3d(bb.minX, bb.minY, bb.minZ);

            glVertex3d(bb.minX, bb.minY, bb.minZ);
            glVertex3d(bb.minX, bb.maxY, bb.minZ);

            glVertex3d(bb.maxX, bb.minY, bb.minZ);
            glVertex3d(bb.maxX, bb.maxY, bb.minZ);

            glVertex3d(bb.maxX, bb.minY, bb.maxZ);
            glVertex3d(bb.maxX, bb.maxY, bb.maxZ);

            glVertex3d(bb.minX, bb.minY, bb.maxZ);
            glVertex3d(bb.minX, bb.maxY, bb.maxZ);

            glVertex3d(bb.minX, bb.maxY, bb.minZ);
            glVertex3d(bb.maxX, bb.maxY, bb.minZ);

            glVertex3d(bb.maxX, bb.maxY, bb.minZ);
            glVertex3d(bb.maxX, bb.maxY, bb.maxZ);

            glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
            glVertex3d(bb.minX, bb.maxY, bb.maxZ);

            glVertex3d(bb.minX, bb.maxY, bb.maxZ);
            glVertex3d(bb.minX, bb.maxY, bb.minZ);
        }
        glEnd();
    }


}