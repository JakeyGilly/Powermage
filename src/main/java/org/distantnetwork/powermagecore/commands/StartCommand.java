package org.distantnetwork.powermagecore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.Items.ConfigItem;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.WeaponsManager;

import java.util.logging.Level;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            FileConfiguration config = WeaponsManager.getConfig(0);
            PowermageCore.getInstance().getLogger().log(Level.INFO, String.format("enabled?: %s", String.valueOf(config.getBoolean("enabled"))));
            ItemStack item = ConfigItem.getItem(config, player);
            if (item == null) return true;
            player.getInventory().addItem(item);
        }
        return true;
    }
}
//public class StartCommand implements CommandExecutor {
//    public static Inventory generateInventory() {
//        Inventory inventory = Bukkit.createInventory(null, 27, String.format("%sChoose your class", ChatColor.RED));
//        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
//        ItemMeta meta = item.getItemMeta();
//        meta.setDisplayName(" ");
//        item.setItemMeta(meta);
//        for (int i = 0; i < 26; i++) inventory.setItem(i, item);
//        item = new ItemStack(Material.IRON_SWORD);
//        meta = item.getItemMeta();
//        meta.setDisplayName(String.format("%s[Lvl 0]%s%s Warrior", ChatColor.GRAY, PowermageCore.classesEmojis.get(Classes.WARRIOR), ChatColor.RED));
//        meta.setLore(Arrays.asList(String.format("%s❤ Health: %s100%%", ChatColor.RED, ChatColor.RESET), String.format("%s✦ Base Speed: %s100%", ChatColor.GOLD, ChatColor.RESET), String.format("%s☄ Base Mana: %s100", ChatColor.LIGHT_PURPLE, ChatColor.RESET), " ", String.format("%sClass Ability: %sCharge", ChatColor.BOLD, ChatColor.GOLD), String.format("%sGives player %s+100 %s✦ Speed %sfor %s10 %sseconds.", ChatColor.GRAY, ChatColor.GREEN, ChatColor.GOLD, ChatColor.GRAY, ChatColor.GREEN, ChatColor.GRAY), String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), "&eLeft Click on your menu to activate!", " ", "&e&LCLICK TO SELECT"));
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(10, item);
//        item = new ItemStack(Material.IRON_CHESTPLATE);
//        meta = item.getItemMeta();
//        meta.setDisplayName(String.format("&7[Lvl 0] &a%s Tank", PowermageCore.classesEmojis.get(Classes.TANK)));
//        meta.setLore(Arrays.asList("&c❤ Health: &f200%", "&6✦ Base Speed: &f50%", "&d☄ Base Mana: &f100", " ", "&f&lClass Ability: &6&l&oTurtle Up", "&7Gives player &9Resistance 2", "&7and &9Regen 5 &7for &a10 seconds.", "&8Cooldown: &a30 Seconds", "&eLeft Click on your menu to activate!", " ", "&e&LCLICK TO SELECT"));
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(12, item);
//        item = new ItemStack(Material.CROSSBOW);
//        meta = item.getItemMeta();
//        meta.setDisplayName(String.format("&7[Lvl 0] &a%s Archer", PowermageCore.classesEmojis.get(Classes.ARCHER)));
//        // arrow rain as secondary ability?
//        meta.setLore(Arrays.asList("&c❤ Health: &f50%", "&6✦ Base Speed: &f200%", "&d☄ Base Mana: &f100", " ", "&f&lClass Ability: &6&l&oQuickshot", "&7Rapid-fires arrows to where", "&7the player is looking at for &a3 seconds.", "&8Cooldown: &a30 Seconds", "&eLeft Click on your menu to activate!", " ", "&e&LCLICK TO SELECT"));
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(14, item);
//        item = new ItemStack(Material.BLAZE_ROD);
//        meta = item.getItemMeta();
//        meta.setDisplayName(String.format("&7[Lvl 0] &a%s Wizard", PowermageCore.classesEmojis.get(Classes.WIZARD)));
//        meta.setLore(Arrays.asList("&c❤ Health: &f75%", "&6✦ Base Speed: &f80%", "&d☄ Base Mana: &f300", " ", "&f&lClass Ability: &6&l&oHeart of Magic", "&7Gives you infinite mana", "&7for an entire &a5 seconds.", "&8Cooldown: &a1 Minute", "&eLeft Click on your menu to activate!", " ", "&e&LCLICK TO SELECT"));
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(16, item);
//        // close
//        ItemStack closeItem = new ItemStack(Material.BARRIER);
//        meta = closeItem.getItemMeta();
//        meta.setDisplayName("&cClose Menu");
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        closeItem.setItemMeta(meta);
//        inventory.setItem(22, closeItem);
//        // back to main menu
//        item = new ItemStack(Material.ARROW);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&f&lBack to Menu");
//        item.setItemMeta(meta);
//        inventory.setItem(18, item);
//        return inventory;
//    }
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (sender instanceof Player) {
//            Player player = (Player) sender;
//            if (player.hasPermission("powermage.start")) {
//                Inventory switchClassInventory = generateInventory();
//                player.openInventory(switchClassInventory);
//            }
//        }
//        return true;
//    }
//}
