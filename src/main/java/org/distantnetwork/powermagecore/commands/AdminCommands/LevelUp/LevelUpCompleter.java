package org.distantnetwork.powermagecore.commands.AdminCommands.LevelUp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.Enums.Classes;

import java.util.ArrayList;
import java.util.List;

public class LevelUpCompleter implements TabCompleter {
    List<String> list = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            list.clear();
            for (int i = 1; i < 64; i*=2) {
                list.add(String.valueOf(i));
            }
        }
        if (args.length == 2) {
            list.clear();
            for (Classes classes : Classes.values()) {
                list.add(classes.name());
            }
        }
        if (args.length == 3) {
            list.clear();
            for (Player player : Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        return list;
    }

}
