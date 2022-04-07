package org.distantnetwork.powermagecore.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

public class MenuListener implements Listener {
    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) && event.getMaterial() == Material.END_CRYSTAL) {
            if (event.getPlayer().isSneaking()) {
                new PowermagePlayer(event.getPlayer()).getClassType().onAbility(event.getPlayer());
            } else {
                event.setCancelled(true);
                event.getPlayer().performCommand("menu");
            }
        } else if (event.getMaterial() == Material.END_CRYSTAL) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.END_CRYSTAL) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) return;
        if (event.getCurrentItem().getType() == Material.END_CRYSTAL) {
            event.setCancelled(true);
        }
    }
}
