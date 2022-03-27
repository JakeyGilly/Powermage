package org.distantnetwork.powermagecore.commands.GUICommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SoulShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("powermage.soulshop")) {
                player.sendMessage(String.format("%sYou don't have permission to use this command!", ChatColor.RED));
            }
//            TODO: Implement levels in classes
//            for (Classes classes : Classes.values()) {
//                if (classes.getLevel() >= 5) {
//                    new SoulShopGUI(player).open(player);
//                    return true;
//                }
//            }
            player.sendMessage(String.format("%sYou need to be level 5 in at least one class to use this command!", ChatColor.RED));
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
