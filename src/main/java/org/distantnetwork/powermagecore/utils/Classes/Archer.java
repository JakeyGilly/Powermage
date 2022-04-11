package org.distantnetwork.powermagecore.utils.Classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.Items.WeaponItem;

import java.util.Arrays;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;

public class Archer extends Classes {
    public Archer() {
        super("Archer",12, 0.4f, 100, 10, 2, Arrays.asList(
                " ",
                String.format("%sClass Ability: %sQuickshot", ChatColor.BOLD, ChatColor.GOLD),
                String.format("%sRapid-fires arrows to where the player is looking at for %s3 seconds.", ChatColor.GRAY, ChatColor.GREEN),
                String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW),
                " "
        ), "\uD83C\uDFF9", new ItemStack(Material.CROSSBOW));
    }

    @Override
    public void onAbility(Player player) {
        PowermagePlayer pmPlayer = new PowermagePlayer(player);
        if (pmPlayer.hasCooldown()) {
            player.sendMessage(String.format("%sYou cant use this ability for %s%s seconds!", ChatColor.RED, ChatColor.GOLD, pmPlayer.getCooldown()));
            return;
        }
        pmPlayer.setCooldown(60);
        pmPlayer.save();
        for (int i = 0; i < 10; i++) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Arrow arrow = player.launchProjectile(Arrow.class);
                    arrow.setVelocity(arrow.getVelocity().multiply(2));
                    arrow.setShooter(player);
                    arrow.setBounce(false);
                    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                }
            }.runTaskLater(getInstance(), i * 3);
        }
    }

    @Override
    public WeaponItem getWeaponItem() {
        return PowermageCore.archerBow;
    }
}
