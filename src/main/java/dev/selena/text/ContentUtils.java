package dev.selena.text;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ContentUtils {
    public static String color(String display_name) {
        return ChatColor.translateAlternateColorCodes('&', display_name);
    }
}
