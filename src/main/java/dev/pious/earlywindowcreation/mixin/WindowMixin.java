package dev.pious.earlywindowcreation.mixin;

import dev.pious.earlywindowcreation.EarlyWindowCreation;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {
// Workaround for Resolution Control+
//    @Inject(method = "onFramebufferSizeChanged", at = @At("HEAD"), cancellable = true)
//    private void onFramebufferSizeChanged(CallbackInfo ci) {
//        if (!EarlyWindowCreation.handedOver) {
//            ci.cancel();
//        }
//    }
//
//    @Inject(method = "updateFramebufferSize", at = @At("HEAD"), cancellable = true)
//    private void onUpdateFramebufferSize(CallbackInfo ci) {
//        if (!EarlyWindowCreation.handedOver) {
//            ci.cancel();
//        }
//    }
}
