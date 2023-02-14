package net.prophet.chatlisten.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatMixin {
    @Inject(method = "logChatMessage", at = @At("TAIL"))
    public void onGameMessage(Text message, MessageIndicator indicator, CallbackInfo ci) {
        if (!(message.getString().contains("logme")) || (message.getString().contains("Chat message received: "))) return;
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of("Chat message received: " + message.getString()));


    }
}
