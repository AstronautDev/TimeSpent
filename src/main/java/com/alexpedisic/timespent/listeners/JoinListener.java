package com.alexpedisic.timespent.listeners;

import com.alexpedisic.timespent.TimeSpent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    TimeSpent plugin;

    public JoinListener(TimeSpent plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String playerUUID = p.getUniqueId().toString();
        long currentTime = System.currentTimeMillis();
        if(plugin.getPlaytimeConfig().contains(playerUUID)) {
            plugin.getPlaytimeConfig().set(playerUUID + ".recentSession", currentTime);
            plugin.savePlaytimeConfig();
            p.sendMessage("Playtime started..");
        } else {
            plugin.getPlaytimeConfig().set(playerUUID + ".firstJoin", currentTime);
            plugin.savePlaytimeConfig();
            p.sendMessage("Added to config!");
        }
    }

}
