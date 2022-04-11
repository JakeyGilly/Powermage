package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.distantnetwork.powermagecore.builders.InventoryBuilder;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.ShopItem;

public class ShopGUI extends InventoryBuilder {
    public ShopGUI(Player p) {
        super((ShopItem.getShopItems().size() % 9 == 0 ? ShopItem.getShopItems().size() : ShopItem.getShopItems().size() + (9 - (ShopItem.getShopItems().size() % 9)))+9, String.format("%sPowermage Shop", ChatColor.GOLD));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        int i = 0;
        if (ShopItem.getShopItems().size() < 1) setItem(i, new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "No items found!").toItem());
        for (ShopItem shopItem : ShopItem.getShopItems()) {
            setItem(i, shopItem.getItem(), player -> {
                PowermagePlayer pmplayer = new PowermagePlayer(player);
                if (shopItem.getPrice() > pmplayer.getMoney()) {
                    player.sendMessage(ChatColor.RED + "You don't have enough coins!");
                    return;
                }
                if (player.getInventory().firstEmpty() == -1) {
                    player.sendMessage(ChatColor.RED + "Your inventory is full!");
                    return;
                }
                shopItem.give(player);
                pmplayer.setMoney(pmplayer.getMoney() - shopItem.getPrice());
                player.sendMessage(ChatColor.GREEN + "You bought " + shopItem.getName() + " for " + shopItem.getPrice() + " coins!");
                pmplayer.save();
            });
            i++;
        }
        setItem(getInventory().getSize() - 9, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> new MenuGUI(player).open(player));
        setItem(getInventory().getSize() - 5, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), HumanEntity::closeInventory);
    }
}
