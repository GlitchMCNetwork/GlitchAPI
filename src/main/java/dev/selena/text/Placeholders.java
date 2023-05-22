package dev.selena.text;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Placeholders {

    private static final String PLAYER_NAME = "${PLAYER_NAME}";




    public static String placeholder(String content, Map<String, String> placeholdersArgs) {
        for (String key : placeholdersArgs.keySet()) {
            content = content.replace(key, placeholdersArgs.get(key));
        }
        return content;
    }

    public static String commandPlaceholder(String content, Player player) {

        content = content.replace(PLAYER_NAME, player.getName());

        content = placeholder(content, new TreeMap<String, String>(){{put(PLAYER_NAME, player.getName());}});
        return content;
    }



}
