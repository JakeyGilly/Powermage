package org.distantnetwork.powermagecore.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

public class OnItemClick implements Listener {
    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.END_CRYSTAL) {
            if (event.getPlayer().isSneaking()) {
                new PowermagePlayer(event.getPlayer()).getClassType().OnAbility(event.getPlayer());
            } else {
                event.setCancelled(true);
                event.getPlayer().performCommand("menu");
            }
        }
    }
}
