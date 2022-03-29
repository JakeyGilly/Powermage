package org.distantnetwork.powermagecore;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
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
import org.distantnetwork.powermagecore.commands.AdminCommands.LevelUp.LevelUpCommand;
import org.distantnetwork.powermagecore.commands.AdminCommands.LevelUp.LevelUpCompleter;
import org.distantnetwork.powermagecore.commands.GUICommands.ClassCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.MenuCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.SoulShopCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.UpgradeCommand;
import org.distantnetwork.powermagecore.listeners.*;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.Enums.Rarity;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermagecore.utils.WeaponItem;

import java.io.File;
import java.util.*;

import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.*;

public final class PowermageCore extends JavaPlugin implements Listener {
    private static PowermageCore instance;
    public static PowermageCore getInstance() {return instance;}

    // TODO UPGRADING TOOLS AND WEAPONS
    // TODO CUSTOM ENCHANTMENTS
    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        if (getFilesAmountInFolder("weapons") > 0) {
            File weaponsFolder = getFileFolder(getDataFolder() + "weapons");
            if (weaponsFolder == null) throw new NullPointerException("Weapons folder is file");
            File[] list = weaponsFolder.listFiles();
            if (list != null) Arrays.stream(list).filter(file -> file.getName().endsWith(".yml")).forEach(file -> getLogger().info("Loading weapon: " + file.getName()));
        } else {
            File file = getFileFile("weapons" + File.separator + "weapon-example.yml");
            if (file == null) throw new IllegalStateException("Weapon example file is a folder.");
            FileConfiguration config = new WeaponItem(Material.WOODEN_SWORD, 1,
                    Arrays.asList(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE),
                    new HashMap<Enchantment, Integer>() {{
                        put(Enchantment.MENDING, 1);
                    }},
                    Collections.singletonList("&7&oThis is an example weapon."),
                    "&7&oExample Weapon", 0, true, 1, Rarity.CORE).save("weapons" + File.separator + "weapon-example.yml");
            ConfigurationManager.saveConfig(file, config);
        }
        setCommands();
        setListeners();
    }

    @EventHandler
    public void onPlayerAbility(PlayerInteractEvent e) {
        if ((e.getAction() == Action.RIGHT_CLICK_AIR ||
                e.getAction() == Action.RIGHT_CLICK_BLOCK ||
                e.getAction() == Action.LEFT_CLICK_AIR ||
                e.getAction() == Action.LEFT_CLICK_BLOCK) &&
                e.getPlayer().isSneaking() &&
                e.getPlayer().getInventory().getItemInMainHand().getType() == Material.END_CRYSTAL) {
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
        getCommand("givesouls").setExecutor(new GiveSoulsCommand());
        getCommand("givecoins").setExecutor(new GiveMoneyCommand());
        getCommand("giveitem").setExecutor(new GiveWeaponCommand());
        getCommand("giveitem").setTabCompleter(new GiveWeaponsCompleter());
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

        //? Abilitys
        getServer().getPluginManager().registerEvents(this, this);
    }
}
