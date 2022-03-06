package org.distantnetwork.powermagecore.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.distantnetwork.powermagecore.Classes;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.commands.MenuCommand;
import org.distantnetwork.powermagecore.commands.StartCommand;
import org.distantnetwork.powermagecore.utils.ItemBuilder;

import java.util.Arrays;

public class OnInventoryClick implements Listener {
    /**
    * TODO
     * get damage from config for stater wepons
     * get more info from config for stater wepons
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack item;
        if (e.getView().getTitle().equals(String.format("%sChoose your class", ChatColor.RED))) {
            e.setCancelled(true);
            boolean starteritems = !PowermageCore.playerClasses.containsKey(player.getUniqueId());
            if (e.getCurrentItem() == null) return;
            if (!PowermageCore.playerCombatLog.containsKey(player.getUniqueId())) return;
            switch (e.getSlot()) {
                case 10:
                    player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %sWarrior %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY));
                    player.sendMessage(String.format("%s===========================\n%s%s NEW STATS: \n\n%s%s❤ Base Health: %s20\n%s✦ Base Speed: %s100%%\n%s☄ Base Mana: %s100\n%s===========================", ChatColor.RED, ChatColor.BOLD, PowermageCore.classesEmojis.get(Classes.WARRIOR), ChatColor.RESET, ChatColor.RED, ChatColor.RESET, ChatColor.GOLD, ChatColor.RESET, ChatColor.LIGHT_PURPLE, ChatColor.RESET, ChatColor.RED));
                    player.closeInventory();
                    player.setHealthScale(20.0);
                    player.setHealth(20.0);
                    player.setWalkSpeed(1.0f);
                    PowermageCore.playerClasses.put(player.getUniqueId(), Classes.WARRIOR);
                    if (starteritems) {
                        player.getInventory().addItem(new ItemBuilder(Material.WOODEN_SWORD).setName(String.format("%sWarrior Sword", ChatColor.GRAY)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                                .setLore(String.format("%sDamage: %s+4", ChatColor.GRAY, ChatColor.RED), String.format("%sAttack Speed: %s1.6", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sThe starter weapon", ChatColor.GRAY), String.format("%sfor the warrior class", ChatColor.GRAY), " ", String.format("%sCOMMON WEAPON", ChatColor.BOLD))
                                .toItem());
                    }
                    break;
                case 12:
                    player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %sTank %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY));
                    player.sendMessage(String.format("%s===========================\n%s%s NEW STATS: \n\n%s%s❤ Base Health: %s40\n%s✦ Base Speed: %s50%%\n%s☄ Base Mana: %s100\n%s===========================", ChatColor.RED, ChatColor.BOLD, PowermageCore.classesEmojis.get(Classes.TANK), ChatColor.RESET, ChatColor.RED, ChatColor.RESET, ChatColor.GOLD, ChatColor.RESET, ChatColor.LIGHT_PURPLE, ChatColor.RESET, ChatColor.RED));
                    player.closeInventory();
                    player.setHealthScale(40.0);
                    player.setHealth(40.0);
                    player.setWalkSpeed(0.5f);
                    PowermageCore.playerClasses.put(player.getUniqueId(), Classes.TANK);
                    if (starteritems) {
                        player.getInventory().addItem(new ItemBuilder(Material.WOODEN_AXE).setName(String.format("%sTank Axe", ChatColor.GRAY)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                                .setLore(String.format("%sDamage: %s+9", ChatColor.GRAY, ChatColor.RED), String.format("%sAttack Speed: %s0.8", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sThe starter weapon", ChatColor.GRAY), String.format("%sfor the tank class", ChatColor.GRAY), " ", String.format("%sCOMMON WEAPON", ChatColor.BOLD))
                                .toItem());
                    }
                    break;
                case 14:
                    player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %sArcher %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY));
                    player.sendMessage(String.format("%s===========================\n%s%s NEW STATS: \n\n%s%s❤ Base Health: %s10\n%s✦ Base Speed: %s200%%\n%s☄ Base Mana: %s100\n%s===========================", ChatColor.RED, ChatColor.BOLD, PowermageCore.classesEmojis.get(Classes.ARCHER), ChatColor.RESET, ChatColor.RED, ChatColor.RESET, ChatColor.GOLD, ChatColor.RESET, ChatColor.LIGHT_PURPLE, ChatColor.RESET, ChatColor.RED));
                    player.closeInventory();
                    player.setHealthScale(10.0);
                    player.setHealth(10.0);
                    player.setWalkSpeed(2.0f);
                    PowermageCore.playerClasses.put(player.getUniqueId(), Classes.ARCHER);
                    if (starteritems) {
                        player.getInventory().addItem(new ItemBuilder(Material.BOW).setName(String.format("%sArcher Bow", ChatColor.GRAY)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                                .setLore(String.format("%sDamage: %s+6", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sThe starter weapon", ChatColor.GRAY), String.format("%sfor the archer class", ChatColor.GRAY), " ", String.format("%sCOMMON WEAPON", ChatColor.BOLD))
                                .toItem());
                        player.getInventory().addItem(new ItemBuilder(Material.ARROW).setItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(String.format("%sCOMMON", ChatColor.BOLD)).toItem());
                    }
                    break;
                case 16:
                    player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %sWizard %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY));
                    player.sendMessage(String.format("%s===========================\n%s%s NEW STATS: \n\n%s%s❤ Base Health: %s15\n%s✦ Base Speed: %s80%%\n%s☄ Base Mana: %s300\n%s===========================", ChatColor.RED, ChatColor.BOLD, PowermageCore.classesEmojis.get(Classes.ARCHER), ChatColor.RESET, ChatColor.RED, ChatColor.RESET, ChatColor.GOLD, ChatColor.RESET, ChatColor.LIGHT_PURPLE, ChatColor.RESET, ChatColor.RED));
                    player.closeInventory();
                    player.setHealthScale(15.0);
                    player.setHealth(15.0);
                    player.setWalkSpeed(0.8f);
                    PowermageCore.playerClasses.put(player.getUniqueId(), Classes.WIZARD);
                    player.getInventory().addItem(new ItemBuilder(Material.WOODEN_SWORD).setName(String.format("%sWizard Sword", ChatColor.GRAY)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                            .setLore(String.format("%sDamage: %s+4", ChatColor.GRAY, ChatColor.RED), String.format("%sAttack Speed: %s1.6", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sThe starter weapon", ChatColor.GRAY), String.format("%sfor the wizard class", ChatColor.GRAY), " ", String.format("%sCOMMON WEAPON", ChatColor.BOLD))
                            .toItem());
                    break;
                case 18:
                    player.closeInventory();
                    Inventory menu = MenuCommand.generateMenu(player);
                    player.openInventory(menu);
                    break;
                case 22:
                    player.closeInventory();
                default:
                    break;
            }
            item = new ItemBuilder(Material.END_CRYSTAL).setName(String.format("%s%s%sMenu %s%s(Right/Sneak Left Click)", ChatColor.RED, ChatColor.BOLD, PowermageCore.classesEmojis.get(Classes.WARRIOR), ChatColor.RESET, ChatColor.GRAY)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    .setLore(String.format("%sRight Click to open the menu,", ChatColor.GRAY), String.format("%sand Left Click while sneaking to", ChatColor.GRAY), String.format("%suse your selected class ability!", ChatColor.GRAY), " ", String.format("%s%sCORE ITEM", ChatColor.RED, ChatColor.BOLD))
                    .toItem();
            player.getInventory().setItem(8, item);
        } else if (e.getView().getTitle().equals(String.format("%sPowermage", ChatColor.RED))) {
            e.setCancelled(true);
            switch (e.getSlot()) {
                case 21:
                    player.closeInventory();
                    // player.openInventory(upgradeMenu);
                    break;
                case 22:
                    player.closeInventory();
                    // player.openInventory(shopMenu);
                    break;
                case 23:
                    player.closeInventory();
                    Inventory switchClassMenu = StartCommand.generateClassSwitcher(player);
                    player.openInventory(switchClassMenu);
                    break;
                case 24:
                    player.closeInventory();
                    // player.openInventory(soulShopMenu);
                    break;
                case 39:
                    player.closeInventory();
                    player.performCommand("store");
                    break;
                case 40:
                    player.closeInventory();
                    break;
                case 41:
                    player.closeInventory();
                    player.performCommand("discord");
                    break;
                default:
                    break;
            }
        } else if (e.getView().getTitle().equals(String.format("%sPowermage Soul Shop", ChatColor.AQUA))) {
            e.setCancelled(true);
            switch (e.getSlot()) {
                case 10:
                    player.closeInventory();
                    if (PowermageCore.playerSouls.get(player.getUniqueId()) < 75) {
                        player.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.DARK_RED));
                    } else {
                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 75);
                        player.getInventory().addItem(new ItemBuilder(Material.SPECTRAL_ARROW).setName(String.format("%sZeus's Bolt", ChatColor.GOLD)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                                .setLore(String.format("%sAbility: Thunderbolt %s%sRIGHT CLICK", ChatColor.GOLD, ChatColor.YELLOW, ChatColor.BOLD), String.format("%sTeleport %s10 blocks %sin front of you,", ChatColor.GRAY, ChatColor.GREEN, ChatColor.GRAY), String.format("%sstriking lighting at your previous location.", ChatColor.GRAY), String.format("%sMana Cost: %s50", ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE), String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), " ", String.format("%s%sLEGENDARY", ChatColor.GOLD, ChatColor.BOLD)).toItem());
                        player.sendMessage(String.format("%sYou purchased %sZeus Bolt %sfor %s50 Souls", ChatColor.GREEN, ChatColor.GOLD, ChatColor.GREEN, ChatColor.AQUA));
                    }
                    break;
                case 11:
                    player.closeInventory();
                    if (PowermageCore.playerSouls.get(player.getUniqueId()) < 20) {
                        player.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.DARK_RED));
                    } else {
                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 20);
                        player.getInventory().addItem(new ItemBuilder(Material.SCUTE).setName(String.format("%sRepulsion", ChatColor.DARK_GREEN)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                                .setLore(String.format("%sAbility: Launch Away %s%sRIGHT CLICK", ChatColor.GOLD, ChatColor.YELLOW, ChatColor.BOLD), String.format("%sLaunches everyone within a", ChatColor.GRAY), String.format("%s5 block %sradius away.", ChatColor.GREEN, ChatColor.GRAY), String.format("%sMana Cost: %s10", ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE), " ", String.format("%s%sUNCOMMON", ChatColor.GREEN, ChatColor.BOLD)).toItem());
                        player.sendMessage(String.format("%sYou purchased %sRepulsion %sfor %s20 Souls", ChatColor.GREEN, ChatColor.DARK_GREEN, ChatColor.GREEN, ChatColor.AQUA));
                    }
                    break;
                case 12:
                    player.closeInventory();
                    if (PowermageCore.playerSouls.get(player.getUniqueId()) < 35) {
                        player.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.DARK_RED));
                    } else {
                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 35);
                        player.getInventory().addItem(new ItemBuilder(Material.SCUTE).setName(String.format("%sSuper Repulsion", ChatColor.DARK_GREEN)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                                .setLore(String.format("%sAbility: Launch Away %s%sRIGHT CLICK", ChatColor.GOLD, ChatColor.YELLOW, ChatColor.BOLD), String.format("%sLaunches everyone within a", ChatColor.GRAY), String.format("%s20 block %sradius away.", ChatColor.DARK_GREEN, ChatColor.GRAY), String.format("%sMana Cost: %s30", ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE), " ", String.format("%s%sRARE", ChatColor.BLUE, ChatColor.BOLD)).toItem());
                        player.sendMessage(String.format("%sYou purchased %sSuper Repulsion %sfor %s35 Souls", ChatColor.GREEN, ChatColor.DARK_GREEN, ChatColor.GREEN, ChatColor.AQUA));
                    }
                    break;
                case 13:
                    player.closeInventory();
                    if (PowermageCore.playerSouls.get(player.getUniqueId()) < 25) {
                        player.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.DARK_RED));
                    } else {
                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 25);
                        player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_SWORD).setName(String.format("%sDasher", ChatColor.GRAY)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                                .setLore(String.format("%sDamage: %s+4", ChatColor.GRAY, ChatColor.RED), String.format("%sAttack Speed: %s1.6", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sAbility: Speed %s%sRIGHT CLICK", ChatColor.GOLD, ChatColor.YELLOW, String.format("%sGives you %s30 speed %sfor %s30 seconds%s.", ChatColor.GRAY, ChatColor.WHITE, ChatColor.GRAY, ChatColor.WHITE, ChatColor.GRAY), String.format("%sMana Cost: %s50", ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE), " ", String.format("%s%sCOMMON", ChatColor.GRAY, ChatColor.BOLD))).toItem());
                        player.sendMessage(String.format("%sYou purchased %sDasher %sfor %s25 Souls", ChatColor.GRAY, ChatColor.GRAY, ChatColor.GREEN, ChatColor.AQUA));
                    }
                    break;
                case 14:
                    player.closeInventory();
                    if (PowermageCore.playerSouls.get(player.getUniqueId()) < 35) {
                        player.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.DARK_RED));
                    } else {
                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 35);
                        player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_SWORD).setName(String.format("%sSuper Dasher", ChatColor.GREEN)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                                .setLore(String.format("%sDamage: %s+5", ChatColor.GRAY, ChatColor.RED), String.format("%sAttack Speed: %s1.6", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sAbility: SuperSpeed %s%sRIGHT CLICK", ChatColor.GOLD, ChatColor.YELLOW, String.format("%sGives you %s40 speed %sfor %s40 seconds%s.", ChatColor.GRAY, ChatColor.WHITE, ChatColor.GRAY, ChatColor.WHITE, ChatColor.GRAY), String.format("%sMana Cost: %s60", ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE), " ", String.format("%s%sUNCOMMON", ChatColor.GREEN, ChatColor.BOLD))).toItem());
                        player.sendMessage(String.format("%sYou purchased %sSuper Dasher %sfor %s35 Souls", ChatColor.GREEN, ChatColor.DARK_GREEN, ChatColor.GREEN, ChatColor.AQUA));
                    }
                    break;
                case 15:
                    player.closeInventory();
                    if (PowermageCore.playerSouls.get(player.getUniqueId()) < 50) {
                        player.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.DARK_RED));
                    } else {
                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 50);
                        player.getInventory().addItem(new ItemBuilder(Material.GOLDEN_SWORD).setName(String.format("%sHyper Dasher", ChatColor.BLUE)).setItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                                .setLore(String.format("%sDamage: %s+6", ChatColor.GRAY, ChatColor.RED), String.format("%sAttack Speed: %s1.6", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sAbility: HyperSpeed %s%sRIGHT CLICK", ChatColor.GOLD, ChatColor.YELLOW, String.format("%sGives you %s50 speed %sfor %s50 seconds%s.", ChatColor.GRAY, ChatColor.WHITE, ChatColor.GRAY, ChatColor.WHITE, ChatColor.GRAY), String.format("%sMana Cost: %s70", ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE), " ", String.format("%s%sRARE", ChatColor.BLUE, ChatColor.BOLD))).toItem());
                        player.sendMessage(String.format("%sYou purchased %sSuper Dasher %sfor %s35 Souls", ChatColor.GREEN, ChatColor.BLUE, ChatColor.GREEN, ChatColor.AQUA));

                    }
                    break;
//                case 16:
//                    player.closeInventory();
//                    if (PowermageCore.playerSouls.get(player.getUniqueId()) >= 75) {
//                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 75);
//                        ItemStack item = new ItemStack(Material.STONE_SWORD);
//                        ItemMeta meta = item.getItemMeta();
//                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Vampire Sword"));
//                        meta.setLore(Arrays.asList("&7Damage: &c+5", "&7Attack Speed: &c1.6", " ", "&6Ability: Bloodlust &e&lPASSIVE", "&7Every hit with this sword steals &c1 ❤ &7from", "&7your opponent and gives it to you.", "&8Mana Cost: &d20", " ", "&6&lLEGENDARY"));
//                        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//                        item.setItemMeta(meta);
//                        player.getInventory().addItem(item);
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou purchased &6Vampire Sword for &b75 Souls"));
//                    } else {
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have enough souls!"));
//                    }
//                case 19:
//                    player.closeInventory();
//                    if (PowermageCore.playerSouls.get(player.getUniqueId()) >= 50) {
//                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 50);
//                        ItemStack item = new ItemStack(Material.IRON_SWORD);
//                        ItemMeta meta = item.getItemMeta();
//                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Day Sword"));
//                        meta.setLore(Arrays.asList("&7Damage: &c+6 &7(+9 ☀)", "&7Attack Speed: &c1.6", " ", "&6Ability: Sunlight &e&lPASSIVE", "&7Deals &a33%% &7more damage during the day.", " ", "&5&lEPIC"));
//                    }
                default:
                    break;
            }
        }
    }
}
