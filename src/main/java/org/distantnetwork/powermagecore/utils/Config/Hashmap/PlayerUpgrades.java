package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.getConfig;
import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.saveConfig;

public class PlayerUpgrades {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_upgrades.yml");
    public static FileConfiguration config = getConfig(file);

    public static void save() {
        PowermageCore.playerUpgrades.forEach((uuid, map) -> map.forEach((upgrade, level) -> config.set(String.format("%s.%s", uuid.toString(), upgrade.name()), level)));
        saveConfig(file, config);
    }

    public static void load() {
        config.getValues(false).forEach((uuid, map) -> config.getConfigurationSection(uuid.toString()).getValues(false).forEach((upgrade, lvl) -> PowermageCore.playerUpgrades.put(UUID.fromString(uuid.toString()), new HashMap<Upgrades, Integer>() {{
                put(Upgrades.valueOf(upgrade.toString()), Integer.parseInt(lvl.toString()));
            }})));
    }

    public static void addUpgrade(UUID uuid, Upgrades upgrade) {
        if (!PowermageCore.playerUpgrades.containsKey(uuid)) {
            PowermageCore.playerUpgrades.put(uuid, new HashMap<>());
            PowermageCore.playerUpgrades.get(uuid).put(upgrade, 1);
            return;
        }
        if (!PowermageCore.playerUpgrades.get(uuid).containsKey(upgrade)) {
            PowermageCore.playerUpgrades.get(uuid).put(upgrade, 1);
            return;
        }
        PowermageCore.playerUpgrades.get(uuid).put(upgrade, PowermageCore.playerUpgrades.get(uuid).get(upgrade) + 1);
        save();
    }

    public static void setUpgrade(UUID uuid, Upgrades upgrade, int level) {
        if (!PowermageCore.playerUpgrades.containsKey(uuid)) {
            PowermageCore.playerUpgrades.put(uuid, new HashMap<>());
            PowermageCore.playerUpgrades.get(uuid).put(upgrade, level);
            return;
        }
        if (!PowermageCore.playerUpgrades.get(uuid).containsKey(upgrade)) {
            PowermageCore.playerUpgrades.get(uuid).put(upgrade, level);
            return;
        }
        PowermageCore.playerUpgrades.get(uuid).put(upgrade, level);
        save();
    }

    public static void removeUpgrade(UUID uuid, Upgrades upgrade) {
        if (!PowermageCore.playerUpgrades.containsKey(uuid)) return;
        PowermageCore.playerUpgrades.get(uuid).put(upgrade, PowermageCore.playerUpgrades.get(uuid).get(upgrade) - 1);
        if (PowermageCore.playerUpgrades.get(uuid).get(upgrade) <= 0) PowermageCore.playerUpgrades.get(uuid).put(upgrade, 0);
        save();
    }

    public static int getUpgradeLevel(UUID uuid, Upgrades upgrade) {
        if (!PowermageCore.playerUpgrades.containsKey(uuid)) return 0;
        if (!PowermageCore.playerUpgrades.get(uuid).containsKey(upgrade)) return 0;
        return PowermageCore.playerUpgrades.get(uuid).get(upgrade);
    }

    public static Map<Upgrades, Integer> getUpgradesMap(UUID uuid) {
        if (!PowermageCore.playerUpgrades.containsKey(uuid)) return new HashMap<>();
        return PowermageCore.playerUpgrades.get(uuid);
    }

    public static void removeUUID(UUID uuid) {
        PowermageCore.playerUpgrades.remove(uuid);
        save();
    }

    public static void removeAll() {
        PowermageCore.playerUpgrades.clear();
        save();
    }

    public static Map<UUID, Map<Upgrades, Integer>> getAll() {
        return PowermageCore.playerUpgrades;
    }
}
