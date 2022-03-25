package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;

import java.io.File;
import java.util.UUID;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.getConfig;
import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.saveConfig;

public class PlayerDeaths {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_deaths.yml");
    public static FileConfiguration config = getConfig(file);

    public static void save() {
        PowermageCore.playerDeaths.forEach((uuid, deaths) -> config.set(uuid.toString(), deaths));
        saveConfig(file, config);
    }

    public static void load() {
        config.getValues(false).forEach((uuid, deaths) -> PowermageCore.playerDeaths.put(UUID.fromString(uuid), Integer.parseInt(deaths.toString())));
    }

    public static Integer getDeaths(UUID uuid) {
        return PowermageCore.playerDeaths.getOrDefault(uuid, 0);
    }

    public static void addDeaths(UUID uuid, Integer deaths) {
        if (PowermageCore.playerDeaths.containsKey(uuid)) {
            PowermageCore.playerDeaths.put(uuid, PowermageCore.playerDeaths.get(uuid) + deaths);
        } else {
            PowermageCore.playerDeaths.put(uuid, deaths);
        }
        save();
    }

    public static void removeDeaths(UUID uuid, Integer deaths) {
        if (PowermageCore.playerDeaths.containsKey(uuid)) {
            PowermageCore.playerDeaths.put(uuid, PowermageCore.playerDeaths.get(uuid) - deaths);
        } else {
            PowermageCore.playerDeaths.put(uuid, -deaths);
        }
        save();
    }

    public static void setDeaths(UUID uuid, Integer deaths) {
        PowermageCore.playerDeaths.put(uuid, deaths);
        save();
    }

    public static void removePlayer(UUID uuid) {
        PowermageCore.playerDeaths.remove(uuid);
        save();
    }

    public static void removeAllDeaths() {
        PowermageCore.playerDeaths.clear();
        save();
    }

    public static void removeAllPlayersAbove(Integer deaths) {
        PowermageCore.playerDeaths.entrySet().removeIf(entry -> entry.getValue() > deaths);
        save();
    }

    public static void removeAllPlayersBelow(Integer deaths) {
        PowermageCore.playerDeaths.entrySet().removeIf(entry -> entry.getValue() < deaths);
        save();
    }
}
