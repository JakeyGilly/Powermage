package org.distantnetwork.powermagecore;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeaponsManager {


    public static void setup(Integer index) {
        if (!new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator)).exists()) {
            new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator)).mkdir();
        }
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator), String.format("weapon-%d.yml", index));
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.addDefault("name", "Name");
            config.addDefault("material", Material.AIR.getKey().getKey());
            config.addDefault("lore", new String[]{"lore", "lore"});
            Map<String, Integer> enchantments = new HashMap<>();
            enchantments.put(Enchantment.MENDING.getKey().getKey(), 1);
            enchantments.put(Enchantment.DURABILITY.getKey().getKey(), 1);
            enchantments.put(Enchantment.DAMAGE_ALL.getKey().getKey(), 1);
            config.addDefault("enchantments", enchantments);
            config.addDefault("damage", 0);
            config.addDefault("unbreakable", false);
            config.addDefault("ability_cooldown", 0);
            config.addDefault("ability_mana", 0);
            config.addDefault("flags", new String[]{ItemFlag.HIDE_UNBREAKABLE.name(), ItemFlag.HIDE_UNBREAKABLE.name()});
            config.options().copyDefaults(true);
            saveConfig(config, index);
        }
    }
    public static FileConfiguration getConfig(Integer index) {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator), String.format("weapon-%d.yml", index));
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void saveConfig(FileConfiguration config, Integer index) {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator), String.format("weapon-%d.yml", index));
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
