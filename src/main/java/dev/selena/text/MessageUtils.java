package dev.selena.text;

public class MessageUtils {


    public static String stringBuilder(String ... args) {
        StringBuilder output = new StringBuilder();
        for (String arg : args) {
            output.append(arg).append(" ");
        }
        return output.toString();
    }

    public static String placeholders(String material_color) {
        return null;
    }
}
