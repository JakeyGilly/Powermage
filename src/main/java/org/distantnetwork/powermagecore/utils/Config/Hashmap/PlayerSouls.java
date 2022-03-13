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
        PowermageCore.playerSouls.forEach((uuid, souls) -> config.set(uuid.toString(), souls));
        ConfigManager.saveConfigFile(file, config);
    }

    public static Map<UUID, Integer> load() {
        config.getValues(false).forEach((uuid, souls) -> PowermageCore.playerSouls.put(UUID.fromString(uuid.toString()), Integer.parseInt(souls.toString())));
        return PowermageCore.playerSouls;
    }
}
