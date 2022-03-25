package org.distantnetwork.powermagecore.utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Enums.Rarity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.*;

public class WeaponItem extends Item {
    int hitDamage;
    Rarity rarity;
    int price;
    int level;

    public WeaponItem(Material material, int amount, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, String[] lore, String name, int damage, boolean unbreakable, int hitDamage, Rarity rarity) {
        super(material, amount, itemFlags, enchantmentLevels, lore, name, damage, unbreakable);
        this.hitDamage = hitDamage;
        this.rarity = rarity;
        this.level = 0;
        this.price = 0;
    }

    public WeaponItem() {
        super();
        this.hitDamage = 0;
        this.rarity = Rarity.COMMON;
        this.level = 0;
        this.price = 0;
    }

    public WeaponItem(ItemStack item) {
        super(item);
        this.hitDamage = 0;
        this.rarity = Rarity.COMMON;
        this.level = 0;
        this.price = 0;
    }

    public WeaponItem(File filepath) {
        super(filepath);
        File file = getFileFile(filepath);
        if (file == null) return;
        FileConfiguration config = getConfig(file);
        if (config == null) return;
        this.hitDamage = config.getInt("hitDamage");
        this.rarity = Rarity.valueOf(config.getString("rarity"));
        this.price = config.getInt("price");
        this.level = config.getInt("level");
    }

    public int getHitDamage() {
        return hitDamage;
    }

    public void setHitDamage(int hitDamage) {
        this.hitDamage = hitDamage;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void save(String path) {
        super.save(path);
        File file = getFileFile(path);
        if (file == null) return;
        FileConfiguration config = getConfig(file);
        if (config == null) return;
        HashMap<String, Object> map = new HashMap<>();
        map.put("hitDamage", hitDamage);
        map.put("rarity", rarity.name());
        map.put("price", price);
        map.put("level", level);
        map.forEach(config::set);
    }

    public static @Nullable Item getWeaponItem(@NotNull String filename) {
        File file = getFileFile(filename);
        if (file == null) return null;
        FileConfiguration config = getConfig(file);
        if (config == null) return null;
        if (!config.contains("material")) return null;
        WeaponItem item = new WeaponItem();
        item.setMaterial(Material.valueOf(config.getString("material")));
        item.setAmount(config.contains("amount") ? config.getInt("amount") : 1);
        if (config.contains("name")) item.setName(config.getString("name"));
        if (config.contains("lore")) item.setLore(config.getStringList("lore").toArray(new String[0]));
        if (config.contains("enchantmentLevels")) config.getConfigurationSection("enchantmentLevels").getValues(false).forEach((enchantment, lvl) -> {
            item.getEnchantmentLevels().put(Enchantment.getByKey(NamespacedKey.minecraft(enchantment)), (Integer) lvl);
        });
        if (config.contains("damage")) item.setDamage(config.getInt("damage"));
        if (config.contains("unbreakable")) item.setUnbreakable(config.getBoolean("unbreakable"));
        if (config.contains("flags")) config.getStringList("flags").forEach(flag -> {
            item.getItemFlags().add(ItemFlag.valueOf(flag));
        });
        if (config.contains("hitDamage")) item.setHitDamage(config.getInt("hitDamage"));
        if (config.contains("rarity")) item.setRarity(Rarity.valueOf(config.getString("rarity")));
        return item;
    }

    public static @Nullable Item getWeaponItem(@NotNull File filepath) {
        File file = getFileFile(filepath);
        if (file == null) return null;
        FileConfiguration config = getConfig(file);
        if (config == null) return null;
        if (!config.contains("material")) return null;
        WeaponItem item = new WeaponItem();
        item.setMaterial(Material.valueOf(config.getString("material")));
        item.setAmount(config.contains("amount") ? config.getInt("amount") : 1);
        if (config.contains("name")) item.setName(config.getString("name"));
        if (config.contains("lore")) item.setLore(config.getStringList("lore").toArray(new String[0]));
        if (config.contains("enchantmentLevels")) config.getConfigurationSection("enchantmentLevels").getValues(false).forEach((enchantment, lvl) -> {
            item.getEnchantmentLevels().put(Enchantment.getByKey(NamespacedKey.minecraft(enchantment)), (Integer) lvl);
        });
        if (config.contains("damage")) item.setDamage(config.getInt("damage"));
        if (config.contains("unbreakable")) item.setUnbreakable(config.getBoolean("unbreakable"));
        if (config.contains("flags")) config.getStringList("flags").forEach(flag -> {
            item.getItemFlags().add(ItemFlag.valueOf(flag));
        });
        if (config.contains("hitDamage")) item.setHitDamage(config.getInt("hitDamage"));
        if (config.contains("rarity")) item.setRarity(Rarity.valueOf(config.getString("rarity")));
        return item;
    }
}
