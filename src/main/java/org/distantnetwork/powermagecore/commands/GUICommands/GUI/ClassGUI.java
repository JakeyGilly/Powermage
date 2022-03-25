package org.distantnetwork.powermagecore.commands.GUICommands.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.distantnetwork.powermagecore.builders.InventoryBuilder;
import org.distantnetwork.powermagecore.utils.Enums.Classes;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;

public class ClassGUI extends InventoryBuilder {
    public ClassGUI(Player p) {
        super(27, String.format("%sChoose your class", ChatColor.RED));
        for (int i = 0; i < getInventory().getSize(); i++)
            setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));
//        HashMap<String, HashMap<String, Object>> map = new HashMap<>();
//        getInstance().getConfig().getConfigurationSection("classes").getValues(false).forEach((k, v) -> {
//            map.put(k, new HashMap<String, Object>() {{
//                this.putAll(getInstance().getConfig().getConfigurationSection("classes." + k).getValues(false));
//            }});
//        });
//        ItemStack[] items = new ItemStack[map.size()];
//        for (String k : map.keySet()) {
//            if ()
//            HashMap<String, Object> v = map.get(k);
//            items[i] = new ItemBuilder(Material.valueOf((String) v.get("material"))).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
//                    .setName(String.format("%s%s%s", ChatColor.RED, ChatColor.BOLD, map.))
//                    .setLore(new String[]{String.format("%s%s%s", ChatColor.GRAY, ChatColor.ITALIC, "Click to choose this class")})
//            ItemStack item = new ItemBuilder(Material.IRON_SWORD).addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).addEnchant(Enchantment.DURABILITY, 1)
//                    .setName(String.format("%s[Lvl 0]%s%s %s", ChatColor.GRAY, ChatColor.RED, c.getString(c.name().toLowerCase() + ".emoji"), c.))
//                    .setLore(
//                            String.format("%s❤ Health: %s%s%%", ChatColor.RED, ChatColor.RESET, getInstance().getConfig().getConfigurationSection("classes").getDouble(c.name().toLowerCase() + ".health")),
//                            String.format("%s✦ Base Speed: %s%s%%", ChatColor.GOLD, ChatColor.RESET, Classes.getWalkSpeed(c)),
//                            String.format("%s☄ Base Mana: %s%s", ChatColor.LIGHT_PURPLE, ChatColor.RESET, Classes.getMana(c)),
//                            "",
//                            String.format("%sClass Ability: %s%s", ChatColor.BOLD, ChatColor.GOLD, Classes.getAbility(c)),
//                            ChatColor.translateAlternateColorCodes('&', Classes.getAbilityDescription(c))
//                    ).toItem();
//            setItem(c.getSlot(), item, player -> {
//                p.closeInventory();
//                setClass(player, c);
//            });
//        }
    }



//    public void setClass(Player p, Classes c) {
//        p.sendMessage(String.format("%s[%sClass Selector%s] %sYou have selected the %sWarrior %s%sclass!", ChatColor.GRAY, ChatColor.RED, ChatColor.GRAY, ChatColor.RED, ChatColor.BOLD, ChatColor.RESET, ChatColor.GRAY));
//        p.sendMessage(String.format("%s===========================\n%s%s NEW STATS: \n\n%s%s❤ Base Health: %s20\n%s✦ Base Speed: %s100%%\n%s☄ Base Mana: %s100\n%s===========================", ChatColor.RED, ChatColor.BOLD, getInstance().getConfig().getConfigurationSection("classEmojis").get(Classes.getEmojiName(Classes.WARRIOR)), ChatColor.RESET, ChatColor.RED, ChatColor.RESET, ChatColor.GOLD, ChatColor.RESET, ChatColor.LIGHT_PURPLE, ChatColor.RESET, ChatColor.RED));
//        p.setWalkSpeed(Classes.getWalkSpeed(Classes.valueOf(c.name())));
//        p.setHealth(Classes.getHealth(Classes.valueOf(c.name())));
//        p.setHealthScale(Classes.getHealth(Classes.valueOf(c.name())));
//        // starter item?
//    }
}