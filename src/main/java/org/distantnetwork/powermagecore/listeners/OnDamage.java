package org.distantnetwork.powermagecore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.PlayerUpgrades;
import org.distantnetwork.powermagecore.utils.Config.MainConfigManager;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;

public class OnDamage implements Listener {
    @EventHandler
    public static void OnDamageEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player attacker = e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK ? ((EntityDamageByEntityEvent) e).getDamager() instanceof Player ? (Player) ((EntityDamageByEntityEvent) e).getDamager() : null : null;
            if (attacker == null) return;
            if (attacker.getInventory().getItemInMainHand().getItemMeta() != null && WeaponConfigManager.getWeaponId(attacker.getInventory().getItemInMainHand().getItemMeta().getDisplayName()) != null && WeaponConfigManager.getValue(WeaponConfigManager.getWeaponId(attacker.getInventory().getItemInMainHand().getItemMeta().getDisplayName()), "damage") != null) {
                e.setDamage(WeaponConfigManager.getConfig(WeaponConfigManager.getWeaponId(attacker.getInventory().getItemInMainHand().getItemMeta().getDisplayName())).getDouble("damage") + (PlayerUpgrades.getUpgradeLevel(attacker.getUniqueId(), Upgrades.STRENGTH) * (int)MainConfigManager.getValue("upgrades.strength.strengthPerLevel")));
            } else {
                e.setCancelled(true);
            }
        }
    }
}
