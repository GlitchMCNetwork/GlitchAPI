package dev.selena.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GSound {

    public String Sound;
    public float Pitch;
    public float Volume;

    public GSound(String sound, float pitch, float volume) {
        Sound = sound;
        Pitch = pitch;
        Volume = volume;
    }

    /**
     * Plays sound for all online players
     */
    public void playSound() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location loc = player.getLocation();
            loc.getWorld().playSound(loc, org.bukkit.Sound.valueOf(Sound), Volume, Pitch);
        }
    }


    /**
     * Plays sound for specific player
     *
     * @param player the player you want to play the sound for
     */
    public void playSound(Player player) {
        Location loc = player.getLocation();
        loc.getWorld().playSound(loc, org.bukkit.Sound.valueOf(Sound), Volume, Pitch);
    }


}
