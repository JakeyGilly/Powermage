package org.distantnetwork.powermagecore;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.distantnetwork.powermagecore.Items.ExampleItem;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.ArcherBow;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.TankSword;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.WarriorSword;
import org.distantnetwork.powermagecore.Items.Weapons.ClassWeapons.WizardSword;
import org.distantnetwork.powermagecore.Items.Weapons.ExampleWeapon;
import org.distantnetwork.powermagecore.builders.InventoryBuilderListeners;
import org.distantnetwork.powermagecore.commands.Admin.*;
import org.distantnetwork.powermagecore.commands.GUICommands.ClassCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.MenuCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.SoulShopCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.UpgradeCommand;
import org.distantnetwork.powermagecore.listeners.*;
import org.distantnetwork.powermagecore.utils.ClassesChildren.Archer;
import org.distantnetwork.powermagecore.utils.ClassesChildren.Tank;
import org.distantnetwork.powermagecore.utils.ClassesChildren.Warrior;
import org.distantnetwork.powermagecore.utils.ClassesChildren.Wizard;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

public final class PowermageCore extends JavaPlugin implements Listener {
    private static PowermageCore instance;
    public static PowermageCore getInstance() {return instance;}
    public static TankSword tankSword;
    public static ArcherBow archerBow;
    public static WarriorSword warriorSword;
    public static WizardSword wizardSword;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        // Setup Classes
        new Warrior();
        new Archer();
        new Tank();
        new Wizard();
        new ExampleWeapon();
        archerBow = new ArcherBow();
        tankSword = new TankSword();
        warriorSword = new WarriorSword();
        wizardSword = new WizardSword();
        new ExampleItem();
        new BukkitRunnable() {
            @Override
            public void run() {
                FileConfiguration defaultConfig = getInstance().getConfig();
                for (Player player : PowermageCore.getInstance().getServer().getOnlinePlayers()) {
                    PowermagePlayer pmPlayer = new PowermagePlayer(player);
                    if (pmPlayer.getClassType() != null) {
                        double maxMana = pmPlayer.getClassType() == null ? 0 : pmPlayer.getClassType().getBaseMana();
                        String mana = String.format("%sMana: %d/%d", ChatColor.AQUA, pmPlayer.getMana(), Math.round(maxMana + pmPlayer.getManaUpgrade() * defaultConfig.getInt("upgrades.mana.manaPerLevel")));
                        String health = String.format("%sHealth: %d/%d", ChatColor.RED, Math.round(player.getHealth() / 20 * player.getHealthScale()), Math.round(player.getHealthScale()));
                        String speed = String.format("%sSpeed: %d", ChatColor.WHITE, Math.round(player.getWalkSpeed() * 500));
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.format("%s    %s    %s", mana, health, speed)));
                        return;
                    }
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(String.format("%sPlease select a class to continue.", ChatColor.RED)));
                }
            }
        }.runTaskTimer(this, 0, 0);
        new BukkitRunnable() {
            @Override
            public void run() {
                FileConfiguration defaultConfig = getInstance().getConfig();
                for (Player player : PowermageCore.getInstance().getServer().getOnlinePlayers()) {
                    PowermagePlayer pmPlayer = new PowermagePlayer(player);
                    if (pmPlayer.getCooldown() > 0) {
                        pmPlayer.setCooldown(pmPlayer.getCooldown() - 1);
                        pmPlayer.save();
                    }
                    if (pmPlayer.getClassType() != null) {
                        pmPlayer.setMana(pmPlayer.getMana() + 5 > pmPlayer.getClassType().getBaseMana() + pmPlayer.getManaUpgrade() * defaultConfig.getDouble("upgrades.mana.manaPerLevel") ?
                                (int)(pmPlayer.getClassType().getBaseMana() + pmPlayer.getManaUpgrade() * defaultConfig.getDouble("upgrades.mana.manaPerLevel")) : pmPlayer.getMana() + 5);
                        pmPlayer.save();
                    }
                }
            }
        }.runTaskTimer(this, 0, 20);
        setCommands();
        setListeners();
    }

    private void setCommands() {
        // gui commands
        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("class").setExecutor(new ClassCommand());
        getCommand("soulshop").setExecutor(new SoulShopCommand());
        getCommand("upgrade").setExecutor(new UpgradeCommand());
        // admin commands
        getCommand("setclass").setExecutor(new SetClass());
        getCommand("setclass").setTabCompleter(new SetClassCompleter());
        getCommand("givecoins").setExecutor(new GiveCoins());
        getCommand("givesouls").setExecutor(new GiveSouls());
        getCommand("giveweapon").setExecutor(new GiveWeapon());
        getCommand("giveweapon").setTabCompleter(new GiveWeaponCompleter());
        getCommand("setupgrades").setExecutor(new SetUpgrades());
        getCommand("setupgrades").setTabCompleter(new SetUpgradesCompleter());
        getCommand("upgradeupgrades").setExecutor(new UpgradeUpgrades());
        getCommand("upgradeupgrades").setTabCompleter(new UpgradeUpgradesCompleter());
    }

    private void setListeners() {
        getServer().getPluginManager().registerEvents(new InventoryBuilderListeners(), this);
        getServer().getPluginManager().registerEvents(new FoodChangeEvent(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new OnDamage(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new WeaponAbilityManager(), this);
    }
}
