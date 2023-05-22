package dev.selena.commands;

import dev.selena.GlitchAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public abstract class GlitchCommand {
    private final Object obj;
    public static Plugin plugin = GlitchAPI.getPlugin();
    private final HashMap<String, List<CommandHandler>> handlers = new HashMap<>();
    private final HashMap<String, Method> helpMessages = new HashMap<>();


    public GlitchCommand() {

        this.obj = this;

        HashMap<String, org.bukkit.command.Command> commands = new HashMap<>();
        for(Method method : obj.getClass().getMethods()) {
            Command commandAnnotation = method.getAnnotation(Command.class);

            if(commandAnnotation != null) {
                String name = commandAnnotation.name().split("\\.")[0];

                List<CommandHandler> list = handlers.get(name);
                if(list == null) list = new ArrayList<>();
                
                list.add(new CommandHandler(method, commandAnnotation));
                handlers.put(name.toLowerCase(), list);

                if(commands.get(name.toLowerCase()) == null) {
                    commands.put(name.toLowerCase(), new org.bukkit.command.Command(name.toLowerCase()) {
                        public boolean execute(CommandSender sender, String label, String[] args) {
                            Player player = null;
                            if(sender instanceof Player) player = (Player) sender;

                            try {
								handle(commandAnnotation.name().split("\\.")[0], new Arguments(player, sender, label, Arrays.asList(args)));
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								e.printStackTrace();
							}

                            return false;
                        }
                    });

                    commands.get(name.toLowerCase()).setAliases(Arrays.asList(commandAnnotation.aliases()));

                    try {
                        Field cmdMap = plugin.getServer().getClass().getDeclaredField("commandMap");
                        cmdMap.setAccessible(true);

                        ((org.bukkit.command.CommandMap)    cmdMap.get(plugin.getServer())).register(plugin.getDescription().getName(), commands.get(name.toLowerCase()));
                    } catch(Exception ex) { ex.printStackTrace(); }
                }
            }
            Help helpAnnotation = method.getAnnotation(Help.class);
            if(helpAnnotation != null) helpMessages.put(helpAnnotation.command(), method);
        }

//        for(Method method : obj.getClass().getMethods()) {
//        	
//            
//        }
    }

    public void handle(String command, Arguments commandArgs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String> arguments = new ArrayList<>(Collections.singletonList(command));
        arguments.addAll(commandArgs.getArguments());

        for(CommandHandler handler : handlers.get(command.toLowerCase())) {

            int currentArgument = 0;

            List<String> unknownArguments = new ArrayList<>();
            currentArgument = 0;
            int optionalCount = 0;
            for(CommandHandler.Argument argument : handler.getArguments()) {
                if(argument instanceof CommandHandler.Argument.UnknownArgument) {
                    if (currentArgument < arguments.size())
                    unknownArguments.add(arguments.get(currentArgument));
                }
                if(argument instanceof CommandHandler.Argument.OptionalArgument) {
                    optionalCount++;
                    if (currentArgument < arguments.size()) {
                        unknownArguments.add(arguments.get(currentArgument));
                    }
                }

                currentArgument++;
            }
            if(arguments.size() <= handler.getArguments().size() && arguments.size() >= handler.getArguments().size() - optionalCount) {


                currentArgument = 0;
                boolean matches = true;
                for(CommandHandler.Argument argument : handler.getArguments()) {
                    if(argument instanceof CommandHandler.Argument.KnownArgument && !arguments.get(currentArgument).equals(argument.getArgument())) matches = false;
                    currentArgument++;
                }

                if(matches) {

                    if(!commandArgs.getSender().hasPermission(handler.getPermission()) && handler.getPermission() != null) {
                        commandArgs.getSender().sendMessage(ChatColor.RED + "No permission.");
                        return;
                    }
                    if(handler.isPlayersOnly() && !(commandArgs.getSender() instanceof Player)) {
                        commandArgs.getSender().sendMessage(ChatColor.RED + "You must be a player to execute this command!");
                        return;
                    }


                    handler.getMethod().invoke(obj, new Arguments(commandArgs.getPlayer(), commandArgs.getSender(), commandArgs.getLabel(), unknownArguments));

                    return;
                }
            }
        }
        if(helpMessages.get(command) != null) {
        	helpMessages.get(command).invoke(obj, commandArgs.getSender());
        }
        else commandArgs.getSender().sendMessage(ChatColor.RED + "Invalid arguments.");
    }

}
