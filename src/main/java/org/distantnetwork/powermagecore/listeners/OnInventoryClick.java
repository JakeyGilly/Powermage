////TODO:
//// ADD COMMENTS
//
//package org.distantnetwork.powermagecore.listeners;
//
//import net.md_5.bungee.api.ChatColor;
//import org.bukkit.Material;
//import org.bukkit.enchantments.Enchantment;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.inventory.InventoryClickEvent;
//import org.bukkit.inventory.Inventory;
//import org.bukkit.inventory.ItemFlag;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;
//import org.distantnetwork.powermagecore.Classes;
//import org.distantnetwork.powermagecore.PowermageCore;
//import org.distantnetwork.powermagecore.commands.MenuCommand;
//import org.distantnetwork.powermagecore.commands.StartCommand;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//public class OnInventoryClick implements Listener {
//    @EventHandler
//    public void onInventoryClick(InventoryClickEvent e) {
//        Player player = (Player) e.getWhoClicked();
//        if (e.getView().getTitle().equals("&c&lChoose your class!")) {
//            e.setCancelled(true);
//            boolean starteritems = true;
//            if (PowermageCore.playerClasses.containsKey(player.getUniqueId())) {
//                starteritems = false;
//                PowermageCore.playerClasses.remove(player.getUniqueId());
//            }
//            if (e.getCurrentItem() == null) return;
//            if (PowermageCore.playerCombatLog.containsKey(player.getUniqueId())) {
//                switch (e.getSlot()) {
//                    case 10:
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aClass Selector&7] &aYou have selected the &a&lWarrior&a class!"));
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r&c===========================\n&r&c&l\uD83D\uDDE1 &c&lNEW STATS: \n\n&r&c❤ Base Health: &f20\n&r&6✦ Base Speed: &f100\n&r&d☄ Base Mana: &f100\n&r&c==========================="));
//                        player.closeInventory();
//                        player.setHealthScale(20.0);
//                        player.setHealth(20.0);
//                        player.setWalkSpeed(1.0f);
//                        PowermageCore.playerClasses.put(player.getUniqueId(), Classes.WARRIOR);
//                        if (starteritems) {
//                            ItemStack item = new ItemStack(Material.WOODEN_SWORD);
//                            ItemMeta meta = item.getItemMeta();
//                            meta.setDisplayName("&fWarrior Sword");
//                            meta.setLore(Arrays.asList("&7Damage: &c+4", "&7Attack Speed: &c1.6", " ", "&7The starter weapon", "&7for the warrior class", " ", "&f&lCOMMON WEAPON"));
//                            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//                            item.setItemMeta(meta);
//                            player.getInventory().addItem(item);
//                        }
//                        ItemStack menuItem = new ItemStack(Material.END_CRYSTAL);
//                        ItemMeta menuMeta = menuItem.getItemMeta();
//                        menuMeta.setDisplayName(String.format("&c%s &c&lMenu &r&7(Right/Sneak Left Click)", PowermageCore.classesEmojis.get(Classes.WARRIOR)));
//                        menuMeta.setLore(Arrays.asList("&7Right Click to open the menu,", "&7and Left Click while sneaking to", "&7use your selected class ability!", " ", "&c&lCORE ITEM"));
//                        menuItem.setItemMeta(menuMeta);
//                        player.getInventory().setItem(8, menuItem);
//                        break;
//                    case 12:
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aClass Selector&7] &aYou have selected the &a&Tank&a class!"));
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r&c===========================\n&r&c&l\uD83D\uDEE1 &c&lNEW STATS: \n\n&r&c❤ Base Health: &f40\n&r&6✦ Base Speed: &f50\n&r&d☄ Base Mana: &f100\n&r&c==========================="));
//                        player.closeInventory();
//                        player.setHealthScale(40.0);
//                        player.setHealth(40.0);
//                        player.setWalkSpeed(0.5f);
//                        PowermageCore.playerClasses.put(player.getUniqueId(), Classes.TANK);
//                        if (starteritems) {
//                            ItemStack item = new ItemStack(Material.WOODEN_AXE);
//                            ItemMeta meta = item.getItemMeta();
//                            meta.setDisplayName("&fTank Axe");
//                            meta.setLore(Arrays.asList("&7Damage: &c+9", "&7Attack Speed: &c0.8", " ", "&7The starter weapon", "&7for the tank class", " ", "&f&lCOMMON WEAPON"));
//                            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//                            item.setItemMeta(meta);
//                            player.getInventory().addItem(item);
//                        }
//                        menuItem = new ItemStack(Material.END_CRYSTAL);
//                        menuMeta = menuItem.getItemMeta();
//                        menuMeta.setDisplayName(String.format("&c%s &c&lMenu &r&7(Right/Sneak Left Click)", PowermageCore.classesEmojis.get(Classes.TANK)));
//                        menuMeta.setLore(Arrays.asList("&7Right Click to open the menu,", "&7and Left Click while sneaking to", "&7use your selected class ability!", " ", "&c&lCORE ITEM"));
//                        menuItem.setItemMeta(menuMeta);
//                        player.getInventory().setItem(8, menuItem);
//                        break;
//                    case 14:
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aClass Selector&7] &aYou have selected the &a&Archer&a class!"));
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r&c===========================\n&r&c&l\uD83C\uDFF9 &c&lNEW STATS: \n\n&r&c❤ Base Health: &f10\n&r&6✦ Base Speed: &f200\n&r&d☄ Base Mana: &f100\n&r&c==========================="));
//                        player.closeInventory();
//                        player.setHealthScale(10.0);
//                        player.setHealth(10.0);
//                        player.setWalkSpeed(2.0f);
//                        PowermageCore.playerClasses.put(player.getUniqueId(), Classes.ARCHER);
//                        if (starteritems) {
//                            ItemStack item = new ItemStack(Material.BOW);
//                            ItemMeta meta = item.getItemMeta();
//                            meta.setDisplayName("&fArcher Bow");
//                            meta.setLore(Arrays.asList("&7Damage: &c+9", " ", "&7The starter weapon", "&7for the archer class", " ", "&f&lCOMMON WEAPON"));
//                            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//                            item.setItemMeta(meta);
//                            ItemStack arrow = new ItemStack(Material.ARROW);
//                            ItemMeta arrowMeta = arrow.getItemMeta();
//                            arrowMeta.setLore(Collections.singletonList("&f&lCOMMON"));
//                            arrow.setItemMeta(arrowMeta);
//                            player.getInventory().addItem(item, arrow);
//                        }
//                        menuItem = new ItemStack(Material.END_CRYSTAL);
//                        menuMeta = menuItem.getItemMeta();
//                        menuMeta.setDisplayName(String.format("&c%s &c&lMenu &r&7(Right/Sneak Left Click)", PowermageCore.classesEmojis.get(Classes.ARCHER)));
//                        menuMeta.setLore(Arrays.asList("&7Right Click to open the menu,", "&7and Left Click while sneaking to", "&7use your selected class ability!", " ", "&c&lCORE ITEM"));
//                        menuItem.setItemMeta(menuMeta);
//                        player.getInventory().setItem(8, menuItem);
//                        break;
//                    case 16:
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&aClass Selector&7] &aYou have selected the &a&Wizard&a class!"));
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r&c===========================\n&r&c&l\uD83E\uDDEA &c&lNEW STATS: \n\n&r&c❤ Base Health: &f15\n&r&6✦ Base Speed: &f80\n&r&d☄ Base Mana: &f300\n&r&c==========================="));
//                        player.closeInventory();
//                        player.setHealthScale(15.0);
//                        player.setHealth(15.0);
//                        player.setWalkSpeed(0.8f);
//                        PowermageCore.playerClasses.put(player.getUniqueId(), Classes.WIZARD);
//                        menuItem = new ItemStack(Material.END_CRYSTAL);
//                        menuMeta = menuItem.getItemMeta();
//                        menuMeta.setDisplayName(String.format("&c%s &c&lMenu &r&7(Right/Sneak Left Click)", PowermageCore.classesEmojis.get(Classes.WIZARD)));
//                        menuMeta.setLore(Arrays.asList("&7Right Click to open the menu,", "&7and Left Click while sneaking to", "&7use your selected class ability!", " ", "&c&lCORE ITEM"));
//                        menuItem.setItemMeta(menuMeta);
//                        player.getInventory().setItem(8, menuItem);
//                        break;
//                    case 18:
//                        player.closeInventory();
//                        Inventory menu = MenuCommand.generateMenu(player);
//                        player.openInventory(menu);
//                        break;
//                    case 22:
//                        player.closeInventory();
//                    default:
//                        break;
//                }
//            }
//        } else if (e.getView().getTitle().equals("&c&lPowerMage")) {
//            e.setCancelled(true);
//            switch (e.getSlot()) {
//                case 21:
//                    player.closeInventory();
//                    // player.openInventory(upgradeMenu);
//                    break;
//                case 22:
//                    player.closeInventory();
//                    // player.openInventory(shopMenu);
//                    break;
//                case 23:
//                    player.closeInventory();
//                    Inventory switchClassMenu = StartCommand.generateInventory();
//                    player.openInventory(switchClassMenu);
//                    break;
//                case 24:
//                    player.closeInventory();
//                    // player.openInventory(soulShopMenu);
//                    break;
//                case 39:
//                    player.closeInventory();
//                    player.performCommand("store");
//                    break;
//                case 40:
//                    player.closeInventory();
//                    break;
//                case 41:
//                    player.closeInventory();
//                    player.performCommand("discord");
//                    break;
//            }
//        } else if (e.getView().getTitle().equals("&b&lPowermage Soul Shop")) {
//            e.setCancelled(true);
//            switch (e.getSlot()) {
//                case 10:
//                    player.closeInventory();
//                    if (PowermageCore.playerSouls.get(player.getUniqueId()) >= 75) {
//                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 75);
//                        ItemStack item = new ItemStack(Material.SPECTRAL_ARROW);
//                        ItemMeta meta = item.getItemMeta();
//                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Zeus's Bolt"));
//                        meta.setLore(Arrays.asList("&6Ability: Thunderbolt &e&lRIGHT CLICK", "&7Teleport &a10 blocks &7in front of you,", "&7striking lighting at your previous location.", "&8Mana Cost: &d50", "&8Cooldown: &a30 Seconds", " ", "&6&lLEGENDARY"));
//                        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//                        item.setItemMeta(meta);
//                        player.getInventory().addItem(item);
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou purchased &6Zeus Bolt for &b50 Souls"));
//                    } else {
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have enough souls!"));
//                    }
//                    break;
//                case 11:
//                    player.closeInventory();
//                    if (PowermageCore.playerSouls.get(player.getUniqueId()) >= 20) {
//                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 20);
//                        ItemStack item = new ItemStack(Material.SCUTE);
//                        ItemMeta meta = item.getItemMeta();
//                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aRepulsion"));
//                        meta.setLore(Arrays.asList("&6Ability: Launch Away &e&lRIGHT CLICK", "&7Launches everyone within a", "&a5 block &7radius away.", "&8Mana Cost: &d10", " ", "&a&lUNCOMMON"));
//                        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//                        item.setItemMeta(meta);
//                        player.getInventory().addItem(item);
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou purchased &6Repulsion for &b20 Souls"));
//                    } else {
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have enough souls!"));
//                    }
//                    break;
//                case 12:
//                    player.closeInventory();
//                    if (PowermageCore.playerSouls.get(player.getUniqueId()) >= 50) {
//                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 50);
//                        ItemStack item = new ItemStack(Material.SCUTE);
//                        ItemMeta meta = item.getItemMeta();
//                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9Super Repulsion"));
//                        meta.setLore(Arrays.asList("&6Ability: Launch Away &e&lRIGHT CLICK", "&7Launches everyone within a", "&a5 block &7radius away.", "&8Mana Cost: &d20", " ", "&9&lRARE"));
//                        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//                        item.setItemMeta(meta);
//                        player.getInventory().addItem(item);
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou purchased &6Super Repulsion for &b50 Souls"));
//                    } else {
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have enough souls!"));
//                    }
//                    break;
//                case 13:
//                    player.closeInventory();
//                    if (PowermageCore.playerSouls.get(player.getUniqueId()) >= 25) {
//                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 25);
//                        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
//                        ItemMeta meta = item.getItemMeta();
//                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fDasher"));
//                        meta.setLore(Arrays.asList("&7Damage: &c+4", "&7Attack Speed: &c1.6", " ", "&6Ability: Speed &e&lRIGHT CLICK", "&7Gives you &a30 speed &7for &a30 seconds&7.", "&8Mana Cost: &d50", " ", "&f&lCOMMON"));
//                        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//                        item.setItemMeta(meta);
//                        player.getInventory().addItem(item);
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou purchased &6Dasher for &b25 Souls"));
//                    } else {
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have enough souls!"));
//                    }
//                    break;
//                case 14:
//                    player.closeInventory();
//                    if (PowermageCore.playerSouls.get(player.getUniqueId()) >= 35) {
//                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 35);
//                        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
//                        ItemMeta meta = item.getItemMeta();
//                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aSuper Dasher"));
//                        meta.setLore(Arrays.asList("&7Damage: &c+5", "&7Attack Speed: &c1.6", " ", "&6Ability: Superspeed &e&lRIGHT CLICK", "&7Gives you &a40 speed &7for &a40 seconds&7.", "&8Mana Cost: &d60", " ", "&a&lUNCOMMON"));
//                        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
//                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//                        item.setItemMeta(meta);
//                        player.getInventory().addItem(item);
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou purchased &6Super Dasher for &b35 Souls"));
//                    } else {
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have enough souls!"));
//                    }
//                    break;
//                case 15:
//                    player.closeInventory();
//                    if (PowermageCore.playerSouls.get(player.getUniqueId()) >= 50) {
//                        PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - 50);
//                        ItemStack item = new ItemStack(Material.GOLDEN_SWORD);
//                        ItemMeta meta = item.getItemMeta();
//                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9Hyper Dasher"));
//                        meta.setLore(Arrays.asList("&7Damage: &c+6", "&7Attack Speed: &c1.6", " ", "&6Ability: Hyperspeed &e&lRIGHT CLICK", "&7Gives you &a50 speed &7for &a50 seconds&7.", "&8Mana Cost: &d70", " ", "&9&lRARE"));
//                        meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
//                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//                        item.setItemMeta(meta);
//                        player.getInventory().addItem(item);
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou purchased &6Hyper Dasher for &b50 Souls"));
//                    } else {
//                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4You don't have enough souls!"));
//                    }
//                    break;
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
//
//
//                    }
//            }
//        }
//    }
//}
