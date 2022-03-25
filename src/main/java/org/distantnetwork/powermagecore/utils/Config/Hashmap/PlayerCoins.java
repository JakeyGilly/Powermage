package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.getConfig;
import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.saveConfig;

public class PlayerCoins {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_coins.yml");
    public static FileConfiguration config = getConfig(file);

    public static void save() {
        PowermageCore.playerCoins.forEach((uuid, souls) -> config.set(uuid.toString(), souls));
        saveConfig(file, config);
    }

    public static void load() {
        config.getValues(false).forEach((uuid, souls) -> PowermageCore.playerCoins.put(UUID.fromString(uuid), Integer.parseInt(souls.toString())));
    }

    public static Integer getCoins(UUID uuid) {
        return PowermageCore.playerCoins.getOrDefault(uuid, 0);
    }

    public static void addCoins(UUID uuid, Integer coins) {
        if (PowermageCore.playerCoins.containsKey(uuid)) {
            PowermageCore.playerCoins.put(uuid, PowermageCore.playerCoins.get(uuid) + coins);
        } else {
            PowermageCore.playerCoins.put(uuid, coins);
        }
        save();
    }

    public static void removeCoins(UUID uuid, Integer coins) {
        if (PowermageCore.playerCoins.containsKey(uuid)) {
            PowermageCore.playerCoins.put(uuid, PowermageCore.playerCoins.get(uuid) - coins);
        } else {
            PowermageCore.playerCoins.put(uuid, -coins);
        }
        save();
    }

    public static void setCoins(UUID uuid, Integer coins) {
        PowermageCore.playerCoins.put(uuid, coins);
        save();
    }

    public static void removeByUUID(UUID uuid) {
        PowermageCore.playerCoins.remove(uuid);
        save();
    }

    public static void removeAll() {
        PowermageCore.playerCoins.clear();
        save();
    }

    public static void removeAllUUIDAbove(Integer coins) {
        PowermageCore.playerCoins.entrySet().removeIf(entry -> entry.getValue() > coins);
        save();
    }

    public static void removeAllUUIDBelow(Integer coins) {
        PowermageCore.playerCoins.entrySet().removeIf(entry -> entry.getValue() < coins);
        save();
    }

    public static void removeAllUUIDBetween(Integer min, Integer max) {
        PowermageCore.playerCoins.entrySet().removeIf(entry -> entry.getValue() < min || entry.getValue() > max);
        save();
    }

    public static void removeAllUUIDAboveOrEqual(Integer coins) {
        PowermageCore.playerCoins.entrySet().removeIf(entry -> entry.getValue() >= coins);
        save();
    }

    public static void removeAllUUIDBelowOrEqual(Integer coins) {
        PowermageCore.playerCoins.entrySet().removeIf(entry -> entry.getValue() <= coins);
        save();
    }

    public static Map<UUID, Integer> getAll() {
        return PowermageCore.playerCoins;
    }
}
