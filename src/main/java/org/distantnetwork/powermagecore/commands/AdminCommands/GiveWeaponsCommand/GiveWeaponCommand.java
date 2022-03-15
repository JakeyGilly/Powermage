package org.distantnetwork.powermagecore.commands.AdminCommands.GiveWeaponsCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;

public class GiveWeaponCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1 || args.length > 3) return false;
            if (WeaponConfigManager.getWeaponId(args[0]) == null) {
                player.sendMessage(ChatColor.RED + "Weapon not found!");
                return true;
            }
            ItemStack item = WeaponConfigManager.loadWeapon(WeaponConfigManager.getWeaponId(args[0]));
            int amount = 1;
            if (args.length > 1) {
                try {
                    amount = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "Invalid amount!");
                    return true;
                }
            }
            if (args.length > 2) {
                player = Bukkit.getPlayer(args[2]);
                if (player == null) {
                    ((Player) sender).sendMessage(ChatColor.RED + "Player not found!");
                }
            }
            item.setAmount(amount);
            player.getInventory().addItem(item);
            player.sendMessage(String.format("%sYou have been given %s%s %s", ChatColor.BLUE, ChatColor.GOLD, amount, WeaponConfigManager.loadWeapon(WeaponConfigManager.getWeaponId(args[0])).getItemMeta().getDisplayName()));
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
