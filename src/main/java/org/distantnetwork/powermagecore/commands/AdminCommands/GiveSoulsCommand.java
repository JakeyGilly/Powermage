package org.distantnetwork.powermagecore.commands.AdminCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerSouls;

public class GiveSoulsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("powermage.admin.givesouls")) {
                    int amount;
                    try {
                        amount = Integer.parseInt(args[0]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(String.format("%sInvalid number", ChatColor.RED));
                        return true;
                    }
                    PlayerSouls.addSouls(player.getUniqueId(), amount);
                } else {
                    sender.sendMessage(String.format("%sYou don't have permission to use this command", ChatColor.RED));
                }
            }
        } else if (args.length == 2) {
            Player player = Bukkit.getPlayer(args[0]);
            if(player != null) {
                if (sender.hasPermission("powermage.admin.givesouls.others")) {
                    int amount;
                    try {
                        amount = Integer.parseInt(args[0]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(String.format("%sInvalid number", ChatColor.RED));
                        return true;
                    }
                    PlayerSouls.addSouls(player.getUniqueId(), amount);
                } else {
                    sender.sendMessage(String.format("%sYou don't have permission to use this command", ChatColor.RED));
                }
            }
        }
        return true;
    }
}
