package org.distantnetwork.powermagecore.Items.Weapons;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.distantnetwork.powermagecore.utils.Enums.Rarity;
import org.distantnetwork.powermagecore.utils.WeaponItem;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class ExampleWeapon extends WeaponItem {
    public ExampleWeapon() {
        super(
                Material.WOODEN_SWORD,
                1,
                "&7&oExample Weapon",
                Collections.singletonList("&7&oThis is an example weapon."),
                Arrays.asList(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE),
                new HashMap<Enchantment, Integer>() {{put(Enchantment.MENDING, 1);}},
                0,
                true,
                Rarity.CORE,
                1,
                1,
                Collections.singletonList("A sword forged in the heart of a dwarven star"),
                true
        );
    }

    @Override
    public void onEquip(Player player) {
        player.sendMessage("Equipped Example Weapon");
    }

    @Override
    public void onUnequip(Player player) {
        player.sendMessage("Unequipped Example Weapon");
    }

    @Override
    public void clickEntity(Player player, Entity target, boolean shifting) {
        player.sendMessage("Clicked on entity with Example Weapon");
    }

    @Override
    public void punchEntity(Player player, Entity target, double damage, boolean shifting) {
        player.sendMessage("Punched entity with Example Weapon");
    }

    @Override
    public void leftClickOnBlock(Player player, Block block, boolean shifting) {
        player.sendMessage("Left clicked on block with Example Weapon");
    }

    @Override
    public void leftClickOnAir(Player player, Block block, boolean shifting) {
        player.sendMessage("Left clicked on air with Example Weapon");
    }

    @Override
    public void rightClickOnBlock(Player player, Block block, boolean shifting) {
        player.sendMessage("Right clicked on block with Example Weapon");
    }

    @Override
    public void rightClickOnAir(Player player, Block block, boolean shifting) {
        player.sendMessage("Right clicked on air with Example Weapon");
    }

    @Override
    public void onBlockBreak(Player player, Block block, boolean shifting) {
        player.sendMessage("Broke block with Example Weapon");
    }

    @Override
    public void onBlockPlace(Player player, Block block, boolean shifting) {
        player.sendMessage("Placed block with Example Weapon");
    }

    @Override
    public void onDrop(Player player) {
        player.sendMessage("Dropped Example Weapon");
    }

    @Override
    public void onPickup(Player player) {
        player.sendMessage("Picked up Example Weapon");
    }
}
