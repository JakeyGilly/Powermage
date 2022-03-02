package org.distantnetwork.powermagecore.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChangeEvent implements Listener {
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
        if (e.getEntity().getType().name().contains("PLAYER")) {
            e.setCancelled(true);
        }
    }
}
