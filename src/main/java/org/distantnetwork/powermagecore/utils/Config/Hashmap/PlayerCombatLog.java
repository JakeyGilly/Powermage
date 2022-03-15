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
        PowermageCore.playerCombatLog.forEach((uuid, combatLog) -> config.set(uuid.toString(), combatLog));
        ConfigManager.saveConfigFile(file, config);
    }

    public static void load() {
        config.getValues(false).forEach((uuid, combatLog) -> PowermageCore.playerCombatLog.put(UUID.fromString(uuid), (Boolean) combatLog));
    }

    public static void set(UUID uuid, boolean combatLog) {
        PowermageCore.playerCombatLog.put(uuid, combatLog);
        save();
    }

    public static void unset(UUID uuid) {
        PowermageCore.playerCombatLog.remove(uuid);
        save();
    }

    public static boolean isSet(UUID uuid) {
        return PowermageCore.playerCombatLog.get(uuid);
    }

    public static boolean isUnset(UUID uuid) {
        return !PowermageCore.playerCombatLog.get(uuid);
    }

    public static boolean get(UUID uuid) {
        return PowermageCore.playerCombatLog.get(uuid);
    }

    public static Map<UUID, Boolean> getAll() {
        return PowermageCore.playerCombatLog;
    }
}
