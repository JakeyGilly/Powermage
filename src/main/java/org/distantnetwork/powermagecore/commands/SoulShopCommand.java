package org.distantnetwork.powermagecore.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.Items.ConfigItem;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.WeaponsManager;

import java.io.File;
import java.util.logging.Level;

public class SoulShopCommand implements CommandExecutor {
    public static Inventory generateShop() {
        Inventory inventory = Bukkit.createInventory(null, 45, String.format("%sPowermage Soul Shop", ChatColor.AQUA));
        for (int i = 0; i < inventory.getSize(); i++) inventory.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        int counter = WeaponsManager.getConfigAmount(new File(PowermageCore.getInstance().getDataFolder(), String.format("weapons%s", File.separator)));

        for (int i = 0; i < counter; i++) {
            FileConfiguration file = WeaponsManager.getConfig(i);
            ItemStack item = ConfigItem.getItem(file);
            inventory.setItem(i, item);
        }
        return inventory;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("powermage.soulshop")) {
//                if (Integer.getInteger(PowermageCore.playerLevels.get(player.getUniqueId()).get(PowermageCore.playerClasses.get(player.getUniqueId())).values().toArray()[0].toString()) > 4) {
                    Inventory inventory = generateShop();
                    player.openInventory(inventory);
//                } else {
//                    player.sendMessage("You need to be level 5 in any class to use this shop!");
//                }
            }
        }
        return true;
    }
}
