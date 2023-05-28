package dev.selena.text;

import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;


public class ContentUtils {


    public static String color(String content) {
        return PlaceholderAPI.setPlaceholders(null, ChatColor.translateAlternateColorCodes('&', content));
    }

    public static String capsFirst(String content) {
        return WordUtils.capitalize(content.replace("_", " "));
    }

}
