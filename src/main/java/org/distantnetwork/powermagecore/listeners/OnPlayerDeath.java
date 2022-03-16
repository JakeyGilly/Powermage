package org.distantnetwork.powermagecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerDeaths;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerKills;
import org.distantnetwork.powermagecore.utils.Config.NonFileHashMaps.PlayerCombatLog;
import org.distantnetwork.powermagecore.utils.Config.NonFileHashMaps.PlayerKillStreak;

public class OnPlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDroppedExp(0);
        e.getDrops().clear();
        e.setDeathMessage(null);
        e.setKeepInventory(true);
        e.setKeepLevel(true);
        if (e.getEntity().getKiller() == null) return;
        PlayerDeaths.addDeaths(e.getEntity().getUniqueId(), 1);
        PlayerKills.addKills(e.getEntity().getKiller().getUniqueId(), 1);
        if (PlayerCombatLog.get(e.getEntity().getUniqueId())) PlayerCombatLog.unset(e.getEntity().getUniqueId());
        PlayerKillStreak.incrementKillStreak(e.getEntity().getKiller().getUniqueId());
        PlayerKillStreak.removeKillStreak(e.getEntity().getUniqueId());
        e.getEntity().sendMessage(ChatColor.RED + "You have died to " + e.getEntity().getKiller().getName() + "!");
        e.getEntity().getKiller().sendMessage(String.format("%sYou have killed %s%s%s!", ChatColor.GREEN, ChatColor.RED, e.getEntity().getName(), ChatColor.GREEN));
    }
}
