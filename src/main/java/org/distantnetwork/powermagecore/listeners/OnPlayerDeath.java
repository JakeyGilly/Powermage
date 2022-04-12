package org.distantnetwork.powermagecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.distantnetwork.powermagecore.utils.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.jetbrains.annotations.NotNull;

import static org.distantnetwork.powermagecore.PowermageCore.getPlugin;

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
            if (killer.getPlayer().hasPermission("powermage.coinmultiplier.legend")) money = (int) ((Math.random() * victim.getMoney() / 10) * 1.2);
            else if (killer.getPlayer().hasPermission("powermage.coinmultiplier.elite")) money = (int) ((Math.random() * victim.getMoney() / 10) * 1.4);
            else if (killer.getPlayer().hasPermission("powermage.coinmultiplier.super")) money = (int) ((Math.random() * victim.getMoney() / 10) * 2);
            else money = (int) (Math.random() * (victim.getMoney() / 10));
        } else {
            if (killer.getPlayer().hasPermission("powermage.coinmultiplier.legend")) money = (int) (Math.random() * 12);
            else if (killer.getPlayer().hasPermission("powermage.coinmultiplier.elite")) money = (int) (Math.random() * 14);
            else if (killer.getPlayer().hasPermission("powermage.coinmultiplier.super")) money = (int) (Math.random() * 20);
            else money = (int) (Math.random() * 10);
        }
        if (victim.getMoney() - money >= 0) {
            victim.setMoney(victim.getMoney() - money);
            victim.getPlayer().sendMessage(String.format("%sYou dropped %d coins!", ChatColor.RED, money));
        }
        killer.setMoney(killer.getMoney() + money);
        killer.getPlayer().sendMessage(String.format("%sYou gained %d coins!", ChatColor.GREEN, money));
        if (killer.getClassType() != null) {
            killer.setClassesExp(killer.getClassType(), (int) (killer.getClassExp(killer.getClassType()) + ConfigurationManager.getDefaultConfig().getDouble("expPerKill")));
            killer = killer.save();
            if (killer.getClassExp(killer.getClassType()) >= ConfigurationManager.getDefaultConfig().getDoubleList("levelUpExp").get(killer.getClassLvl(killer.getClassType()))) {
                killer.setClassesLvl(killer.getClassType(), killer.getClassLvl(killer.getClassType()) + 1);
                killer.setClassesExp(killer.getClassType(), 0);
                killer.getPlayer().sendTitle(String.format("%sLevel up!", ChatColor.RED), String.format("%s%d -> %d", ChatColor.GOLD, killer.getClassLvl(killer.getClassType()) - 1, killer.getClassLvl(killer.getClassType())), 10, 20, 10);
                killer.getPlayer().sendMessage(String.format("%sYou have leveled up to %d!", ChatColor.GREEN, killer.getClassLvl(killer.getClassType())));
            }
        }
        victim = victim.save();
        killer = killer.save();
        victim.getPlayer().sendMessage(String.format("%sYou have died to %s!", ChatColor.RED, e.getEntity().getKiller().getName()));
        killer.getPlayer().sendMessage(String.format("%sYou have killed %s%s%s!", ChatColor.GREEN, ChatColor.RED, e.getEntity().getName(), ChatColor.GREEN));
        int respawnTime;
        if (victim.getPlayer().hasPermission("powermage.respawn.legend")) respawnTime = 0;
        else if (victim.getPlayer().hasPermission("powermage.respawn.elite")) respawnTime = 3;
        else if (victim.getPlayer().hasPermission("powermage.respawn.super")) respawnTime = 5;
        else respawnTime = 10;
        if (respawnTime > 0) {
            victim.getPlayer().setGameMode(GameMode.SPECTATOR);
            PowermagePlayer finalVictim = victim;
            new BukkitRunnable() {
                int time = respawnTime;
                final PowermagePlayer p = finalVictim;
                @Override
                public void run() {
                    if (time == 0) {
                        p.getPlayer().setGameMode(GameMode.ADVENTURE);
                        p.getPlayer().teleport(p.getPlayer().getWorld().getSpawnLocation());
                        this.cancel();
                    } else {
                        p.getPlayer().sendTitle(String.format("%sYou are dead!", ChatColor.RED), String.format("%sYou will respawn in %d seconds", ChatColor.GOLD, time), 10, 20, 10);
                        p.getPlayer().sendMessage(String.format("%sYou will respawn in %d seconds", ChatColor.GREEN, time));
                        time--;
                    }
                }
            }.runTaskTimer(getPlugin(), 0, 20);
        }
    }
}
