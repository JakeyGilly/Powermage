package org.distantnetwork.powermagecore.utils.Config;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Enums.Rarity;

import java.io.File;
import java.util.*;

public class WeaponConfigManager {
    public static File folder = new File(PowermageCore.getInstance().getDataFolder(), "weapons");
    public static File getFile(Integer id) {return new File(PowermageCore.getInstance().getDataFolder() + File.separator + "weapons-"+id+".yml");}
    public static FileConfiguration getConfig(Integer id) {return ConfigManager.loadConfigFile(getFile(id));}
    public static <T> T getValue(Integer id, String key) {return ConfigManager.getValue(getFile(id), key);}
    public static void setValue(Integer id, String key, Object value) {ConfigManager.setValue(getFile(id), key, value);}
    public static boolean keyExists(Integer id, String key) {return ConfigManager.keyExists(getFile(id), key);}
    public static void removeKey(Integer id, String key) {ConfigManager.removeKey(getFile(id), key);}
    public static void setValueIfNotExists(Integer id, String key, Object value) {ConfigManager.setValueIfNotExists(getFile(id), key, value);}
    public static Integer loadFileLength(Integer id) {return getConfig(id).getKeys(false).size();}

    public static ItemStack loadWeapon(int id) {
        FileConfiguration config = getConfig(id);
        if (!(Boolean) getValue(id, "enabled")) return null;
        if (!keyExists(id, "material")) return null;
        ItemBuilder item = new ItemBuilder(Material.getMaterial(getValue(id, "material")));
        if (keyExists(id, "name")) item.setName(ChatColor.translateAlternateColorCodes('&', getValue(id, "name").toString()));
        if (keyExists(id, "lore") && !config.getStringList("lore").isEmpty()) for (String s : config.getStringList("lore")) item.addLoreLine(ChatColor.translateAlternateColorCodes('&', s));
        if (keyExists(id, "enchantments")) config.getConfigurationSection("enchantments").getValues(false).forEach((key, value) -> {
            Enchantment enchant = Enchantment.getByKey(NamespacedKey.minecraft(key));
            item.addEnchant(enchant, (Integer) value);
        });
        if (keyExists(id, "flags")) config.getStringList("flags").forEach(s -> item.addItemFlags(ItemFlag.valueOf(s)));
        if (getValue(id,"unbreakable")) item.setUnbreakable();
        if (keyExists(id, "rarity")) item.setRarity(Rarity.valueOf(getValue(id, "rarity").toString()), true, false);
        return item.toItem();
    }

    public static int loadWeaponAmount() {
        int count = 0;
        for (File f : ConfigManager.getFilesInFolder(folder)) if (f.getName().startsWith("weapon-")) count++;
        return count;
    }

    public static Integer[] loadWeaponIDs() {
        List<Integer> ids = new ArrayList<Integer>();
        for (File f : ConfigManager.getFilesInFolder(folder)) if (f.getName().startsWith("weapon-")) ids.add(Integer.parseInt(f.getName().substring(7, f.getName().indexOf(".yml"))));
        return ids.toArray(new Integer[ids.size()]);
    }

    public static void saveDefaultWeapons() {
        File file = getFile(0);
        FileConfiguration config = getConfig(0);

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
        ConfigManager.saveConfigFile(file, config);
    }
}
