package org.distantnetwork.powermagecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerClasses;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerUpgrades;
import org.distantnetwork.powermagecore.utils.Enums.ClassesEnum;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;

public class OnPlayerJoin implements Listener {
    public static void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        e.getPlayer().sendTitle(String.format("%s%sPowerMage", ChatColor.RED, ChatColor.BOLD), String.format("%sDo /start to get started!", ChatColor.GRAY), 10, 70, 20);
        try {
            e.getPlayer().setHealthScale(ClassesEnum.getHealth(PlayerClasses.getClasses(e.getPlayer().getUniqueId())) + (PlayerUpgrades.getUpgradeLevel(e.getPlayer().getUniqueId(), Upgrades.HEALTH) * ConfigurationManager.getDefaultConfig().getDouble("upgrades.health.healthPerLevel")));
        } catch (IllegalArgumentException ignored) {}
        try {
            e.getPlayer().setHealth(ClassesEnum.getHealth(PlayerClasses.getClasses(e.getPlayer().getUniqueId())) + (PlayerUpgrades.getUpgradeLevel(e.getPlayer().getUniqueId(), Upgrades.HEALTH) * ConfigurationManager.getDefaultConfig().getDouble("upgrades.health.healthPerLevel")));
        } catch (IllegalArgumentException ignored) {}
        e.getPlayer().setWalkSpeed((float) (ClassesEnum.getWalkSpeed(PlayerClasses.getClasses(e.getPlayer().getUniqueId())) + (PlayerUpgrades.getUpgradeLevel(e.getPlayer().getUniqueId(), Upgrades.SPEED) * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel"))));
    }
}
