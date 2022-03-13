package org.distantnetwork.powermagecore.utils.Config.Hashmap;

import org.bukkit.configuration.file.FileConfiguration;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.ConfigManager;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerUpgrades {
    public static File file = new File(PowermageCore.getInstance().getDataFolder(), "player_upgrades.yml");
    public static FileConfiguration config = ConfigManager.loadConfigFile(file);

    public static void save() {
        PowermageCore.playerUpgrades.forEach((key, value) -> {
            value.forEach((upgrade, level) -> {
                config.set("data." + key.toString() + "." + upgrade.name(), level);
            });
        });
        ConfigManager.saveConfigFile(file, config);
    }

    public static Map<UUID, Map<Upgrades, Integer>> load() {
        config.getConfigurationSection("data").getKeys(false).forEach(uuid -> {
            PowermageCore.playerUpgrades.put(UUID.fromString(uuid), new HashMap<>());
            config.getConfigurationSection("data." + uuid).getKeys(false).forEach(upgrade -> {
                PowermageCore.playerUpgrades.get(UUID.fromString(uuid)).put(Upgrades.valueOf(upgrade), config.getInt("data." + uuid + "." + upgrade));
            });
        });
        return PowermageCore.playerUpgrades;
    }
}
