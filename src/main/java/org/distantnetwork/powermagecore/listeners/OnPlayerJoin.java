package org.distantnetwork.powermagecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

public class OnPlayerJoin implements Listener {
    public static void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        e.getPlayer().sendTitle(String.format("%s%sPowerMage", ChatColor.RED, ChatColor.BOLD), String.format("%sDo /start to get started!", ChatColor.GRAY), 10, 70, 20);
        PowermagePlayer player = new PowermagePlayer(e.getPlayer());
        try {
            player.getPlayer().setHealthScale(player.getClassType().getBaseHealth() + player.getHealthUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
        } catch (IllegalArgumentException ignored) {}
        try {
            player.getPlayer().setHealth(player.getClassType().getBaseHealth() + player.getHealthUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
        } catch (IllegalArgumentException ignored) {}
        player.getPlayer().setWalkSpeed((float) (player.getClassType().getBaseSpeed() + player.getSpeedUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel")));
    }
}
