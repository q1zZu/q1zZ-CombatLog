package me.q1zz.combatlog.helper;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageHelper {

    private static final Pattern HEX_REGEX = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String fixColor(String message) {
        if (message == null || message.isEmpty()) {
            return "";
        }
        for (Matcher matcher = HEX_REGEX.matcher(message); matcher.find(); matcher = HEX_REGEX.matcher(message)) {
            String color = message.substring(matcher.start(), matcher.end());
            message = message.replace(color, ChatColor.of(color) + "");
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(fixColor(message));
    }

    public static void sendMessage(Player player, List<String> messages) {
        messages.forEach(message -> sendMessage(player, message));
    }

    public static void sendActionBar(Player player, String text) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(fixColor(text)));
    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(fixColor(message));
    }

    public static void broadcastMessage(List<String> messages) {
        messages.forEach(MessageHelper::broadcastMessage);
    }

}
