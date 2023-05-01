package dev.selena.text;

import java.util.HashMap;

public interface Placeholders {

    static String placeholder(String content, HashMap<String, String> placeholdersArgs) {
        for (String key : placeholdersArgs.keySet()) {
            content = content.replace(key, placeholdersArgs.get(key));
        }
        return content;
    }



}
