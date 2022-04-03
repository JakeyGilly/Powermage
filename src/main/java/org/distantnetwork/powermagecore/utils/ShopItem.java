package org.distantnetwork.powermagecore.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Enums.Rarity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopItem extends Item {
    int price;
    List<String> description;
    boolean storeItem;
    Rarity rarity;
    static List<ShopItem> shopItems = new ArrayList<>();

    public ShopItem(Material material, int amount, String name, List<String> lore, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, int durability, boolean unbreakable, Rarity rarity, int price, List<String> description, boolean storeItem) {
        super(material, amount, name, lore, itemFlags, enchantmentLevels, durability, unbreakable);
        this.price = price;
        this.description = description;
        this.storeItem = storeItem;
        this.rarity = rarity;
        shopItems.add(this);
    }

    public int getPrice() {
        return price;
    }

    public List<String> getDescription() {
        return description;
    }

    public boolean isStoreItem() {
        return storeItem;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setStoreItem(boolean storeItem) {
        this.storeItem = storeItem;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public void give(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(this.getMaterial(), this.getAmount());
        if (this.getName() != null) itemBuilder.setName(this.getFormattedName());
        if (this.getLore() != null) itemBuilder.setLore(this.getFormattedLore());
        if (this.getRarity() != null) {
            StringBuilder s = new StringBuilder();
            for (ChatColor colour : this.getRarity().getColor()) s.append(colour);
            itemBuilder.addLoreLine(s.toString() + this.getRarity().name() + " ITEM");
        }
        if (this.getDurability() > 0) itemBuilder.setDurability((short) this.getDurability());
        if (this.isUnbreakable()) itemBuilder.setUnbreakable(true);
        if (this.getItemFlags() != null && this.getItemFlags().size() > 0)
            itemBuilder.addItemFlags(this.getItemFlags());
        if (this.getEnchantmentLevels() != null && this.getEnchantmentLevels().size() > 0) this.getEnchantmentLevels().forEach(itemBuilder::setEnchantment);
        player.getInventory().addItem(itemBuilder.toItem());
    }

    @Override
    public ItemStack getItem() {
        ItemBuilder itemBuilder = new ItemBuilder(this.getMaterial(), this.getAmount());
        if (this.getName() != null) itemBuilder.setName(this.getFormattedName());
        if (this.getLore() != null) itemBuilder.setLore(this.getFormattedLore());
        if (this.getRarity() != null) {
            StringBuilder s = new StringBuilder();
            for (ChatColor colour : this.getRarity().getColor()) s.append(colour);
            itemBuilder.addLoreLine(s.toString() + this.getRarity().name() + " ITEM");
        }
        if (this.description != null) {
            itemBuilder.addLoreLine("");
            for (String s : this.description) itemBuilder.addLoreLine(ChatColor.translateAlternateColorCodes('&', s));
        }
        if (this.storeItem) itemBuilder.addLoreLine(String.format("%sPrice: %d coins", ChatColor.AQUA, this.getPrice()));
        if (this.getDurability() > 0) itemBuilder.setDurability((short) this.getDurability());
        if (this.isUnbreakable()) itemBuilder.setUnbreakable(true);
        if (this.getItemFlags() != null && this.getItemFlags().size() > 0)
            itemBuilder.addItemFlags(this.getItemFlags());
        if (this.getEnchantmentLevels().size() > 0)
            this.getEnchantmentLevels().forEach(itemBuilder::setEnchantment);
        return itemBuilder.build();
    }

    public static void setShopItems(List<ShopItem> shopItems) {
        ShopItem.shopItems = shopItems;
    }

    public static List<ShopItem> getShopItems() {
        return ShopItem.shopItems;
    }
}
