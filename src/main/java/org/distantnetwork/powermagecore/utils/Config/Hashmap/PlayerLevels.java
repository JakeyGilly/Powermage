package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.ConfigManager;
import org.distantnetwork.powermagecore.utils.Enums.Classes;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerLevels {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_levels.yml");
    public static FileConfiguration config = ConfigManager.loadConfigFile(file);

    public static void save() {
        PowermageCore.playerLevels.forEach((uuid, map) -> map.forEach((classType, arr1) -> config.set(String.format("%s.%s", uuid.toString(), classType.toString()), arr1)));
    }

    public static void load() {
        config.getValues(false).forEach((uuid, map) -> config.getConfigurationSection(uuid.toString()).getValues(false).forEach((classType, map1) -> config.getConfigurationSection(String.format("%s.%s", uuid, classType.toString())).getValues(false).forEach((level, xp) -> PowermageCore.playerLevels.put(UUID.fromString(uuid.toString()), new HashMap<Classes, ArrayList<Integer>>() {{
                    put(Classes.valueOf(classType.toString()), new ArrayList<Integer>() {{
                        add(Integer.parseInt(level.toString()));
                        add(Integer.parseInt(xp.toString()));
                    }});
                }}))));
    }

    public static void addPlayerLevel(UUID uuid, Classes classType, int level, int xp) {
        if (!PowermageCore.playerLevels.containsKey(uuid)) {
            PowermageCore.playerLevels.put(uuid, new HashMap<>());
        }
        if (!PowermageCore.playerLevels.get(uuid).containsKey(classType)) {
            PowermageCore.playerLevels.get(uuid).put(classType, new ArrayList<>());
        }
        PowermageCore.playerLevels.get(uuid).get(classType).add(0, level);
        PowermageCore.playerLevels.get(uuid).get(classType).add(1, xp);
        save();
    }

    public static void subtractPlayerLevel(UUID uuid, Classes classType, int level, int xp) {
        if (!PowermageCore.playerLevels.containsKey(uuid)) {
            PowermageCore.playerLevels.put(uuid, new HashMap<>());
        }
        if (!PowermageCore.playerLevels.get(uuid).containsKey(classType)) {
            PowermageCore.playerLevels.get(uuid).put(classType, new ArrayList<>());
        }
        PowermageCore.playerLevels.get(uuid).get(classType).set(0, PowermageCore.playerLevels.get(uuid).get(classType).get(0) - level);
        PowermageCore.playerLevels.get(uuid).get(classType).set(1, PowermageCore.playerLevels.get(uuid).get(classType).get(1) - xp);
        save();
    }

    public static void removeByClass(UUID uuid, Classes classType) {
        if (PowermageCore.playerLevels.containsKey(uuid)) {
            PowermageCore.playerLevels.get(uuid).remove(classType);
        }
        save();
    }

    public static void removeByUUID(UUID uuid) {
        PowermageCore.playerLevels.remove(uuid);
        save();
    }

    public static void removeAll() {
        PowermageCore.playerLevels.clear();
        save();
    }

    public static Map<Classes, ArrayList<Integer>> getPlayerClassLevelMap(UUID uuid) {
        return PowermageCore.playerLevels.get(uuid);
    }

    public static ArrayList<Integer> getPlayerLevelMap(UUID uuid, Classes classType) {
        return PowermageCore.playerLevels.get(uuid).get(classType);
    }

    public static Map<UUID, HashMap<Classes, ArrayList<Integer>>> getAll() {
        return PowermageCore.playerLevels;
    }

    public static int getPlayerLevel(UUID uuid, Classes classType) {
        if (PowermageCore.playerLevels.containsKey(uuid) && PowermageCore.playerLevels.get(uuid).containsKey(classType)) {
            return PowermageCore.playerLevels.get(uuid).get(classType).get(0);
        } else {
            return 0;
        }
    }

    public static int getPlayerXP(UUID uuid, Classes classType) {
        if (PowermageCore.playerLevels.containsKey(uuid) && PowermageCore.playerLevels.get(uuid).containsKey(classType)) {
            return PowermageCore.playerLevels.get(uuid).get(classType).get(1);
        } else {
            return 0;
        }
    }

    public static void setPlayerLevel(UUID uuid, Classes classType, int level) {
        if (PowermageCore.playerLevels.containsKey(uuid) && PowermageCore.playerLevels.get(uuid).containsKey(classType)) {
            PowermageCore.playerLevels.get(uuid).get(classType).set(0, level);
        } else if (PowermageCore.playerLevels.containsKey(uuid)) {
            PowermageCore.playerLevels.get(uuid).put(classType, new ArrayList<>());
            PowermageCore.playerLevels.get(uuid).get(classType).add(0, level);
        } else {
            PowermageCore.playerLevels.put(uuid, new HashMap<>());
            PowermageCore.playerLevels.get(uuid).put(classType, new ArrayList<>());
            PowermageCore.playerLevels.get(uuid).get(classType).set(0, level);
        }
        save();
    }

    public static void setPlayerXP(UUID uuid, Classes classType, int xp) {
        if (PowermageCore.playerLevels.containsKey(uuid) && PowermageCore.playerLevels.get(uuid).containsKey(classType)) {
            PowermageCore.playerLevels.get(uuid).get(classType).set(1, xp);
        } else if (PowermageCore.playerLevels.containsKey(uuid)) {
            PowermageCore.playerLevels.get(uuid).put(classType, new ArrayList<>());
            PowermageCore.playerLevels.get(uuid).get(classType).add(1, xp);
        } else {
            PowermageCore.playerLevels.put(uuid, new HashMap<>());
            PowermageCore.playerLevels.get(uuid).put(classType, new ArrayList<>());
            PowermageCore.playerLevels.get(uuid).get(classType).set(1, xp);
        }
        save();
    }
}
