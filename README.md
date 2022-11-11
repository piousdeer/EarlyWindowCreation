# Early Window Creation

After you click "Play" in your launcher, Minecraft does a lot of stuff before creating a window. This Fabric mod moves
the window creation part so that it happens way earlier in the game's startup.

[LazyDFU](https://github.com/astei/lazydfu) is highly recommended to be used alongside.

## Mod status

- Reduces Minecraft 1.19.2's window creation delay from 8 to 2 seconds on my laptop. Should be even better on slower machines and highly modded games.
- Doesn't read options.txt yet, the game always starts up in windowed mode
- Iris and Resolution Control+ crash because they incorrectly assume they're initialized when the window is created
    - Iris
      crashes [here](https://github.com/IrisShaders/Iris/blob/13424825714cb287e1374792157f1d30611ffed9/src/main/java/net/coderbot/iris/mixin/MixinWindow.java#L18),
      initialization
      happens [here](https://github.com/IrisShaders/Iris/blob/13424825714cb287e1374792157f1d30611ffed9/src/main/java/net/coderbot/iris/mixin/MixinOptions_Entrypoint.java),
      parsing options.txt via Minecraft's `Options` should probably fix this...
    - Resolution Control+
      crashes [here](https://github.com/fantahund/Resolution-Control/blob/3ea362d88ffbc57ad372f35212891f455202a926/src/main/java/io/github/ultimateboomer/resolutioncontrol/mixin/WindowMixin.java#L42-L50),
      should probably be fixed by the maintainer. A workaround is commented out in [WindowMixin.java](src/main/java/dev/pious/earlywindowcreation/mixin/WindowMixin.java)
- Causes an annoying "window is not responding" dialog on GNOME. Drawing something might prevent this?
- No icon yet, no prebuilt jar, not published to Modrinth or Curseforge
