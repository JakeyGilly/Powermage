package org.distantnetwork.powermagecore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.distantnetwork.powermagecore.utils.WeaponItem;

public class WeaponAbilityManager implements Listener {
    @EventHandler
    private void onChangeWeapon(PlayerItemHeldEvent event) {
        if (WeaponItem.isWeaponItem(event.getPlayer().getInventory().getItem(event.getNewSlot()))) {
            WeaponItem weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItem(event.getNewSlot()));
            weaponItem.onEquip(event.getPlayer());
        } else if (WeaponItem.isWeaponItem(event.getPlayer().getInventory().getItem(event.getPreviousSlot()))) {
            WeaponItem weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItem(event.getPreviousSlot()));
            weaponItem.onUnequip(event.getPlayer());
        }
    }

    @EventHandler
    private void onAttackEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (WeaponItem.isWeaponItem(player.getInventory().getItemInMainHand())) {
                WeaponItem weaponItem = WeaponItem.getWeaponItem(player.getInventory().getItemInMainHand());
                weaponItem.punchEntity(player, event.getEntity(), event.getDamage(), player.isSneaking());
            }
        }
    }

    @EventHandler
    private void playerInteractionEntity(PlayerInteractEntityEvent event) {
        WeaponItem weaponItem = null;
        if (event.getHand() == EquipmentSlot.HAND) {
            weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItemInMainHand());
        } else if (event.getHand() == EquipmentSlot.OFF_HAND) {
            weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItemInOffHand());
        }
        if (weaponItem != null) {
            weaponItem.clickEntity(event.getPlayer(), event.getRightClicked(), event.getPlayer().isSneaking());
        }
    }

    @EventHandler
    private void playerInteraction(PlayerInteractEvent event) {
        WeaponItem weaponItem = null;
        if (event.getHand() == EquipmentSlot.HAND) {
            weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItemInMainHand());
        } else if (event.getHand() == EquipmentSlot.OFF_HAND) {
            weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItemInOffHand());
        }
        if (weaponItem != null) {
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                weaponItem.leftClickOnBlock(event.getPlayer(), event.getClickedBlock(), event.getPlayer().isSneaking());
            } else if (event.getAction() == Action.LEFT_CLICK_AIR) {
                weaponItem.leftClickOnAir(event.getPlayer(), event.getClickedBlock(), event.getPlayer().isSneaking());
            } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                weaponItem.rightClickOnBlock(event.getPlayer(), event.getClickedBlock(), event.getPlayer().isSneaking());
            } else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                weaponItem.rightClickOnAir(event.getPlayer(), event.getClickedBlock(), event.getPlayer().isSneaking());
            }
        }
    }

    @EventHandler
    private void playerBreakBlock(BlockBreakEvent event) {
        if (WeaponItem.isWeaponItem(event.getPlayer().getInventory().getItemInMainHand())) {
            WeaponItem weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItemInMainHand());
            weaponItem.onBlockBreak(event.getPlayer(), event.getBlock(), event.getPlayer().isSneaking());
        }
    }

    @EventHandler
    private void playerPlaceBlock(BlockPlaceEvent event) {
        WeaponItem weaponItem = null;
        if (event.getHand() == EquipmentSlot.HAND) {
            weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItemInMainHand());
        } else if (event.getHand() == EquipmentSlot.OFF_HAND) {
            weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItemInOffHand());
        }
        if (weaponItem != null) {
            weaponItem.onBlockPlace(event.getPlayer(), event.getBlock(), event.getPlayer().isSneaking());
        }
    }

    @EventHandler
    private void playerDropItem(PlayerDropItemEvent event) {
        if (WeaponItem.isWeaponItem(event.getItemDrop().getItemStack())) {
            WeaponItem weaponItem = WeaponItem.getWeaponItem(event.getItemDrop().getItemStack());
            weaponItem.onDrop(event.getPlayer());
        }
    }

    @EventHandler
    private void playerPickupItem(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (WeaponItem.isWeaponItem(event.getItem().getItemStack())) {
                WeaponItem weaponItem = WeaponItem.getWeaponItem(event.getItem().getItemStack());
                weaponItem.onPickup(player);
            }
        }
    }
}
