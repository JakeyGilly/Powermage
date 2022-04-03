package org.distantnetwork.powermagecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.WeaponItem;
import org.jetbrains.annotations.NotNull;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;

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
            ArmorStand armourStand = (ArmorStand) e.getEntity().getLocation().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ARMOR_STAND);
            armourStand.setVisible(false);
            armourStand.setCustomNameVisible(true);
            armourStand.setGravity(false);
            if (attacker.getFallDistance() > 0.0F &&
                    !attacker.isOnGround() &&
                    !attacker.isInsideVehicle() &&
                    !attacker.getLocation().getBlock().isLiquid() &&
                    !attacker.getActivePotionEffects().contains(PotionEffectType.BLINDNESS) &&
                    !attacker.isSprinting()) {
                armourStand.setCustomName(String.format("%s✧-%s✧", ChatColor.RED, e.getDamage()));
            } else {
                armourStand.setCustomName(String.format("%s-%s", ChatColor.RED, e.getDamage()));
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    armourStand.remove();
                }
            }.runTaskLater(getInstance(), 20);
        }
    }
}
