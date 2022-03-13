package org.distantnetwork.powermagecore.commands.GUICommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.commands.GUICommands.GUI.ClassGUI;

public class ClassCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if (player.hasPermission("powermage.start")) new ClassGUI(player).open(player);
        return true;
    }
}
