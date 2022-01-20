package com.alexpedisic.timespent.util;

import com.alexpedisic.timespent.TimeSpent;
import org.bukkit.entity.Player;

public class Playtime {

    private static TimeSpent plugin;
    public Playtime(TimeSpent plugin) {
        this.plugin = plugin;
    }

    public static String getPlaytime(long startTime) {
        long currentTime = System.currentTimeMillis();
        long timeSinceJoin = currentTime - startTime;

        long seconds = timeSinceJoin / 1000 % 60;
        long minutes = timeSinceJoin / (60 * 1000) % 60;
        long hours = timeSinceJoin / (60 * 60 * 1000) % 24;
        long days = timeSinceJoin / (24 * 60 * 60 * 1000);

        return days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
    }

    public static String getTotalPlaytime(Player p) {
        String playerUUID = p.getUniqueId().toString();
        long playtime = plugin.getPlaytimeConfig().getLong(playerUUID + ".totalPlaytime");

        long seconds = playtime / 1000 % 60;
        long minutes = playtime / (60 * 1000) % 60;
        long hours = playtime / (60 * 60 * 1000) % 24;
        long days = playtime / (24 * 60 * 60 * 1000);

        return days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
    }

    public static long calculateTotalPlaytime(Player p) {
        String playerUUID = p.getUniqueId().toString();
        long playtime = plugin.getPlaytimeConfig().getLong(playerUUID + ".recentSession");
        long currentTime = System.currentTimeMillis();

        long totalPlaytime = plugin.getPlaytimeConfig().getLong(playerUUID + ".totalPlaytime") + (currentTime - playtime);

        return totalPlaytime;
    }

    public static void updatePlaytime(Player p) {
        String playerUUID = p.getUniqueId().toString();
        if(plugin.getPlaytimeConfig().contains(playerUUID)) {
            plugin.getPlaytimeConfig().set(playerUUID + ".totalPlaytime", calculateTotalPlaytime(p));
            plugin.savePlaytimeConfig();
        }
    }
}
