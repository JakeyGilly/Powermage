package org.distantnetwork.powermagecore.utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.*;

public class Item {
    private Material material;
    private int amount;
    private List<ItemFlag>itemFlags;
    private Map<Enchantment, Integer> enchantmentLevels;
    private String[] lore;
    private String name;
    private int damage;
    private boolean unbreakable;

    public Item(Material material, int amount, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, String[] lore, String name, int damage, boolean unbreakable) {
        this.material = material;
        this.amount = amount;
        this.itemFlags = itemFlags;
        this.enchantmentLevels = enchantmentLevels;
        this.lore = lore;
        this.name = name;
        this.damage = damage;
        this.unbreakable = unbreakable;
    }

    public Item() {
        this.material = null;
        this.amount = 0;
        this.itemFlags = null;
        this.enchantmentLevels = null;
        this.lore = null;
        this.name = null;
        this.damage = 0;
        this.unbreakable = false;
    }

    public Item(@NotNull File filepath) {
        File file = getFileFile(filepath);
        if (file == null) return;
        FileConfiguration fileConfiguration = getConfig(file);
        if (fileConfiguration == null) return;
        this.material = Material.valueOf(fileConfiguration.getString("material"));
        this.amount = fileConfiguration.getInt("amount");
        this.enchantmentLevels = new HashMap<Enchantment, Integer>();
        for (String enchantment : fileConfiguration.getConfigurationSection("enchantments").getKeys(false)) {
            this.enchantmentLevels.put(Enchantment.getByKey(NamespacedKey.minecraft(enchantment)), fileConfiguration.getInt("enchantments." + enchantment));
        }
        this.lore = fileConfiguration.getStringList("lore").toArray(new String[0]);
        this.name = fileConfiguration.getString("name");
        this.damage = fileConfiguration.getInt("damage");
        this.unbreakable = fileConfiguration.getBoolean("unbreakable");
    }

    public Item(@NotNull ItemStack itemStack) {
        this.material = itemStack.getType();
        this.amount = itemStack.getAmount();
        this.itemFlags = new ArrayList<ItemFlag>() {{
            if (itemStack.getItemMeta() != null) addAll(itemStack.getItemMeta().getItemFlags());
        }};
        this.enchantmentLevels = itemStack.getEnchantments();
        this.lore = itemStack.getItemMeta().getLore().toArray(new String[0]);
        this.name = itemStack.getItemMeta().getDisplayName();
        this.damage = ((Damageable) itemStack.getItemMeta()).getDamage();
        this.unbreakable = itemStack.getItemMeta().isUnbreakable();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<ItemFlag> getItemFlags() {
        return itemFlags;
    }

    public void setItemFlags(List<ItemFlag> itemFlags) {
        this.itemFlags = itemFlags;
    }

    public Map<Enchantment, Integer> getEnchantmentLevels() {
        return enchantmentLevels;
    }

    public void setEnchantmentLevels(Map<Enchantment, Integer> enchantmentLevels) {
        this.enchantmentLevels = enchantmentLevels;
    }

    public String[] getLore() {
        return lore;
    }

    public void setLore(String[] lore) {
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }

    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    public void save(String path) {
        Map<String, Object> itemMap = new HashMap<String, Object>();
        itemMap.put("material", this.material.name());
        if (this.amount > 1) itemMap.put("amount", this.amount);
        if (this.enchantmentLevels.size() > 0) enchantmentLevels.forEach((enchantment, level) -> itemMap.put("enchantments." + enchantment.getKey().getKey(), level));
        if (this.name != null) itemMap.put("name", this.name);
        if (this.lore != null) itemMap.put("lore", this.lore);
        if (this.damage > 0) itemMap.put("damage", this.damage);
        if (this.unbreakable) itemMap.put("unbreakable", true);
        if (this.itemFlags.size() > 0) itemMap.put("itemFlags", this.itemFlags);
        File file = getFileFile(path);
        if (file == null) return;
        FileConfiguration config = getConfig(file);
        if (config == null) return;
        for (String key : itemMap.keySet()) {
            config.set(key, itemMap.get(key));
        }
    }

    public static @Nullable Item getItem(@NotNull String filename) {
        File file = getFileFile(filename);
        if (file == null) return null;
        FileConfiguration config = getConfig(file);
        if (config == null) return null;
        if (!config.contains("material")) return null;
        Item item = new Item();
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
        return item;
    }

    public static @Nullable Item getItem(@NotNull File filepath) {
        File file = getFileFile(filepath);
        if (file == null) return null;
        FileConfiguration config = getConfig(file);
        if (config == null) return null;
        if (!config.contains("material")) return null;
        Item item = new Item();
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
        return item;
    }

    public void give(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(this.material, this.amount);
        if (this.name != null) itemBuilder.setName(this.name);
        if (this.lore != null) itemBuilder.setLore(this.lore);
        if (this.damage > 0) itemBuilder.setDurability((short) this.damage);
        if (this.unbreakable) itemBuilder.setUnbreakable(true);
        if (this.itemFlags.size() > 0) itemBuilder.addItemFlags(this.itemFlags);
        if (this.enchantmentLevels.size() > 0) this.enchantmentLevels.forEach(itemBuilder::setEnchantment);
        player.getInventory().addItem(itemBuilder.build());
    }

    public ItemStack getItem() {
        ItemBuilder itemBuilder = new ItemBuilder(this.material, this.amount);
        if (this.name != null) itemBuilder.setName(this.name);
        if (this.lore != null) itemBuilder.setLore(this.lore);
        if (this.damage > 0) itemBuilder.setDurability((short) this.damage);
        if (this.unbreakable) itemBuilder.setUnbreakable(true);
        if (this.itemFlags.size() > 0) itemBuilder.addItemFlags(this.itemFlags);
        if (this.enchantmentLevels.size() > 0) this.enchantmentLevels.forEach(itemBuilder::setEnchantment);
        return itemBuilder.build();
    }
}