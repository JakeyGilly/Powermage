package org.distantnetwork.powermagecore.commands.GUICommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.commands.GUICommands.GUI.ClassGUI;

public class ClassCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("powermage.class")) {
                player.sendMessage(String.format("%sYou don't have permission to use this command!", ChatColor.RED));
            }
            new ClassGUI(player).open(player);
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
