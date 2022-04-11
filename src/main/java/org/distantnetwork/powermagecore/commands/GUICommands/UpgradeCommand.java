package org.distantnetwork.powermagecore.commands.GUICommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.GUI.UpgradeGUI;
import org.jetbrains.annotations.NotNull;

public class UpgradeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("powermage.upgrade")) {
                player.sendMessage(String.format("%sYou don't have permission to use this command!", ChatColor.RED));
            }
            new UpgradeGUI(player).open(player);
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
