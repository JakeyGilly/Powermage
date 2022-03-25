package org.distantnetwork.powermagecore.commands.AdminCommands.LevelUpCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerLevels;
import org.distantnetwork.powermagecore.utils.Enums.ClassesEnum;

public class LevelUpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1 || args.length > 3) {
                return false;
            }
            if (!player.hasPermission("powermage.admin.levelup")) {
                sender.sendMessage(String.format("%sYou do not have permission to use this command.", ChatColor.RED));
                return true;
            }
            ClassesEnum classes = ClassesEnum.valueOf(args[0].toUpperCase());
            int level = 1;
            if (args.length > 1) {
                player = PowermageCore.getInstance().getServer().getPlayer(args[1]);
                if (player == null) {
                    ((Player) sender).sendMessage(String.format("%sPlayer not found.", ChatColor.RED));
                    return true;
                }
            }
            if (args.length > 2) {
                try {
                    level = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    player.sendMessage(String.format("%sInvalid number.", ChatColor.RED));
                    return true;
                }
            }
            PlayerLevels.addPlayerLevel(player.getUniqueId(), classes, level, 0);
            sender.sendMessage(String.format("%sYou have leveled up your %s class %d times!", ChatColor.GREEN, classes, level));
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return false;
    }
}
