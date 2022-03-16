package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Builders.InventoryBuilder;
import org.distantnetwork.powermagecore.utils.Builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerClasses;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerSouls;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerUpgrades;
import org.distantnetwork.powermagecore.utils.Config.MainConfigManager;
import org.distantnetwork.powermagecore.utils.Enums.Classes;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;

public class UpgradeGUI extends InventoryBuilder {
    //TODO: implement money & soul spending
    //TODO: implement % or amount increase in config per upgrade

    public UpgradeGUI(Player p) {
        super(54, String.format("%sUpgrade Stats", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++)
            setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setName(" ").toItem());
        if (PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH) == (int) MainConfigManager.getValue("upgrades.strength.maxLevel")) {
            setItem(19, new ItemBuilder(Material.IRON_SWORD).setName(String.format("%sStrength", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH)), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem(), player -> player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED)));
        } else {
            setItem(19, new ItemBuilder(Material.IRON_SWORD).setName(String.format("%sStrength", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %d", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.strength.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH)))).toItem(), player -> {
                if (!(MainConfigManager.config.get("upgrades.strength.cost") != null && MainConfigManager.config.getIntegerList("upgrades.strength.cost").size() == MainConfigManager.config.getInt("upgrades.strength.maxLevel"))) {
                    p.sendMessage("The config is not set up correctly!");
                    PowermageCore.getInstance().getLogger().warning("The Strength upgrade costs length are not equal to the max level!");
                    return;
                }
                if (PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH) == MainConfigManager.config.getInt("upgrades.strength.maxLevel")) {
                    player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED));
                    return;
                }
                if (PlayerSouls.getSouls(p.getUniqueId()) < MainConfigManager.config.getIntegerList("upgrades.strength.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH))) {
                    p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
                    return;
                }
                PlayerSouls.removeSouls(p.getUniqueId(), MainConfigManager.config.getIntegerList("upgrades.strength.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH)));
                PlayerUpgrades.addUpgrade(p.getUniqueId(), Upgrades.STRENGTH);
                getInventory().setItem(19, new ItemBuilder(Material.IRON_SWORD).setName(String.format("%sStrength", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                        .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH)),
                                PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH) == (int) MainConfigManager.getValue("upgrades.strength.maxLevel") ? String.format("%sMAX LEVEL", ChatColor.DARK_RED) :
                                        String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.strength.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH)))).toItem());
                player.sendMessage(String.format("%sYou have bought the %sStrength %supgrade for %s!", ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.DARK_GREEN, MainConfigManager.config.getIntegerList("upgrades.strength.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.STRENGTH)-1)));
            });
        }
        if (PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH) == (int) MainConfigManager.getValue("upgrades.health.maxLevel")) {
            setItem(21, new ItemBuilder(Material.REDSTONE).setName(String.format("%sHealth", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH)), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem(), player -> player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED)));
        } else {
            setItem(21, new ItemBuilder(Material.REDSTONE).setName(String.format("%sHealth", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %d", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.health.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH)))).toItem(), player -> {
                if (!(MainConfigManager.config.get("upgrades.health.cost") != null && MainConfigManager.config.getIntegerList("upgrades.health.cost").size() == MainConfigManager.config.getInt("upgrades.health.maxLevel"))) {
                    p.sendMessage("The config is not set up correctly!");
                    PowermageCore.getInstance().getLogger().warning("The Health upgrade costs length are not equal to the max level!");
                    return;
                }
                if (PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH) == MainConfigManager.config.getInt("upgrades.health.maxLevel")) {
                    player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED));
                    return;
                }
                if (PlayerSouls.getSouls(p.getUniqueId()) < MainConfigManager.config.getIntegerList("upgrades.health.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH))) {
                    p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
                    return;
                }
                PlayerSouls.removeSouls(p.getUniqueId(), MainConfigManager.config.getIntegerList("upgrades.health.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH)));
                PlayerUpgrades.addUpgrade(p.getUniqueId(), Upgrades.HEALTH);
                getInventory().setItem(21, new ItemBuilder(Material.REDSTONE).setName(String.format("%sHealth", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                        .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH)),
                                PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH) == (int) MainConfigManager.getValue("upgrades.health.maxLevel") ? String.format("%sMAX LEVEL", ChatColor.DARK_RED) :
                                        String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.health.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH)))).toItem());
                player.sendMessage(String.format("%sYou have bought the %sHealth %supgrade for %s!", ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.DARK_GREEN, MainConfigManager.config.getIntegerList("upgrades.health.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.HEALTH)-1)));
                try {
                    player.setHealthScale(Classes.getHealth(PlayerClasses.getClasses(player.getUniqueId())) + (PlayerUpgrades.getUpgradeLevel(player.getUniqueId(), Upgrades.HEALTH) * MainConfigManager.config.getDouble("upgrades.health.healthPerLevel")));
                } catch (IllegalArgumentException ignored) {}
                try {
                    player.setHealth(Classes.getHealth(PlayerClasses.getClasses(player.getUniqueId())) + (PlayerUpgrades.getUpgradeLevel(player.getUniqueId(), Upgrades.HEALTH) * MainConfigManager.config.getDouble("upgrades.health.healthPerLevel")));
                } catch (IllegalArgumentException ignored) {}
            });
        }
        if (PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA) == (int) MainConfigManager.getValue("upgrades.mana.maxLevel")) {
            setItem(23, new ItemBuilder(Material.BLUE_DYE).setName(String.format("%sMana", ChatColor.BLUE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA)), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem(), player -> player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED)));
        } else {
            setItem(23, new ItemBuilder(Material.BLUE_DYE).setName(String.format("%sMana", ChatColor.BLUE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %d", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.mana.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA)))).toItem(), player -> {
                if (!(MainConfigManager.config.get("upgrades.mana.cost") != null && MainConfigManager.config.getIntegerList("upgrades.mana.cost").size() == MainConfigManager.config.getInt("upgrades.mana.maxLevel"))) {
                    p.sendMessage("The config is not set up correctly!");
                    PowermageCore.getInstance().getLogger().warning("The Mana upgrade costs length are not equal to the max level!");
                    return;
                }
                if (PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA) == MainConfigManager.config.getInt("upgrades.mana.maxLevel")) {
                    player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED));
                    return;
                }
                if (PlayerSouls.getSouls(p.getUniqueId()) < MainConfigManager.config.getIntegerList("upgrades.mana.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA))) {
                    p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
                    return;
                }
                PlayerSouls.removeSouls(p.getUniqueId(), MainConfigManager.config.getIntegerList("upgrades.mana.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA)));
                PlayerUpgrades.addUpgrade(p.getUniqueId(), Upgrades.MANA);
                getInventory().setItem(23, new ItemBuilder(Material.BLUE_DYE).setName(String.format("%sMana", ChatColor.BLUE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                        .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA)),
                                PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA) == (int) MainConfigManager.getValue("upgrades.mana.maxLevel") ? String.format("%sMAX LEVEL", ChatColor.DARK_RED) :
                                        String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.mana.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA)))).toItem());
                player.sendMessage(String.format("%sYou have bought the %sMana %supgrade for %s!", ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.DARK_GREEN, MainConfigManager.config.getIntegerList("upgrades.mana.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.MANA)-1)));
            });
        }
        if (PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED) == (int) MainConfigManager.getValue("upgrades.speed.maxLevel")) {
            setItem(25, new ItemBuilder(Material.SUGAR).setName(String.format("%sSpeed", ChatColor.WHITE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED)), String.format("%sMAX LEVEL", ChatColor.DARK_RED)).toItem(), player -> player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED)));
        } else {
            setItem(25, new ItemBuilder(Material.SUGAR).setName(String.format("%sSpeed", ChatColor.WHITE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                    .setLore(String.format("%sLevel %d", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED)), String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.speed.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED)))).toItem(), player -> {
                if (!(MainConfigManager.config.get("upgrades.speed.cost") != null && MainConfigManager.config.getIntegerList("upgrades.speed.cost").size() == MainConfigManager.config.getInt("upgrades.speed.maxLevel"))) {
                    p.sendMessage("The config is not set up correctly!");
                    PowermageCore.getInstance().getLogger().warning("The Speed upgrade costs length are not equal to the max level!");
                    return;
                }
                if (PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED) == MainConfigManager.config.getInt("upgrades.speed.maxLevel")) {
                    player.sendMessage(String.format("%sYou have reached the max level!", ChatColor.RED));
                    return;
                }
                if (PlayerSouls.getSouls(p.getUniqueId()) < MainConfigManager.config.getIntegerList("upgrades.speed.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED))) {
                    p.sendMessage(String.format("%sYou don't have enough souls!", ChatColor.RED));
                    return;
                }
                PlayerSouls.removeSouls(p.getUniqueId(), MainConfigManager.config.getIntegerList("upgrades.speed.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED)));
                PlayerUpgrades.addUpgrade(p.getUniqueId(), Upgrades.SPEED);
                getInventory().setItem(25, new ItemBuilder(Material.SUGAR).setName(String.format("%sSpeed", ChatColor.WHITE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                        .setLore(String.format("%sLevel %s", ChatColor.DARK_GREEN, PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED)),
                                PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED) == (int) MainConfigManager.getValue("upgrades.speed.maxLevel") ? String.format("%sMAX LEVEL", ChatColor.DARK_RED) :
                                        String.format("%sSouls for next upgrade: %s", ChatColor.GOLD, MainConfigManager.config.getIntegerList("upgrades.speed.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED)))).toItem());
                player.sendMessage(String.format("%sYou have bought the %sSpeed %supgrade for %s!", ChatColor.DARK_GREEN, ChatColor.RED, ChatColor.DARK_GREEN, MainConfigManager.config.getIntegerList("upgrades.speed.cost").get(PlayerUpgrades.getUpgradeLevel(p.getUniqueId(), Upgrades.SPEED)-1)));
                player.setWalkSpeed((float) (Classes.getWalkSpeed(PlayerClasses.getClasses(player.getUniqueId())) + (PlayerUpgrades.getUpgradeLevel(player.getUniqueId(), Upgrades.SPEED) * MainConfigManager.config.getDouble("upgrades.speed.speedPerLevel"))));
            });
        }
        setItem(getInventory().getSize() - 9, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> new MenuGUI(player).open(player));
        setItem(getInventory().getSize() - 5, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), HumanEntity::closeInventory);
    }
}