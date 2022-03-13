package org.distantnetwork.powermagecore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.commands.GUI.UpgradeGUI;

public class UpgradeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            return true;
        }
        Player player = (Player) sender;
        if (player.hasPermission("powermage.upgrade")) new UpgradeGUI(player).open(player);
        else player.sendMessage(String.format("%sYou don't have permission to use this command.", ChatColor.RED));
        return true;
    }
}
