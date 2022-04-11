package org.distantnetwork.powermagecore.commands.Admin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.jetbrains.annotations.NotNull;

public class UpgradeUpgrades implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("powermage.admin")) {
                sender.sendMessage(String.format("%sYou don't have permission to use this command", ChatColor.RED));
                return true;
            }
            if (args.length < 1 || args.length > 3) return false;
            if (!(args[0].equalsIgnoreCase("mana") || args[0].equalsIgnoreCase("health") || args[0].equalsIgnoreCase("speed") || args[0].equalsIgnoreCase("strength"))) {
                sender.sendMessage(String.format("%sInvalid upgrade type", ChatColor.RED));
                return true;
            }
            int amount = 1;
            if (args.length > 1) {
                try {
                    amount = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(String.format("%sInvalid number", ChatColor.RED));
                    return true;
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
            PowermagePlayer pmplayer = new PowermagePlayer(player);
            switch (args[0].toLowerCase()) {
                case "mana":
                    pmplayer.setManaUpgrade(pmplayer.getManaUpgrade() + amount);
                    break;
                case "health":
                    pmplayer.setHealthUpgrade(pmplayer.getHealthUpgrade() + amount);
                    break;
                case "speed":
                    pmplayer.setSpeedUpgrade(pmplayer.getSpeedUpgrade() + amount);
                    break;
                case "strength":
                    pmplayer.setStrengthUpgrade(pmplayer.getStrengthUpgrade() + amount);
                    break;
            }
            pmplayer.save();
            if (player != sender) {
                sender.sendMessage(String.format("%s%s's %s%s upgrade has been increased by %d", ChatColor.GREEN, ChatColor.LIGHT_PURPLE, player.getName(), args[0], amount));
                player.sendMessage(String.format("%sYour %s%s upgrade has been increased by %d", ChatColor.GREEN, ChatColor.LIGHT_PURPLE, args[0], amount));
            } else {
                sender.sendMessage(String.format("%sYour %s%s upgrade has been increased by %d", ChatColor.GREEN, ChatColor.LIGHT_PURPLE, args[0], amount));
            }
            return true;
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
