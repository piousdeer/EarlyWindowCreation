package dev.pious.earlywindowcreation;

import net.minecraft.client.WindowEventHandler;

public class DummyWindowEventHandler implements WindowEventHandler {
    public boolean windowFocused;
    public void onWindowFocusChanged(boolean _focused) {
        this.windowFocused = _focused;
    }
    public void onResolutionChanged() {}
    public void onCursorEnterChanged() {}
}
