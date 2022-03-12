package org.distantnetwork.powermagecore.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

// CREDIT TO NonameSL for this Class
/**
 * Easily create items, without messing your hands.
 * <i>Note that if you do use this in one of your projects, leave this notice.</i>
 * <i>Please do credit me if you do use this in one of your projects.</i>
 * @author NonameSL
 */
public class ItemBuilder {
    private final ItemStack item;
    /**
     * Create a new ItemBuilder from scratch.
     * @param material The material to create the ItemBuilder with.
     */
    public ItemBuilder(Material material){
        this(material, 1);
    }
    /**
     * Create a new ItemBuilder over an existing item.
     * @param item The item to create the ItemBuilder over.
     */
    public ItemBuilder(ItemStack item){
        this.item=item;
    }
    /**
     * Create a new ItemBuilder from scratch.
     * @param material The material of the item.
     * @param amount The amount of the item.
     */
    public ItemBuilder(Material material, int amount){
        item = new ItemStack(material, amount);
    }
    /**
     * Create a new ItemBuilder from scratch.
     * @param material The material of the item.
     * @param amount The amount of the item.
     * @param durability The durability of the item.
     */
    public ItemBuilder(Material material, int amount, byte durability){
        item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta instanceof Damageable) {
            Damageable dmg = (Damageable) item.getItemMeta();
            if (dmg.hasDamage()) dmg.setDamage(durability);
            item.setItemMeta(dmg);
        }
    }
    /**
     * Clone the ItemBuilder into a new one.
     * @return The cloned instance.
     */
    public ItemBuilder clone(){
        return new ItemBuilder(item);
    }
    /**
     * Change the durability of the item.
     * @param durability The durability to set it to.
     */
    public ItemBuilder setDurability(short durability){
        ItemMeta meta = item.getItemMeta();
        if (meta instanceof Damageable) {
            Damageable dmg = (Damageable) item.getItemMeta();
            if (dmg.hasDamage()) dmg.setDamage(durability);
            item.setItemMeta(dmg);
        }
        return this;
    }
    /**
     * Set the displayname of the item.
     * @param name The name to change it to.
     */
    public ItemBuilder setName(String name){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return this;
    }
    /**
     * Add an unsafe enchantment.
     * @param ench The enchantment to add.
     * @param level The level to put the enchant on.
     */
    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level){
        item.addUnsafeEnchantment(ench, level);
        return this;
    }
    /**
     * Remove a certain enchant from the item.
     * @param ench The enchantment to remove
     */
    public ItemBuilder removeEnchantment(Enchantment ench){
        item.removeEnchantment(ench);
        return this;
    }
    /**
     * Set the skull owner for the item. Works on skulls only.
     * @param owner The name of the skull's owner.
     */
    public ItemBuilder setSkullOwner(String owner){
        try {
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwner(owner);
            item.setItemMeta(meta);
        } catch(ClassCastException e) {
            e.printStackTrace();
        }
        return this;
    }
    /**
     * Add an enchant to the item.
     * @param ench The enchant to add
     * @param level The level
     */
    public ItemBuilder addEnchant(Enchantment ench, int level){
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(ench, level, true);
        item.setItemMeta(meta);
        return this;
    }
    /**
     * Add multiple enchants at once.
     * @param enchantments The enchants to add.
     */
    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments){
        item.addEnchantments(enchantments);
        return this;
    }
    /**
     * Sets infinity durability on the item by setting unbreakable to true.
     */
    public ItemBuilder setInfinityDurability(){
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        return this;
    }
    /**
     * Sets the item to unbreakable.
     */
    public ItemBuilder setUnbreakable(){
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        return this;
    }
    /**
     * Re-sets the lore.
     * @param lore The lore to set it to.
     */
    public ItemBuilder setLore(String... lore){
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return this;
    }
    /**
     * Re-sets the lore.
     * @param lore The lore to set it to.
     */
    public ItemBuilder setLore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }
    /**
     * Remove a lore line.
     * @param line The lore to remove.
     */
    public ItemBuilder removeLoreLine(String line){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>(meta.getLore());
        if(!lore.contains(line))return this;
        lore.remove(line);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }
    /**
     * Remove a lore line.
     * @param index The index of the lore line to remove.
     */
    public ItemBuilder removeLoreLine(int index){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>(meta.getLore());
        if(index<0||index>lore.size())return this;
        lore.remove(index);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }
    /**
     * Add a lore line.
     * @param line The lore line to add.
     */
    public ItemBuilder addLoreLine(String line){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        if(meta.hasLore())lore = new ArrayList<>(meta.getLore());
        lore.add(line);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }
    /**
     * Add a lore line.
     * @param line The lore line to add.
     * @param pos The index of where to put it.
     */
    public ItemBuilder addLoreLine(String line, int pos){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>(meta.getLore());
        lore.set(pos, line);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }
    /**
     * Set Item Flags.
     * @param flags The flags to set.
     */
    public ItemBuilder addItemFlags(ItemFlag... flags){
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(flags);
        item.setItemMeta(meta);
        return this;
    }
    public ItemBuilder addItemFlags(List<ItemFlag> flags){
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(flags.toArray(new ItemFlag[flags.size()]));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setRarity(Rarity rarity){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(rarity.getColor(rarity)+rarity.name());
        return this;
    }
    /**
     * Retrieves the item from the ItemBuilder.
     * @return The item created/modified by the ItemBuilder instance.
     */
    public ItemStack toItem(){
        return item;
    }
}
