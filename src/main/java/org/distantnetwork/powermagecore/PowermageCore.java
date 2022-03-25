package org.distantnetwork.powermagecore;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.plugin.java.JavaPlugin;
import org.distantnetwork.powermagecore.builders.InventoryBuilderListeners;
import org.distantnetwork.powermagecore.commands.*;
import org.distantnetwork.powermagecore.commands.AdminCommands.GiveMoneyCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.GiveSoulsCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.GiveWeaponsCommand.GiveWeaponCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.GiveWeaponsCommand.GiveWeaponsCompleter;
import org.distantnetwork.powermagecore.commands.AdminCommands.LevelUpCommand.LevelUpCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.LevelUpCommand.LevelUpCompleter;
import org.distantnetwork.powermagecore.commands.AdminCommands.SetUpgradesCommand.SetUpgradesCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.SetUpgradesCommand.SetUpgradesComplete;
import org.distantnetwork.powermagecore.commands.PluginCommand.PluginCommandCompleter;
import org.distantnetwork.powermagecore.commands.GUICommands.ClassCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.MenuCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.SoulShopCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.UpgradeCommand;
import org.distantnetwork.powermagecore.commands.PluginCommand.PluginCommand;
import org.distantnetwork.powermagecore.listeners.*;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.Config.Hashmap.*;
import org.distantnetwork.powermagecore.utils.Enums.ClassesEnum;
import org.distantnetwork.powermagecore.utils.Enums.Rarity;
import org.distantnetwork.powermagecore.utils.Enums.Upgrades;
import org.distantnetwork.powermagecore.utils.WeaponItem;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class PowermageCore extends JavaPlugin {
    private static PowermageCore instance;
    public static PowermageCore getInstance() {return instance;}

    public static Map<UUID, ClassesEnum> playerClasses = new HashMap<>();
    public static Map<UUID, HashMap<ClassesEnum, ArrayList<Integer>>> playerLevels = new HashMap<>();
    public static Map<UUID, Integer> playerSouls = new HashMap<>();
    public static Map<UUID, Integer> playerCoins = new HashMap<>();
    public static Map<UUID, Map<Upgrades, Integer>> playerUpgrades = new HashMap<>();
    public static Map<UUID, Integer> playerDeaths = new HashMap<>();
    public static Map<UUID, Integer> playerKills = new HashMap<>();

    // non file based maps
    public static Map<UUID, Boolean> playerCombatLog = new HashMap<>();
    public static Map<UUID, Integer> playerKillStreak = new HashMap<>();

    // TODO UPGRADING TOOLS AND WEAPONS
    // TODO CUSTOM ENCHANTMENTS
    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        PlayerClasses.load();
        PlayerCoins.load();
        PlayerLevels.load();
        PlayerSouls.load();
        PlayerUpgrades.load();
        PlayerDeaths.load();
        PlayerKills.load();
        if (instance.getConfig().getValues(false).size() > 0) ConfigurationManager.saveDefaultConfig();
        File folder = ConfigurationManager.getFileFolder(instance.getDataFolder() + File.separator + "weapons");
        if (folder == null) throw new IllegalStateException("Weapons folder is a file.");
        if (ConfigurationManager.getFilesAmountInFolder(folder) == 0) {
            File file = new File(folder, "weapon-example.yml");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<ItemFlag> itemFlagList = new ArrayList<>();
            itemFlagList.add(ItemFlag.HIDE_ENCHANTS);
            itemFlagList.add(ItemFlag.HIDE_ATTRIBUTES);
            itemFlagList.add(ItemFlag.HIDE_UNBREAKABLE);
            String[] lorelist = new ArrayList<String>() {{
                add("&7&oThis is an example weapon.");
            }}.toArray(new String[0]);
            new WeaponItem(Material.WOODEN_SWORD, 1, itemFlagList, null,
                    lorelist, "&7&oExample Weapon", 0, true, 1, Rarity.CORE).save(file.getPath());
        }
        setCommands();
        setListeners();
    }

    @Override
    public void onDisable() {
        PlayerClasses.save();
        PlayerCoins.save();
        PlayerLevels.save();
        PlayerSouls.save();
        PlayerUpgrades.save();
        PlayerDeaths.save();
        PlayerKills.save();
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
        getServer().getPluginManager().registerEvents(new InventoryBuilderListeners(), this);
        getServer().getPluginManager().registerEvents(new FoodChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new OnItemClick(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new OnDamage(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
    }
}
