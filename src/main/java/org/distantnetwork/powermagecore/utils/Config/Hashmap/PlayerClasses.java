package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.ConfigManager;
import org.distantnetwork.powermagecore.utils.Enums.Classes;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public class PlayerClasses {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_classes.yml");
    public static FileConfiguration config = ConfigManager.loadConfigFile(file);

    public static void save() {
        for (Map.Entry<UUID, Classes> entry : PowermageCore.playerClasses.entrySet()) {
            config.set(entry.getKey().toString(), entry.getValue().toString());
        }
        ConfigManager.saveConfigFile(file, config);
    }

    public static Map<UUID, Classes> load() {
        for (String uuid : config.getKeys(false)) {
            if (PowermageCore.playerClasses.isEmpty() || !PowermageCore.playerClasses.containsKey(UUID.fromString(uuid)))
                PowermageCore.playerClasses.put(UUID.fromString(uuid), Classes.valueOf(config.getString(uuid)));
            else PowermageCore.playerClasses.replace(UUID.fromString(uuid), Classes.valueOf(config.getString(uuid)));
        }
        return PowermageCore.playerClasses;
    }
}
