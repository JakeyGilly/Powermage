package org.distantnetwork.powermagecore.utils.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Rarity;

import java.util.*;

import static org.distantnetwork.powermagecore.PowermageCore.getPlugin;

public abstract class WeaponItem extends Item {
    int damage;
    Rarity rarity;
    int price;
    List<String> description;
    boolean storeItem;
    static List<WeaponItem> weapons = new ArrayList<>();

    public WeaponItem(Material material, int amount, String name, List<String> lore, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, int durability, boolean unbreakable, Rarity rarity, int damage, int price, List<String> description, boolean storeItem) {
        super(material, amount, name, lore, itemFlags, enchantmentLevels, durability, unbreakable);
        this.rarity = rarity;
        this.damage = damage;
        this.price = price;
        this.description = description;
        this.storeItem = storeItem;
        weapons.add(this);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int hitDamage) {
        this.damage = hitDamage;
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

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public boolean isStoreItem() {
        return storeItem;
    }

    public void setStoreItem(boolean storeItem) {
        this.storeItem = storeItem;
    }

    public abstract void onEquip(Player player);
    public abstract void onUnequip(Player player);
    public abstract void clickEntity(Player player, Entity target, boolean shifting);
    public abstract void punchEntity(Player player, Entity target, double damage, boolean shifting);
    public abstract void leftClickOnBlock(Player player, Block block, boolean shifting);
    public abstract void leftClickOnAir(Player player, Block block, boolean shifting);
    public abstract void rightClickOnBlock(Player player, Block block, boolean shifting);
    public abstract void rightClickOnAir(Player player, Block block, boolean shifting);
    public abstract void onBlockBreak(Player player, Block block, boolean shifting);
    public abstract void onBlockPlace(Player player, Block block, boolean shifting);
    public abstract void onDrop(Player player);
    public abstract void onPickup(Player player);

    @Override
    public void give(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(this.getMaterial(), this.getAmount());
        if (this.getName() != null) itemBuilder.setName(this.getFormattedName());
        if (this.getLore() != null) itemBuilder.setLore(this.getFormattedLore());
        if (this.getRarity() != null) {
            StringBuilder s = new StringBuilder();
            for (ChatColor colour : this.getRarity().getColor()) s.append(colour);
            itemBuilder.addLoreLine(String.format("%s%s WEAPON", s, this.getRarity().name()));
        }
        if (this.getDurability() > 0) itemBuilder.setDurability((short) this.getDurability());
        if (this.isUnbreakable()) itemBuilder.setUnbreakable(true);
        if (this.getItemFlags() != null && this.getItemFlags().size() > 0)
            itemBuilder.addItemFlags(this.getItemFlags());
        if (this.getEnchantmentLevels() != null && this.getEnchantmentLevels().size() > 0) this.getEnchantmentLevels().forEach(itemBuilder::setEnchantment);
        ItemStack item = itemBuilder.build();
        NamespacedKey key = new NamespacedKey(getPlugin(), "weaponItem");
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return;
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, itemMeta.getDisplayName());
        item.setItemMeta(itemMeta);
        player.getInventory().addItem(item);
    }

    @Override
    public ItemStack getItem() {
        super.getItem();
        ItemBuilder itemBuilder = new ItemBuilder(this.getMaterial(), this.getAmount());
        if (this.getName() != null) itemBuilder.setName(this.getFormattedName());
        if (this.getLore() != null) itemBuilder.setLore(this.getFormattedLore());
        if (this.getRarity() != null) {
            StringBuilder s = new StringBuilder();
            for (ChatColor colour : this.getRarity().getColor()) s.append(colour);
            itemBuilder.addLoreLine(String.format("%s%s WEAPON", s, this.getRarity().name()));
        }
        if (this.description != null) {
            itemBuilder.addLoreLine("");
            for (String s : this.description) itemBuilder.addLoreLine(ChatColor.translateAlternateColorCodes('&', s));
        }
        if (this.storeItem) itemBuilder.addLoreLine(String.format("%sPrice: %d souls", ChatColor.AQUA, this.getPrice()));
        if (this.getDurability() > 0) itemBuilder.setDurability((short) this.getDurability());
        if (this.isUnbreakable()) itemBuilder.setUnbreakable(true);
        if (this.getItemFlags() != null && this.getItemFlags().size() > 0)
            itemBuilder.addItemFlags(this.getItemFlags());
        if (this.getEnchantmentLevels().size() > 0)
            this.getEnchantmentLevels().forEach(itemBuilder::setEnchantment);
        ItemStack item = itemBuilder.build();
        NamespacedKey key = new NamespacedKey(getPlugin(), "weaponItem");
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return null;
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, itemMeta.getDisplayName());
        item.setItemMeta(itemMeta);
        return itemBuilder.build();
    }

    public static WeaponItem getWeaponItem(ItemStack item) {
        NamespacedKey key = new NamespacedKey(getPlugin(), "weaponItem");
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return null;
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if (!container.has(key, PersistentDataType.STRING)) return null;
        String name = container.get(key, PersistentDataType.STRING);
        for (WeaponItem weaponItem : weapons) {
            if (weaponItem.getFormattedName().equals(name)) return weaponItem;
        }
        return null;
    }

    public static boolean isWeaponItem(ItemStack item) {
        NamespacedKey k = new NamespacedKey(getPlugin(), "weaponItem");
        if (item == null) return false;
        if (!item.hasItemMeta()) return false;
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return false;
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.has(k, PersistentDataType.STRING);
    }

    public static void setWeapons(List<WeaponItem> weapons) {
        WeaponItem.weapons = weapons;
    }

    public static List<WeaponItem> getWeapons() {
        return WeaponItem.weapons;
    }
}
