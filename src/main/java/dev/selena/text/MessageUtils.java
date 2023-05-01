package dev.selena.text;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

}
