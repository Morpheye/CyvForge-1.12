package net.cyvforge.keybinding;

import net.cyvforge.util.defaults.CyvKeybinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeybindingTogglesprint extends CyvKeybinding {
    public KeybindingTogglesprint() {
        super("Toggle Sprint", Keyboard.KEY_NONE);
    }

    public static boolean sprintToggled = false;

    @Override
    public void onTickStart(boolean isPressed) {
        if (sprintToggled) {
            if (Minecraft.getMinecraft().player != null) {
                GameSettings settings = Minecraft.getMinecraft().gameSettings;
                KeyBinding.setKeyBindState(settings.keyBindSprint.getKeyCode(), true);
            }
        }
    }

    @Override
    public void onTickEnd(boolean isPressed) {
        if (isPressed) {
            sprintToggled = !sprintToggled;

            if (!sprintToggled) {
                GameSettings settings = Minecraft.getMinecraft().gameSettings;
                KeyBinding.setKeyBindState(settings.keyBindSprint.getKeyCode(), false);
            }
        }
    }
}
