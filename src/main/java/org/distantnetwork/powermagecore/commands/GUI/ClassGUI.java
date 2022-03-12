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

public class ClassGUI extends InventoryBuilder {
    public ClassGUI(Player p) {
        super(27, String.format("%sChoose your class", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));
        ItemStack item;
        item = new ItemBuilder(Material.IRON_SWORD).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setName(String.format("%s[Lvl 0]%s%s Warrior", ChatColor.GRAY, PowermageCore.classesEmojis.get(Classes.WARRIOR), ChatColor.RED))
                .setLore(String.format("%s❤ Health: %s100%%", ChatColor.RED, ChatColor.RESET), String.format("%s✦ Base Speed: %s100%%", ChatColor.GOLD, ChatColor.RESET), String.format("%s☄ Base Mana: %s100", ChatColor.LIGHT_PURPLE, ChatColor.RESET), " ", String.format("%sClass Ability: %sCharge", ChatColor.BOLD, ChatColor.GOLD), String.format("%sGives player %s+100 %s✦ Speed %sfor %s10 %sseconds.", ChatColor.GRAY, ChatColor.GREEN, ChatColor.GOLD, ChatColor.GRAY, ChatColor.GREEN, ChatColor.GRAY), String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW), " ", String.format("%s%sCLICK TO SELECT", ChatColor.YELLOW, ChatColor.BOLD))
                .toItem();
        setItem(10, item, player -> {
            player.closeInventory();
            player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %sWarrior %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY));
            player.sendMessage(String.format("%s===========================\n%s%s NEW STATS: \n\n%s%s❤ Base Health: %s20\n%s✦ Base Speed: %s100%%\n%s☄ Base Mana: %s100\n%s===========================", ChatColor.RED, ChatColor.BOLD, PowermageCore.classesEmojis.get(Classes.WARRIOR), ChatColor.RESET, ChatColor.RED, ChatColor.RESET, ChatColor.GOLD, ChatColor.RESET, ChatColor.LIGHT_PURPLE, ChatColor.RESET, ChatColor.RED));
            player.setHealthScale(20);
            player.setHealth(20.0);
            player.setWalkSpeed(0.2f);
            if (PowermageCore.playerClasses.containsKey(player.getUniqueId())) {
                player.getInventory().addItem(new ItemBuilder(Material.WOODEN_SWORD).setName(String.format("%sWarrior Sword", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                        .setLore(String.format("%sDamage: %s+4", ChatColor.GRAY, ChatColor.RED), String.format("%sAttack Speed: %s1.6", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sThe starter weapon", ChatColor.GRAY), String.format("%sfor the warrior class", ChatColor.GRAY), " ", String.format("%s%sCOMMON WEAPON", ChatColor.GRAY, ChatColor.BOLD))
                        .toItem());
            }
            PowermageCore.playerClasses.put(player.getUniqueId(), Classes.WARRIOR);
        });
        item = new ItemBuilder(Material.IRON_CHESTPLATE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setName(String.format("%s[Lvl 0] %s%s Tank", ChatColor.GRAY, ChatColor.GREEN, PowermageCore.classesEmojis.get(Classes.TANK)))
                .setLore(String.format("%s❤ Health: %s200%%", ChatColor.RED, ChatColor.RESET), String.format("%s✦ Base Speed: %s50%%", ChatColor.GOLD, ChatColor.RESET), String.format("%s☄ Base Mana: %s100", ChatColor.LIGHT_PURPLE, ChatColor.RESET), " ", String.format("%sClass Ability: %sTurtle Up", ChatColor.BOLD, ChatColor.GOLD), String.format("%sGives player %sResistance 2 %sfor %s1 %sminute", ChatColor.GRAY, ChatColor.GREEN, ChatColor.GRAY, ChatColor.GREEN, ChatColor.GRAY), String.format("%sand %sRegen 5 %sfor %s10 seconds.", ChatColor.GRAY, ChatColor.GREEN, ChatColor.GRAY, ChatColor.GREEN), String.format("%sCooldown: %s3 minutes", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW), " ", String.format("%s%sCLICK TO SELECT", ChatColor.YELLOW, ChatColor.BOLD))
                .toItem();
        setItem(12, item, player -> {
            player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %sTank %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY));
            player.sendMessage(String.format("%s===========================\n%s%s NEW STATS: \n\n%s%s❤ Base Health: %s40\n%s✦ Base Speed: %s50%%\n%s☄ Base Mana: %s100\n%s===========================", ChatColor.RED, ChatColor.BOLD, PowermageCore.classesEmojis.get(Classes.TANK), ChatColor.RESET, ChatColor.RED, ChatColor.RESET, ChatColor.GOLD, ChatColor.RESET, ChatColor.LIGHT_PURPLE, ChatColor.RESET, ChatColor.RED));
            player.closeInventory();
            try {
                player.setHealthScale(40);
            } catch (IllegalArgumentException ignored) {}
            try {
                player.setHealth(40.0);
            } catch (IllegalArgumentException ignored) {}
            player.setWalkSpeed(0.1f);
            if (PowermageCore.playerClasses.containsKey(player.getUniqueId())) {
                player.getInventory().addItem(new ItemBuilder(Material.WOODEN_AXE).setName(String.format("%sTank Axe", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                        .setLore(String.format("%sDamage: %s+9", ChatColor.GRAY, ChatColor.RED), String.format("%sAttack Speed: %s0.8", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sThe starter weapon", ChatColor.GRAY), String.format("%sfor the tank class", ChatColor.GRAY), " ", String.format("%s%sCOMMON WEAPON", ChatColor.GRAY, ChatColor.BOLD))
                        .toItem());
            }
            PowermageCore.playerClasses.put(player.getUniqueId(), Classes.TANK);
        });
        item = new ItemBuilder(Material.CROSSBOW).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setName(String.format("%s[Lvl 0] %s%s Archer", ChatColor.GRAY, ChatColor.GREEN, PowermageCore.classesEmojis.get(Classes.ARCHER)))
                .setLore(String.format("%s❤ Health: %s50%%", ChatColor.RED, ChatColor.RESET), String.format("%s✦ Base Speed: %s200%%", ChatColor.GOLD, ChatColor.RESET), String.format("%s☄ Base Mana: %s100", ChatColor.LIGHT_PURPLE, ChatColor.RESET), " ", String.format("%sClass Ability: %sQuickshot", ChatColor.BOLD, ChatColor.GOLD), String.format("%sRapid-fires arrows to where", ChatColor.GRAY), String.format("%sthe player is looking at for %s3 seconds.", ChatColor.GRAY, ChatColor.GREEN), String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW), " ", String.format("%s%sCLICK TO SELECT", ChatColor.YELLOW, ChatColor.BOLD))
                .toItem();
        setItem(14, item, player -> {
            player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %sArcher %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY));
            player.sendMessage(String.format("%s===========================\n%s%s NEW STATS: \n\n%s%s❤ Base Health: %s10\n%s✦ Base Speed: %s200%%\n%s☄ Base Mana: %s100\n%s===========================", ChatColor.RED, ChatColor.BOLD, PowermageCore.classesEmojis.get(Classes.ARCHER), ChatColor.RESET, ChatColor.RED, ChatColor.RESET, ChatColor.GOLD, ChatColor.RESET, ChatColor.LIGHT_PURPLE, ChatColor.RESET, ChatColor.RED));
            player.closeInventory();
            player.setHealthScale(10);
            player.setHealth(10.0);
            player.setWalkSpeed(0.4f);
            if (PowermageCore.playerClasses.containsKey(player.getUniqueId())) {
                player.getInventory().addItem(new ItemBuilder(Material.BOW).setName(String.format("%sArcher Bow", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                        .setLore(String.format("%sDamage: %s+6", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sThe starter weapon", ChatColor.GRAY), String.format("%sfor the archer class", ChatColor.GRAY), " ", String.format("%s%sCOMMON WEAPON", ChatColor.GRAY, ChatColor.BOLD))
                        .toItem());
                player.getInventory().addItem(new ItemBuilder(Material.ARROW).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(String.format("%s%sCOMMON", ChatColor.GRAY, ChatColor.BOLD)).toItem());
            }
            PowermageCore.playerClasses.put(player.getUniqueId(), Classes.ARCHER);
        });
        item = new ItemBuilder(Material.BLAZE_ROD).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setName(String.format("%s[Lvl 0] %s%s Wizard", ChatColor.GRAY, ChatColor.GREEN, PowermageCore.classesEmojis.get(Classes.WIZARD)))
                .setLore(String.format("%s❤ Health: %s75%%", ChatColor.RED, ChatColor.RESET), String.format("%s✦ Base Speed: %s80%%", ChatColor.GOLD, ChatColor.RESET), String.format("%s☄ Base Mana: %s300", ChatColor.LIGHT_PURPLE, ChatColor.RESET), " ", String.format("%sClass Ability: %sHeart of Magic", ChatColor.BOLD, ChatColor.GOLD), String.format("%sGives you infinite mana", ChatColor.GRAY), String.format("%sfor an entire %s5 seconds.", ChatColor.GRAY, ChatColor.GREEN), String.format("%sfor %s5 seconds.", ChatColor.GRAY, ChatColor.GREEN), String.format("%sCooldown: %s1 minute", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW), " ", String.format("%s%sCLICK TO SELECT", ChatColor.YELLOW, ChatColor.BOLD))
                .toItem();
        setItem(16, item, player -> {
            player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %sWizard %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY));
            player.sendMessage(String.format("%s===========================\n%s%s NEW STATS: \n\n%s%s❤ Base Health: %s15\n%s✦ Base Speed: %s80%%\n%s☄ Base Mana: %s300\n%s===========================", ChatColor.RED, ChatColor.BOLD, PowermageCore.classesEmojis.get(Classes.ARCHER), ChatColor.RESET, ChatColor.RED, ChatColor.RESET, ChatColor.GOLD, ChatColor.RESET, ChatColor.LIGHT_PURPLE, ChatColor.RESET, ChatColor.RED));
            player.closeInventory();
            player.setHealthScale(15);
            player.setHealth(15.0);
            player.setWalkSpeed(0.16f);
            if (PowermageCore.playerClasses.containsKey(player.getUniqueId())) {
                player.getInventory().addItem(new ItemBuilder(Material.WOODEN_SWORD).setName(String.format("%sWizard Sword", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                        .setLore(String.format("%sDamage: %s+4", ChatColor.GRAY, ChatColor.RED), String.format("%sAttack Speed: %s1.6", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sThe starter weapon", ChatColor.GRAY), String.format("%sfor the wizard class", ChatColor.GRAY), " ", String.format("%s%sCOMMON WEAPON", ChatColor.GRAY, ChatColor.BOLD))
                        .toItem());
            }
            PowermageCore.playerClasses.put(player.getUniqueId(), Classes.WIZARD);
        });
        setItem(18, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> new MenuGUI(player).open(player));
        setItem(22, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> player.closeInventory());
    }
}