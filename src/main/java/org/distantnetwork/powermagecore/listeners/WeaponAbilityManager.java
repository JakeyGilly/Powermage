package org.distantnetwork.powermagecore.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.ArcherBow;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.TankSword;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.WarriorSword;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.WizardSword;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.WeaponItem;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;
public class WeaponAbilityManager implements Listener {
    @EventHandler
    private void onChangeWeapon(PlayerItemHeldEvent event) {
        if (WeaponItem.isWeaponItem(event.getPlayer().getInventory().getItem(event.getNewSlot()))) {
            WeaponItem weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItem(event.getNewSlot()));
            if (weaponItem != null) {
                weaponItem.onEquip(event.getPlayer());
            }
        } else if (WeaponItem.isWeaponItem(event.getPlayer().getInventory().getItem(event.getPreviousSlot()))) {
            WeaponItem weaponItem = WeaponItem.getWeaponItem(event.getPlayer().getInventory().getItem(event.getPreviousSlot()));
            if (weaponItem != null) {
                weaponItem.onUnequip(event.getPlayer());
            }
        }
    }

    @EventHandler
    private void onAttackEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (WeaponItem.isWeaponItem(player.getInventory().getItemInMainHand())) {
                WeaponItem weaponItem = WeaponItem.getWeaponItem(player.getInventory().getItemInMainHand());
                if (weaponItem != null) {
                    if (weaponItem.equals(PowermageCore.archerBow)) {
                        if (!new PowermagePlayer(player).getClassType().getName().equalsIgnoreCase("archer")) {
                            event.setCancelled(true);
                            player.sendMessage(ChatColor.RED + "You must be an archer to use this bow!");
                        }
                    }
                    if (weaponItem.equals(PowermageCore.tankSword)) {
                        if (!new PowermagePlayer(player).getClassType().getName().equalsIgnoreCase("tank")) {
                            event.setCancelled(true);
                            player.sendMessage(ChatColor.RED + "You must be a tank to use this weapon!");
                        }
                    }
                    if (weaponItem.equals(PowermageCore.warriorSword)) {
                        if (!new PowermagePlayer(player).getClassType().getName().equalsIgnoreCase("warrior")) {
                            event.setCancelled(true);
                            player.sendMessage(ChatColor.RED + "You must be a warrior to use this weapon!");
                        }
                    }
                    if (weaponItem.equals(PowermageCore.wizardSword)) {
                        if (!new PowermagePlayer(player).getClassType().getName().equalsIgnoreCase("wizard")) {
                            event.setCancelled(true);
                            player.sendMessage(ChatColor.RED + "You must be a wizard to use this weapon!");
                        }
                    }
                    weaponItem.punchEntity(player, event.getEntity(), event.getDamage(), player.isSneaking());
                }
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
            if (weaponItem != null) {
                weaponItem.onBlockBreak(event.getPlayer(), event.getBlock(), event.getPlayer().isSneaking());
            }
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
            if (weaponItem != null) {
                weaponItem.onDrop(event.getPlayer());
            }
        }
    }

    @EventHandler
    private void playerPickupItem(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (WeaponItem.isWeaponItem(event.getItem().getItemStack())) {
                WeaponItem weaponItem = WeaponItem.getWeaponItem(event.getItem().getItemStack());
                if (weaponItem != null) {
                    weaponItem.onPickup(player);
                }
            }
        }
    }

    @EventHandler
    private void bowUse(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!new PowermagePlayer(player).getClassType().getName().equalsIgnoreCase("archer")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You must be an archer to use this bow!");
            }
        }
    }


}
