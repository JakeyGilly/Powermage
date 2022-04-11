package org.distantnetwork.powermagecore.listeners;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.distantnetwork.powermagecore.utils.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.Items.WeaponItem;
import org.jetbrains.annotations.NotNull;

public class OnDamage implements Listener {
    @EventHandler
    public static void OnDamageEvent(@NotNull EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player attacker = (Player) e.getDamager();
            if (WeaponItem.isWeaponItem(attacker.getInventory().getItemInMainHand())) {
                WeaponItem weapon = WeaponItem.getWeaponItem(attacker.getInventory().getItemInMainHand());
                if (weapon == null) return;
                if (weapon.getDamage() == 0) e.setCancelled(true);
                e.setDamage(weapon.getDamage() + (new PowermagePlayer(attacker).getStrengthUpgrade() * ConfigurationManager.getDefaultConfig().getInt("upgrades.strength.strengthPerLevel")));
            }
        }
    }
}
