package org.distantnetwork.powermagecore.commands.PluginCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.*;

public class PluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) return false;
            if (args[0].equalsIgnoreCase("reload")) {
                if (!player.hasPermission("powermagecore.reload")) {
                    player.sendMessage(String.format("%sYou don't have permission to use this command!", ChatColor.RED));
                    return true;
                }
                player.sendMessage("Reloading...");
                PlayerUpgrades.load();
                PlayerSouls.load();
                PlayerLevels.load();
                PlayerCoins.load();
                PlayerClasses.load();
                player.sendMessage("Reloaded.");
            } else if (args[0].equalsIgnoreCase("version")) {
                if (!player.hasPermission("powermagecore.version")) {
                    player.sendMessage(String.format("%sYou don't have permission to use this command!", ChatColor.RED));
                    return true;
                }
                player.sendMessage(String.format("PowerMageCore v%s", PowermageCore.getInstance().getDescription().getVersion()));
            } else return false;
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
