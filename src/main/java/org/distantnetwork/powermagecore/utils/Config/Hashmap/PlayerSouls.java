package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;

import java.io.File;
import java.util.UUID;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.getConfig;
import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.saveConfig;

public class PlayerSouls {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_souls.yml");
    public static FileConfiguration config = getConfig(file);

    public static void save() {
        PowermageCore.playerSouls.forEach((uuid, souls) -> config.set(uuid.toString(), souls));
        saveConfig(file, config);
    }

    public static void load() {
        config.getValues(false).forEach((uuid, souls) -> PowermageCore.playerSouls.put(UUID.fromString(uuid), Integer.parseInt(souls.toString())));
    }

    public static Integer getSouls(UUID uuid) {
        return PowermageCore.playerSouls.getOrDefault(uuid, 0);
    }

    public static void addSouls(UUID uuid, Integer souls) {
        if (PowermageCore.playerSouls.containsKey(uuid)) {
            PowermageCore.playerSouls.put(uuid, PowermageCore.playerSouls.get(uuid) + souls);
        } else {
            PowermageCore.playerSouls.put(uuid, souls);
        }
        save();
    }

    public static void removeSouls(UUID uuid, Integer souls) {
        if (PowermageCore.playerSouls.containsKey(uuid)) {
            PowermageCore.playerSouls.put(uuid, PowermageCore.playerSouls.get(uuid) - souls);
        } else {
            PowermageCore.playerSouls.put(uuid, -souls);
        }
        save();
    }

    public static void setSouls(UUID uuid, Integer souls) {
        PowermageCore.playerSouls.put(uuid, souls);
        save();
    }

    public static void removePlayer(UUID uuid) {
        PowermageCore.playerSouls.remove(uuid);
        save();
    }

    public static void removeAllSouls() {
        PowermageCore.playerSouls.clear();
        save();
    }

    public static void removeAllPlayersAbove(Integer souls) {
        PowermageCore.playerSouls.entrySet().removeIf(entry -> entry.getValue() > souls);
        save();
    }

    public static void removeAllPlayersBelow(Integer souls) {
        PowermageCore.playerSouls.entrySet().removeIf(entry -> entry.getValue() < souls);
        save();
    }
}
