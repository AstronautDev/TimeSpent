package com.alexpedisic.timespent.listeners;

import com.alexpedisic.timespent.TimeSpent;
import com.alexpedisic.timespent.util.Playtime;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    TimeSpent plugin;
    public LeaveListener(TimeSpent plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void PlayerLeaveEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(plugin.getPlaytimeConfig().contains(p.getUniqueId().toString())) {
            Playtime.updatePlaytime(p);
        }
    }

}
