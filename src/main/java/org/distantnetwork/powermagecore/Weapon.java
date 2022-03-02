package org.distantnetwork.powermagecore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class Weapon {
    private final Integer id;
    private final String name;
    private final Material material;
    private final Integer damage;
    private final String[] lore;
    private final ItemFlag[] flags;
    private final Map<Enchantment, Integer> enchantments;

    public Weapon(Integer id, String name, Material material, Map<Enchantment, Integer> enchantments, Integer damage, String[] lore, ItemFlag[] flags) {
        this.id = id;
        this.name = name;
        this.material = material;
        this.enchantments = enchantments;
        this.damage = damage;
        this.lore = lore;
        this.flags = flags;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public Integer getDamage() {
        return damage;
    }

    public String[] getLore() {
        return lore;
    }

    public ItemFlag[] getFlags() {
        return flags;
    }

    public Map<Enchantment, Integer> getEnchantmentsMap() {
        return enchantments;
    }

    public Enchantment[] getEnchantments() {
        return enchantments.keySet().toArray(new Enchantment[0]);
    }

    public Integer getEnchantmentLevel(Enchantment enchantment) {
        return enchantments.get(enchantment);
    }

    public boolean hasEnchantment(Enchantment enchantment) {
        return enchantments.containsKey(enchantment);
    }
    public boolean hasFlag(ItemFlag flag) {
        for (ItemFlag f : flags) {
            if (f == flag) {
                return true;
            }
        }
        return false;
    }

    public ItemStack toItem(Integer id, FileConfiguration file) {
        ItemStack item = new ItemStack(Material.valueOf(file.getString("weapons." + id + ".material")));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(file.getString("weapons." + id + ".name"));
        meta.setLore(file.getStringList("weapons." + id + ".lore"));
        for (Integer i = 0; i < file.getInt("weapons." + id + ".enchantments.length"); i++) {
            meta.addEnchant(Enchantment.getByKey(new NamespacedKey(Bukkit.getServer().getPluginManager().getPlugin("PowermageCore"), file.getString("weapons." + id + ".enchantment." + i))), file.getInt("weapons." + id + ".enchantments." + i + ".level"), true);
        }
        for (Integer i = 0; i < file.getInt("weapons." + id + ".flags.length"); i++) {
            meta.addItemFlags(ItemFlag.valueOf(file.getString("weapons." + id + ".flags." + i)));
        }
        meta.addItemFlags();
        item.setItemMeta(meta);
        return item;
    }
}
