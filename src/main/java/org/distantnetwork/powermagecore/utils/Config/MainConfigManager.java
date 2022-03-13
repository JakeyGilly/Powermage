package org.distantnetwork.powermagecore.utils.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainConfigManager {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "config.yml");
    public static FileConfiguration config = ConfigManager.loadConfigFile(file);
    public static <T> T getValue(String key) {return ConfigManager.getValue(file, key);}
    public static void setValue(String key, Object value) {ConfigManager.setValue(file, key, value);}
    public static boolean keyExists(String key) {return ConfigManager.keyExists(file, key);}
    public static void removeKey(String key) {ConfigManager.removeKey(file, key);}
    public static void setValueIfNotExists(String key, Object value) {ConfigManager.setValueIfNotExists(file, key, value);}
    public static Integer loadFileLength() {return config.getKeys(false).size();}
    public static void saveDefaultConfig() {
        if (config == null) return;
        config.set("version", PowermageCore.getInstance().getDescription().getVersion());
        Map<String, String> emojis = new HashMap<>();
        emojis.put("warriorEmoji", "⚔️");
        emojis.put("archerEmoji", "\uD83C\uDFF9");
        emojis.put("wizardEmoji", "\uD83E\uDE84");
        emojis.put("tankEmoji", "\uD83D\uDEE1️️");
        config.addDefault("classEmojis", emojis);
        config.options().copyDefaults(true);
        ConfigManager.saveConfigFile(file, config);
    }
}
