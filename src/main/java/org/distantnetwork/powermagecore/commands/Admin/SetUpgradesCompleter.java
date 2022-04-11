package org.distantnetwork.powermagecore.commands.Admin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SetUpgradesCompleter implements TabCompleter {
    List<String> list = new ArrayList<>();
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1 && player.hasPermission("powermage.admin")) {
                list.clear();
                list.add("health");
                list.add("mana");
                list.add("speed");
                list.add("strength");
                return list;
            }
            if (args.length == 2 && player.hasPermission("powermage.admin")) {
                list.clear();
                for (int i = 1; i < 64; i *= 2) {
                    list.add(String.valueOf(i));
                }
                return list;
            }
            if (args.length == 3 && player.hasPermission("powermage.admin.others")) {
                list.clear();
                for (Player p : player.getServer().getOnlinePlayers()) {
                    list.add(p.getName());
                }
                return list;
            }
        }
        return null;
    }
}
