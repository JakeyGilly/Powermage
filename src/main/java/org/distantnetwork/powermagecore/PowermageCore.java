package org.distantnetwork.powermagecore;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.plugin.java.JavaPlugin;
import org.distantnetwork.powermagecore.commands.*;
import org.distantnetwork.powermagecore.listeners.FoodChangeEvent;
import org.distantnetwork.powermagecore.listeners.OnInventoryClick;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PowermageCore extends JavaPlugin {
    public static Map<Classes, String> classesEmojis = new HashMap<>(ImmutableMap.of(Classes.WARRIOR, "\uD83D\uDDE1", Classes.TANK, "\uD83D\uDEE1", Classes.ARCHER, "\uD83C\uDFF9", Classes.WIZARD, "\uD83E\uDDEA"));
    public static Map<UUID, Classes> playerClasses = new HashMap<>();
    public static Map<UUID, Map<Classes, Map<Integer, Integer>>> playerLevels = new HashMap<>();
    // {uuid, {WARRIOR, {warriorlvl, warriorexp}}, {TANK, {tanklvl, tankexp}, {ARCHER, {archerlvl, archerexp}, {WIZARD, {wizardlvl, wizardexp}}}, {...}
    public static Map<UUID, Integer> playerSouls = new HashMap<>();
    public static Map<UUID, Integer> playerCoins = new HashMap<>();
    public static Map<UUID, Boolean> playerCombatLog = new HashMap<>();

    @Override
    public void onEnable() {
        WeaponsManager.setup();
        Map<Enchantment, Integer> enchantment = new HashMap<>();
        enchantment.put(Enchantment.DIG_SPEED, 1);
        String[] lore = {"ah", "ah"};
        ItemFlag[] flags = {ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES};
        Weapon weapon = new Weapon(0, "ah", Material.IRON_SWORD, enchantment, 7, lore, flags);
        WeaponsManager.setWeapon(weapon);
        WeaponsManager.saveFile();
        getCommand("start").setExecutor(new StartCommand());
        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("store").setExecutor(new StoreCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("soulshop").setExecutor(new SoulShopCommand());
        getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new FoodChangeEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
