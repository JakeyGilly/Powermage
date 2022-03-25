package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Enums.Classes;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.getConfig;
import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.saveConfig;

public class PlayerClasses {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_classes.yml");
    public static FileConfiguration config = getConfig(file);

    public static void save() {
        PowermageCore.playerClasses.forEach((uuid, classes) -> config.set(uuid.toString(), classes.name()));
        saveConfig(file, config);
    }

    public static void load() {
        config.getValues(false).forEach((uuid, classes) -> PowermageCore.playerClasses.put(UUID.fromString(uuid), Classes.valueOf(classes.toString())));
    }

    public static Classes getClasses(UUID uuid) {
        return PowermageCore.playerClasses.get(uuid);
    }

    public static void setClasses(UUID uuid, Classes classes) {
        PowermageCore.playerClasses.put(uuid, classes);
        save();
    }

    public static void removeByUUID(UUID uuid) {
        PowermageCore.playerClasses.remove(uuid);
        save();
    }

    public static void removeByClasses(Classes classes) {
        PowermageCore.playerClasses.entrySet().removeIf(entry -> entry.getValue() == classes);
        save();
    }

    public static void removeAll() {
        PowermageCore.playerClasses.clear();
        save();
    }

    public static Map<UUID, Classes> getAll() {
        return PowermageCore.playerClasses;
    }
}
