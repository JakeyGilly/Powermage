package org.distantnetwork.powermagecore.commands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.distantnetwork.powermagecore.utils.Builders.InventoryBuilder;
import org.distantnetwork.powermagecore.utils.Builders.ItemBuilder;

public class UpgradeGUI extends InventoryBuilder {

    public UpgradeGUI(Player p) {
        super(54, String.format("%sUpgrade Stats", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName(" ").toItem());
        setItem(46, new ItemBuilder(Material.IRON_SWORD).setName(String.format("%sStrength", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sSouls for next upgrade: 0", ChatColor.GOLD)).toItem(), player -> {
            // TODO: Implement
        });
        setItem(48, new ItemBuilder(Material.REDSTONE).setName(String.format("%sHeath", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sSouls for next upgrade: 0", ChatColor.GOLD)).toItem(), player -> {
            // TODO: Implement
        });
        setItem(50, new ItemBuilder(Material.BLUE_DYE).setName(String.format("%sMana", ChatColor.AQUA)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sSouls for next upgrade: 0", ChatColor.GOLD)).toItem(), player -> {
            // TODO: Implement
        });
        setItem(52, new ItemBuilder(Material.SUGAR).setName(String.format("%sSpeed", ChatColor.WHITE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sSouls for next upgrade: 0", ChatColor.GOLD)).toItem(), player -> {
            // TODO: Implement
        });

    }
}
