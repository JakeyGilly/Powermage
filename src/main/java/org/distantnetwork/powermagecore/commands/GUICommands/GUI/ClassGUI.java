package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.builders.InventoryBuilder;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.Enums.Classes;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

public class ClassGUI extends InventoryBuilder {
    public ClassGUI(Player p) {
        super(27, String.format("%sChoose your class", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));
        ItemStack item;
        int i = 10;
        for (Classes c : Classes.values()) {
            item = new ItemBuilder(Material.IRON_SWORD).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setName(String.format("%s[Lvl 0] %s%s", ChatColor.GRAY, ChatColor.RED, c.name()))
                    .setLore(String.format("%s❤ Health: %s%%", ChatColor.RED, c.getMaxHealth() * 5),
                            String.format("%s✦ Base Speed: %s%%", ChatColor.GOLD, c.getSpeed() * 500),
                            String.format("%s☄ Base Mana: %s", ChatColor.LIGHT_PURPLE, c.getMaxMana()),
// TODO: get from classes class
//                            " ",
//                            String.format("%sClass Ability: %sCharge", ChatColor.BOLD, ChatColor.GOLD),
//                            String.format("%sGives player %s+100 %s✦ Speed %sfor %s10 %sseconds.", ChatColor.GRAY, ChatColor.GREEN, ChatColor.GOLD, ChatColor.GRAY, ChatColor.GREEN, ChatColor.GRAY),
//                            String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW),
//                            " ",
                            String.format("%s%sCLICK TO SELECT", ChatColor.YELLOW, ChatColor.BOLD))
                    .toItem();
            setItem(i, item, player -> {
                PowermagePlayer pmPlayer = new PowermagePlayer(p);
                player.closeInventory();
                player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %sWarrior %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY));
                player.sendMessage(String.format("%s===========================\n%s%sNEW STATS:\n\n%s%s❤ Base Health: %s%%\n%s%s✦ Base Speed: %s%%\n%s%s☄ Base Mana: %s100\n%s===========================",
                        ChatColor.RED,
                        ChatColor.RESET, ChatColor.BOLD,
                        ChatColor.RESET, ChatColor.RED, (c.getMaxHealth() + pmPlayer.getHealthUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel")) * 5,
                        ChatColor.RESET, ChatColor.GOLD, (c.getSpeed() + pmPlayer.getSpeedUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel")) * 500,
                        ChatColor.RESET, ChatColor.LIGHT_PURPLE, (c.getMaxMana() + pmPlayer.getManaUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.mana.manaPerLevel")),
                        ChatColor.RED));
                try {
                    player.setHealthScale(c.getMaxHealth() + pmPlayer.getHealthUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
                } catch (Exception ignored) {
                }
                try {
                    player.setHealth(c.getMaxHealth() + pmPlayer.getHealthUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
                } catch (Exception ignored) {
                }
                player.setWalkSpeed((float) (c.getSpeed() + pmPlayer.getSpeedUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel")));
                if (c.getLvl(player) == 0) {
                    // TODO: Replace with class weapon
                    player.getInventory().addItem(new ItemBuilder(Material.WOODEN_SWORD).setName(String.format("%sWarrior Sword", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                            .setLore(String.format("%sDamage: %s+4", ChatColor.GRAY, ChatColor.RED), String.format("%sAttack Speed: %s1.6", ChatColor.GRAY, ChatColor.RED), " ", String.format("%sThe starter weapon", ChatColor.GRAY), String.format("%sfor the warrior class", ChatColor.GRAY), " ", String.format("%s%sCOMMON WEAPON", ChatColor.GRAY, ChatColor.BOLD))
                            .toItem());
                }
                pmPlayer.setClassType(c);
                pmPlayer = pmPlayer.save();
            });
            i += 2;
        }
        setItem(18, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> new MenuGUI(player).open(player));
        setItem(22, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), HumanEntity::closeInventory);
    }
}