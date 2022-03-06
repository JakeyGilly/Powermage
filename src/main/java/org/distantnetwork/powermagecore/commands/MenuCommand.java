package org.distantnetwork.powermagecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.distantnetwork.powermagecore.Classes;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.ItemBuilder;

import java.util.*;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if (player.hasPermission("powermage.menu")) player.openInventory(generateMenu(player));
        return true;
    }

    public static Inventory generateMenu(Player player) {
        ItemStack item;
        Inventory inv = Bukkit.createInventory(null, 45, String.format("%sPowermage", ChatColor.RED));
        for (int i = 0; i < 44; i++) inv.setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").setItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        for (int i = 19; i < 25; i++) inv.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").setItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        item = new ItemBuilder(Material.PLAYER_HEAD).setName(String.format("%sYour Stats", ChatColor.GREEN)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES).setSkullOwner(player.getName())
                .setLore(String.format("%sWarrior Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.WARRIOR)), String.format("%sTank Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.TANK)), String.format("%sArcher Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.ARCHER)), String.format("%sWizard Level: %s0%s", ChatColor.DARK_AQUA, ChatColor.GOLD, PowermageCore.classesEmojis.get(Classes.WIZARD)), String.format("%sBalance: %s0 Coins", ChatColor.GRAY, ChatColor.GOLD), String.format("%sSouls: %s0 Souls", ChatColor.GRAY, ChatColor.AQUA), String.format("%sKills: %s0", ChatColor.GRAY, ChatColor.RED), String.format("%sDeaths: %s0", ChatColor.GRAY, ChatColor.RED))
                .toItem();
        inv.setItem(13, item);
        item = new ItemBuilder(Material.SUGAR).setName(String.format("%sUpgrade Stats", ChatColor.RED)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sBecome stronger by upgrading", ChatColor.GRAY), String.format("%syour stats using souls.", ChatColor.GRAY), " ", String.format("%sClick to view upgrades", ChatColor.GOLD))
                .toItem();
        inv.setItem(21, item);
        item = new ItemBuilder(Material.GOLD_INGOT).setName(String.format("%sItem Shop", ChatColor.GOLD)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sBuy powerful weapons, armor", ChatColor.GRAY), String.format("%sand gadgets for a price", ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD))
                .toItem();
        inv.setItem(22, item);
        item = new ItemBuilder(Material.NETHER_STAR).setName(String.format("%sSwitch Classes", ChatColor.RED)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sDon't like your current class?", ChatColor.GRAY), String.format("%sSwitch to a another", ChatColor.GRAY), String.format("%sPowermage class!", ChatColor.GRAY), " ", String.format("%sClick to view", ChatColor.GOLD))
                .toItem();
        inv.setItem(23, item);
        ItemBuilder itembuild = new ItemBuilder(Material.BEACON).setName(String.format("%sSoul Shop", ChatColor.RED)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if (PowermageCore.playerLevels.get(player.getUniqueId()) != null && Integer.getInteger(PowermageCore.playerLevels.get(player.getUniqueId()).get(PowermageCore.playerClasses.get(player.getUniqueId())).values().toArray()[0].toString()) > 5) {
            item = itembuild.setLore(String.format("%sGot too many %sSouls%s?", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), String.format("%sSpend your %sSouls %sto get special items", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD)).toItem();
        } else {
            item = itembuild.setLore(String.format("%sGot too many %sSouls%s?", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), String.format("%sSpend your %sSouls %sto get special items", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), String.format("%sUnlocked at level 5 of any class", ChatColor.DARK_RED), " ", String.format("%sClick to view the shop", ChatColor.GOLD)).toItem();
        }
        inv.setItem(24, item);
        item = new ItemBuilder(Material.GOLD_NUGGET).setName(String.format("%sRanks Shop", ChatColor.GOLD)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sBuy ranks to get access to", ChatColor.GRAY), String.format("%sspecial features on the server", ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD))
                .toItem();
        inv.setItem(39, item);
        inv.setItem(40, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose", ChatColor.RED)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        inv.setItem(41, new ItemBuilder(Material.LIGHT_BLUE_DYE).setName(String.format("%sDiscord", ChatColor.BLUE)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1).toItem());
        return inv;
    }
}
