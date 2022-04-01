package org.distantnetwork.powermagecore;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
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
import org.bukkit.scheduler.BukkitRunnable;
import org.distantnetwork.powermagecore.builders.InventoryBuilderListeners;
import org.distantnetwork.powermagecore.commands.*;
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
            File weaponsFolder = getFileFolder("weapons");
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
                    "&7&oExample Weapon", 0, true, 1, Rarity.CORE, 1, Collections.singletonList("A sword forged in the heart of a dwarven star idk im just making stuff up"), true).save("weapons" + File.separator + "weapon-example.yml");
            ConfigurationManager.saveConfig(file, config);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                FileConfiguration defaultConfig = getInstance().getConfig();
                for (Player player : PowermageCore.getInstance().getServer().getOnlinePlayers()) {
                    PowermagePlayer pmPlayer = new PowermagePlayer(player);
                    if (pmPlayer.getClassType() != null) {
                        double maxMana = pmPlayer.getClassType() == null ? 0 : pmPlayer.getClassType().getMaxMana();
                        String mana = ChatColor.AQUA + "Mana: " + pmPlayer.getMana() + "/" + Math.round(maxMana + pmPlayer.getManaUpgrade() * defaultConfig.getInt("upgrades.mana.manaPerLevel"));
                        String health = ChatColor.RED + "Health: " + Math.round(player.getHealth() / 20 * player.getHealthScale()) + "/" + Math.round(player.getHealthScale());
                        String speed = ChatColor.WHITE + "Speed: " + Math.round(player.getWalkSpeed() * 500);
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(mana + "    " + health + "    " + speed));
                        return;
                    }
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "Please select a class to continue."));
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
                        pmPlayer.setMana(pmPlayer.getMana() + 5 > pmPlayer.getClassType().getMaxMana() + pmPlayer.getManaUpgrade() * defaultConfig.getDouble("upgrades.mana.manaPerLevel") ?
                                (int)(pmPlayer.getClassType().getMaxMana() + pmPlayer.getManaUpgrade() * defaultConfig.getDouble("upgrades.mana.manaPerLevel")) : pmPlayer.getMana() + 5);
                        pmPlayer.save();
                    }
                }
            }
        }.runTaskTimer(this, 0, 20);
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
