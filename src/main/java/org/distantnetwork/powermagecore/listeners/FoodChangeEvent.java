package org.distantnetwork.powermagecore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChangeEvent implements Listener {
    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
        if (e.getEntity().getType().name().contains("PLAYER")) {
            e.setCancelled(true);
        }
    }
}
