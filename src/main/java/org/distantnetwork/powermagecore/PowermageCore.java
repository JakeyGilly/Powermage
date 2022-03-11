package org.distantnetwork.powermagecore;

import com.google.common.collect.ImmutableMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.distantnetwork.powermagecore.commands.*;
import org.distantnetwork.powermagecore.listeners.FoodChangeEvent;
import org.distantnetwork.powermagecore.listeners.OnItemClick;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;
import org.distantnetwork.powermagecore.utils.InventoryBuilderListener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PowermageCore extends JavaPlugin {
    private static PowermageCore instance;
    public static Map<Classes, String> classesEmojis = new HashMap<>(ImmutableMap.of(Classes.WARRIOR, "\uD83D\uDDE1", Classes.TANK, "\uD83D\uDEE1", Classes.ARCHER, "\uD83C\uDFF9", Classes.WIZARD, "\uD83E\uDDEA"));
    public static Map<UUID, Boolean> playerCombatLog = new HashMap<>();
    public static Map<UUID, Classes> playerClasses = new HashMap<>();
    public static Map<UUID, Map<Classes, Map<Integer, Integer>>> playerLevels = new HashMap<>();
    // {uuid, {WARRIOR, {warriorlvl, warriorexp}}, {TANK, {tanklvl, tankexp}, {ARCHER, {archerlvl, archerexp}, {WIZARD, {wizardlvl, wizardexp}}}, {...}
    public static Map<UUID, Integer> playerSouls = new HashMap<>();
    public static Map<UUID, Integer> playerCoins = new HashMap<>();

    public PowermageCore() {
        instance = this;
    }
    public static PowermageCore getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        if (WeaponConfigManager.getWeaponAmount() == 0) WeaponConfigManager.createDefaultWeapons();


        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("store").setExecutor(new StoreCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("class").setExecutor(new ClassCommand());
        getCommand("giveitem").setExecutor(new GiveWeaponCommand());
        getCommand("soulshop").setExecutor(new SoulShopCommand());
        getServer().getPluginManager().registerEvents(new InventoryBuilderListener(), this);
        getServer().getPluginManager().registerEvents(new FoodChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new OnItemClick(), this);
    }
}
