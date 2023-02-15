package net.prophet.chatlisten.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(ChatHud.class)
public class ChatMixin {
    @Inject(method = "logChatMessage", at = @At("TAIL"))
    public void onGameMessage(Text message, MessageIndicator indicator, CallbackInfo ci) {
        List<Float> coordinates = new ArrayList<Float>();
        if (!(message.getString().contains("logme")) || (message.getString().contains("Chat message received: "))) return;
        Pattern pattern = Pattern.compile("(-?\\d{1,10}.\\d{1,4}\\s?)");
        Matcher matcher = pattern.matcher(message.getString());
        while(matcher.find()){
            coordinates.add(Float.valueOf(matcher.group()));
        }
        MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of(String.format("Chat message received: x:%f y:%f z:%f", coordinates.get(0), coordinates.get(1), coordinates.get(2))));
    }
}
