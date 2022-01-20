package com.alexpedisic.timespent;

import com.alexpedisic.timespent.commands.TimeSpentCommand;
import com.alexpedisic.timespent.listeners.JoinListener;
import com.alexpedisic.timespent.listeners.LeaveListener;
import com.alexpedisic.timespent.util.Playtime;
import com.alexpedisic.timespent.util.PlaytimeConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class TimeSpent extends JavaPlugin {

    JoinListener joinListener;
    LeaveListener leaveListener;
    TimeSpentCommand timeSpentCommand;
    PlaytimeConfig playtimeConfig;
    Playtime playtime;

    public void onEnable() {
        initListeners();
        initCommands();
        initConfigs();
        initUtil();
    }

    private void initListeners() {
        this.joinListener = new JoinListener(this);
        this.leaveListener = new LeaveListener(this);
    }

    private void initCommands() {
        this.timeSpentCommand = new TimeSpentCommand(this);
    }

    private void initConfigs() {
        this.playtimeConfig = new PlaytimeConfig(this);
    }

    public FileConfiguration getPlaytimeConfig() {
        return playtimeConfig.playtimeConfig;
    }

    public void savePlaytimeConfig() {
        playtimeConfig.saveConfig();
    }

    public void initUtil() {
        this.playtime = new Playtime(this);
    }

}
