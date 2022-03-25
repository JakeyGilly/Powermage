package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;

import java.io.File;
import java.util.UUID;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.getConfig;
import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.saveConfig;

public class PlayerKills {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_kills.yml");
    public static FileConfiguration config = getConfig(file);

    public static void save() {
        PowermageCore.playerKills.forEach((uuid, kills) -> config.set(uuid.toString(), kills));
        saveConfig(file, config);
    }

    public static void load() {
        config.getValues(false).forEach((uuid, kills) -> PowermageCore.playerKills.put(UUID.fromString(uuid), Integer.parseInt(kills.toString())));
    }

    public static Integer getKills(UUID uuid) {
        return PowermageCore.playerKills.getOrDefault(uuid, 0);
    }

    public static void addKills(UUID uuid, Integer kills) {
        if (PowermageCore.playerKills.containsKey(uuid)) {
            PowermageCore.playerKills.put(uuid, PowermageCore.playerKills.get(uuid) + kills);
        } else {
            PowermageCore.playerKills.put(uuid, kills);
        }
        save();
    }

    public static void removeKills(UUID uuid, Integer kills) {
        if (PowermageCore.playerKills.containsKey(uuid)) {
            PowermageCore.playerKills.put(uuid, PowermageCore.playerKills.get(uuid) - kills);
        } else {
            PowermageCore.playerKills.put(uuid, -kills);
        }
        save();
    }

    public static void setKills(UUID uuid, Integer kills) {
        PowermageCore.playerKills.put(uuid, kills);
        save();
    }

    public static void removePlayer(UUID uuid) {
        PowermageCore.playerKills.remove(uuid);
        save();
    }

    public static void removeAllKills() {
        PowermageCore.playerKills.clear();
        save();
    }

    public static void removeAllPlayersAbove(Integer kills) {
        PowermageCore.playerKills.entrySet().removeIf(entry -> entry.getValue() > kills);
        save();
    }

    public static void removeAllPlayersBelow(Integer kills) {
        PowermageCore.playerKills.entrySet().removeIf(entry -> entry.getValue() < kills);
        save();
    }
}
