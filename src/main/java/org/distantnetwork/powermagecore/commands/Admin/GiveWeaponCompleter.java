package org.distantnetwork.powermagecore.commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.Items.WeaponItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GiveWeaponCompleter implements TabCompleter {
    List<String> list = new ArrayList<>();
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            list.clear();
            for (WeaponItem weapon : WeaponItem.getWeapons()) {
                list.add(weapon.getName());
            }
            return list;
        }
        if (args.length == 2) {
            list.clear();
            for (int i = 1; i <= 64; i*=2) {
                list.add(String.valueOf(i));
            }
            return list;
        }
        if (args.length == 3) {
            list.clear();
            for (Player player : sender.getServer().getOnlinePlayers()) {
                list.add(player.getName());
            }
            return list;
        }
        return null;
    }
}
