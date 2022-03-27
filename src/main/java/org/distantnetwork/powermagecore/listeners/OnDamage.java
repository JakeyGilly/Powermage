package org.distantnetwork.powermagecore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.WeaponItem;
import org.jetbrains.annotations.NotNull;

public class OnDamage implements Listener {
    @EventHandler
    public static void OnDamageEvent(@NotNull EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player attacker = e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK ? ((EntityDamageByEntityEvent) e).getDamager() instanceof Player ? (Player) ((EntityDamageByEntityEvent) e).getDamager() : null : null;
            if (attacker == null) return;
            WeaponItem weapon = new WeaponItem(attacker.getInventory().getItemInMainHand());
            if (weapon.getHitDamage() == 0) e.setCancelled(true);
            e.setDamage(weapon.getHitDamage() + (new PowermagePlayer(attacker).getStrengthUpgrade() * ConfigurationManager.getDefaultConfig().getInt("upgrades.strength.strengthPerLevel")));
        }
    }
}
