package org.distantnetwork.powermagecore.commands.AdminCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

public class GiveMoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            if (!player.hasPermission("powermage.admin.givemoney")) {
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
            if (player != (Player) sender) {
                if (!player.hasPermission("powermage.admin.givemoney.others")) {
                    sender.sendMessage(String.format("%sYou don't have permission to give money to others", ChatColor.RED));
                }
            }
            pmPlayer.setMoney(pmPlayer.getMoney() + amount);
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
