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
    public static FileConfiguration getConfig(Integer index) {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator), String.format("weapon-%d.yml", index));
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void saveConfig(FileConfiguration config, Integer index) {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator), String.format("weapon-%d.yml", index));
        try {
            if (!file.exists()) file.createNewFile();
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getConfigAmount(File file) {
        int i = 0;
        String[] list = file.list();
        if (list != null) for (String s : list)
            if (s.equals(String.format("weapon-%d.yml", i))) i++;
        return i;
    }

    public static void createDefaultWeapons() {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator), "weapon-0.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                config.set("enabled", true);
                config.addDefault("name", "DemoItem");
                config.addDefault("material", Material.STONE.getKey().getKey().toUpperCase());
                config.addDefault("lore", new String[]{"&alore", "&clore"});
                Map<String, Integer> enchantments = new HashMap<String, Integer>();
                enchantments.put(Enchantment.MENDING.getKey().getKey(), 1);
                config.addDefault("enchantments", enchantments);
                config.addDefault("unbreakable", true);
                config.addDefault("flags", new String[]{ItemFlag.HIDE_UNBREAKABLE.name()});
                config.addDefault("damage", "NOT IMPLEMENTED");
                config.addDefault("ability_cooldown", "NOT IMPLEMENTED");
                config.addDefault("ability_mana", "NOT IMPLEMENTED");
                config.options().copyDefaults(true);
                saveConfig(config, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
