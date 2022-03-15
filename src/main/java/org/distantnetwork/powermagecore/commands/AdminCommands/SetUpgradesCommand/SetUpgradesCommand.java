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
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 2 || args.length > 3) return false;
            if (!player.hasPermission("powermage.admin.upgradestats")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                return true;
            }
            Upgrades upgrade = Upgrades.valueOf(args[0].toUpperCase());
            int value;
            try {
                value = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "Invalid value.");
                return true;
            }
            if (args.length > 2) {
                player = PowermageCore.getInstance().getServer().getPlayer(args[1]);
                if (player == null) {
                    ((Player) sender).sendMessage(ChatColor.RED + "Player not found.");
                    return true;
                }
            }
            PlayerUpgrades.setUpgrade(player.getUniqueId(), upgrade, value);
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return false;
    }
}
