package org.distantnetwork.powermagecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerClasses;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerUpgrades;
import org.distantnetwork.powermagecore.utils.Config.MainConfigManager;
import org.distantnetwork.powermagecore.utils.Enums.Classes;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;

public class OnPlayerJoin implements Listener {
    public static void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        // if first time player joins, send them a welcome message
        e.getPlayer().sendTitle(String.format("%s%sPowerMage", ChatColor.RED, ChatColor.BOLD), String.format("%sDo /start to get started!", ChatColor.GRAY), 10, 70, 20);
        try {
            e.getPlayer().setHealthScale(Classes.getHealth(PlayerClasses.getClasses(e.getPlayer().getUniqueId())) + (PlayerUpgrades.getUpgradeLevel(e.getPlayer().getUniqueId(), Upgrades.HEALTH) * MainConfigManager.config.getDouble("upgrades.health.healthPerLevel")));
        } catch (IllegalArgumentException ignored) {}
        try {
            e.getPlayer().setHealth(Classes.getHealth(PlayerClasses.getClasses(e.getPlayer().getUniqueId())) + (PlayerUpgrades.getUpgradeLevel(e.getPlayer().getUniqueId(), Upgrades.HEALTH) * MainConfigManager.config.getDouble("upgrades.health.healthPerLevel")));
        } catch (IllegalArgumentException ignored) {}
        e.getPlayer().setWalkSpeed((float) (Classes.getWalkSpeed(PlayerClasses.getClasses(e.getPlayer().getUniqueId())) + (PlayerUpgrades.getUpgradeLevel(e.getPlayer().getUniqueId(), Upgrades.SPEED) * MainConfigManager.config.getDouble("upgrades.speed.speedPerLevel"))));
    }
}
