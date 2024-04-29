package dev.florbo.features.visuals;


import dev.florbo.config.FlorboConfig;
import dev.florbo.util.BlockRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.awt.*;
public class ChestEsp {
    public Minecraft mc = Minecraft.getMinecraft();
    public static BlockRenderer blockRenderer = new BlockRenderer();
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (!FlorboConfig.chestEsp)
            return;
        for (Object o : mc.theWorld.loadedTileEntityList) {
            if (o instanceof TileEntityChest) {
                TileEntityChest chest = (TileEntityChest)o;
                blockRenderer.renderAABB(chest.getPos(), FlorboConfig.getChestEspColor());
            }
        }
    }
}
