package org.distantnetwork.powermagecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.Weapon;
import org.distantnetwork.powermagecore.WeaponsManager;

public class SoulShopCommand implements CommandExecutor {
    public Inventory generateShop() {
        Inventory inventory = Bukkit.createInventory(null, 45, "&b&lPowermage Soul Shop");
        for (int i = 0; i < inventory.getSize(); i++) inventory.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));

        FileConfiguration file = WeaponsManager.getFile();
        for (int i = 0; i < file.getInt("weapons.length"); i++) {
            Weapon weapon = WeaponsManager.getWeapon(i);
            ItemStack item = weapon.toItem(i, file);
            inventory.setItem(i, item);
        }
//        ItemStack item;
//        ItemMeta meta;
//        item = new ItemStack(Material.SPECTRAL_ARROW);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&6Zeus's Bolt");
//        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//        meta.setLore(Arrays.asList("&6Ability: Thunderbolt &e&lRIGHT CLICK", "&7Teleport &a10 blocks &7in front of you,", "&7striking lighting at your previous location.", "&8Mana Cost: &d50", "&8Cooldown: &a30 Seconds", " ", "&6&lLEGENDARY", "&675 souls", " ", "&eClick to buy!"));
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(10, item);
//        item = new ItemStack(Material.SCUTE);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&aRepulsion");
//        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//        meta.setLore(Arrays.asList("&6Ability: Launch Away &e&lRIGHT CLICK", "&7Launches everyone within a", "&a5 block &7radius away.", "&8Mana Cost: &d10", " ", "&a&lUNCOMMON", "&b20 souls", " ", "&e&mClick to buy!"));
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(11, item);
//        item = new ItemStack(Material.SCUTE);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&9Super Repulsion");
//        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//        meta.setLore(Arrays.asList("&6Ability: Launch Away &e&lRIGHT CLICK", "&7Launches everyone within a", "&a5 block &7radius away.", "&8Mana Cost: &d20", " ", "&9&lRARE", "&b50 souls", " ", "&e&mClick to buy!"));
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(12, item);
//        item = new ItemStack(Material.GOLDEN_SWORD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&fDasher");
//        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//        meta.setLore(Arrays.asList("&7Damage: &c+4", "&7Attack Speed: &c1.6", " ", "&6Ability: Speed &e&lRIGHT CLICK", "&7Gives you &a30 speed &7for &a30 seconds&7.", "&8Mana Cost: &d50", " ", "&f&lCOMMON", "&b25 souls", " ", "&eClick to buy!"));
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(13, item);
//        item = new ItemStack(Material.GOLDEN_SWORD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&aSuper Dasher");
//        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
//        meta.setLore(Arrays.asList("&7Damage: &c+5", "&7Attack Speed: &c1.6", " ", "&6Ability: Superspeed &e&lRIGHT CLICK", "&7Gives you &a40 speed &7for &a40 seconds&7.", "&8Mana Cost: &d60", " ", "&a&lUNCOMMON", "&b35 souls", " ", "&eClick to buy!"));
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(14, item);
//        item = new ItemStack(Material.GOLDEN_SWORD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&9Hyper Dasher");
//        meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
//        meta.setLore(Arrays.asList("&7Damage: &c+6", "&7Attack Speed: &c1.6", " ", "&6Ability: Hyperspeed &e&lRIGHT CLICK", "&7Gives you &a50 speed &7for &a50 seconds&7.", "&8Mana Cost: &d70", " ", "&9&lRARE", "&b50 souls", " ", "&eClick to buy!"));
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(15, item);
//        item = new ItemStack(Material.STONE_SWORD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&6Vampire Sword");
//        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//        meta.setLore(Arrays.asList("&7Damage: &c+5", "&7Attack Speed: &c1.6", " ", "&6Ability: Bloodlust &e&lPASSIVE", "&7Every hit with this sword steals &c1 ❤ &7from", "&7your opponent and gives it to you.", "&8Mana Cost: &d20", " ", "&6&lLEGENDARY", "&b75 souls", " ", "&eClick to buy!"));
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(16, item);
//        item = new ItemStack(Material.IRON_SWORD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&5Day Sword");
//        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//        meta.setLore(Arrays.asList("&7Damage: &c+6 &7(+9 ☀)", "&7Attack Speed: &c1.6", " ", "&6Ability: Sunlight &e&lPASSIVE", "&7Deals &a33%% &7more damage during the day.", " ", "&5&lEPIC", "&b50 souls", " ", "&eClick to buy!"));
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        meta.addItemFlags(ItemFlag.);
//        item.setItemMeta(meta);
//        inventory.setItem(19, item);
//        item = new ItemStack(Material.IRON_SWORD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&5Night Sword");
//        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//        meta.setLore(Arrays.asList("&7Damage: &c+6 &7(+9 ☁)", "&7Attack Speed: &c1.6", " ", "&6Ability: Moonlight &e&lPASSIVE", "&7Deals &a33%% &7more damage during the night.", " ", "&5&lEPIC", "&b50 souls", " ", "&eClick to buy!"));
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(20, item);
//        item = new ItemStack(Material.BLAZE_ROD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&aWand of Mending");
//        meta.setLore(Arrays.asList("&6Ability: Heal &e&lRIGHT CLICK","&7Heals &c1 ❤&7, but inflicts", "&7you with &9Weakness &7for 2 second.", "&8Mana Cost: &d50", " ", "&a&lUNCOMMON", "&b25 souls", " ", "&eClick to buy!"));
//        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(21, item);
//        item = new ItemStack(Material.BLAZE_ROD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&9Super Wand of Mending");
//        meta.setLore(Arrays.asList("&6Ability: Super Heal &e&lRIGHT CLICK","&7Heals &c2 ❤&7, but inflicts", "&7you with &9Weakness &7for 3 second.", "&8Mana Cost: &d60", " ", "&9&lRARE", "&b35 souls", " ", "&eClick to buy!"));
//        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(22, item);
//        item = new ItemStack(Material.BLAZE_ROD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&5Hyper Wand of Mending");
//        meta.setLore(Arrays.asList("&6Ability: Hyper Heal &e&lRIGHT CLICK","&7Heals &c3 ❤&7, but inflicts", "&7you with &9Weakness &7for 4 second.", "&8Mana Cost: &d70", " ", "&5&lEPIC", "&b60 souls", " ", "&eClick to buy!"));
//        meta.addEnchant(Enchantment.DIG_SPEED, 1, true);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(23, item);
//        item = new ItemStack(Material.FISHING_ROD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&fGrappling Hook");
//        meta.setLore(Arrays.asList("&6Ability: Grapple &e&lRIGHT CLICK", "&7Fling yourself through the air at","&7mach speed and reach places", "&7previously unreachable.", "&8Cooldown: &a2 Seconds", " ", "&f&lCOMMON", "&b25 souls", " ", "&eClick to buy!"));
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        item.setItemMeta(meta);
//        inventory.setItem(24, item);
//        item = new ItemStack(Material.COD);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&aSlappy The Fish");
//        meta.setLore(Arrays.asList("&6[LIMITED EDITION]", " ", "&6Ability: Knockback &e&lPASSIVE", "&7Knocks whoever you hit back &a6 &7blocks.", " ", "&a&lUNCOMMON", "&b100 souls", " ", "&eClick to buy!"));
//        meta.addEnchant(Enchantment.KNOCKBACK, 2, true);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        item.setItemMeta(meta);
//        inventory.setItem(25, item);
//        item = new ItemStack(Material.DRAGON_EGG);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&dThe Dragon's Blade");
//        meta.setLore(Arrays.asList("&7Damage: &c+8", "&7Attack Speed: &c2", " ", "&6Ability: Breath &e&lRIGHT CLICK", "&7Summons the mighty dragon's breath where", "&7the wielder of this blade is standing", "&8Mana Cost: &d100", "&8Cooldown: &a120 Seconds", " ", "&d&lMYTHIC", "&6100 souls", " ", "&eClick to buy!"));
//        meta.addEnchant(Enchantment.DAMAGE_ALL, 8, true);
//        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//        item.setItemMeta(meta);
//        inventory.setItem(31, item);
//        item = new ItemStack(Material.ARROW);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&fGo Back");
//        item.setItemMeta(meta);
//        inventory.setItem(39, item);
//        item = new ItemStack(Material.BARRIER);
//        meta = item.getItemMeta();
//        meta.setDisplayName("&cClose");
//        item.setItemMeta(meta);
//        inventory.setItem(40, item);
        return inventory;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("powermage.soulshop")) {
//                if (Integer.getInteger(PowermageCore.playerLevels.get(player.getUniqueId()).get(PowermageCore.playerClasses.get(player.getUniqueId())).values().toArray()[0].toString()) > 4) {
                    Inventory inventory = generateShop();
                    player.openInventory(inventory);
//                } else {
//                    player.sendMessage("You need to be level 5 in any class to use this shop!");
//                }
            }
        }
        return true;
    }
}
