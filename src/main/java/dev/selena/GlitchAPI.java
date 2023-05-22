package dev.selena;

import dev.selena.text.Placeholders;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class GlitchAPI {

    private static Plugin plugin;

    public static void setPlugin(Plugin plugin) {
        GlitchAPI.plugin = plugin;
    }

    public static Plugin getPlugin() {
        return plugin;
    }



}
