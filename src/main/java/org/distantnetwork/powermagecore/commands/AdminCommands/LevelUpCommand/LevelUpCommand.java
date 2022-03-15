package org.distantnetwork.powermagecore.commands.AdminCommands.LevelUpCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerLevels;
import org.distantnetwork.powermagecore.utils.Enums.Classes;

public class LevelUpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1 || args.length > 3) {
                return false;
            }
            if (args.length == 1) {
                if (!player.hasPermission("powermage.admin.levelup")) {
                    sender.sendMessage(String.format("%sYou do not have permission to use this command.", ChatColor.RED));
                    return true;
                }
                Classes classes = Classes.valueOf(args[0].toUpperCase());
                PlayerLevels.addPlayerLevel(player.getUniqueId(), classes, 1, 0);
                sender.sendMessage(String.format("%sYou have leveled up your %s class!", ChatColor.GREEN, classes));
            } else if (args.length == 2) {
                if (PowermageCore.getInstance().getServer().getPlayer(args[1]) == null) {
                    sender.sendMessage(String.format("%sPlayer not found.", ChatColor.RED));
                    return true;
                }
                if (PowermageCore.getInstance().getServer().getPlayer(args[1]) == player) {
                    if (!player.hasPermission("powermage.admin.levelup")) {
                        sender.sendMessage(String.format("%sYou do not have permission to use this command.", ChatColor.RED));
                        return true;
                    }
                    Classes classes = Classes.valueOf(args[0].toUpperCase());
                    PlayerLevels.addPlayerLevel(player.getUniqueId(), classes, 1, 0);
                    sender.sendMessage(String.format("%sYou have leveled up your %s class!", ChatColor.GREEN, classes));
                } else {
                    if (!sender.hasPermission("powermage.admin.levelup.others")) {
                        sender.sendMessage(String.format("%sYou don't have permission to use this command!", ChatColor.RED));
                        return true;
                    }
                    Player target = PowermageCore.getInstance().getServer().getPlayer(args[1]);
                    if (target == null) {
                        sender.sendMessage(String.format("%sPlayer not found!", ChatColor.RED));
                        return true;
                    }
                    Classes classes = Classes.valueOf(args[0].toUpperCase());
                    PlayerLevels.addPlayerLevel(player.getUniqueId(), classes, 1, 0);
                    sender.sendMessage(String.format("%sYou have leveled up %s's %s class!", ChatColor.GREEN, player.getName(), classes));
                }
            } else {
                if (PowermageCore.getInstance().getServer().getPlayer(args[1]) == null) {
                    sender.sendMessage(String.format("%sPlayer not found.", ChatColor.RED));
                    return true;
                }
                if (PowermageCore.getInstance().getServer().getPlayer(args[1]) == player) {
                    if (!player.hasPermission("powermage.admin.levelup")) {
                        sender.sendMessage(String.format("%sYou do not have permission to use this command.", ChatColor.RED));
                        return true;
                    }
                    int amount = Integer.parseInt(args[2]);
                    Classes classes = Classes.valueOf(args[0].toUpperCase());
                    PlayerLevels.addPlayerLevel(player.getUniqueId(), classes, amount, 0);
                    sender.sendMessage(String.format("%sYou have leveled up your %s class %d times!", ChatColor.GREEN, classes, amount));
                } else {
                    if (!sender.hasPermission("powermage.admin.levelup.others")) {
                        sender.sendMessage(String.format("%sYou don't have permission to use this command!", ChatColor.RED));
                        return true;
                    }
                    Player target = PowermageCore.getInstance().getServer().getPlayer(args[1]);
                    if (target == null) {
                        sender.sendMessage(String.format("%sPlayer not found!", ChatColor.RED));
                        return true;
                    }
                    int amount = Integer.parseInt(args[2]);
                    Classes classes = Classes.valueOf(args[0].toUpperCase());
                    PlayerLevels.addPlayerLevel(player.getUniqueId(), classes, amount, 0);
                    sender.sendMessage(String.format("%sYou have leveled up %s's %s class %d times!", ChatColor.GREEN, player.getName(), classes, amount));
                }
            }
        }
        return false;
    }
}
