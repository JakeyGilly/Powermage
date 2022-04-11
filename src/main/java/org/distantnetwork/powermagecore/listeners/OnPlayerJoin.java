package org.distantnetwork.powermagecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.distantnetwork.powermagecore.builders.ItemBuilder;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

public class OnPlayerJoin implements Listener {
    public static void onPlayerJoin(PlayerJoinEvent e) {
        PowermagePlayer player = new PowermagePlayer(e.getPlayer());
        try {
            player.getPlayer().setHealthScale(player.getClassType().getBaseHealth() + player.getHealthUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
        } catch (IllegalArgumentException ignored) {}
        try {
            player.getPlayer().setHealth(player.getClassType().getBaseHealth() + player.getHealthUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.health.healthPerLevel"));
        } catch (IllegalArgumentException ignored) {}
        player.getPlayer().setWalkSpeed((float) (player.getClassType().getBaseSpeed() + player.getSpeedUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel")));
        if (!e.getPlayer().hasPlayedBefore()) {
            e.getPlayer().sendTitle(String.format("%s%sPowerMage", ChatColor.RED, ChatColor.BOLD), String.format("%sDo /start to get started!", ChatColor.GRAY), 10, 70, 20);
            player.setMoney(1000);
            e.getPlayer().performCommand("class");
            e.getPlayer().getInventory().setItem(8, new ItemBuilder(Material.END_CRYSTAL).setName(String.format("%sMenu %s(Right/Sneak Left Click)", ChatColor.LIGHT_PURPLE, ChatColor.GRAY))
                    .setLore(String.format("%sClick to open the menu!", ChatColor.GRAY), "", String.format("%sClick while sneaking to", ChatColor.GRAY), String.format("%suse your selected class ability!", ChatColor.GRAY), "", String.format("%s%sCORE ITEM", ChatColor.RED, ChatColor.BOLD)).build());
        }
    }
}
