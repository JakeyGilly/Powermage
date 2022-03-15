package org.distantnetwork.powermagecore.commands.AdminCommands.GiveWeaponsCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;

public class GiveWeaponCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player;
        int amount;
        if (args.length > 0 && sender instanceof Player) {
            if (args.length > 1) {
                try {
                    amount = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Invalid amount!");
                    return true;
                }
            } else {
                amount = 1;
            }
            if (args.length > 2) {
                try {
                    player = Bukkit.getPlayer(args[2]);
                } catch (ClassCastException e) {
                    sender.sendMessage(ChatColor.RED + "Invalid player!");
                    return true;
                }
            } else {
                player = (Player) sender;
            }
            for (String weaponName : WeaponConfigManager.loadWeaponNames()) {
                if (weaponName.equalsIgnoreCase(args[0])) {
                    Integer i = WeaponConfigManager.getWeaponId(weaponName);
                    if (i != null) {
                        ItemStack item = WeaponConfigManager.loadWeapon(i);
                        if (item != null) {
                            item.setAmount(amount);
                            player.getInventory().addItem(item);
                            player.sendMessage(String.format("%sYou have been given %s%s %s", ChatColor.BLUE, ChatColor.GOLD, amount, weaponName));
                        }
                    }
                }
            }
        }
        return true;
    }
}
