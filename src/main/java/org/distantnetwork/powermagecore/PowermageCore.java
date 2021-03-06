package org.distantnetwork.powermagecore;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.distantnetwork.powermagecore.utils.Classes.Archer;
import org.distantnetwork.powermagecore.utils.Classes.Tank;
import org.distantnetwork.powermagecore.utils.Classes.Warrior;
import org.distantnetwork.powermagecore.utils.Classes.Weapons.ArcherBow;
import org.distantnetwork.powermagecore.utils.Classes.Weapons.TankSword;
import org.distantnetwork.powermagecore.utils.Classes.Weapons.WarriorSword;
import org.distantnetwork.powermagecore.utils.Classes.Weapons.WizardSword;
import org.distantnetwork.powermagecore.builders.InventoryBuilderListeners;
import org.distantnetwork.powermagecore.commands.Admin.*;
import org.distantnetwork.powermagecore.commands.GUICommands.ClassCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.MenuCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.SoulShopCommand;
import org.distantnetwork.powermagecore.commands.GUICommands.UpgradeCommand;
import org.distantnetwork.powermagecore.listeners.*;

import org.distantnetwork.powermagecore.utils.Classes.Wizard;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;
import org.distantnetwork.powermageitems.Items.*;

public final class PowermageCore extends JavaPlugin implements Listener {
    private static JavaPlugin plugin;
    public static JavaPlugin getPlugin() {
        return plugin;
    }
    public static TankSword tankSword;
    public static ArcherBow archerBow;
    public static WarriorSword warriorSword;
    public static WizardSword wizardSword;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        // Setup Classes
        new Warrior();
        new Archer();
        new Tank();
        new Wizard();
        archerBow = new ArcherBow();
        tankSword = new TankSword();
        warriorSword = new WarriorSword();
        wizardSword = new WizardSword();
        new ZeusBolt();
        new Repulsion();
        new SuperRepulsion();
        new Dasher();
        new SuperDasher();
        new HyperDasher();
        new ForkOfForce();
        new GrapplingHook();
        new NightSword();
        new DaySword();
        new WandOfMending();
        new SuperWandOfMending();
        new HyperWandOfMending();
        new SlappyTheFish();
        new BukkitRunnable() {
            @Override
            public void run() {
                FileConfiguration defaultConfig = getConfig();
                for (Player player : getServer().getOnlinePlayers()) {
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
                FileConfiguration defaultConfig = getConfig();
                for (Player player : getServer().getOnlinePlayers()) {
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
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
    }
}
