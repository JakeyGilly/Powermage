package org.distantnetwork.powermagecore.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.distantnetwork.powermagecore.PowermageCore;

import java.util.*;
import java.util.logging.Level;

public class ConfigItem {
    public static ItemStack getItem(FileConfiguration file, Player player) {
        if (!file.getBoolean("enabled")) return null;
        PowermageCore.getInstance().getLogger().log(Level.INFO, file.getString("material"));
        ItemStack item = new ItemStack(Material.getMaterial(Objects.requireNonNull(file.getString("material"))));
        PowermageCore.getInstance().getLogger().log(Level.INFO, "Material: " + item.getType().toString());
        ItemMeta meta = item.getItemMeta();
        if (file.getString("name") != null) meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', file.getString("name")));
        if (!file.getStringList("lore").isEmpty()) {
            List<String> lore = file.getStringList("lore");
            for(int i = lore.size(); i > 0; i--) lore.set(i-1, ChatColor.translateAlternateColorCodes('&', lore.get(i-1)));
            meta.setLore(lore);
        }
        if (!file.getMapList("enchantments").isEmpty() && file.getConfigurationSection("enchantments") != null) {
            file.getConfigurationSection("enchantments").getValues(false).forEach((key, value) -> {
                Enchantment enchant = Enchantment.getByKey(NamespacedKey.fromString(key));
                if (enchant != null) meta.addEnchant(Enchantment.getByKey(NamespacedKey.fromString(key)), (Integer) value, true);

            });
        }
        for (String flag : file.getStringList("flags")) meta.addItemFlags(ItemFlag.valueOf(flag));
        if (file.getBoolean("unbreakable")) meta.setUnbreakable(true);
        item.setItemMeta(meta);
        return item;
    }
}
