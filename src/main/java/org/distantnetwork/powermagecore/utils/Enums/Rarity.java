package org.distantnetwork.powermagecore.utils.Enums;

import org.bukkit.ChatColor;

public enum Rarity {
    CORE(ChatColor.RED, ChatColor.BOLD),
    COMMON(ChatColor.WHITE, ChatColor.BOLD),
    UNCOMMON(ChatColor.GREEN, ChatColor.BOLD),
    RARE(ChatColor.BLUE, ChatColor.BOLD),
    EPIC(ChatColor.LIGHT_PURPLE, ChatColor.BOLD),
    LEGENDARY(ChatColor.GOLD, ChatColor.BOLD),
    MYTHIC(ChatColor.DARK_PURPLE, ChatColor.BOLD);

    private final ChatColor[] color;
    Rarity(ChatColor... color) {
        this.color = color;
    }
    public ChatColor[] getColor() {
        return color;
    }
}
