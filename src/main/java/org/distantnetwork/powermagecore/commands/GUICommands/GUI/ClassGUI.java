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
import org.distantnetwork.powermagecore.utils.Enums.Rarity;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.WeaponItem;

import java.util.Arrays;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.getDefaultConfig;

public class ClassGUI extends InventoryBuilder {
    public ClassGUI(Player p) {
        super(27, String.format("%sChoose your class", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));
        ItemBuilder item;
        int i = 10;
        for (Classes c : Classes.values()) {
            item = new ItemBuilder(c.getMaterial()).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setName(String.format("%s[Lvl 0] %s%s%s", ChatColor.GRAY, ChatColor.RED, c.getEmoji(), c.name()))
                    .setLore(String.format("%s❤ Health: %s%%", ChatColor.RED, c.getMaxHealth() * 5),
                            String.format("%s✦ Base Speed: %s%%", ChatColor.GOLD, c.getSpeed() * 500),
                            String.format("%s☄ Base Mana: %s", ChatColor.LIGHT_PURPLE, c.getMaxMana()));
            for (String s : c.getDescription()) item.addLoreLine(s);
            item.addLoreLine(String.format("%s%sCLICK TO SELECT", ChatColor.YELLOW, ChatColor.BOLD));
            setItem(i, item.build(), player -> {
                PowermagePlayer pmPlayer = new PowermagePlayer(p);
                player.closeInventory();
                player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %s%s %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, c.name(), ChatColor.RESET, ChatColor.GRAY));
                player.sendMessage(String.format("%s===========================\n%s%sNEW STATS:\n\n%s%s❤ Base Health: %s%%\n%s%s✦ Base Speed: %s%%\n%s%s☄ Base Mana: %s100\n%s===========================",
                        ChatColor.RED,
                        ChatColor.RESET, ChatColor.BOLD,
                        ChatColor.RESET, ChatColor.RED, (int) (c.getMaxHealth() + (pmPlayer.getHealthUpgrade() * getDefaultConfig().getDouble("upgrades.speed.speedPerLevel") * 5)),
                        ChatColor.RESET, ChatColor.GOLD, (int) (c.getSpeed() + (pmPlayer.getSpeedUpgrade() * getDefaultConfig().getDouble("upgrades.speed.speedPerLevel") * 500)),
                        ChatColor.RESET, ChatColor.LIGHT_PURPLE, (int) (c.getMaxMana() + (pmPlayer.getManaUpgrade() * getDefaultConfig().getDouble("upgrades.mana.manaPerLevel"))),
                        ChatColor.RED));
                try {
                    player.setHealthScale(c.getMaxHealth() + pmPlayer.getHealthUpgrade() * getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
                } catch (Exception ignored) {
                }
                try {
                    player.setHealth(c.getMaxHealth() + pmPlayer.getHealthUpgrade() * getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
                } catch (Exception ignored) {
                }
                player.setWalkSpeed((float) (c.getSpeed() + pmPlayer.getSpeedUpgrade() * getDefaultConfig().getDouble("upgrades.speed.speedPerLevel")));
                if (c.getLvl(player) == 0) {
                    // TODO: Replace with class weapon
                    // give class weapon
                }

                pmPlayer.setClassType(c);
                if (c == Classes.WARRIOR) {
                    pmPlayer.setWarriorLevel(1);
                } else if (c == Classes.ARCHER) {
                    pmPlayer.setArcherLevel(1);
                } else if (c == Classes.WIZARD) {
                    pmPlayer.setWizardLevel(1);
                } else if (c == Classes.TANK) {
                    pmPlayer.setTankLevel(1);
                }
                pmPlayer.save();
            });
            i += 2;
        }
        setItem(18, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> new MenuGUI(player).open(player));
        setItem(22, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), HumanEntity::closeInventory);
    }
}