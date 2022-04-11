package org.distantnetwork.powermagecore.commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.Items.WeaponItem;
import org.jetbrains.annotations.NotNull;

public class GiveWeapon implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("powermage.admin")) {
                sender.sendMessage(String.format("%sYou don't have permission to use this command", ChatColor.RED));
                return true;
            }
            if (args.length < 1 || args.length > 3) return false;
            WeaponItem weaponItem = null;
            for (WeaponItem weapon : WeaponItem.getWeapons()) {
                if (weapon.getName().equalsIgnoreCase(args[0])) {
                    weaponItem = weapon;
                }
            }
            if (weaponItem == null) {
                sender.sendMessage(String.format("%sWeapon %s not found", ChatColor.RED, args[0]));
                return true;
            }
            if (args.length > 1) {
                try {
                    int amount = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(String.format("%sInvalid amount", ChatColor.RED));
                }
            }
            if (args.length > 2) {
                player = Bukkit.getPlayer(args[2]);
                if (player == null) {
                    sender.sendMessage(String.format("%sPlayer not found", ChatColor.RED));
                    return true;
                }
            }
            if (player != sender) {
                if (!player.hasPermission("powermage.admin.others")) {
                    sender.sendMessage(String.format("%sYou don't have permission to give money to others", ChatColor.RED));
                    player = (Player) sender;
                }
            }
            weaponItem.give(player);
            if (player != sender) {
                sender.sendMessage(String.format("%sGiven %s%s%s to %s", ChatColor.GREEN, ChatColor.LIGHT_PURPLE, weaponItem.getName(), ChatColor.GREEN, player.getName()));
                player.sendMessage(String.format("%sYou have been given %s%s", ChatColor.GREEN, ChatColor.LIGHT_PURPLE, weaponItem.getName()));
            } else {
                player.sendMessage(String.format("%sYou have been given %s%s", ChatColor.GREEN, ChatColor.LIGHT_PURPLE, weaponItem.getName()));
            }
        }
        return true;
    }
}
