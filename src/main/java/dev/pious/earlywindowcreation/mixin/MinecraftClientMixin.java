package dev.pious.earlywindowcreation.mixin;

import dev.pious.earlywindowcreation.EarlyWindowCreation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.WindowProvider;
import net.minecraft.util.Util;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin extends ReentrantThreadExecutor<Runnable> implements WindowEventHandler {
    @Shadow @Final
    private WindowProvider windowProvider;

    public MinecraftClientMixin(String string) {
        super(string);
    }

    @Redirect(
            method = "<init>",
            at = @At(value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;initBackendSystem()Lnet/minecraft/util/TimeSupplier$Nanoseconds;")
    )
    private net.minecraft.util.TimeSupplier.Nanoseconds returnNanoTimeSupplier() {
        return Util.nanoTimeSupplier;
    }

    @Redirect(
            method = "<init>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/util/WindowProvider;createWindow(Lnet/minecraft/client/WindowSettings;Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/client/util/Window;")
    )
    private Window handWindowOver(WindowProvider instance, WindowSettings settings, String videoMode, String title) {
        long timeSaved = System.currentTimeMillis() - EarlyWindowCreation.windowCreationTime;
        EarlyWindowCreation.LOGGER.info("Handing window over, {}ms saved", timeSaved);
        EarlyWindowCreation.windowProvider.close();
        EarlyWindowCreation.window.eventHandler = this;
        EarlyWindowCreation.window.monitorTracker = this.windowProvider.monitorTracker;
        EarlyWindowCreation.handedOver = true;
        return EarlyWindowCreation.window;
    }

    @Redirect(
            method = "<init>",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/MinecraftClient;onWindowFocusChanged(Z)V")
    )
    private void setRealFocusedValue(MinecraftClient instance, boolean focused) {
        instance.onWindowFocusChanged(EarlyWindowCreation.windowProvider.eventHandler.windowFocused);
    }
}
