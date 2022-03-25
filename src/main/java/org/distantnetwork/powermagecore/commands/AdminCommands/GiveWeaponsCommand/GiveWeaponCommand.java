package org.distantnetwork.powermagecore.commands.AdminCommands.GiveWeaponsCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.WeaponItem;

import java.io.File;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.getFileFile;

public class GiveWeaponCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1 || args.length > 3) return false;
            WeaponItem weaponItem = new WeaponItem(getFileFile("weapons" + File.separator + args[0] + ".yml"));
            if (weaponItem.getMaterial() == null) {
                player.sendMessage(ChatColor.RED + "Weapon not found!");
                return true;
            }
            int amount = 1;
            if (args.length > 1) {
                try {
                    amount = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(String.format("%sInvalid amount!", ChatColor.RED));
                    return true;
                }
            }
            if (args.length > 2) {
                player = Bukkit.getPlayer(args[2]);
                if (player == null) {
                    ((Player) sender).sendMessage(String.format("%sPlayer not found!", ChatColor.RED));
                    return true;
                }
            }
            weaponItem.setAmount(amount);
            weaponItem.give(player);
            player.sendMessage(String.format("%sYou have been given %s%s %s", ChatColor.BLUE, ChatColor.GOLD, amount, weaponItem.getName()));
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
