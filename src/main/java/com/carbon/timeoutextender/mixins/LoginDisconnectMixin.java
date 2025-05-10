package com.carbon.timeoutextender.mixins;

import com.carbon.timeoutextender.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLoginPacketListenerImpl.class)
public abstract class LoginDisconnectMixin  {
    @Shadow(remap = false)
    private int tick;

    @Inject(method = "tick", at = @At("TAIL"), remap = false)
    public void tick(CallbackInfo ci) {
        if(tick >= Config.loginTimeoutExtraSeconds * 20) {
            ((ServerLoginPacketListenerImpl)(Object)this).disconnect(Component.translatable("multiplayer.disconnect.slow_login"));
        }
    }

    @Redirect(method = "tick", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/network/ServerLoginPacketListenerImpl;disconnect(Lnet/minecraft/network/chat/Component;)V"),
    remap = false)
    public void disconnect(ServerLoginPacketListenerImpl instance, Component component) {
        // no-op.
    }
}
