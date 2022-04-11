package org.distantnetwork.powermagecore.commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.Classes;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.jetbrains.annotations.NotNull;

public class SetClass implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("powermage.admin")) {
                sender.sendMessage(String.format("%sYou don't have permission to use this command", ChatColor.RED));
                return true;
            }
            if (args.length < 1 || args.length > 2) return false;
            Classes clazz = null;
            for (Classes c : Classes.getClasses()) {
                if (c.getName().equalsIgnoreCase(args[0])) {
                    clazz = c;
                }
            }
            if (clazz == null) {
                sender.sendMessage(String.format("%sClass %s not found", ChatColor.RED, args[0]));
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
                if (!player.hasPermission("powermage.admin.others")) {
                    sender.sendMessage(String.format("%sYou don't have permission to give money to others", ChatColor.RED));
                }
            }
            PowermagePlayer pmplayer = new PowermagePlayer(player);
            pmplayer.setClassType(clazz);
            pmplayer.save();
            sender.sendMessage(String.format("%s %s's class has been set to %s", ChatColor.GREEN, player.getDisplayName(), clazz.getName()));
            return true;
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
