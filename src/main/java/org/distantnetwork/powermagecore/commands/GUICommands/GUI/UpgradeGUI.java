package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.builders.InventoryBuilder;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.getDefaultConfig;

public class UpgradeGUI extends InventoryBuilder {
    //TODO: implement money & soul spending
    //TODO: implement % or amount increase in config per upgrade
    public UpgradeGUI(Player p) {
        super(54, String.format("%sUpgrade Stats", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++)
            setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName(" ").toItem());
        PowermagePlayer pmPlayer = new PowermagePlayer(p);
        if (pmPlayer.getStrengthUpgrade() == getDefaultConfig().getInt("upgrades.strength.maxLevel")) {
            setItem(19, new ItemBuilder(Material.IRON_SWORD).setName(String.format("%sStrength", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, pmPlayer.getStrengthUpgrade()), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem(), player -> player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED)));
        } else {
            setItem(19, new ItemBuilder(Material.IRON_SWORD).setName(String.format("%sStrength", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %d", ChatColor.DARK_GREEN, pmPlayer.getStrengthUpgrade()), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, getDefaultConfig().getIntegerList("upgrades.strength.cost").get(pmPlayer.getStrengthUpgrade()))).toItem(), player -> {
                if (!(getDefaultConfig().get("upgrades.strength.cost") != null && getDefaultConfig().getIntegerList("upgrades.strength.cost").size() == getDefaultConfig().getInt("upgrades.strength.maxLevel"))) {
                    p.sendMessage("The config is not set up correctly!");
                    PowermageCore.getInstance().getLogger().warning("The Strength upgrade costs length are not equal to the max level!");
                    return;
                }
                if (pmPlayer.getStrengthUpgrade() == getDefaultConfig().getInt("upgrades.strength.maxLevel")) {
                    player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED));
                    return;
                }
                if (pmPlayer.getSouls() < getDefaultConfig().getIntegerList("upgrades.strength.cost").get(pmPlayer.getStrengthUpgrade())) {
                    p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
                    return;
                }
                pmPlayer.setSouls(pmPlayer.getSouls() - getDefaultConfig().getIntegerList("upgrades.strength.cost").get(pmPlayer.getStrengthUpgrade()));
                pmPlayer.setStrengthUpgrade(pmPlayer.getStrengthUpgrade() + 1);
                PowermagePlayer newpmPlayer = pmPlayer.save();
                getInventory().setItem(19, new ItemBuilder(Material.IRON_SWORD).setName(String.format("%sStrength", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                        .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, newpmPlayer.getStrengthUpgrade()),
                                newpmPlayer.getStrengthUpgrade() == getDefaultConfig().getInt("upgrades.strength.maxLevel") ? String.format("%sMAX LEVEL", ChatColor.DARK_RED) :
                                        String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, getDefaultConfig().getIntegerList("upgrades.strength.cost").get(newpmPlayer.getStrengthUpgrade()))).toItem());
                player.sendMessage(String.format("%sYou have bought the %sStrength %supgrade for %s!", ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.DARK_GREEN, getDefaultConfig().getIntegerList("upgrades.strength.cost").get(newpmPlayer.getStrengthUpgrade()-1)));
            });
        }
        if (pmPlayer.getHealthUpgrade() == getDefaultConfig().getInt("upgrades.health.maxLevel")) {
            setItem(21, new ItemBuilder(Material.REDSTONE).setName(String.format("%sHealth", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, pmPlayer.getHealthUpgrade()), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem(), player -> player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED)));
        } else {
            setItem(21, new ItemBuilder(Material.REDSTONE).setName(String.format("%sHealth", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %d", ChatColor.DARK_GREEN, pmPlayer.getHealthUpgrade()), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, getDefaultConfig().getIntegerList("upgrades.health.cost").get(pmPlayer.getHealthUpgrade()))).toItem(), player -> {
                if (!(getDefaultConfig().get("upgrades.health.cost") != null && getDefaultConfig().getIntegerList("upgrades.health.cost").size() == getDefaultConfig().getInt("upgrades.health.maxLevel"))) {
                    p.sendMessage("The config is not set up correctly!");
                    PowermageCore.getInstance().getLogger().warning("The Health upgrade costs length are not equal to the max level!");
                    return;
                }
                if (pmPlayer.getHealthUpgrade() == getDefaultConfig().getInt("upgrades.health.maxLevel")) {
                    player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED));
                    return;
                }
                if (pmPlayer.getSouls() < getDefaultConfig().getIntegerList("upgrades.health.cost").get(pmPlayer.getHealthUpgrade())) {
                    p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
                    return;
                }
                pmPlayer.setSouls(pmPlayer.getSouls() - getDefaultConfig().getIntegerList("upgrades.health.cost").get(pmPlayer.getHealthUpgrade()));
                pmPlayer.setHealthUpgrade(pmPlayer.getHealthUpgrade() + 1);
                PowermagePlayer newpmPlayer = pmPlayer.save();
                getInventory().setItem(21, new ItemBuilder(Material.REDSTONE).setName(String.format("%sHealth", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                        .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, newpmPlayer.getHealthUpgrade()),
                                newpmPlayer.getHealthUpgrade() == getDefaultConfig().getInt("upgrades.health.maxLevel") ? String.format("%sMAX LEVEL", ChatColor.DARK_RED) :
                                        String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, getDefaultConfig().getIntegerList("upgrades.health.cost").get(newpmPlayer.getHealthUpgrade()))).toItem());
                player.sendMessage(String.format("%sYou have bought the %sHealth %supgrade for %s!", ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.DARK_GREEN, getDefaultConfig().getIntegerList("upgrades.health.cost").get(newpmPlayer.getHealthUpgrade()-1)));
                try {
                    player.setHealthScale(newpmPlayer.getClassType().getBaseHealth() + newpmPlayer.getHealthUpgrade() * getDefaultConfig().getInt("upgrades.health.healthPerLevel"));
                } catch (IllegalArgumentException ignored) {}
                try {
                    player.setHealth(newpmPlayer.getClassType().getBaseHealth() + newpmPlayer.getHealthUpgrade() * getDefaultConfig().getInt("upgrades.health.healthPerLevel"));
                } catch (IllegalArgumentException ignored) {}
            });
        }
        if (pmPlayer.getManaUpgrade() == getDefaultConfig().getInt("upgrades.mana.maxLevel")) {
            setItem(23, new ItemBuilder(Material.BLUE_DYE).setName(String.format("%sMana", ChatColor.BLUE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, pmPlayer.getManaUpgrade()), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem(), player -> player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED)));
        } else {
            setItem(23, new ItemBuilder(Material.BLUE_DYE).setName(String.format("%sMana", ChatColor.BLUE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %d", ChatColor.DARK_GREEN, pmPlayer.getManaUpgrade()), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, getDefaultConfig().getIntegerList("upgrades.mana.cost").get(pmPlayer.getManaUpgrade()))).toItem(), player -> {
                if (!(getDefaultConfig().get("upgrades.mana.cost") != null && getDefaultConfig().getIntegerList("upgrades.mana.cost").size() == getDefaultConfig().getInt("upgrades.mana.maxLevel"))) {
                    p.sendMessage("The config is not set up correctly!");
                    PowermageCore.getInstance().getLogger().warning("The Mana upgrade costs length are not equal to the max level!");
                    return;
                }
                if (pmPlayer.getManaUpgrade() == getDefaultConfig().getInt("upgrades.mana.maxLevel")) {
                    player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED));
                    return;
                }
                if (pmPlayer.getSouls() < getDefaultConfig().getIntegerList("upgrades.mana.cost").get(pmPlayer.getManaUpgrade())) {
                    p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
                    return;
                }
                pmPlayer.setSouls(pmPlayer.getSouls() - getDefaultConfig().getIntegerList("upgrades.mana.cost").get(pmPlayer.getManaUpgrade()));
                pmPlayer.setManaUpgrade(pmPlayer.getManaUpgrade() + 1);
                PowermagePlayer newpmPlayer = pmPlayer.save();
                getInventory().setItem(23, new ItemBuilder(Material.BLUE_DYE).setName(String.format("%sMana", ChatColor.BLUE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                        .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, newpmPlayer.getManaUpgrade()),
                                newpmPlayer.getManaUpgrade() == getDefaultConfig().getInt("upgrades.mana.maxLevel") ? String.format("%sMAX LEVEL", ChatColor.DARK_RED) :
                                        String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, getDefaultConfig().getIntegerList("upgrades.mana.cost").get(newpmPlayer.getManaUpgrade()))).toItem());
                player.sendMessage(String.format("%sYou have bought the %sMana %supgrade for %s!", ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.DARK_GREEN, getDefaultConfig().getIntegerList("upgrades.mana.cost").get(newpmPlayer.getManaUpgrade()-1)));
            });
        }
        if (pmPlayer.getSpeedUpgrade() == getDefaultConfig().getInt("upgrades.speed.maxLevel")) {
            setItem(25, new ItemBuilder(Material.SUGAR).setName(String.format("%sSpeed", ChatColor.WHITE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, pmPlayer.getSpeedUpgrade()), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem(), player -> player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED)));
        } else {
            setItem(25, new ItemBuilder(Material.SUGAR).setName(String.format("%sSpeed", ChatColor.WHITE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %d", ChatColor.DARK_GREEN, pmPlayer.getSpeedUpgrade()), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, getDefaultConfig().getIntegerList("upgrades.speed.cost").get(pmPlayer.getSpeedUpgrade()))).toItem(), player -> {
                // do if they dont exist error message
                if (!(getDefaultConfig().get("upgrades.speed.cost") != null && getDefaultConfig().getIntegerList("upgrades.speed.cost").size() == getDefaultConfig().getInt("upgrades.speed.maxLevel"))) {
                    p.sendMessage("The config is not set up correctly!");
                    PowermageCore.getInstance().getLogger().warning("The Speed upgrade costs length are not equal to the max level!");
                    return;
                }
                if (pmPlayer.getSpeedUpgrade() == getDefaultConfig().getInt("upgrades.speed.maxLevel")) {
                    player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED));
                    return;
                }
                if (pmPlayer.getSouls() < getDefaultConfig().getIntegerList("upgrades.speed.cost").get(pmPlayer.getSpeedUpgrade())) {
                    p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
                    return;
                }
                pmPlayer.setSouls(pmPlayer.getSouls() - getDefaultConfig().getIntegerList("upgrades.speed.cost").get(pmPlayer.getSpeedUpgrade()));
                pmPlayer.setSpeedUpgrade(pmPlayer.getSpeedUpgrade() + 1);
                PowermagePlayer newpmPlayer = pmPlayer.save();
                getInventory().setItem(25, new ItemBuilder(Material.SUGAR).setName(String.format("%sSpeed", ChatColor.WHITE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).setEnchantment(Enchantment.DURABILITY, 1)
                        .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, newpmPlayer.getSpeedUpgrade()),
                                newpmPlayer.getSpeedUpgrade() == getDefaultConfig().getInt("upgrades.speed.maxLevel") ? String.format("%sMAX LEVEL", ChatColor.DARK_RED) :
                                        String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, getDefaultConfig().getIntegerList("upgrades.speed.cost").get(newpmPlayer.getSpeedUpgrade()))).toItem());
                player.sendMessage(String.format("%sYou have bought the %sSpeed %supgrade for %s!", ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.DARK_GREEN, getDefaultConfig().getIntegerList("upgrades.speed.cost").get(newpmPlayer.getSpeedUpgrade()-1)));
                player.setWalkSpeed((float) (newpmPlayer.getClassType().getBaseSpeed() + (newpmPlayer.getSpeedUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel"))));
            });
        }
        setItem(getInventory().getSize() - 9, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> new MenuGUI(player).open(player));
        setItem(getInventory().getSize() - 5, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), HumanEntity::closeInventory);
    }
}