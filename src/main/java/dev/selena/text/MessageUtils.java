package dev.selena.text;

import dev.selena.GlitchAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageUtils {


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
        receiver.sendMessage(ContentUtils.color(content));
    }

    public static void playerSend(Player player, String content) {
        player.sendMessage(ContentUtils.color(content));
    }

    public static void senderSend(CommandSender sender, String content) {
        sender.sendMessage(ContentUtils.color(content));
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
