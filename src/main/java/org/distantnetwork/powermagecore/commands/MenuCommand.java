package org.distantnetwork.powermagecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.distantnetwork.powermagecore.Classes;
import org.distantnetwork.powermagecore.PowermageCore;

import java.util.*;

public class MenuCommand implements CommandExecutor, Listener {
    public static Inventory generateMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 45, "&c&lPowerMage");
        ItemStack blackGlassItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = blackGlassItem.getItemMeta();
        meta.setDisplayName(" ");
        blackGlassItem.setItemMeta(meta);
        ItemStack grayGlassItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        meta = grayGlassItem.getItemMeta();
        meta.setDisplayName(" ");
        grayGlassItem.setItemMeta(meta);
        for (int i = 0; i < 44; i++) inv.setItem(i, blackGlassItem);
        for (int i = 19; i < 25; i++) inv.setItem(i, grayGlassItem);
        ItemStack playerStatsItem = new ItemStack(Material.PLAYER_HEAD);
        meta = playerStatsItem.getItemMeta();
        meta.setDisplayName("&cYour Stats");
        // this line is WAY TOO LONG
        meta.setLore(Arrays.asList(String.format("&fWarrior Level: &60%s", PowermageCore.classesEmojis.get(Classes.WARRIOR)), String.format("&fTank Level: &60%s", PowermageCore.classesEmojis.get(Classes.TANK)), String.format("&fArcher Level: &60%s", PowermageCore.classesEmojis.get(Classes.ARCHER)), String.format("&fWizard Level: &60%s", PowermageCore.classesEmojis.get(Classes.WIZARD)), "&fBalance: &60 Coins", "&fSouls: &b0 Souls", "&fKills: &c0", "&fDeaths: &c0"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        playerStatsItem.setItemMeta(meta);
        inv.setItem(13, playerStatsItem);
        ItemStack upgradeItem = new ItemStack(Material.SUGAR);
        meta = upgradeItem.getItemMeta();
        meta.setDisplayName("&cUpgrade Stats");
        meta.setLore(Arrays.asList("&7Become stronger by upgrading", "&7your stats using souls.", " ", "&eClick to view upgrades"));
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        upgradeItem.setItemMeta(meta);
        inv.setItem(21, upgradeItem);
        ItemStack shopItem = new ItemStack(Material.GOLD_INGOT);
        meta = shopItem.getItemMeta();
        meta.setDisplayName("&6Item Shop");
        meta.setLore(Arrays.asList("&7Buy powerful weapons, armor", "&7and gadgets for a price", " ", "&eClick to view the shop"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        shopItem.setItemMeta(meta);
        inv.setItem(22, shopItem);
        ItemStack switchClassItem = new ItemStack(Material.NETHER_STAR);
        meta = switchClassItem.getItemMeta();
        meta.setDisplayName("&cSwitch Classes");
        meta.setLore(Arrays.asList("&7Don't like your current class?", "&7Switch to a another", "&7powermage class!", " ", "&eClick to view"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        switchClassItem.setItemMeta(meta);
        inv.setItem(23, switchClassItem);
        ItemStack soulShopItem = new ItemStack(Material.BEACON);
        meta = soulShopItem.getItemMeta();
        meta.setDisplayName("&cSoul Shop");
        if (PowermageCore.playerLevels.get(player.getUniqueId()) != null) {
            // this will probilby break everything
            if (Integer.getInteger(PowermageCore.playerLevels.get(player.getUniqueId()).get(PowermageCore.playerClasses.get(player.getUniqueId())).values().toArray()[0].toString()) > 5) {
                meta.setLore(Arrays.asList("&7Got too many &bSouls&7?", "&7Spend your &bsouls &7to get special items", " ", "&eClick to view the shop"));
            } else {
                meta.setLore(Arrays.asList("&7Got too many &bSouls&7?", "&7Spend your &bsouls &7to get special items", "&4Unlocked at level 5 of any class", " ", "&eClick to view the shop"));
            }
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        soulShopItem.setItemMeta(meta);
        inv.setItem(24, soulShopItem);
        ItemStack ranksShopItem = new ItemStack(Material.GOLD_NUGGET);
        meta = ranksShopItem.getItemMeta();
        meta.setDisplayName("&eRanks Shop");
        meta.setLore(Arrays.asList("&7Buy ranks to get access to", "&7special features on the server", " ", "&eClick to view the shop"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ranksShopItem.setItemMeta(meta);
        inv.setItem(39, ranksShopItem);
        ItemStack closeItem = new ItemStack(Material.BARRIER);
        meta = closeItem.getItemMeta();
        meta.setDisplayName("&cClose Menu");
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        closeItem.setItemMeta(meta);
        inv.setItem(40, closeItem);
        ItemStack discordItem = new ItemStack(Material.LIGHT_BLUE_DYE);
        meta = discordItem.getItemMeta();
        meta.setDisplayName("&9Join our Discord");
        meta.setLore(Arrays.asList("&7Join our Discord to get", "&7updates on the server", " ", "&eClick to join"));
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        discordItem.setItemMeta(meta);
        inv.setItem(41, discordItem);
        return inv;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("powermage.menu")) {
                Inventory menu = generateMenu(player);
                player.openInventory(menu);
            }
        }
        return true;
    }
}
