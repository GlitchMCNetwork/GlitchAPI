package dev.selena.text;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.TreeMap;

public class Placeholders {

    private static final String PLAYER_NAME = "${PLAYER_NAME}";




    public static String placeholder(String content, Map<String, String> placeholdersArgs) {
        for (String key : placeholdersArgs.keySet()) {
            content = PlaceholderAPI.setPlaceholders(null, content.replace(key, placeholdersArgs.get(key)));
        }
        return content;
    }

    public static String commandPlaceholder(String content, Player player) {

        content = PlaceholderAPI.setPlaceholders(player, content.replace(PLAYER_NAME, player.getName()));

        content = placeholder(content, new TreeMap<String, String>(){{put(PLAYER_NAME, player.getName());}});
        return content;
    }



}
