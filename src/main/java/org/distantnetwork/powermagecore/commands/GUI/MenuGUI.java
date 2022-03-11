package org.distantnetwork.powermagecore.commands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.distantnetwork.powermagecore.Classes;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.InventoryBuilder;
import org.distantnetwork.powermagecore.utils.ItemBuilder;

import java.util.Arrays;

public class MenuGUI extends InventoryBuilder {
    public MenuGUI() {
        super(45, String.format("%sPowermage", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").setItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        ItemStack item;
        item = new ItemBuilder(Material.PLAYER_HEAD).setName(String.format("%sYour Stats", ChatColor.GREEN)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sWarrior Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.WARRIOR)), String.format("%sTank Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.TANK)), String.format("%sArcher Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.ARCHER)), String.format("%sWizard Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.WIZARD)), String.format("%sBalance: %s0 Coins", ChatColor.GRAY, ChatColor.GOLD), String.format("%sSouls: %s0 Souls", ChatColor.GRAY, ChatColor.AQUA), String.format("%sKills: %s0", ChatColor.GRAY, ChatColor.RED), String.format("%sDeaths: %s0", ChatColor.GRAY, ChatColor.RED))
                .toItem();
        ItemStack finalItem = item;
        setItem(13, item, null, player -> {
            SkullMeta meta = (SkullMeta) finalItem.getItemMeta();
            meta.setOwner(player.getName());
            player.getInventory().getItem(13).setItemMeta(meta);
        });
        item = new ItemBuilder(Material.SUGAR).setName(String.format("%sUpgrade Stats", ChatColor.RED)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sBecome stronger by upgrading", ChatColor.GRAY), String.format("%syour stats using souls.", ChatColor.GRAY), " ", String.format("%sClick to view upgrades", ChatColor.GOLD))
                .toItem();
        setItem(21, item); // , player -> new ShopGUI().open(player));
        item = new ItemBuilder(Material.GOLD_INGOT).setName(String.format("%sItem Shop", ChatColor.GOLD)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sBuy powerful weapons, armor", ChatColor.GRAY), String.format("%sand gadgets for a price", ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD))
                .toItem();
        setItem(22, item); // , player -> new ShopGUI().open(player));
        item = new ItemBuilder(Material.NETHER_STAR).setName(String.format("%sSwitch Classes", ChatColor.RED)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sDon't like your current class?", ChatColor.GRAY), String.format("%sSwitch to a another", ChatColor.GRAY), String.format("%sPowermage class!", ChatColor.GRAY), " ", String.format("%sClick to view", ChatColor.GOLD))
                .toItem();
        setItem(23, item, player -> new ClassGUI().open(player));
        ItemStack itembuild = new ItemBuilder(Material.BEACON).setName(String.format("%sSoul Shop", ChatColor.RED)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem();
        setItem(24, itembuild, player -> new SoulShopGUI().open(player), player -> {
            ItemMeta meta = itembuild.getItemMeta();
            if (PowermageCore.playerLevels.get(player.getUniqueId()) != null && Integer.getInteger(PowermageCore.playerLevels.get(player.getUniqueId()).get(PowermageCore.playerClasses.get(player.getUniqueId())).values().toArray()[0].toString()) > 5) {
                meta.setLore(Arrays.asList(String.format("%sGot too many %sSouls%s?", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), String.format("%sSpend your %sSouls %sto get special items", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD)));
            } else {
                meta.setLore(Arrays.asList(String.format("%sGot too many %sSouls%s?", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), String.format("%sSpend your %sSouls %sto get special items", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), String.format("%sUnlocked at level 5 of any class", ChatColor.DARK_RED), " ", String.format("%sClick to view the shop", ChatColor.GOLD)));
            }
            player.getInventory().getItem(24).setItemMeta(meta);
        });
        item = new ItemBuilder(Material.GOLD_NUGGET).setName(String.format("%sRanks Shop", ChatColor.GOLD)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sBuy ranks to get access to", ChatColor.GRAY), String.format("%sspecial features on the server", ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD))
                .toItem();
        setItem(39, item, player -> {
            player.closeInventory();
            player.performCommand("store");
        });
        setItem(40, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose", ChatColor.RED)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> player.closeInventory());
        setItem(41, new ItemBuilder(Material.LIGHT_BLUE_DYE).setName(String.format("%sDiscord", ChatColor.BLUE)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1).toItem(), player -> {
            player.closeInventory();
            player.performCommand("discord");
        });
    }
}
