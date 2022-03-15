package org.distantnetwork.powermagecore;

import org.bukkit.plugin.java.JavaPlugin;
import org.distantnetwork.powermagecore.commands.*;
import org.distantnetwork.powermagecore.commands.AdminCommands.GiveMoneyCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.GiveSoulsCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.GiveWeaponsCommand.GiveWeaponCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.GiveWeaponsCommand.GiveWeaponsCompleter;
import org.distantnetwork.powermagecore.commands.AdminCommands.LevelUpCommand.LevelUpCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.LevelUpCommand.LevelUpCompleter;
import org.distantnetwork.powermagecore.commands.AdminCommands.SetCombatLogCommand.SetCombatLogCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.SetCombatLogCommand.SetCombatLogCompleter;
import org.distantnetwork.powermagecore.commands.AdminCommands.SetUpgradesCommand.SetUpgradesCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.SetUpgradesCommand.SetUpgradesComplete;
import org.distantnetwork.powermagecore.commands.PluginCommand.PluginCommandCompleter;
import org.distantnetwork.powermagecore.commands.GUICommands.ClassCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.MenuCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.SoulShopCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.UpgradeCommand;
import org.distantnetwork.powermagecore.commands.PluginCommand.PluginCommand;
import org.distantnetwork.powermagecore.listeners.FoodChangeEvent;
import org.distantnetwork.powermagecore.listeners.OnItemClick;
import org.distantnetwork.powermagecore.listeners.OnPlayerDeath;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.*;
import org.distantnetwork.powermagecore.utils.Config.MainConfigManager;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;
import org.distantnetwork.powermagecore.utils.Builders.InventoryBuilderListener;
import org.distantnetwork.powermagecore.utils.Enums.Classes;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PowermageCore extends JavaPlugin {
    private static PowermageCore instance;
    public static Map<UUID, Classes> playerClasses = new HashMap<>();
    public static Map<UUID, HashMap<Classes, ArrayList<Integer>>> playerLevels = new HashMap<>();
    public static Map<UUID, Integer> playerSouls = new HashMap<>();
    public static Map<UUID, Integer> playerCoins = new HashMap<>();
    public static Map<UUID, Map<Upgrades, Integer>> playerUpgrades = new HashMap<>();
    public static Map<UUID, Integer> playerDeaths = new HashMap<>();
    public static Map<UUID, Integer> playerKills = new HashMap<>();

    // non file based maps
    public static Map<UUID, Boolean> playerCombatLog = new HashMap<>();
    public static Map<UUID, Integer> playerKillStreak = new HashMap<>();



    public PowermageCore() {
        instance = this;
    }
    public static PowermageCore getInstance() {return instance;}
    // TODO UPGRADING TOOLS AND WEAPONS
    // TODO CUSTOM ENCHANTMENTS
    @Override
    public void onEnable() {
        PlayerClasses.load();
        PlayerCoins.load();
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
        getCommand("soulshop").setExecutor(new SoulShopCommand());
        getCommand("upgrade").setExecutor(new UpgradeCommand());
        getCommand("pmc").setExecutor(new PluginCommand());
        getCommand("pmc").setTabCompleter(new PluginCommandCompleter());
        getCommand("givesouls").setExecutor(new GiveSoulsCommand());
        getCommand("givecoins").setExecutor(new GiveMoneyCommand());
        getCommand("giveitem").setExecutor(new GiveWeaponCommand());
        getCommand("giveitem").setTabCompleter(new GiveWeaponsCompleter());
        getCommand("setupgrades").setExecutor(new SetUpgradesCommand());
        getCommand("setupgrades").setTabCompleter(new SetUpgradesComplete());
        getCommand("levelup").setExecutor(new LevelUpCommand());
        getCommand("levelup").setTabCompleter(new LevelUpCompleter());
    }

    private void setListeners() {
        getServer().getPluginManager().registerEvents(new InventoryBuilderListener(), this);
        getServer().getPluginManager().registerEvents(new FoodChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new OnItemClick(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
    }
}
