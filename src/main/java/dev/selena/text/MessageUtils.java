package dev.selena.text;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import dev.selena.GlitchAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageUtils {

    private static final Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .serializeNulls()
            .create();

    public static void json_dump(Object cls) {
        String classGson = gson.toJson(cls);
        consoleSend("\n" + gson.toJson(JsonParser.parseString(classGson)));
    }

    public static String stringBuilder(String ... args) {
        StringBuilder output = new StringBuilder();
        for (String arg : args) {
            output.append(arg).append(" ");
        }
        return output.toString();
    }

    public static void sendPlayer(String content, Player receiver, CommandSender sender) {
        if (!receiver.isOnline())
            sender.sendMessage(ChatColor.RED + "That player is not online");
        receiver.sendMessage(PlaceholderAPI.setPlaceholders(receiver, ContentUtils.color(content)));
    }

    public static void playerSend(Player player, String content) {
        player.sendMessage(PlaceholderAPI.setPlaceholders(player, ContentUtils.color(content)));
    }

    public static void senderSend(CommandSender sender, String content) {
        sender.sendMessage(PlaceholderAPI.setPlaceholders(sender instanceof Player ? (Player) sender : null, ContentUtils.color(content)));
    }

    @Deprecated
    /*
    Use consoleSend(String) instead
     */
    public static void consoleSend(String content, JavaPlugin server) {
        server.getLogger().info(ContentUtils.color(content));
    }

    public static void consoleSend(String content) {
        GlitchAPI.getPlugin().getLogger().info(ContentUtils.color(content));
    }

    public static void announce(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerSend(player, message);
        }
    }


}
