package org.distantnetwork.powermagecore.commands.AdminCommands.SetUpgradesCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerUpgrades;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;


public class SetUpgradesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /setupgrades <player> stats");
            return true;
        }
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("powermage.admin.upgradestats")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }
            if (args.length == 3) {
                Player target = PowermageCore.getInstance().getServer().getPlayer(args[2]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Player not found.");
                    return true;
                }
                PlayerUpgrades.setUpgrade(target.getUniqueId(), Upgrades.valueOf(args[0].toUpperCase()), Integer.parseInt(args[1].toUpperCase()));
                return true;
            } else if (args.length == 2) {
                PlayerUpgrades.setUpgrade(player.getUniqueId(), Upgrades.valueOf(args[0].toUpperCase()), Integer.parseInt(args[1].toUpperCase()));
                return true;
            }
        }
        return false;
    }
}
