package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.*;
import org.distantnetwork.powermagecore.utils.Config.MainConfigManager;
import org.distantnetwork.powermagecore.utils.Config.NonFileHashMaps.PlayerKillStreak;
import org.distantnetwork.powermagecore.utils.Enums.Classes;
import org.distantnetwork.powermagecore.utils.Builders.InventoryBuilder;
import org.distantnetwork.powermagecore.utils.Builders.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public class MenuGUI extends InventoryBuilder {
    public MenuGUI(Player p) {
        super(45, String.format("%sPowermage", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        ItemStack item;
        List<String> lore = new ArrayList<>();
        for (Classes c : Classes.values()) {
            lore.add(String.format("%s%s Level: %s%d%s", ChatColor.DARK_AQUA, c.name().substring(0, 1).toUpperCase() + c.name().substring(1).toLowerCase(), ChatColor.GOLD, PlayerLevels.getPlayerLevel(p.getUniqueId(), c), MainConfigManager.config.getConfigurationSection("classEmojis").get(Classes.getEmojiName(c))));
        }
        lore.add(String.format("%sBalance: %s%s Coins", ChatColor.GRAY, ChatColor.GOLD, PlayerCoins.getCoins(p.getUniqueId())));
        lore.add(String.format("%sSouls: %s%s Souls", ChatColor.GRAY, ChatColor.AQUA, PlayerSouls.getSouls(p.getUniqueId())));
        lore.add(String.format("%sKills: %s%s", ChatColor.GRAY, ChatColor.RED, PlayerKills.getKills(p.getUniqueId())));
        lore.add(String.format("%sKill Streak: %s%s", ChatColor.GRAY, ChatColor.RED, PlayerKillStreak.getKillStreak(p.getUniqueId())));
        lore.add(String.format("%sDeaths: %s%s", ChatColor.GRAY, ChatColor.RED, PlayerDeaths.getDeaths(p.getUniqueId())));
        item = new ItemBuilder(Material.PLAYER_HEAD).setName(String.format("%sYour Stats", ChatColor.GREEN)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setSkullOwner(p.getName()).setLore(lore).toItem();
        ItemStack finalItem = item;
        setItem(13, item);
        item = new ItemBuilder(Material.SUGAR).setName(String.format("%sUpgrade Stats", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
                .setLore(String.format("%sBecome stronger by upgrading", ChatColor.GRAY), String.format("%syour stats using souls.", ChatColor.GRAY), " ", String.format("%sClick to view upgrades", ChatColor.GOLD))
                .toItem();
        setItem(21, item, player -> new UpgradeGUI(player).open(player));
        item = new ItemBuilder(Material.GOLD_INGOT).setName(String.format("%sItem Shop", ChatColor.GOLD)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sBuy powerful weapons, armor", ChatColor.GRAY), String.format("%sand gadgets for a price", ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD))
                .toItem();
        setItem(22, item); // , player -> new ShopGUI().open(player));
        item = new ItemBuilder(Material.NETHER_STAR).setName(String.format("%sSwitch Classes", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sDon't like your current class?", ChatColor.GRAY), String.format("%sSwitch to a another", ChatColor.GRAY), String.format("%sPowermage class!", ChatColor.GRAY), " ", String.format("%sClick to view", ChatColor.GOLD))
                .toItem();
        setItem(23, item, player -> new ClassGUI(player).open(player));
        ItemBuilder itembuild = new ItemBuilder(Material.BEACON).setName(String.format("%sSoul Shop", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).setLore(String.format("%sGot too many %sSouls%s?", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), String.format("%sSpend your %sSouls %sto get special items", ChatColor.GRAY, ChatColor.AQUA, ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD));
        for (Classes c : Classes.values()) if (PlayerLevels.getPlayerLevel(p.getUniqueId(), c) >= 5) {
            itembuild.setLore(String.format("%sClick to view the shop", ChatColor.GOLD));
            setItem(24, itembuild.toItem(), player -> new SoulShopGUI(player).open(player));
        }
        if (getInventory().getItem(24) == null) {
            itembuild.addLoreLine(String.format("%sUnlocked at level 5 of any class", ChatColor.DARK_RED), 3);
            setItem(24, itembuild.toItem());
        }
        item = new ItemBuilder(Material.GOLD_NUGGET).setName(String.format("%sRanks Shop", ChatColor.GOLD)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                .setLore(String.format("%sBuy ranks to get access to", ChatColor.GRAY), String.format("%sspecial features on the server", ChatColor.GRAY), " ", String.format("%sClick to view the shop", ChatColor.GOLD))
                .toItem();
        setItem(39, item, player -> {
            player.closeInventory();
            player.performCommand("store");
        });
        setItem(40, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), HumanEntity::closeInventory);
        setItem(41, new ItemBuilder(Material.LIGHT_BLUE_DYE).setName(String.format("%sDiscord", ChatColor.BLUE)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1).toItem(), player -> {
            player.closeInventory();
            player.performCommand("discord");
        });
    }
}
