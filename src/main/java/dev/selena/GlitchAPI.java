package dev.selena;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.plugin.Plugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GlitchAPI {

    private static Plugin plugin;

    public static void setPlugin(Plugin plugin) {
        GlitchAPI.plugin = plugin;
    }

    public static String getServerVersion() {
        return plugin.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static boolean isLatest(String project) throws IOException {
        URL url = new URL("https://repo.jadeisacutie.com/api/maven/latest/version/private/" + project + "/" + plugin.getName());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Authorization", "Basic dmVyc2lvbjoyc0ZjQ3F6amZhc1NmdmZNUkhDcjRiL3hlVjNyTHhnMFZZQXJCcEFkbUJRYUV3TkFMVUFjVll2UUJiUkIzaS9w");
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        JsonObject jsonObject = JsonParser.parseString(content.toString()).getAsJsonObject();

        String version = jsonObject.get("version").getAsString();

        return plugin.getDescription().getVersion().equalsIgnoreCase(version);
    }

    public static Plugin getPlugin() {
        return plugin;
    }



}
