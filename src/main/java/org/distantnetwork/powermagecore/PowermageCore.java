package org.distantnetwork.powermagecore;

import org.bukkit.plugin.java.JavaPlugin;
import org.distantnetwork.powermagecore.commands.*;
import org.distantnetwork.powermagecore.commands.AutoCompleters.PluginCommandCompleter;
import org.distantnetwork.powermagecore.listeners.FoodChangeEvent;
import org.distantnetwork.powermagecore.listeners.OnItemClick;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.*;
import org.distantnetwork.powermagecore.utils.Config.MainConfigManager;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;
import org.distantnetwork.powermagecore.utils.Builders.InventoryBuilderListener;
import org.distantnetwork.powermagecore.utils.Enums.Classes;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PowermageCore extends JavaPlugin {
    private static PowermageCore instance;
    public static Map<UUID, Boolean> playerCombatLog = new HashMap<>();
    public static Map<UUID, Classes> playerClasses = new HashMap<>();
    public static Map<UUID, Map<Classes, Map<Integer, Integer>>> playerLevels = new HashMap<>();
    public static Map<UUID, Integer> playerSouls = new HashMap<>();
    public static Map<UUID, Integer> playerCoins = new HashMap<>();
    public static Map<UUID, Map<Upgrades, Integer>> playerUpgrades = new HashMap<>();


    public PowermageCore() {
        instance = this;
    }
    public static PowermageCore getInstance() {return instance;}
    // TODO UPGRADING TOOLS AND WEAPONS
    @Override
    public void onEnable() {
        PlayerClasses.load();
        PlayerCoins.load();
        PlayerCombatLog.load();
        PlayerLevels.load();
        PlayerSouls.load();
        PlayerUpgrades.load();
        if (MainConfigManager.loadFileLength() == 0) MainConfigManager.saveDefaultConfig();
        if (WeaponConfigManager.loadWeaponAmount() == 0) WeaponConfigManager.saveDefaultWeapons();
        setCommands();
        setListeners();
    }

    @Override
    public void onDisable() {
        PlayerClasses.save();
        PlayerCoins.save();
        PlayerCombatLog.save();
        PlayerLevels.save();
        PlayerSouls.save();
        PlayerUpgrades.save();
    }

    private void setCommands() {
        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("store").setExecutor(new StoreCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("class").setExecutor(new ClassCommand());
        getCommand("giveitem").setExecutor(new GiveWeaponCommand());
        getCommand("soulshop").setExecutor(new SoulShopCommand());
        getCommand("upgrade").setExecutor(new UpgradeCommand());
        getCommand("pmc").setExecutor(new PluginCommand());
        getCommand("pmc").setTabCompleter(new PluginCommandCompleter());
    }

    private void setListeners() {
        getServer().getPluginManager().registerEvents(new InventoryBuilderListener(), this);
        getServer().getPluginManager().registerEvents(new FoodChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new OnItemClick(), this);
    }
}
