package org.distantnetwork.powermagecore.utils.ClassesChildren;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.TankSword;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Classes;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.WeaponItem;

import java.util.Arrays;

public class Tank extends Classes {
    public Tank() {
        super("Tank", 14, 0.1f, 100, 40, 2, Arrays.asList(" ",
                String.format("%sClass Ability: %sTurtle Up", ChatColor.BOLD, ChatColor.GOLD),
                String.format("%sGives player %sResistance 2 %sand %sRegen 5 %sfor %s10 seconds.", ChatColor.GRAY, ChatColor.BLUE, ChatColor.GRAY, ChatColor.BLUE, ChatColor.GRAY, ChatColor.GREEN),
                String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW),
                " "
        ),"\uD83D\uDEE1", new ItemStack(Material.IRON_CHESTPLATE));
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
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 5));
    }

    @Override
    public WeaponItem getWeaponItem() {
        return PowermageCore.tankSword;
    }
}
