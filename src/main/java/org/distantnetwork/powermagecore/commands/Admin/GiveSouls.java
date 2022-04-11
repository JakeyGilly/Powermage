package org.distantnetwork.powermagecore.commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.jetbrains.annotations.NotNull;

public class GiveSouls implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("powermage.admin")) {
                sender.sendMessage(String.format("%sYou don't have permission to use this command", ChatColor.RED));
                return true;
            }
            if (args.length < 1 || args.length > 2) return false;
            int amount;
            try {
                amount = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage(String.format("%sInvalid number", ChatColor.RED));
                return true;
            }
            if (args.length > 1) {
                player = Bukkit.getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage(String.format("%sPlayer not found", ChatColor.RED));
                    return true;
                }
            }
            if (player != sender) {
                if (!player.hasPermission("powermage.admin.others")) {
                    sender.sendMessage(String.format("%sYou don't have permission to give souls to others", ChatColor.RED));
                    player = (Player) sender;
                }
            }
            PowermagePlayer pmplayer = new PowermagePlayer(player);
            pmplayer.setSouls(pmplayer.getSouls() + amount);
            pmplayer.save();
            if (player != sender) {
                sender.sendMessage(String.format("%sYou have given %s%d%s souls to %s%s%s", ChatColor.GREEN, ChatColor.AQUA, amount, ChatColor.GREEN, ChatColor.GOLD, player.getName(), ChatColor.GREEN));
                player.sendMessage(String.format("%sYou have been given %s%d%s souls", ChatColor.GREEN, ChatColor.AQUA, amount, ChatColor.GREEN));
            } else {
                player.sendMessage(String.format("%sYou have been given %s%d%s souls", ChatColor.GREEN, ChatColor.AQUA, amount, ChatColor.GREEN));
            }
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
