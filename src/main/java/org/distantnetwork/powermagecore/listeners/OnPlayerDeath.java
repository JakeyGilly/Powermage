package org.distantnetwork.powermagecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.jetbrains.annotations.NotNull;

public class OnPlayerDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(@NotNull PlayerDeathEvent e) {
        e.setDroppedExp(0);
        e.getDrops().clear();
        e.setDeathMessage(null);
        e.setKeepInventory(true);
        e.setKeepLevel(true);
        if (e.getEntity().getKiller() == null) return;
        PowermagePlayer victim = new PowermagePlayer(e.getEntity());
        PowermagePlayer killer = new PowermagePlayer(e.getEntity().getKiller());
        victim.setDeaths(victim.getDeaths() + 1);
        victim.setSouls(victim.getSouls() - 1);
        victim.setKillStreak(0);
        victim.setCombatLog(false);
        killer.setKills(killer.getKills() + 1);
        killer.setKillStreak(killer.getKillStreak() + 1);
        killer.setSouls(killer.getSouls() + 1);
        int money = (int) (Math.random() * (victim.getMoney() / 10));
        victim.setMoney(victim.getMoney() - money);
        killer.setMoney(killer.getMoney() + money);
        killer.setClassesExp(killer.getClassType(), (int) Math.round(killer.getClassExp(killer.getClassType()) + ConfigurationManager.getDefaultConfig().getDouble("expPerKill")));
        if (killer.getClassExp(killer.getClassType()) >= ConfigurationManager.getDefaultConfig().getDoubleList("levelUpExp").get(killer.getClassLvl(killer.getClassType()))) {
            killer.setClassesLvl(killer.getClassType(), killer.getClassLvl(killer.getClassType()) + 1);
        }
        victim = victim.save();
        killer = killer.save();
        victim.getPlayer().sendMessage(ChatColor.RED + "You have died to " + e.getEntity().getKiller().getName() + "!");
        killer.getPlayer().sendMessage(String.format("%sYou have killed %s%s%s!", ChatColor.GREEN, ChatColor.RED, e.getEntity().getName(), ChatColor.GREEN));
    }
}
