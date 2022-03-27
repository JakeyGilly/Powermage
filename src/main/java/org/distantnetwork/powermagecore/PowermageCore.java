package org.distantnetwork.powermagecore;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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
import org.distantnetwork.powermagecore.utils.Enums.Rarity;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.WeaponItem;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class PowermageCore extends JavaPlugin implements Listener {
    private static PowermageCore instance;
    public static PowermageCore getInstance() {return instance;}

    // TODO UPGRADING TOOLS AND WEAPONS
    // TODO CUSTOM ENCHANTMENTS
    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
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

    @EventHandler
    public void onPlayerAbility(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_AIR ||
                e.getAction() == Action.RIGHT_CLICK_BLOCK ||
                e.getAction() == Action.LEFT_CLICK_AIR ||
                e.getAction() == Action.LEFT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            new PowermagePlayer(player).getClassType().OnAbility(player);
        }
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
//        getCommand("levelup").setExecutor(new LevelUpCommand());
//        getCommand("levelup").setTabCompleter(new LevelUpCompleter());
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
