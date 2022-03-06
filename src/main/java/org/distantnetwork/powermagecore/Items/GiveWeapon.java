package org.distantnetwork.powermagecore.Items;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.WeaponsManager;

import java.util.logging.Level;

public class GiveWeapon implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            FileConfiguration config = WeaponsManager.getConfig(0);
            PowermageCore.getInstance().getLogger().log(Level.INFO, String.format("enabled?: %s", String.valueOf(config.getBoolean("enabled"))));
            ItemStack item = ConfigItem.getItem(config, player);
            if (item == null) return true;
            player.getInventory().addItem(item);
        }
        return true;
    }
}