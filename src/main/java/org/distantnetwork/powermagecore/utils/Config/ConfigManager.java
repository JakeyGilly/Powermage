package org.distantnetwork.powermagecore.utils.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    public static FileConfiguration loadConfigFile(File file) {
        if (file.isDirectory()) return null;
        if (!new File(file.getParent()).exists()) new File(file.getParent()).mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void saveConfigFile(File file, FileConfiguration config) {
        if (file.isDirectory()) return;
        if (!new File(file.getParent()).exists()) new File(file.getParent()).mkdirs();
        try {
            if (!file.exists()) file.createNewFile();
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer loadFileLength(File file, String path) {
        return loadConfigFile(file).getKeys(false).size();
    }

    public static void deleteConfigFile(File file) {
        if (file.isDirectory()) return;
        if (!file.exists()) return;
        file.delete();
    }

    public static void createConfigFolder(File folder) {
        if (folder.isFile()) return;
        if (!folder.exists()) folder.mkdirs();
    }

    public static void deleteConfigFolder(File folder) {
        if (folder.isFile()) return;
        if (!folder.exists()) return;
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) deleteConfigFolder(file);
            else file.delete();
        }
        folder.delete();
    }

    public static <T> T getValue(File file, String key) {
        FileConfiguration config = ConfigManager.loadConfigFile(file);
        if (config == null) throw new NullPointerException("Config is null");
        if (!config.contains(key)) return null;
        if (config.get(key).getClass() == String.class) return (T) config.getString(key);
        else if (config.get(key).getClass() == Integer.class) return (T) new Integer(config.getInt(key));
        else if (config.get(key).getClass() == Double.class) return (T) new Double(config.getDouble(key));
        else if (config.get(key).getClass() == Boolean.class) return (T) new Boolean(config.getBoolean(key));
        throw new IllegalArgumentException("Type not supported");
    }

    public static void setValue(File file, String key, Object value) {
        FileConfiguration config = ConfigManager.loadConfigFile(file);
        if (config == null) throw new NullPointerException("Config is null");
        config.set(key, value);
        ConfigManager.saveConfigFile(file, config);
    }

    public static boolean keyExists(File file, String key) {
        FileConfiguration config = ConfigManager.loadConfigFile(file);
        if (config == null) throw new NullPointerException("Config is null");
        return config.contains(key);
    }

    public static void removeKey(File file, String key) {
        FileConfiguration config = ConfigManager.loadConfigFile(file);
        if (config == null || !config.contains(key)) throw new NullPointerException("Config is null");
        config.set(key, null);
        ConfigManager.saveConfigFile(file, config);
    }

    public static void setValueIfNotExists(File file, String key, Object value) {
        FileConfiguration config = ConfigManager.loadConfigFile(file);
        if (config == null) throw new NullPointerException("Config is null");
        if (!config.contains(key)) return;
        config.set(key, value);
        ConfigManager.saveConfigFile(file, config);
    }
}

