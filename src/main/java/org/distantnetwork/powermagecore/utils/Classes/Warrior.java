package org.distantnetwork.powermagecore.utils.Classes;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.Items.WeaponItem;

import java.util.Arrays;

import static org.distantnetwork.powermagecore.PowermageCore.getPlugin;

public class Warrior extends Classes {
    public Warrior() {
        super("Warrior",10, 0.2f, 100, 20, 5, Arrays.asList(
                " ",
                String.format("%sClass Ability: %sCharge", ChatColor.BOLD, ChatColor.GOLD),
                String.format("%sGives player %s+100 %s✦ Speed %sfor %s10 %sseconds.", ChatColor.GRAY, ChatColor.GREEN, ChatColor.GOLD, ChatColor.GRAY, ChatColor.GREEN, ChatColor.GRAY),
                String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW),
                " "
        ), "\uD83D\uDDE1", new ItemStack(Material.IRON_SWORD));
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
        player.setWalkSpeed((float) (pmPlayer.getClassType().getBaseSpeed() + pmPlayer.getSpeedUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel") + 0.2f));
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setWalkSpeed((float) (pmPlayer.getClassType().getBaseSpeed() + pmPlayer.getSpeedUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel")));
            }
        }.runTaskLater(getPlugin(), 200);
    }

    @Override
    public WeaponItem getWeaponItem() {
        return PowermageCore.warriorSword;
    }
}
