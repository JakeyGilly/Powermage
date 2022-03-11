package org.distantnetwork.powermagecore.utils.Config;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponConfigManager {
    public static ItemStack getWeapon(int id) {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator), "weapon-" + id + ".yml");
        if (!file.exists()) {
            PowermageCore.getInstance().getLogger().warning(String.format("Weapon file %s does not exist!", file.getName()));
            return null;
        }
        FileConfiguration config = ConfigManager.getConfig(file);
        if (!config.getBoolean("enabled")) return null;
        assert config.getString("material") != null;
        ItemStack item = new ItemStack(Material.getMaterial(config.getString("material")));
        ItemMeta meta = item.getItemMeta();
        if (config.getString("name") != null) meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("name")));
        if (!config.getStringList("lore").isEmpty()) {
            List<String> lore = config.getStringList("lore");
            for(int i = lore.size(); i > 0; i--) lore.set(i-1, ChatColor.translateAlternateColorCodes('&', lore.get(i-1)));
            meta.setLore(lore);
        }
        if (config.getConfigurationSection("enchantments") != null) {
            config.getConfigurationSection("enchantments").getValues(false).forEach((key, value) -> {
                Enchantment enchant = Enchantment.getByKey(NamespacedKey.minecraft(key));
                if (enchant != null) {
                    meta.addEnchant(enchant, (Integer) value, true);
                }
            });
        }
        for (String flag : config.getStringList("flags")) meta.addItemFlags(ItemFlag.valueOf(flag));
        if (config.getBoolean("unbreakable")) meta.setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }

    public static int getWeaponAmount() {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator));
        if (!file.exists()) {
            PowermageCore.getInstance().getLogger().warning(String.format("Weapon folder %s does not exist!", file.getName()));
            return 0;
        }
        int count = 0;
        String[] list = file.list();
        if (list != null) {
            int i = 0;
            while (list[i].contains("weapon-" + Character.isDigit(list[i].charAt(7)) + ".yml")) {
                count++;
                i++;
            }
        }
        return count;
    }

    public static Integer[] getWeaponIDs() {
        File file = new File(String.format("%s%sweapons", PowermageCore.getInstance().getDataFolder(), File.separator));
        if (!file.exists()) {
            PowermageCore.getInstance().getLogger().warning(String.format("Weapon folder %s does not exist!", file.getName()));
            return null;
        }
        Integer[] ids = new Integer[getWeaponAmount()];
        String[] list = file.list();
        if (list != null) {
            int i = 0;
            while (list[i].contains("weapon-" + Character.isDigit(list[i].charAt(7)) + ".yml")) {
                ids[i] = Integer.parseInt(list[i].substring(7, list[i].indexOf(".yml")));
                i++;
            }
        }
        return ids;
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
