package dev.pious.earlywindowcreation;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.util.Monitor;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public final class CustomWindowProvider implements AutoCloseable {
    public DummyWindowEventHandler eventHandler;
    private final MonitorTracker monitorTracker;

    public CustomWindowProvider() {
        this.eventHandler = new DummyWindowEventHandler();
        this.monitorTracker = new MonitorTracker(Monitor::new);
    }

    public Window createWindow(WindowSettings settings, @Nullable String videoMode, String title) {
        return new Window(this.eventHandler, this.monitorTracker, settings, videoMode, title);
    }

    public void close() {
        this.monitorTracker.stop();
    }
}
