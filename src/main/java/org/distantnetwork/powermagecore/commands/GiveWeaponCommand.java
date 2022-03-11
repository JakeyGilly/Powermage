package org.distantnetwork.powermagecore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;

public class GiveWeaponCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && sender instanceof Player) {
            try {
                Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage("First argument must be an integer.");
            }
            Player player = (Player) sender;
            ItemStack item = WeaponConfigManager.getWeapon(Integer.parseInt(args[0]));
            if (item == null) return true;
            player.getInventory().addItem(item);
        }
        return true;
    }
}
