package org.distantnetwork.powermagecore.commands.AdminCommands.LevelUp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.Enums.Classes;
import org.jetbrains.annotations.NotNull;

public class LevelUpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1 || args.length > 3) return false;
            if (args.length == 1) {
                for (Classes classes : Classes.values()) {
                    if (classes.name().equalsIgnoreCase(args[0])) {
                        classes.setLvl(player, classes.getLvl(player) + 1);
                    }
                }
            }
            if (args.length == 2) {
                try {
                    int level = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Invalid number!");
                    return true;
                }
                for (Classes classes : Classes.values()) {
                    if (classes.name().equalsIgnoreCase(args[0])) {
                        classes.setLvl(player, classes.getLvl(player) + Integer.parseInt(args[1]));
                    }
                }
            }
            if (args.length == 3) {
                Player target = player.getServer().getPlayer(args[2]);
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Player not found!");
                    return true;
                }
                try {
                    int level = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Invalid number!");
                    return true;
                }
                for (Classes classes : Classes.values()) {
                    if (classes.name().equalsIgnoreCase(args[1])) {
                        classes.setLvl(player, classes.getLvl(player) + Integer.parseInt(args[1]));
                    }
                }
            }
        }
        return true;
    }
}
