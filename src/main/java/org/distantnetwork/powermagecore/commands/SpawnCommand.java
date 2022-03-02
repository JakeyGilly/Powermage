package org.distantnetwork.powermagecore.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.PowermageCore;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (PowermageCore.playerCombatLog.get(player.getUniqueId()) != null) {
                player.sendMessage("&cYou are currently in combat!");
                return true;
            }
            Location location = player.getLocation();
            World world = location.getWorld();
            player.sendMessage("Teleporting you to spawn in &c3 seconds...");
            player.sendMessage("&cPlease keep still!");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.sendMessage("&aTeleporting to spawn...");
            if (player.getLocation() == location) {
                player.teleport(player.getWorld().getSpawnLocation());
            } else {
                player.sendMessage("&cTeleport failed because you moved!");
            }
        }
        return true;
    }
}
