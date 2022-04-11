package org.distantnetwork.powermagecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.jetbrains.annotations.NotNull;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;

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
        if (victim.getSouls() > 0) {
            victim.setSouls(victim.getSouls() - 1);
        }
        victim.setKillStreak(0);
        victim.setCombatLog(false);
        killer.setKills(killer.getKills() + 1);
        killer.setKillStreak(killer.getKillStreak() + 1);
        killer.setSouls(killer.getSouls() + 1);
        int money;
        if (victim.getMoney() != 0) {
            money = (int) (Math.random() * (victim.getMoney() / 10));
        } else {
            money = (int) (Math.random() * 10);
        }
        if (victim.getMoney() - money >= 0) {
            victim.setMoney(victim.getMoney() - money);
        }
        killer.setMoney(killer.getMoney() + money);
        if (killer.getClassType() != null) {
            killer.setClassesExp(killer.getClassType(), (int) (killer.getClassExp(killer.getClassType()) + ConfigurationManager.getDefaultConfig().getDouble("expPerKill")));
            killer = killer.save();
            if (killer.getClassExp(killer.getClassType()) >= ConfigurationManager.getDefaultConfig().getDoubleList("levelUpExp").get(killer.getClassLvl(killer.getClassType()))) {
                killer.setClassesLvl(killer.getClassType(), killer.getClassLvl(killer.getClassType()) + 1);
                killer.setClassesExp(killer.getClassType(), 0);
                killer.getPlayer().sendTitle(ChatColor.RED + "Level up!", ChatColor.GOLD + "" + (killer.getClassLvl(killer.getClassType())-1) + " -> " + (killer.getClassLvl(killer.getClassType())), 10, 20, 10);
                killer.getPlayer().sendMessage(ChatColor.GREEN + "You have leveled up to " + killer.getClassLvl(killer.getClassType()) + "!");
            }
        }
        victim = victim.save();
        killer = killer.save();
        victim.getPlayer().sendMessage(ChatColor.RED + "You have died to " + e.getEntity().getKiller().getName() + "!");
        killer.getPlayer().sendMessage(String.format("%sYou have killed %s%s%s!", ChatColor.GREEN, ChatColor.RED, e.getEntity().getName(), ChatColor.GREEN));
        int respawnTime;
        if (victim.getPlayer().hasPermission("powermage.respawn.legend")) respawnTime = 0;
        else if (victim.getPlayer().hasPermission("powermage.respawn.elite")) respawnTime = 1;
        else if (victim.getPlayer().hasPermission("powermage.respawn.super")) respawnTime = 2;
        else respawnTime = 5;
        if (respawnTime > 0) {
            victim.getPlayer().setGameMode(GameMode.SPECTATOR);
            victim.getPlayer().sendTitle(ChatColor.RED + "You are dead!", ChatColor.GOLD + "You will respawn in " + respawnTime + " seconds", 10, 20, 10);
            victim.getPlayer().sendMessage(ChatColor.GREEN + "You will respawn in " + respawnTime + " seconds");
            PowermagePlayer finalVictim = victim;
            new BukkitRunnable() {
                int time = respawnTime;
                PowermagePlayer p = finalVictim;
                @Override
                public void run() {
                    if (time == 0) {
                        p.getPlayer().setGameMode(GameMode.ADVENTURE);
                        p.getPlayer().teleport(p.getPlayer().getWorld().getSpawnLocation());
                        this.cancel();
                    } else {
                        p.getPlayer().sendTitle(ChatColor.RED + "You are dead!", ChatColor.GOLD + "You will respawn in " + time + " seconds", 10, 20, 10);
                        p.getPlayer().sendMessage(ChatColor.GREEN + "You will respawn in " + time + " seconds");
                        time--;
                    }
                }
            }.runTaskTimer(getInstance(), 0, 20);
        }
    }
}
