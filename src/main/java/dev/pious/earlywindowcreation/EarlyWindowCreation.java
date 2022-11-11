package dev.pious.earlywindowcreation;

import net.minecraft.client.util.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EarlyWindowCreation {
    public static final Logger LOGGER = LoggerFactory.getLogger("earlywindowcreation");
    public static CustomWindowProvider windowProvider;
    public static Window window;
    public static long windowCreationTime;
    public static boolean handedOver = false;
}
