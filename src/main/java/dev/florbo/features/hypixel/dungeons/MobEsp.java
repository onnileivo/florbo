package dev.florbo.features.hypixel.dungeons;

import dev.florbo.config.FlorboConfig;
import dev.florbo.features.hypixel.dungeons.helpers.impl.Fels;
import dev.florbo.features.hypixel.dungeons.helpers.Renderable;
import dev.florbo.features.hypixel.dungeons.helpers.impl.ShadowAssasin;
import dev.florbo.features.hypixel.dungeons.helpers.impl.StarredMob;
import dev.florbo.mixin.AccessorRenderManager;
import dev.florbo.mixin.MinecraftMixin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import static dev.florbo.util.RenderUtil.drawOutlinedBox;
import static dev.florbo.util.RenderUtil.drawPathAsLines;
import static org.lwjgl.opengl.GL11.*;

public class MobEsp {
    //dooky oop solution coming up

    public static Minecraft mc = Minecraft.getMinecraft();
    public Renderable renderable;

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
            GL11.glTranslated(-getRenderX(),
                    -getRenderY(),
                    -getRenderZ());

            for (Entity e : mc.theWorld.loadedEntityList) {
                if (e == mc.thePlayer) {continue;}
                renderable = getRenderable(e);
                if (renderable != null && renderable.getEnabled()) {
                    double boxWidth, boxHeight;
                    float r, g, b, a;
                    r = (float) renderable.getColor().getRed() / 255;
                    g = (float) renderable.getColor().getGreen() / 255;
                    b = (float) renderable.getColor().getBlue() / 255;
                    a = (float) renderable.getColor().getAlpha() / 255;
                    boxWidth = renderable.getBoxWidth();
                    boxHeight = renderable.getBoxHeight();

                    double partialTicks = ((MinecraftMixin) mc).getTimer().renderPartialTicks;
                    GL11.glPushMatrix();
                    GL11.glColor4f(r, g, b, a);
                    GL11.glTranslated(
                            e.prevPosX
                                    + (e.posX - e.prevPosX) * partialTicks,
                            e.prevPosY
                                    + (e.posY - e.prevPosY) * partialTicks,
                            e.prevPosZ
                                    + (e.posZ - e.prevPosZ) * partialTicks);

                    GL11.glScaled(boxWidth, boxHeight, boxWidth);
                    drawOutlinedBox(MOB_BOX);
                    GL11.glPopMatrix();
                }
            }

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }
    }

    private Renderable getRenderable(Entity e) {
        if (isStarred(e)) {return new StarredMob();}
        if (isFels(e)) {return new Fels();}
        if (isSA(e)) {return new ShadowAssasin();}
        if (FlorboConfig.starredMobEspAllEntities) {return new StarredMob();}
        return null;
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

    private boolean isStarred(Entity e) {return (e instanceof EntityArmorStand && e.hasCustomName() && e.getAlwaysRenderNameTag()) && (e.getName().startsWith("§6✯ ") && e.getName().endsWith("§c❤"));}

    private boolean isFels(Entity e) {return e.getName().equals("Dinnerbone") && e instanceof EntityEnderman;}

    private boolean isSA(Entity e) {return e.getName().startsWith("Shadow Assasin") && e instanceof EntityOtherPlayerMP; } //Invis shadow assasin check


}
