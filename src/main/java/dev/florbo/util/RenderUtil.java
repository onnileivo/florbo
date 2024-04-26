package dev.florbo.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtil {
    // ALL OF THIS SKIDDED FROM https://github.com/valekatoz/KoreForge/blob/main/src/main/java/net/kore/utils/render/RenderUtils.java
    // ty gidhub
    public static void setColor(final Color c) {
        GL11.glColor4f(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
    }
    public static void entityESPBox(final Entity entity, final float partialTicks, final Color color) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(1.5f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        setColor(color);
        RenderUtil.drawSolidBox(new AxisAlignedBB(entity.getEntityBoundingBox().minX - entity.posX + (entity.prevPosX + (entity.posX - entity.prevPosX) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().minY - entity.posY + (entity.prevPosY + (entity.posY - entity.prevPosY) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().minZ - entity.posZ + (entity.prevPosZ + (entity.posZ - entity.prevPosZ) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosZ), entity.getEntityBoundingBox().maxX - entity.posX + (entity.prevPosX + (entity.posX - entity.prevPosX) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().maxY - entity.posY + (entity.prevPosY + (entity.posY - entity.prevPosY) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().maxZ - entity.posZ + (entity.prevPosZ + (entity.posZ - entity.prevPosZ) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosZ)));
        //RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(entity.getEntityBoundingBox().minX - entity.posX + (entity.prevPosX + (entity.posX - entity.prevPosX) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().minY - entity.posY + (entity.prevPosY + (entity.posY - entity.prevPosY) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().minZ - entity.posZ + (entity.prevPosZ + (entity.posZ - entity.prevPosZ) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosZ), entity.getEntityBoundingBox().maxX - entity.posX + (entity.prevPosX + (entity.posX - entity.prevPosX) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosX), entity.getEntityBoundingBox().maxY - entity.posY + (entity.prevPosY + (entity.posY - entity.prevPosY) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosY), entity.getEntityBoundingBox().maxZ - entity.posZ + (entity.prevPosZ + (entity.posZ - entity.prevPosZ) * partialTicks - Minecraft.getMinecraft().getRenderManager().viewerPosZ)), 255, 255 ,255, 255);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    public static void drawSolidBox(AxisAlignedBB bb) {

        glBegin(GL_QUADS);
        {
            glVertex3d(bb.minX, bb.minY, bb.minZ);
            glVertex3d(bb.maxX, bb.minY, bb.minZ);
            glVertex3d(bb.maxX, bb.minY, bb.maxZ);
            glVertex3d(bb.minX, bb.minY, bb.maxZ);

            glVertex3d(bb.minX, bb.maxY, bb.minZ);
            glVertex3d(bb.minX, bb.maxY, bb.maxZ);
            glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
            glVertex3d(bb.maxX, bb.maxY, bb.minZ);

            glVertex3d(bb.minX, bb.minY, bb.minZ);
            glVertex3d(bb.minX, bb.maxY, bb.minZ);
            glVertex3d(bb.maxX, bb.maxY, bb.minZ);
            glVertex3d(bb.maxX, bb.minY, bb.minZ);

            glVertex3d(bb.maxX, bb.minY, bb.minZ);
            glVertex3d(bb.maxX, bb.maxY, bb.minZ);
            glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
            glVertex3d(bb.maxX, bb.minY, bb.maxZ);

            glVertex3d(bb.minX, bb.minY, bb.maxZ);
            glVertex3d(bb.maxX, bb.minY, bb.maxZ);
            glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
            glVertex3d(bb.minX, bb.maxY, bb.maxZ);

            glVertex3d(bb.minX, bb.minY, bb.minZ);
            glVertex3d(bb.minX, bb.minY, bb.maxZ);
            glVertex3d(bb.minX, bb.maxY, bb.maxZ);
            glVertex3d(bb.minX, bb.maxY, bb.minZ);
        }
        glEnd();
    }
}
