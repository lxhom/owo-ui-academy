package io.wispforest.academy;

import io.wispforest.academy.screen.TutorialIntroScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.hud.Hud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class OwoUIAcademy implements ClientModInitializer {

    private static final KeyBinding BEGIN = new KeyBinding("key.owo-ui-academy.begin", GLFW.GLFW_KEY_H, "key.categories.misc");

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(BEGIN);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (BEGIN.wasPressed()) {
                client.setScreen(new TutorialIntroScreen());
            }
        });

        Hud.add(new Identifier("owo-ui-academy", "hint"), () ->
                Containers.verticalFlow(Sizing.content(), Sizing.content())
                        .child(Components.label(
                                Text.empty()
                                        .append(Text.literal("! ").formatted(Formatting.YELLOW, Formatting.BOLD))
                                        .append(" Presssss ")
                                        .append(KeyBindingHelper.getBoundKeyOf(BEGIN).getLocalizedText().copy().formatted(Formatting.BLUE))
                                        .append(" to\nbegin owo-ui Academy")
                        ).horizontalTextAlignment(HorizontalAlignment.CENTER).shadow(true))
                        .surface(Surface.flat(0x77000000).and(Surface.outline(0xFF121212)))
                        .padding(Insets.of(5))
                        .positioning(Positioning.relative(100, 35))
        );

    }
}
