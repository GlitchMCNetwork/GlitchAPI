package dev.selena.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Arguments {
    private final Player player;
    private final CommandSender sender;
    private final String label;
    private final List<String> arguments;

	/***
	 * Command arguments
	 * @param player Sender as Player
	 * @param sender Sender as CommandSender
	 * @param label Command name
	 * @param arguments List of the input arguments (/label arg1 arg2 ect)
	 */
    public Arguments(Player player, CommandSender sender, String label, List<String> arguments) {

		this.player = player;
		this.sender = sender;
		this.label = label;
		this.arguments = arguments;
    }

	/***
	 * @return Command sender as Player
	 */
	public Player getPlayer() {
		return player;
	}
	public CommandSender getSender() {
		return sender;
	}
	public String getLabel() {
		return label;
	}
	public List<String> getArguments() {
		return arguments;
	}

}
