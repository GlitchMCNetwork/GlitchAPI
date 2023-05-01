package dev.selena.text;

import org.bukkit.ChatColor;

public class ContentUtils {
    public static String color(String display_name) {
        return ChatColor.translateAlternateColorCodes('&', display_name);
    }
}
