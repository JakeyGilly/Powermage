package org.distantnetwork.powermagecore.utils.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;

public class ConfigurationManager {
    public static void setValue(String filePath, String path, Object value) {
        FileConfiguration config = getConfig(filePath);
        config.set(path, value);
        saveConfig(filePath, config);
    }

    public static void setValue(File file, String path, Object value) {
        FileConfiguration config = getConfig(file);
        if (config == null) return;
        config.set(path, value);
        saveConfig(file, config);
    }

    public static boolean isKeyExists(String filePath, String path) {
        FileConfiguration config = getConfig(filePath);
        return config.contains(path);
    }

    public static boolean isKeyExists(File file, String path) {
        FileConfiguration config = getConfig(file);
        if (config == null) return false;
        return config.contains(path);
    }

    public static void setDefault(String filePath, String path, Object value, boolean save) {
        FileConfiguration config = getConfig(filePath);
        config.addDefault(path, value);
        if (save) config.options().copyDefaults(true);
        saveConfig(filePath, config);
    }

    public static void setDefault(File file, String path, Object value, boolean save) {
        FileConfiguration config = getConfig(file);
        if (config == null) return;
        config.addDefault(path, value);
        if (save) config.options().copyDefaults(true);
        saveConfig(file, config);
    }

    public static void save(String filePath) {
        FileConfiguration config = getConfig(filePath);
        if (config == null) return;
        saveConfig(filePath, config);
    }

    public static void save(File file) {
        FileConfiguration config = getConfig(file);
        if (config == null) return;
        saveConfig(file, config);
    }

    public static @Nullable File getFileFolder(@NotNull String folderPath) {
        File folder = new File(getInstance().getDataFolder(), folderPath); // get the folder
        if (!folder.exists()) folder.mkdirs(); // if the folder doesn't exist, make it
        if (!folder.isDirectory()) return null; // if it's not a folder, return null
        return folder; // if it's a folder, return it
    }

    public static @Nullable File getFileFile(@NotNull String filePath) {
        File file = new File(getInstance().getDataFolder(), filePath); // get the file
        if (!file.getParentFile().exists()) file.mkdirs(); // if the parent folder doesn't exist, make it
        if (!file.exists()) { // if the file doesn't exist, make it
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!file.isFile()) return null; // if it's not a file, return null
        return file; // if it's a file, return it
    }

    public static @NotNull File getFile(@NotNull String filePath) {
        File file = new File(getInstance().getDataFolder(), filePath); // get the file
        if (!file.getParentFile().exists()) file.mkdirs(); // if the parent folder doesn't exist, make it
        if (!file.exists()) { // if the file doesn't exist, make it
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file; // return it
    }

    public static @Nullable File getFileFolder(@NotNull File file) {
        if (!file.exists()) file.mkdirs(); // if the file doesn't exist, make it
        if (!file.isDirectory()) return null; // if it's not a folder, return null
        return file; // if it's a folder, return it
    }

    public static @Nullable File getFileFile(@NotNull File file) {
        if (!file.getParentFile().exists()) file.mkdirs(); // if the parent folder doesn't exist, make it
        if (!file.exists()) { // if the file doesn't exist, make it
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!file.isFile()) return null; // if it's not a file, return null
        return file; // if it's a file, return it
    }

    public static @NotNull File getFile(@NotNull File file) {
        if (!file.getParentFile().exists()) file.mkdirs(); // if the parent folder doesn't exist, make it
        if (!file.exists()) { // if the file doesn't exist, make it
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file; // return it
    }


    public static @Nullable FileConfiguration getConfig(@NotNull String filePath) {
        File file = getFileFile(filePath); // get the file
        if (file == null) return null; // if the file isn't a file, return null
        return YamlConfiguration.loadConfiguration(file); // if it does exist, return it
    }

    public static @Nullable FileConfiguration getConfig(@NotNull File file) {
        file = getFileFile(file); // get the file
        if (file == null) return null; // if the file isn't a file, return null
        return YamlConfiguration.loadConfiguration(file); // return it
    }

    public static void saveConfig(@NotNull String filePath, @NotNull FileConfiguration config) {
        File file = getFileFile(filePath); // get the file
        if (file == null) return; // if the file isn't a file, return
        try {
            config.save(file); // save the config
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig(@NotNull File file, @NotNull FileConfiguration config) {
        file = getFileFile(file); // get the file
        if (file == null) return; // if the file isn't a file, return
        try {
            config.save(file); // save the config
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer getFilesAmountInFolder(@NotNull String folderPath) {
        File folder = getFileFolder(folderPath); // get the folder
        if (folder == null) return 0; // if the folder isn't a folder, return 0
        File[] files = folder.listFiles(); // get the files in the folder
        return files == null ? 0 : files.length; // if the files aren't null, return the amount of files
    }

    public static Integer getFilesAmountInFolder(@NotNull File folder) {
        folder = getFileFolder(folder); // get the folder
        if (folder == null) return 0; // if the folder isn't a folder, return 0
        File[] files = folder.listFiles(); // get the files in the folder
        return files == null ? 0 : files.length; // if the files aren't null, return the amount of files
    }

    public static void deleteFile(@NotNull String filePath) {
        File file = getFileFile(filePath); // get the file
        if (file == null) return; // if the file isn't a file, return
        file.delete(); // delete the file
    }

    public static void deleteFile(@NotNull File file) {
        file = getFileFile(file); // get the file
        if (file == null) return; // if the file isn't a file, return
        file.delete(); // delete the file
    }

    public static void deleteFolder(@NotNull String folderPath) {
        File folder = getFileFolder(folderPath); // get the folder
        if (folder == null) return; // if the folder isn't a folder, return
        folder.delete(); // delete the folder
    }

    public static void deleteFolder(@NotNull File folder) {
        folder = getFileFolder(folder); // get the folder
        if (folder == null) return; // if the folder isn't a folder, return
        folder.delete(); // delete the folder
    }



    //? DEFAULT CONFIGURATION

    public static FileConfiguration getDefaultConfig() {
        return getInstance().getConfig();
    }

    public static void saveDefaultConfig() {
        getInstance().saveConfig();
    }

    //? WEAPONS CONFIGURATION

    public static File getWeaponsFolder() {
        return getFileFolder(getInstance().getDataFolder() + "weapons");
    }
}
