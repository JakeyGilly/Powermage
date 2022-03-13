package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.ConfigManager;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public class PlayerSouls {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_souls.yml");
    public static FileConfiguration config = ConfigManager.loadConfigFile(file);

    public static void save() {
        for (Map.Entry<UUID, Integer> entry : PowermageCore.playerSouls.entrySet()) config.set(entry.getKey().toString(), entry.getValue());
        ConfigManager.saveConfigFile(file, config);
    }

    public static Map<UUID, Integer> load() {
        for (String uuid : config.getKeys(false)) {
            if (PowermageCore.playerSouls.isEmpty() || !PowermageCore.playerSouls.containsKey(UUID.fromString(uuid)))
                PowermageCore.playerSouls.put(UUID.fromString(uuid), config.getInt(uuid));
            else PowermageCore.playerSouls.replace(UUID.fromString(uuid), config.getInt(uuid));
        }
        return PowermageCore.playerSouls;
    }
}
