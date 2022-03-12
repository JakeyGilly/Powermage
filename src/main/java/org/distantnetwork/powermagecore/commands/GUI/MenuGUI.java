package org.distantnetwork.powermagecore.commands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.Classes;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.InventoryBuilder;
import org.distantnetwork.powermagecore.utils.ItemBuilder;

public class MenuGUI extends InventoryBuilder {
    public MenuGUI(Player p) {
        super(45, String.format("%sPowermage", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        ItemStack item;
        item = new ItemBuilder(Material.PLAYER_HEAD).setName(String.format("%sYour Stats", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setSkullOwner(p.getName())
                .setLore(String.format("%sWarrior Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.WARRIOR)), String.format("%sTank Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.TANK)), String.format("%sArcher Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.ARCHER)), String.format("%sWizard Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.WIZARD)), String.format("%sBalance: %s0 Coins", ChatColor.GRAY, ChatColor.GOLD), String.format("%sSouls: %s0 Souls", ChatColor.GRAY, ChatColor.AQUA), String.format("%sKills: %s0", ChatColor.GRAY, ChatColor.RED), String.format("%sDeaths: %s0", ChatColor.GRAY, ChatColor.RED))
                .toItem();
        ItemStack finalItem = item;
        setItem(13, item);
        item = new ItemBuilder(Material.SUGAR).setName(String.format("%sUpgrade Stats", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sBecome stronger by upgrading", ChatColor.GRAY), String.format("%syour stats using souls.", ChatColor.GRAY), " ", String.format("%sClick to view upgrades", ChatColor.GOLD))
                .toItem();
        setItem(21, item); // , player -> new ShopGUI().open(player));
        item = new ItemBuilder(Material.GOLD_INGOT).setName(String.format("%sItem Shop", ChatColor.GOLD)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sBuy powerful weapons, armor", ChatColor.GRAY), String.format("%sand gadgets for a price", ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD))
                .toItem();
        setItem(22, item); // , player -> new ShopGUI().open(player));
        item = new ItemBuilder(Material.NETHER_STAR).setName(String.format("%sSwitch Classes", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sDon't like your current class?", ChatColor.GRAY), String.format("%sSwitch to a another", ChatColor.GRAY), String.format("%sPowermage class!", ChatColor.GRAY), " ", String.format("%sClick to view", ChatColor.GOLD))
                .toItem();
        setItem(23, item, player -> new ClassGUI(player).open(player));
        ItemBuilder itembuild = new ItemBuilder(Material.BEACON).setName(String.format("%sSoul Shop", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(String.format("%sGot too many %sSouls%s?", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), String.format("%sSpend your %sSouls %sto get special items", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD));
        if (!(PowermageCore.playerLevels.get(p.getUniqueId()) != null && Integer.getInteger(PowermageCore.playerLevels.get(p.getUniqueId()).get(PowermageCore.playerClasses.get(p.getUniqueId())).values().toArray()[0].toString()) > 5)) {
            itembuild.addLoreLine(String.format("%sUnlocked at level 5 of any class", ChatColor.DARK_RED),3);
        }
        setItem(24, itembuild.toItem(), player -> {
            if (PowermageCore.playerLevels.get(p.getUniqueId()) != null && Integer.getInteger(PowermageCore.playerLevels.get(p.getUniqueId()).get(PowermageCore.playerClasses.get(p.getUniqueId())).values().toArray()[0].toString()) > 5) {
                new SoulShopGUI(player).open(player);
            }
        });
        item = new ItemBuilder(Material.GOLD_NUGGET).setName(String.format("%sRanks Shop", ChatColor.GOLD)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sBuy ranks to get access to", ChatColor.GRAY), String.format("%sspecial features on the server", ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD))
                .toItem();
        setItem(39, item, player -> {
            player.closeInventory();
            player.performCommand("store");
        });
        setItem(40, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> player.closeInventory());
        setItem(41, new ItemBuilder(Material.LIGHT_BLUE_DYE).setName(String.format("%sDiscord", ChatColor.BLUE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1).toItem(), player -> {
            player.closeInventory();
            player.performCommand("discord");
        });
    }
}
