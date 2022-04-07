package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.ArcherBow;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.TankSword;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.WarriorSword;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.WizardSword;
import org.distantnetwork.powermagecore.builders.InventoryBuilder;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Classes;
import org.distantnetwork.powermagecore.utils.ClassesChildren.Warrior;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.getDefaultConfig;

public class ClassGUI extends InventoryBuilder {
    public ClassGUI(Player p) {
        super(27, String.format("%sChoose your class", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));
        ItemBuilder item;
        for (Classes c : Classes.getClasses()) {
            item = new ItemBuilder(c.getWeaponIcon()).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setName(String.format("%s[Lvl 0] %s%s%s", ChatColor.GRAY, ChatColor.RED, c.getEmoji(), c.getName()))
                    .setLore(String.format("%s❤ Health: %s%%", ChatColor.RED, (int)Math.round(c.getBaseHealth() * 5)),
                            String.format("%s✦ Base Speed: %s%%", ChatColor.GOLD, Math.round(c.getBaseSpeed() * 500)),
                            String.format("%s☄ Base Mana: %s", ChatColor.LIGHT_PURPLE, c.getBaseMana()));
            for (String s : c.getDescription()) item.addLoreLine(s);
            item.addLoreLine(String.format("%s%sCLICK TO SELECT", ChatColor.YELLOW, ChatColor.BOLD));
            setItem(c.getClassMenuSlot(), item.build(), player -> {
                PowermagePlayer pmPlayer = new PowermagePlayer(p);
                player.closeInventory();
                player.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %s%s %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, c.getName(), ChatColor.RESET, ChatColor.GRAY));
                player.sendMessage(String.format("%s===========================\n%s%sNEW STATS:\n\n%s%s❤ Base Health: %s%%\n%s%s✦ Base Speed: %s%%\n%s%s☄ Base Mana: %s\n%s===========================",
                        ChatColor.RED,
                        ChatColor.RESET, ChatColor.BOLD,
                        ChatColor.RESET, ChatColor.RED, (int)Math.round((c.getBaseHealth() * 5) + (pmPlayer.getHealthUpgrade() * getDefaultConfig().getDouble("upgrades.speed.speedPerLevel"))),
                        ChatColor.RESET, ChatColor.GOLD, (int)Math.round((c.getBaseSpeed() * 500) + (pmPlayer.getSpeedUpgrade() * getDefaultConfig().getDouble("upgrades.speed.speedPerLevel"))),
                        ChatColor.RESET, ChatColor.LIGHT_PURPLE, (int)Math.round(c.getBaseMana() + (pmPlayer.getManaUpgrade() * getDefaultConfig().getDouble("upgrades.mana.manaPerLevel"))),
                        ChatColor.RED));
                try {
                    player.setHealthScale(c.getBaseHealth() + pmPlayer.getHealthUpgrade() * getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
                } catch (Exception ignored) {
                }
                try {
                    player.setHealth(c.getBaseHealth() + pmPlayer.getHealthUpgrade() * getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
                } catch (Exception ignored) {
                }
                player.setWalkSpeed((float) (c.getBaseSpeed() + pmPlayer.getSpeedUpgrade() * getDefaultConfig().getDouble("upgrades.speed.speedPerLevel")));
                pmPlayer.setClassType(c);
                pmPlayer = pmPlayer.save();
                if (pmPlayer.getClassLvl(pmPlayer.getClassType()) == 0) {
                    c.getWeaponItem().give(player);
                    pmPlayer.setClassesLvl(pmPlayer.getClassType(), 1);
                }
                pmPlayer.save();
            });
        }
        setItem(18, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> new MenuGUI(player).open(player));
        setItem(22, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), HumanEntity::closeInventory);
    }
}