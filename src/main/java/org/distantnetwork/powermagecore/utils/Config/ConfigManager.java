package org.distantnetwork.powermagecore.utils.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    public static FileConfiguration getConfig(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void saveConfig(File file, FileConfiguration config) {
        try {
            if (!file.exists()) file.createNewFile();
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
