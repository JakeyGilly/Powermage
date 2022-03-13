package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;
import org.distantnetwork.powermagecore.utils.Builders.InventoryBuilder;
import org.distantnetwork.powermagecore.utils.Builders.ItemBuilder;

public class SoulShopGUI extends InventoryBuilder {
    public SoulShopGUI(Player p) {
        super((WeaponConfigManager.loadWeaponAmount() % 9 == 0 ? WeaponConfigManager.loadWeaponAmount() : WeaponConfigManager.loadWeaponAmount() + (9 - (WeaponConfigManager.loadWeaponAmount() % 9)))+9, String.format("%sPowermage Soul Shop", ChatColor.AQUA));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        Integer[] weaponIds = WeaponConfigManager.loadWeaponIDs();
        if (weaponIds.length <= 0) return;
        for (Integer id : weaponIds) {
            ItemStack weapon = WeaponConfigManager.loadWeapon(id);
            setItem(id, weapon, player -> {
                if (PowermageCore.playerSouls.get(player.getUniqueId()) != null && PowermageCore.playerSouls.get(player.getUniqueId()) >= (int)WeaponConfigManager.getValue(id, "price")) {
                    PowermageCore.playerSouls.put(player.getUniqueId(), PowermageCore.playerSouls.get(player.getUniqueId()) - (int)WeaponConfigManager.getValue(id, "price"));
                    player.sendMessage(ChatColor.GREEN + "You bought " + weapon.getItemMeta().getDisplayName() + "for " + (int)WeaponConfigManager.getValue(id, "price") + " souls.");
                    player.getInventory().addItem(weapon);
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough souls!");
                }
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
