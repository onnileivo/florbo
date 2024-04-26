package dev.florbo.mixin;

import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(RenderManager.class)
public interface AccessorRenderManager {

    @Accessor("renderPosX")
    double getRenderX();

    @Accessor("renderPosY")
    double getRenderY();

    @Accessor("renderPosZ")
    double getRenderZ();
}