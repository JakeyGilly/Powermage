package org.distantnetwork.powermagecore.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class InventoryBuilderListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();
        UUID inventoryUUID = InventoryBuilder.openInventories.get(playerUUID);
        if (inventoryUUID == null) return;
        e.setCancelled(true);
        InventoryBuilder gui = InventoryBuilder.getInventoriesByUUID().get(inventoryUUID);
        InventoryBuilder.onInventoryClick click = gui.getClickActions().get(e.getSlot());
        if (click == null) return;
        click.onClick(player);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        UUID playerUUID = player.getUniqueId();
        InventoryBuilder.openInventories.remove(playerUUID);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();
        InventoryBuilder.openInventories.remove(playerUUID);
    }
}
