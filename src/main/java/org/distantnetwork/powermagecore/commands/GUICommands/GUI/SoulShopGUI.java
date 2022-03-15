package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerSouls;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;
import org.distantnetwork.powermagecore.utils.Builders.InventoryBuilder;
import org.distantnetwork.powermagecore.utils.Builders.ItemBuilder;

public class SoulShopGUI extends InventoryBuilder {
    public SoulShopGUI(Player p) {
        super((WeaponConfigManager.loadWeaponAmount() % 9 == 0 ? WeaponConfigManager.loadWeaponAmount() : WeaponConfigManager.loadWeaponAmount() + (9 - (WeaponConfigManager.loadWeaponAmount() % 9)))+9, String.format("%sPowermage Soul Shop", ChatColor.AQUA));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        if (WeaponConfigManager.loadWeaponAmount() <= 0) {
            setItem(4, new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "No weapons found!").toItem());
        }
        for (String name : WeaponConfigManager.loadWeaponNames()) {
            Integer weaponID = WeaponConfigManager.getWeaponId(name);
            if (weaponID == null) continue;
            ItemStack weapon = WeaponConfigManager.loadWeapon(weaponID);
            if (weapon == null) continue;
            setItem(weaponID, weapon, player -> {
                if (PlayerSouls.getSouls(player.getUniqueId()) >= (int)WeaponConfigManager.getValue(weaponID, "price")) {
                    PlayerSouls.removeSouls(player.getUniqueId(), WeaponConfigManager.getValue(weaponID, "price"));
                    player.sendMessage(ChatColor.GREEN + "You bought " + weapon.getItemMeta().getDisplayName() + "for " + WeaponConfigManager.getValue(weaponID, "price") + " souls.");
                    player.getInventory().addItem(weapon);
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have enough souls!");
                }
            });
        }
        setItem(getInventory().getSize() - 9, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> new MenuGUI(player).open(player));
        setItem(getInventory().getSize() - 5, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", org.bukkit.ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), HumanEntity::closeInventory);
    }
}
