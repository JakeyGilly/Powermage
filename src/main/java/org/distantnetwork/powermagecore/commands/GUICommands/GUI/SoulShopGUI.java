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

import java.io.File;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.*;

public class SoulShopGUI extends InventoryBuilder {
    public SoulShopGUI(Player p) {
        super((getFilesAmountInFolder("weapons") % 9 == 0 ? getFilesAmountInFolder("weapons") : getFilesAmountInFolder("weapons") + (9 - (getFilesAmountInFolder("weapons") % 9)))+9, String.format("%sPowermage Soul Shop", ChatColor.AQUA));
        for (int i = 0; i < getInventory().getSize(); i++) setItem(i, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem());
        File weaponsFolder = getFileFolder("weapons");
        if (weaponsFolder == null) throw new NullPointerException("Weapons folder is file");
        String[] list = weaponsFolder.list();
        if (getFilesAmountInFolder("weapons") <= 0 || list == null) {
            setItem(4, new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "No weapons found!").toItem());
            return;
        } else {
            int i = 0;
            for (String name : list) {
                WeaponItem weaponItem = new WeaponItem(getFileFile("weapons" + File.separator + name));
                setItem(i, weaponItem.getItem(), (player -> {
                    PowermagePlayer pmPlayer = new PowermagePlayer(player);
                    if (player.getInventory().firstEmpty() == -1) {
                        player.sendMessage(ChatColor.RED + "Your inventory is full!");
                        return;
                    } else if (pmPlayer.getSouls() < weaponItem.getPrice()) {
                        player.sendMessage(ChatColor.RED + "You don't have enough souls!");
                        return;
                    }
                    pmPlayer.setSouls(pmPlayer.getSouls() - weaponItem.getPrice());
                    pmPlayer.save();
                    weaponItem.give(player);
                    player.sendMessage(ChatColor.GREEN + "You bought " + weaponItem.getFormattedName() + ChatColor.GREEN + " for " + weaponItem.getPrice() + " souls!");
                }));
                i++;
            }
        }
        setItem(getInventory().getSize() - 9, new ItemBuilder(Material.ARROW).setName(String.format("%sBack to Main Menu", ChatColor.GRAY)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), player -> new MenuGUI(player).open(player));
        setItem(getInventory().getSize() - 5, new ItemBuilder(Material.BARRIER).setName(String.format("%sClose Menu", ChatColor.RED)).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).toItem(), HumanEntity::closeInventory);
    }
}
