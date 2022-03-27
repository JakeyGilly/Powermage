package org.distantnetwork.powermagecore.commands.AdminCommands.SetUpgradesCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.Enums.Classes;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

public class SetUpgradesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 2 || args.length > 3) return false;
            if (!player.hasPermission("powermage.admin.upgradestats")) {
                player.sendMessage(String.format("%sYou don't have permission to use this command.", ChatColor.RED));
                return true;
            }
            int value;
            try {
                value = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage(String.format("%sInvalid value.", ChatColor.RED));
                return true;
            }
            if (args.length > 2) {
                player = PowermageCore.getInstance().getServer().getPlayer(args[2]);
                if (player == null) {
                    ((Player) sender).sendMessage(String.format("%sPlayer not found.", ChatColor.RED));
                    return true;
                }
            }
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            if (args[0].equalsIgnoreCase("mana")) {
                pmPlayer.setManaUpgrade(value);
            }
            if (args[0].equalsIgnoreCase("health")) {
                pmPlayer.setHealthUpgrade(value);
                pmPlayer = pmPlayer.save();
                try {
                    player.setHealthScale(pmPlayer.getClassType().getMaxHealth() + pmPlayer.getHealthUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
                } catch (Exception ignored) {}
                try {
                    player.setHealth(pmPlayer.getClassType().getMaxHealth() + pmPlayer.getHealthUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
                } catch (Exception ignored) {}
            }
            if (args[0].equalsIgnoreCase("strength")) {
                pmPlayer.setStrengthUpgrade(value);
            }
            if (args[0].equalsIgnoreCase("speed")) {
                pmPlayer.setSpeedUpgrade(value);
                pmPlayer = pmPlayer.save();
                player.setWalkSpeed((float) (pmPlayer.getClassType().getSpeed() + pmPlayer.getManaUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel")));
            }
        } else sender.sendMessage(String.format("%sThis command is only for players!", ChatColor.RED));
        return true;
    }
}
