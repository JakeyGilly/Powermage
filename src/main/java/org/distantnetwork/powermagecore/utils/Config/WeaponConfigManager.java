package org.distantnetwork.powermagecore.utils.Config;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Enums.Rarity;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WeaponConfigManager {
    public static ItemStack getWeapon(int id) {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator), "weapon-" + id + ".yml");
        if (!file.exists()) {
            PowermageCore.getInstance().getLogger().warning(String.format("Weapon file %s does not exist!", file.getName()));
            return null;
        }
        FileConfiguration config = ConfigManager.getConfig(file);
        if (!config.getBoolean("enabled")) return null;
        PowermageCore.getInstance().getLogger().info(config.getString("material").equals("") ? "null" : config.getString("material"));
        if (!config.contains("material") && config.getString("material").equals("")) return null;
        ItemBuilder item = new ItemBuilder(Material.getMaterial(config.getString("material")), 1);
        if (config.getString("name") != null) item.setName(ChatColor.translateAlternateColorCodes('&', config.getString("name")));
        if (!config.getStringList("lore").isEmpty()) for (String s : config.getStringList("lore")) item.addLoreLine(ChatColor.translateAlternateColorCodes('&', s));
        if (config.getConfigurationSection("enchantments") != null) {
            config.getConfigurationSection("enchantments").getValues(false).forEach((key, value) -> {
                Enchantment enchant = Enchantment.getByKey(NamespacedKey.minecraft(key));
                item.addEnchant(enchant, (Integer) value);
            });
        }
        for (String flag : config.getStringList("flags")) {
            item.addItemFlags(ItemFlag.valueOf(flag));
        }
        if (config.getBoolean("unbreakable")) item.setUnbreakable();
        if (config.getString("rarity") != null) {
            Rarity rarity = Rarity.valueOf(config.getString("rarity"));
            item.addLoreLine(Rarity.getColor(rarity) + Rarity.getName(rarity, true, false));
        }
        return item.toItem();
    }

    public static int getWeaponAmount() {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator));
        if (!file.exists()) {
            PowermageCore.getInstance().getLogger().warning(String.format("Weapon folder %s does not exist!", file.getName()));
            return 0;
        }
        File[] list = file.listFiles();
        if (list == null) return 0;
        int count = 0;
        for (File f : list) if (f.getName().startsWith("weapon-")) count++;
        return count;
    }

    public static Integer[] getWeaponIDs() {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator));
        if (!file.exists()) {
            PowermageCore.getInstance().getLogger().warning(String.format("Weapon folder %s does not exist!", file.getName()));
            return new Integer[0];
        }
        List<Integer> ids = new ArrayList<Integer>();
        File[] list = file.listFiles();
        if (list != null) {
            for (File f : list) {
                if (f.getName().startsWith("weapon-")) {
                    ids.add(Integer.parseInt(f.getName().substring(7, f.getName().indexOf(".yml"))));
                }
            }
        }
        return ids.toArray(new Integer[ids.size()]);
    }

    public static void createDefaultWeapons() {
        if (!new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator)).exists())
            new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator)).mkdir();
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
                config.addDefault("rarity", Rarity.COMMON.name());
                config.addDefault("damage", "NOT IMPLEMENTED");
                config.addDefault("ability_cooldown", "NOT IMPLEMENTED");
                config.addDefault("ability_mana", "NOT IMPLEMENTED");
                config.options().copyDefaults(true);
                ConfigManager.saveConfig(file, config);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
