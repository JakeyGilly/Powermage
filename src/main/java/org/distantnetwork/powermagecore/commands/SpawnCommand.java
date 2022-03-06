package org.distantnetwork.powermagecore.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.PowermageCore;

import java.util.concurrent.TimeUnit;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        if (PowermageCore.playerCombatLog.get(player.getUniqueId()) != null) {
            player.sendMessage(String.format("%sYou are currently in combat!", ChatColor.RED));
            return true;
        }
        Location location = player.getLocation();
        World world = location.getWorld();
        player.sendMessage(String.format("Teleporting you to spawn in %s3 seconds%s...", ChatColor.GREEN, ChatColor.RESET));
        player.sendMessage(String.format("%sPlease keep still!", ChatColor.GREEN));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.sendMessage(String.format("%sTeleporting to spawn...", ChatColor.GREEN));
        if (player.getLocation() == location) player.teleport(player.getWorld().getSpawnLocation());
        else player.sendMessage(String.format("%sTeleport failed because you moved!", ChatColor.RED));
        return true;
    }
}
