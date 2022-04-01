package org.distantnetwork.powermagecore.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Enums.Rarity;

import java.io.File;
import java.util.*;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.*;
import static org.distantnetwork.powermagecore.PowermageCore.getInstance;

public class WeaponItem extends Item {
    int hitDamage;
    Rarity rarity;
    int price;
    String[] description;
    boolean storeItem;

    public WeaponItem(Material material, int amount, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, String[] lore, String name, int damage, boolean unbreakable, int hitDamage, Rarity rarity, int price, String[] description, boolean storeItem) {
        super(material, amount, itemFlags, enchantmentLevels, lore, name, damage, unbreakable);
        this.hitDamage = hitDamage;
        this.rarity = rarity;
        this.price = price;
        this.description = description;
        this.storeItem = storeItem;
    }

    public WeaponItem(Material material, int amount, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, List<String> lore, String name, int damage, boolean unbreakable, int hitDamage, Rarity rarity, int price, List<String> description, boolean storeItem) {
        super(material, amount, itemFlags, enchantmentLevels, lore, name, damage, unbreakable);
        this.hitDamage = hitDamage;
        this.rarity = rarity;
        this.price = price;
        this.description = description.toArray(new String[0]);
        this.storeItem = storeItem;
    }

    public WeaponItem(Material material, int amount, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, String[] lore, String name, int damage, boolean unbreakable, int hitDamage, Rarity rarity, int price, List<String> description, boolean storeItem) {
        super(material, amount, itemFlags, enchantmentLevels, lore, name, damage, unbreakable);
        this.hitDamage = hitDamage;
        this.rarity = rarity;
        this.price = price;
        this.description = description.toArray(new String[0]);
        this.storeItem = storeItem;
    }

    public WeaponItem(Material material, int amount, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, List<String> lore, String name, int damage, boolean unbreakable, int hitDamage, Rarity rarity, int price, String[] description, boolean storeItem) {
        super(material, amount, itemFlags, enchantmentLevels, lore, name, damage, unbreakable);
        this.hitDamage = hitDamage;
        this.rarity = rarity;
        this.price = price;
        this.description = description;
        this.storeItem = storeItem;
    }

    public WeaponItem() {
        super();
        this.hitDamage = 0;
        this.rarity = Rarity.COMMON;
        this.price = 0;
        this.description = null;
        this.storeItem = false;
    }

    public WeaponItem(ItemStack item) {
        super(item);
        this.hitDamage = 0;
        this.rarity = Rarity.COMMON;
        this.price = 0;
        this.description = null;
        this.storeItem = false;
    }

    public WeaponItem(File filepath) {
        super(filepath);
        File file = getFileFile(filepath);
        if (file == null) return;
        FileConfiguration config = getConfig(file);
        if (config == null) return;
        this.hitDamage = config.getInt("hitDamage");
        String rarit = config.getString("rarity");
        if (rarit == null) rarit = Rarity.COMMON.name();
        this.rarity = Rarity.valueOf(rarit);
        this.price = config.getInt("price");
        this.description = config.getStringList("description").toArray(new String[0]);
        this.storeItem = config.getBoolean("storeItem");
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

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public boolean isStoreItem() {
        return storeItem;
    }

    public void setStoreItem(boolean storeItem) {
        this.storeItem = storeItem;
    }

    @Override
    public FileConfiguration save(String path) {
        FileConfiguration config = super.save(path);
        HashMap<String, Object> map = new HashMap<>();
        map.put("hitDamage", hitDamage);
        map.put("rarity", rarity.name());
        map.put("price", price);
        map.put("description", description);
        map.put("storeItem", storeItem);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            config.set(entry.getKey(), entry.getValue());
        }
        return config;
    }

    @Override
    public void give(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(this.getMaterial(), this.getAmount());
        if (this.getName() != null) itemBuilder.setName(this.getFormattedName());
        if (this.getLore() != null) itemBuilder.setLore(this.getFormattedLore());
        if (this.getRarity() != null) {
            StringBuilder s = new StringBuilder();
            for (ChatColor colour : this.getRarity().getColor()) s.append(colour);
            itemBuilder.addLoreLine(s.toString() + this.getRarity().name() + " WEAPON");
        }
        if (this.getDamage() > 0) itemBuilder.setDurability((short) this.getDamage());
        if (this.isUnbreakable()) itemBuilder.setUnbreakable(true);
        if (this.getItemFlags() != null && this.getItemFlags().size() > 0)
            itemBuilder.addItemFlags(this.getItemFlags());
        if (this.getEnchantmentLevels() != null && this.getEnchantmentLevels().size() > 0) this.getEnchantmentLevels().forEach(itemBuilder::setEnchantment);
        player.getInventory().addItem(itemBuilder.build());
    }

    @Override
    public ItemStack getItem() {
        ItemBuilder itemBuilder = new ItemBuilder(this.getMaterial(), this.getAmount());
        if (this.getName() != null) itemBuilder.setName(this.getFormattedName());
        if (this.getLore() != null) itemBuilder.setLore(this.getFormattedLore());
        if (this.getRarity() != null) {
            StringBuilder s = new StringBuilder();
            for (ChatColor colour : this.getRarity().getColor()) s.append(colour);
            itemBuilder.addLoreLine(s.toString() + this.getRarity().name() + " WEAPON");
        }
        if (this.description != null) {
            itemBuilder.addLoreLine("");
            for (String s : this.description) itemBuilder.addLoreLine(ChatColor.translateAlternateColorCodes('&', s));
        }
        if (this.storeItem) itemBuilder.addLoreLine(String.format("%sPrice: %d souls", ChatColor.AQUA, this.getPrice()));
        if (this.getDamage() > 0) itemBuilder.setDurability((short) this.getDamage());
        if (this.isUnbreakable()) itemBuilder.setUnbreakable(true);
        if (this.getItemFlags() != null && this.getItemFlags().size() > 0)
            itemBuilder.addItemFlags(this.getItemFlags());
        if (this.getEnchantmentLevels().size() > 0)
            this.getEnchantmentLevels().forEach(itemBuilder::setEnchantment);
        return itemBuilder.build();
    }
}
