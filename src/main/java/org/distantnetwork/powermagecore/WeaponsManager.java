package org.distantnetwork.powermagecore;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class WeaponsManager {
    private static File file;
    private static FileConfiguration weaponsFile;
    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("PowermageCore").getDataFolder(), "weapons.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        weaponsFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getFile() {
        return weaponsFile;
    }

    public static void saveFile() {
        try {
            weaponsFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        weaponsFile = YamlConfiguration.loadConfiguration(file);
    }

    public static Weapon getWeapon(Integer id) {
        String name = weaponsFile.getString(String.format("weapons.%d.name", id));
        Material material = Material.valueOf(weaponsFile.getString(String.format("weapons.%d.material", id)));
        Map<Enchantment, Integer> enchantment = new HashMap<>();
        for (int i = 0; i < weaponsFile.getInt(String.format("weapons.%d.enchantment.length", id)); i++) {
            Enchantment enchant = Enchantment.getByKey(new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("PowermageCore"), weaponsFile.getString(String.format("weapons.%d.enchantment.%d", id, i))));
            Integer level = weaponsFile.getInt(String.format("weapons.%d.enchantment.%d.level", id, i));
            enchantment.put(enchant, level);
        }
        Integer damage = weaponsFile.getInt(String.format("weapons.%d.damage", id));
        String[] lore = weaponsFile.getStringList(String.format("weapons.%d.lore", id)).toArray(new String[0]);
        ItemFlag[] flags = new ItemFlag[]{};
        for (int i = 0; i < weaponsFile.getInt(String.format("weapons.%d.flags.length", id)); i++) {
            ArrayUtils.add(flags, ItemFlag.valueOf(weaponsFile.getString(String.format("weapons.%d.flags.%d", id, i))));
        }
        return new Weapon(id, name, material, enchantment, damage, lore, flags);
    }

    public static void setWeapon(Weapon weapon) {
        Map<String, Object> weaponData = new HashMap<>();
        weaponData.put("name", weapon.getName());
        weaponData.put("material", weapon.getMaterial().toString());
        weaponData.put("damage", weapon.getDamage().toString());
        weaponData.put("lore", weapon.getLore());
        weaponData.put("flags", weapon.getFlags());
        weaponData.put("enchantment.length", weapon.getEnchantments().length);
        int i = 0;
        for (Map.Entry<Enchantment, Integer> entry : weapon.getEnchantmentsMap().entrySet()) {
            weaponData.put(String.format("enchantment.%d.enchantment", i), entry.getKey().getKey().toString());
            weaponData.put(String.format("enchantment.%d.level", i++), entry.getValue());
        }
        weaponsFile.set(String.format("weapons.%d", weapon.getId()), weaponData);
        if (weaponsFile.contains("weapons.length")) {
            weaponsFile.set("weapons.length", weaponsFile.getInt("weapons.length") + 1);
        } else {
            weaponsFile.set("weapons.length", 1);
        }
    }
}
