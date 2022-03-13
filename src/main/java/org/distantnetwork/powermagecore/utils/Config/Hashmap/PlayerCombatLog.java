package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.ConfigManager;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public class PlayerCombatLog {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_combatlog.yml");
    public static FileConfiguration config = ConfigManager.loadConfigFile(file);

    public static void save() {
        for (Map.Entry<UUID, Boolean> entry : PowermageCore.playerCombatLog.entrySet()) config.set(entry.getKey().toString(), entry.getValue());
        ConfigManager.saveConfigFile(file, config);
    }

    public static Map<UUID, Boolean> load() {
        for (String uuid : config.getKeys(false)) {
            if (PowermageCore.playerCombatLog.isEmpty() || !PowermageCore.playerCombatLog.containsKey(UUID.fromString(uuid)))
                PowermageCore.playerCombatLog.put(UUID.fromString(uuid), config.getBoolean(uuid));
            else PowermageCore.playerCombatLog.replace(UUID.fromString(uuid), config.getBoolean(uuid));
        }
        return PowermageCore.playerCombatLog;
    }
}
