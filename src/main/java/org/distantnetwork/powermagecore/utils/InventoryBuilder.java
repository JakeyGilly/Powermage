package org.distantnetwork.powermagecore.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryBuilder {
    private final UUID uuid;
    private final Inventory inventory;
    private final Map<Integer, onInventoryClick> actionsClick;
    private final Map<Integer, onInventoryOpen> actionsOpen;
    public static Map<UUID, InventoryBuilder> inventoriesByUUID = new HashMap<>();
    public static Map<UUID, UUID> openInventories = new HashMap<>();

    public InventoryBuilder(int size, String title) {
        uuid = UUID.randomUUID();
        inventory = Bukkit.createInventory(null, size, title);
        actionsClick = new HashMap<>();
        actionsOpen = new HashMap<>();
        inventoriesByUUID.put(getUuid(), this);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void open(Player p) {
        p.openInventory(inventory);
        openInventories.put(p.getUniqueId(), getUuid());
    }

    public void delete(){
        for (Player p : Bukkit.getOnlinePlayers()){
            UUID u = openInventories.get(p.getUniqueId());
            if (u.equals(getUuid())){
                p.closeInventory();
            }
        }
        inventoriesByUUID.remove(getUuid());
    }

    public UUID getUuid() {
        return uuid;
    }

    public static Map<UUID, InventoryBuilder> getInventoriesByUUID() {
        return inventoriesByUUID;
    }

    public static Map<UUID, UUID> getOpenInventories() {
        return openInventories;
    }

    public Map<Integer, onInventoryClick> getClickActions() {
        return actionsClick;
    }
    public Map<Integer, onInventoryOpen> getOpenActions() { return actionsOpen; }

    public void setItem(int slot, ItemStack stack, onInventoryClick click, onInventoryOpen open){
        inventory.setItem(slot, stack);
        if (click != null){
            actionsClick.put(slot, click);
        }
        if (open != null){
            actionsOpen.put(slot, open);
        }
    }

    public void setItem(int slot, ItemStack stack, onInventoryClick click){
        setItem(slot, stack, click, null);
    }

    public void setItem(int slot, ItemStack stack){
        setItem(slot, stack, null);
    }

    public interface onInventoryClick {
        void onClick(Player player);
    }

    public interface onInventoryOpen {
        void onOpen(Player player);
    }
}
