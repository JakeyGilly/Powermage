package org.distantnetwork.powermagecore.utils.Classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.Items.WeaponItem;

import java.util.Arrays;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;

public class Wizard extends Classes {
    public Wizard() {
        super("Wizard", 16,0.16f, 300, 15, 2, Arrays.asList(
                " ",
                String.format("%sClass Ability: %sHeart of Magic", ChatColor.BOLD, ChatColor.GOLD),
                String.format("%sGives you infinite mana for %s5 seconds.", ChatColor.GRAY, ChatColor.GREEN),
                String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW),
                " "
        ), "\uD83E\uDDEA", new ItemStack(Material.BLAZE_ROD));
    }

    @Override
    public void onAbility(Player player) {
        PowermagePlayer pmPlayer = new PowermagePlayer(player);
        if (pmPlayer.hasCooldown()) {
            player.sendMessage(String.format("%sYou cant use this ability for %s%s seconds!", ChatColor.RED, ChatColor.GOLD, pmPlayer.getCooldown()));
            return;
        }
        pmPlayer.setCooldown(60);
        pmPlayer.setInfiniteMana(true);
        pmPlayer.save();
        new BukkitRunnable() {
            @Override
            public void run() {
                pmPlayer.setInfiniteMana(false);
                pmPlayer.save();
            }
        }.runTaskLater(getInstance(), 100);
    }

    @Override
    public WeaponItem getWeaponItem() {
        return PowermageCore.wizardSword;
    }
}
