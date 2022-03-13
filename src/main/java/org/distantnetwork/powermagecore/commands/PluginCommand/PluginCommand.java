package org.distantnetwork.powermagecore.commands.PluginCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.*;

public class PluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(String.format("/%s [reload/version]", command.getName()));
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("powermagecore.reload")) {
                sender.sendMessage("You don't have permission to reload.");
                return true;
            }
            sender.sendMessage("Reloading...");
            PlayerUpgrades.load();
            PlayerSouls.load();
            PlayerCombatLog.load();
            PlayerLevels.load();
            PlayerCoins.load();
            PlayerClasses.load();
            sender.sendMessage("Reloaded.");
            return true;
        } else if (args[0].equalsIgnoreCase("version")) {
            sender.sendMessage(sender.hasPermission("powermagecore.version") ? "PowerMageCore v" + PowermageCore.getInstance().getDescription().getVersion() : "You don't have permission to use this command.");
            return true;
        }
        return false;
    }
}
