package org.distantnetwork.powermagecore.utils.Classes.Weapons;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.distantnetwork.powermagecore.utils.Rarity;
import org.distantnetwork.powermagecore.utils.Items.WeaponItem;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class WarriorSword extends WeaponItem {
    public WarriorSword() {
        super(
                Material.STONE_SWORD,
                1,
                "&7Warrior Sword",
                Collections.singletonList("The starter sword for the Warrior Class"),
                Arrays.asList(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE),
                new HashMap<Enchantment, Integer>() {{put(Enchantment.MENDING, 1);}},
                0,
                true,
                Rarity.COMMON,
                2,
                0,
                Collections.singletonList("A sharp pointy object"),
                true
        );
    }

    @Override
    public void onEquip(Player player) {

    }

    @Override
    public void onUnequip(Player player) {

    }

    @Override
    public void clickEntity(Player player, Entity target, boolean shifting) {

    }

    @Override
    public void punchEntity(Player player, Entity target, double damage, boolean shifting) {

    }

    @Override
    public void leftClickOnBlock(Player player, Block block, boolean shifting) {

    }

    @Override
    public void leftClickOnAir(Player player, Block block, boolean shifting) {

    }

    @Override
    public void rightClickOnBlock(Player player, Block block, boolean shifting) {

    }

    @Override
    public void rightClickOnAir(Player player, Block block, boolean shifting) {

    }

    @Override
    public void onBlockBreak(Player player, Block block, boolean shifting) {

    }

    @Override
    public void onBlockPlace(Player player, Block block, boolean shifting) {

    }

    @Override
    public void onDrop(Player player) {

    }

    @Override
    public void onPickup(Player player) {

    }
}