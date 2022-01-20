package com.alexpedisic.timespent.util;

import com.alexpedisic.timespent.TimeSpent;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlaytimeConfig {

    TimeSpent plugin;

    private File playtimeFile;
    public FileConfiguration playtimeConfig;

    public PlaytimeConfig(TimeSpent plugin) {
        this.plugin = plugin;

        createConfig();
    }

    private void createConfig() {
        playtimeFile = new File(plugin.getDataFolder(), "playtime.yml");
        if(!playtimeFile.exists()) {
            try {
                playtimeFile.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        playtimeConfig = new YamlConfiguration();
        try {
            playtimeConfig.load(playtimeFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        if(playtimeFile.exists()) {
            try {
                playtimeConfig.save(playtimeFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createConfig();
        }
    }





}
