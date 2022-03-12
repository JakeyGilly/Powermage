package org.distantnetwork.powermagecore.utils.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;

import java.io.File;
import java.util.Arrays;

public class MainConfigManager {
    public static File getFile() {return new File(PowermageCore.getInstance().getDataFolder(), "config.yml");}
    public static FileConfiguration getConfig() {return ConfigManager.loadConfigFile(getFile());}
    public static <T> T getValue(String key) {return ConfigManager.getValue(getFile(), key);}
    public static void setValue(String key, Object value) {ConfigManager.setValue(getFile(), key, value);}
    public static boolean keyExists(String key) {return ConfigManager.keyExists(getFile(), key);}
    public static void removeKey(String key) {ConfigManager.removeKey(getFile(), key);}
    public static void setValueIfNotExists(String key, Object value) {ConfigManager.setValueIfNotExists(getFile(), key, value);}
    public static Integer loadFileLength() {return getConfig().getKeys(false).size();}
    public static void saveDefaultConfig() {
        File file = getFile();
        FileConfiguration config = getConfig();
        if (config == null) return;
        config.addDefault("int", 1);
        config.addDefault("double", 1.0);
        config.addDefault("bool", true);
        config.addDefault("long", 1L);
        config.addDefault("string", "string");
        config.addDefault("liststr", Arrays.asList("string", "string"));
        config.options().copyDefaults(true);
        ConfigManager.saveConfigFile(file, config);
    }
}
