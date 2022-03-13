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
        PowermageCore.playerUpgrades.forEach((uuid, map) -> map.forEach((upgrade, level) -> config.set(String.format("%s.%s", uuid.toString(), upgrade.name()), level)));
        ConfigManager.saveConfigFile(file, config);
    }

    public static Map<UUID, Map<Upgrades, Integer>> load() {
        config.getValues(false).forEach((uuid, map) -> {
            PowermageCore.playerUpgrades.put(UUID.fromString(uuid.toString()), new HashMap<>());
            config.getConfigurationSection(uuid).getValues(false).forEach((upgrade, lvl) -> PowermageCore.playerUpgrades.get(UUID.fromString(uuid.toString())).put(Upgrades.valueOf(upgrade.toString()), Integer.parseInt(lvl.toString())));
        });
        return PowermageCore.playerUpgrades;
    }
}
