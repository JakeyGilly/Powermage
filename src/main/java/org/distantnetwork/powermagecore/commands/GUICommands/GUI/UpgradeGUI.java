package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Builders.InventoryBuilder;
import org.distantnetwork.powermagecore.utils.Builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerUpgrades;
import org.distantnetwork.powermagecore.utils.Config.MainConfigManager;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;

public class UpgradeGUI extends InventoryBuilder {
    //TODO: implement money & soul spending
    //TODO: implement % or amount increase in config per upgrade

    public UpgradeGUI(Player p) {
        super(54, String.format("%sUpgrade Stats", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName(" ").toItem());
        PlayerUpgrades.load();
        setItem(19, new ItemBuilder(Material.IRON_SWORD).setName(String.format("%sStrength", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.STRENGTH) == null ? 0 : PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.STRENGTH)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.strength.cost").get(0))).toItem(), player -> {
            if (PowermageCore.playerSouls.containsKey(p.getUniqueId()) && PowermageCore.playerSouls.get(p.getUniqueId()) >= MainConfigManager.config.getIntegerList("upgrades.strength.cost").get(0)) {//TODO: make this change per level
                PowermageCore.playerSouls.put(p.getUniqueId(), PowermageCore.playerSouls.get(p.getUniqueId()) - MainConfigManager.config.getIntegerList("upgrades.strength.cost").get(0));
                if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.STRENGTH) == null || PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.STRENGTH) == 0) {
                    PowermageCore.playerUpgrades.get(p.getUniqueId()).put(Upgrades.STRENGTH, 1);
                } else {
                    if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.STRENGTH) < (int) MainConfigManager.getValue("upgrades.strength.maxLevel")) {
                        PowermageCore.playerUpgrades.get(p.getUniqueId()).put(Upgrades.STRENGTH, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.STRENGTH) + 1);
                    }
                }
                PlayerUpgrades.save();
                if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.STRENGTH) == (int) MainConfigManager.getValue("upgrades.strength.maxLevel")) {
                    getInventory().setItem(19, new ItemBuilder(Material.IRON_SWORD).setName(String.format("%sStrength", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                            .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN ,PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.STRENGTH)), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem());
                } else {
                    getInventory().setItem(19, new ItemBuilder(Material.IRON_SWORD).setName(String.format("%sStrength", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                            .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.STRENGTH)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.strength.cost").get(0))).toItem());
                }
            } else {
                p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
            }
        });
        setItem(21, new ItemBuilder(Material.REDSTONE).setName(String.format("%sHealth", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.HEALTH) == null ? 0 : PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.HEALTH)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.health.cost").get(0))).toItem(), player -> {
            if (PowermageCore.playerSouls.containsKey(p.getUniqueId()) && PowermageCore.playerSouls.get(p.getUniqueId()) >= MainConfigManager.config.getIntegerList("upgrades.health.cost").get(0)) {//TODO: make this change per level
                PowermageCore.playerSouls.put(p.getUniqueId(), PowermageCore.playerSouls.get(p.getUniqueId()) - MainConfigManager.config.getIntegerList("upgrades.health.cost").get(0));
                if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.HEALTH) == null || PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.HEALTH) == 0) {
                    PowermageCore.playerUpgrades.get(p.getUniqueId()).put(Upgrades.HEALTH, 1);
                } else {
                    if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.HEALTH) < (int) MainConfigManager.getValue("upgrades.health.maxLevel")) {
                        PowermageCore.playerUpgrades.get(p.getUniqueId()).put(Upgrades.HEALTH, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.HEALTH) + 1);
                    }
                }
                PlayerUpgrades.save();
                if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.HEALTH) == (int) MainConfigManager.getValue("upgrades.health.maxLevel")) {
                    getInventory().setItem(21, new ItemBuilder(Material.REDSTONE).setName(String.format("%sHealth", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                            .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.HEALTH)), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem());
                } else {
                    getInventory().setItem(21, new ItemBuilder(Material.REDSTONE).setName(String.format("%sHealth", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                            .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.HEALTH)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.health.cost").get(0))).toItem());
                }
            } else {
                p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
            }
        });
        setItem(23, new ItemBuilder(Material.BLUE_DYE).setName(String.format("%sMana", ChatColor.AQUA)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.MANA) == null ? 0 : PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.MANA)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.mana.cost").get(0))).toItem(), player -> {
            if (PowermageCore.playerSouls.containsKey(p.getUniqueId()) && PowermageCore.playerSouls.get(p.getUniqueId()) >= MainConfigManager.config.getIntegerList("upgrades.mana.cost").get(0)) {//TODO: make this change per level
                PowermageCore.playerSouls.put(p.getUniqueId(), PowermageCore.playerSouls.get(p.getUniqueId()) - MainConfigManager.config.getIntegerList("upgrades.mana.cost").get(0));
                if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.MANA) == null || PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.MANA) == 0) {
                    PowermageCore.playerUpgrades.get(p.getUniqueId()).put(Upgrades.MANA, 1);
                } else {
                    if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.MANA) < (int) MainConfigManager.getValue("upgrades.mana.maxLevel")) {
                        PowermageCore.playerUpgrades.get(p.getUniqueId()).put(Upgrades.MANA, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.MANA) + 1);
                    }
                }
                PlayerUpgrades.save();
                if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.MANA) == (int) MainConfigManager.getValue("upgrades.mana.maxLevel")) {
                    getInventory().setItem(23, new ItemBuilder(Material.BLUE_DYE).setName(String.format("%sMana", ChatColor.AQUA)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                            .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.MANA)), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem());
                } else {
                    getInventory().setItem(23, new ItemBuilder(Material.BLUE_DYE).setName(String.format("%sMana", ChatColor.AQUA)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                            .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.MANA)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.mana.cost").get(0))).toItem());
                }
            } else {
                p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
            }
        });
        setItem(25, new ItemBuilder(Material.SUGAR).setName(String.format("%sSpeed", ChatColor.WHITE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.SPEED) == null ? 0 : PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.SPEED)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.speed.cost").get(0))).toItem(), player -> {
            if (PowermageCore.playerSouls.containsKey(p.getUniqueId()) && PowermageCore.playerSouls.get(p.getUniqueId()) >= MainConfigManager.config.getIntegerList("upgrades.speed.cost").get(0)) {//TODO: make this change per level
                PowermageCore.playerSouls.put(p.getUniqueId(), PowermageCore.playerSouls.get(p.getUniqueId()) - MainConfigManager.config.getIntegerList("upgrades.speed.cost").get(0));
                if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.SPEED) == null || PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.SPEED) == 0) {
                    PowermageCore.playerUpgrades.get(p.getUniqueId()).put(Upgrades.SPEED, 1);
                } else {
                    if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.SPEED) < (int) MainConfigManager.getValue("upgrades.speed.maxLevel")) {
                        PowermageCore.playerUpgrades.get(p.getUniqueId()).put(Upgrades.SPEED, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.SPEED) + 1);
                    }
                }
                PlayerUpgrades.save();
                if (PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.SPEED) == (int) MainConfigManager.getValue("upgrades.speed.maxLevel")) {
                    getInventory().setItem(25, new ItemBuilder(Material.SUGAR).setName(String.format("%sSpeed", ChatColor.WHITE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                            .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.SPEED)), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem());
                } else {
                    getInventory().setItem(25, new ItemBuilder(Material.SUGAR).setName(String.format("%sSpeed", ChatColor.WHITE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                            .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PowermageCore.playerUpgrades.get(p.getUniqueId()).get(Upgrades.SPEED)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.speed.cost").get(0))).toItem());
                }
            } else {
                p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
            }
        });
        setItem(getInventory().getSize() - 9, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", net.md_5.bungee.api.ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> {
            new MenuGUI(player).open(player);
        });
        setItem(getInventory().getSize() - 5, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", org.bukkit.ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> {
            player.closeInventory();
        });
    }
}
