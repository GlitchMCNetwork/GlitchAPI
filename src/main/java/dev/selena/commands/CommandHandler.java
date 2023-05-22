package dev.selena.commands;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    private final Method method;

     private final String name;
     private final String permission;
     private final String[] aliases;
     private final boolean playersOnly;

     private final List<Argument> arguments = new ArrayList<>();

     public CommandHandler(Method method, Command command) {
         this.method = method;
         this.name = command.name();
         this.permission = command.permission();
         this.aliases = command.aliases();
         this.playersOnly = command.playerOnly();

         for(String s : name.split("\\.")) {

             String substring = s.substring(1, s.length() - 2);
             if(s.startsWith("<") && s.endsWith(">")) arguments.add(new Argument.UnknownArgument(substring));
             else if(s.startsWith("[") && s.endsWith("]")) arguments.add(new Argument.OptionalArgument(substring));
             else arguments.add(new Argument.KnownArgument(s));
         }
     }

     public Method getMethod() {
			return method;
		}

		public String getName() {
			return name;
		}

		public String getPermission() {
			return permission;
		}

		public String[] getAliases() {
			return aliases;
		}

		public boolean isPlayersOnly() {
			return playersOnly;
		}

		public List<Argument> getArguments() {
			return arguments;
		}

		public static abstract class Argument {
			private final String argument;
         public Argument(String argument) { this.argument = argument; }

         public String getArgument() {
				return argument;
			}

            public static class KnownArgument extends Argument { public KnownArgument(String argument) { super(argument); }}
         public static class UnknownArgument extends Argument { public UnknownArgument(String lookingFor) { super(lookingFor); }}
            public static class OptionalArgument extends Argument { public OptionalArgument(String optional) { super(optional); }}
     }
 }
