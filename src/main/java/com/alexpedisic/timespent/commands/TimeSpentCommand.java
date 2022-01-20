package com.alexpedisic.timespent.commands;

import com.alexpedisic.timespent.TimeSpent;
import com.alexpedisic.timespent.util.Playtime;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeSpentCommand implements CommandExecutor {

    TimeSpent plugin;
    public TimeSpentCommand(TimeSpent plugin) {
        this.plugin = plugin;

        plugin.getCommand("timespent").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            String playerUUID = p.getUniqueId().toString();

            if(s.equalsIgnoreCase("timespent")) {
                if(plugin.getPlaytimeConfig().contains(playerUUID)) {

                    //Time Since First Join
                    p.sendMessage("Time Since First Join: " + Playtime.getPlaytime(plugin.getPlaytimeConfig().getLong(playerUUID + ".firstJoin")));

                    //Current Playtime
                    p.sendMessage("Current Playtime: " + Playtime.getPlaytime(plugin.getPlaytimeConfig().getLong(playerUUID + ".recentSession")));

                    //Total Playtime
                    p.sendMessage("Total Playtime: " + Playtime.getTotalPlaytime(p));

                    return true;
                }
            }

        } else {
            sender.sendMessage(ChatColor.RED + "You Must Be A Player To Execute!");
            return false;
        }

        return false;
    }
}
