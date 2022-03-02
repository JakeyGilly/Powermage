package org.distantnetwork.powermagecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.distantnetwork.powermagecore.Classes;
import org.distantnetwork.powermagecore.PowermageCore;

import java.util.*;

public class StartCommand implements CommandExecutor, Listener {
    public static Inventory generateInventory() {
        Inventory inventory = Bukkit.createInventory(null, 27, "&c&lChoose your class!");
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        for (int i = 0; i < 26; i++) inventory.setItem(i, item);
        item = new ItemStack(Material.IRON_SWORD);
        meta = item.getItemMeta();
        meta.setDisplayName(String.format("&7[Lvl 0] &a%s Warrior", PowermageCore.classesEmojis.get(Classes.WARRIOR)));
        meta.setLore(Arrays.asList("&c❤ Health: &f100%", "&6✦ Base Speed: &f100%", "&d☄ Base Mana: &f100", " ", "&f&lClass Ability: &6&l&oCharge", "&7Gives player &a+100 &6✦ Speed&7 for &a10 seconds.", "&8Cooldown: &a30 Seconds", "&eLeft Click on your menu to activate!", " ", "&e&LCLICK TO SELECT"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        inventory.setItem(10, item);
        item = new ItemStack(Material.IRON_CHESTPLATE);
        meta = item.getItemMeta();
        meta.setDisplayName(String.format("&7[Lvl 0] &a%s Tank", PowermageCore.classesEmojis.get(Classes.TANK)));
        meta.setLore(Arrays.asList("&c❤ Health: &f200%", "&6✦ Base Speed: &f50%", "&d☄ Base Mana: &f100", " ", "&f&lClass Ability: &6&l&oTurtle Up", "&7Gives player &9Resistance 2", "&7and &9Regen 5 &7for &a10 seconds.", "&8Cooldown: &a30 Seconds", "&eLeft Click on your menu to activate!", " ", "&e&LCLICK TO SELECT"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        inventory.setItem(12, item);
        item = new ItemStack(Material.CROSSBOW);
        meta = item.getItemMeta();
        meta.setDisplayName(String.format("&7[Lvl 0] &a%s Archer", PowermageCore.classesEmojis.get(Classes.ARCHER)));
        // arrow rain as secondary ability?
        meta.setLore(Arrays.asList("&c❤ Health: &f50%", "&6✦ Base Speed: &f200%", "&d☄ Base Mana: &f100", " ", "&f&lClass Ability: &6&l&oQuickshot", "&7Rapid-fires arrows to where", "&7the player is looking at for &a3 seconds.", "&8Cooldown: &a30 Seconds", "&eLeft Click on your menu to activate!", " ", "&e&LCLICK TO SELECT"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        inventory.setItem(14, item);
        item = new ItemStack(Material.WATER);
        meta = item.getItemMeta();
        meta.setDisplayName(String.format("&7[Lvl 0] &a%s Wizard", PowermageCore.classesEmojis.get(Classes.WIZARD)));
        meta.setLore(Arrays.asList("&c❤ Health: &f75%", "&6✦ Base Speed: &f80%", "&d☄ Base Mana: &f300", " ", "&f&lClass Ability: &6&l&oHeart of Magic", "&7Gives you infinite mana", "&7for an entire &a5 seconds.", "&8Cooldown: &a1 Minute", "&eLeft Click on your menu to activate!", " ", "&e&LCLICK TO SELECT"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        inventory.setItem(16, item);
        // close
        ItemStack closeItem = new ItemStack(Material.BARRIER);
        meta = closeItem.getItemMeta();
        meta.setDisplayName("&cClose Menu");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        closeItem.setItemMeta(meta);
        inventory.setItem(22, closeItem);
        // back to main menu
        item = new ItemStack(Material.ARROW);
        meta = item.getItemMeta();
        meta.setDisplayName("&f&lBack to Menu");
        item.setItemMeta(meta);
        inventory.setItem(18, item);
        return inventory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("powermage.start")) {
                Inventory switchClassInventory = generateInventory();
                player.openInventory(switchClassInventory);
            }
        }
        return true;
    }
}
