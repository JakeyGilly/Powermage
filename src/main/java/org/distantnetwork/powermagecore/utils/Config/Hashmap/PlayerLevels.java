package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.ConfigManager;
import org.distantnetwork.powermagecore.utils.Enums.Classes;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public class PlayerLevels {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_levels.yml");
    public static FileConfiguration config = ConfigManager.loadConfigFile(file);

    public static void save() {
        for (Map.Entry<UUID, Map<Classes, Map<Integer, Integer>>> entry : PowermageCore.playerLevels.entrySet()) config.set(entry.getKey().toString(), entry.getValue());
        ConfigManager.saveConfigFile(file, config);
    }

    public static Map<UUID, Map<Classes, Map<Integer, Integer>>> load() {
        for (String uuid : config.getKeys(false)) {
            if (PowermageCore.playerLevels.isEmpty() || !PowermageCore.playerLevels.containsKey(UUID.fromString(uuid)))
                PowermageCore.playerLevels.put(UUID.fromString(uuid), (Map<Classes, Map<Integer, Integer>>) config.getMapList(uuid));
            else PowermageCore.playerLevels.replace(UUID.fromString(uuid), (Map<Classes, Map<Integer, Integer>>) config.getMapList(uuid));
        }
        return PowermageCore.playerLevels;
    }
}
