package org.distantnetwork.powermagecore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.Items.ConfigItem;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.WeaponsManager;

import java.util.logging.Level;

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
            FileConfiguration config = WeaponsManager.getConfig(Integer.valueOf(args[0]));
            ItemStack item = ConfigItem.getItem(config);
            if (item == null) return true;
            player.getInventory().addItem(item);
        }
        return true;
    }
}
