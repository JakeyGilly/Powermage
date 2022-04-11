package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.distantnetwork.powermagecore.builders.InventoryBuilder;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.WeaponItem;

public class SoulShopGUI extends InventoryBuilder {
    public SoulShopGUI(Player p) {
        super((WeaponItem.getWeapons().size() % 9 == 0 ? WeaponItem.getWeapons().size() : WeaponItem.getWeapons().size() + (9 - (WeaponItem.getWeapons().size() % 9)))+9, String.format("%sPowermage Soul Shop", ChatColor.AQUA));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        int i = 0;
        if (WeaponItem.getWeapons().size() < 1) setItem(i, new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "No weapons found!").toItem());
        for (WeaponItem weaponItem : WeaponItem.getWeapons()) {
            setItem(i, weaponItem.getItem(), player -> {
                PowermagePlayer pmplayer = new PowermagePlayer(player);
                if (weaponItem.getPrice() > pmplayer.getSouls()) {
                    player.sendMessage(ChatColor.RED + "You don't have enough souls!");
                    return;
                }
                if (player.getInventory().firstEmpty() == -1) {
                    player.sendMessage(ChatColor.RED + "Your inventory is full!");
                    return;
                }
                weaponItem.give(player);
                pmplayer.setSouls(pmplayer.getSouls() - weaponItem.getPrice());
                player.sendMessage(ChatColor.GREEN + "You bought " + weaponItem.getName() + " for " + weaponItem.getPrice() + " souls!");
                pmplayer.save();
            });
            i++;
        }
        setItem(getInventory().getSize() - 9, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> new MenuGUI(player).open(player));
        setItem(getInventory().getSize() - 5, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), HumanEntity::closeInventory);
    }
}
