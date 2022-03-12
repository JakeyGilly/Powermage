package org.distantnetwork.powermagecore.utils;

import org.bukkit.ChatColor;

public enum Rarity {
    CORE, COMMON, UNCOMMON, RARE, EPIC, LEGENDARY, MYTHIC;

    public static String getColor(Rarity rarity) {
        switch (rarity) {
            case CORE:
                return ChatColor.RED + ChatColor.BOLD.toString();
            case COMMON:
                return ChatColor.WHITE + ChatColor.BOLD.toString();
            case UNCOMMON:
                return ChatColor.GREEN + ChatColor.BOLD.toString();
            case RARE:
                return ChatColor.BLUE + ChatColor.BOLD.toString();
            case EPIC:
                return ChatColor.LIGHT_PURPLE + ChatColor.BOLD.toString();
            case LEGENDARY:
                return ChatColor.GOLD + ChatColor.BOLD.toString();
            case MYTHIC:
                return ChatColor.DARK_PURPLE + ChatColor.BOLD.toString();
            default:
                return null;
        }
    }

    public static String getName(Rarity rarity, boolean weapon, boolean armor) {
        if (weapon && armor) return null;
        if (weapon && !armor) {
            switch (rarity) {
                case CORE:
                    return "CORE WEAPON";
                case COMMON:
                    return "COMMON WEAPON";
                case UNCOMMON:
                    return "UNCOMMON WEAPON";
                case RARE:
                    return "RARE WEAPON";
                case EPIC:
                    return "EPIC WEAPON";
                case LEGENDARY:
                    return "LEGENDARY WEAPON";
                case MYTHIC:
                    return "MYTHIC WEAPON";
                default:
                    return null;
            }
        } else if (armor && !weapon) {
            switch (rarity) {
                case CORE:
                    return "CORE ARMOR";
                case COMMON:
                    return "COMMON ARMOR";
                case UNCOMMON:
                    return "UNCOMMON ARMOR";
                case RARE:
                    return "RARE ARMOR";
                case EPIC:
                    return "EPIC ARMOR";
                case LEGENDARY:
                    return "LEGENDARY ARMOR";
                case MYTHIC:
                    return "MYTHIC ARMOR";
                default:
                    return null;
            }
        } else {
            switch (rarity) {
                case CORE:
                    return "CORE ITEM";
                case COMMON:
                    return "COMMON ITEM";
                case UNCOMMON:
                    return "UNCOMMON ITEM";
                case RARE:
                    return "RARE ITEM";
                case EPIC:
                    return "EPIC ITEM";
                case LEGENDARY:
                    return "LEGENDARY ITEM";
                case MYTHIC:
                    return "MYTHIC ITEM";
                default:
                    return null;
            }
        }
    }
}
