package dev.florbo.mixin;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(NetworkManager.class)
public class NetworkManagerMixin {
//    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
//    private void onSendPacket(Packet<?> packet, CallbackInfo callbackInfo)
//    {
//        System.out.println("Packet Sent: " + packet.toString());
//    }
//
//    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
//    private void onChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo)
//    {
//        System.out.println("Packet Recieved: " + packet.toString());
//    }
}
