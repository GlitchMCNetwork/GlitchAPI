package dev.selena.text;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Placeholders {


    private final String PLAYER_NAME;

    public Placeholders(String player_placeholder) {
        this.PLAYER_NAME = player_placeholder;

    }

    public String placeholder(String content, Map<String, String> placeholdersArgs) {
        for (String key : placeholdersArgs.keySet()) {
            content = content.replace(key, placeholdersArgs.get(key));
        }
        return content;
    }

    public String commandPlaceholder(String content, Player player) {

        content = content.replace(PLAYER_NAME, player.getName());

        content = placeholder(content, new TreeMap<String, String>(){{put(PLAYER_NAME, player.getName());}});
        return content;
    }



}
