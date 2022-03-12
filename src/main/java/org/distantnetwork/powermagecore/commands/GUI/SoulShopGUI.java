package org.distantnetwork.powermagecore.commands.GUI;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;
import org.distantnetwork.powermagecore.utils.Builders.InventoryBuilder;
import org.distantnetwork.powermagecore.utils.Builders.ItemBuilder;

public class SoulShopGUI extends InventoryBuilder {
    public SoulShopGUI(Player p) {
        super((WeaponConfigManager.loadWeaponAmount() % 9 == 0 ? WeaponConfigManager.loadWeaponAmount() : WeaponConfigManager.loadWeaponAmount() + (9 - (WeaponConfigManager.loadWeaponAmount() % 9)))+9, String.format("%sPowermage Soul Shop", ChatColor.AQUA));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        if (WeaponConfigManager.loadWeaponIDs().length <= 0) return;
        for (Integer id : WeaponConfigManager.loadWeaponIDs()) {
            setItem(id, WeaponConfigManager.loadWeapon(id), player -> {
                player.sendMessage(ChatColor.GREEN + "You bought " + WeaponConfigManager.loadWeapon(id).getItemMeta().getDisplayName());
                player.getInventory().addItem(WeaponConfigManager.loadWeapon(id));
            });
        }
        setItem(getInventory().getSize() - 9, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> {
            new MenuGUI(player).open(player);
        });
        setItem(getInventory().getSize() - 5, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", org.bukkit.ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> {
            player.closeInventory();
        });
    }
}
