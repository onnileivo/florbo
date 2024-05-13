package dev.florbo.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.Inject;

/**
 * An example mixin using SpongePowered's Mixin library and ObjectWeb ASM.
 *
 * @see Inject
 * @see Mixin
 */
@Mixin(Minecraft.class)
public interface MinecraftMixin {
    @Accessor("timer")
    Timer getTimer();
}
