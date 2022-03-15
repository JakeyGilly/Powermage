package org.distantnetwork.powermagecore.commands.AdminCommands.LevelUpCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Enums.Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelUpCompleter implements TabCompleter {
    List<String> list = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            list.clear();
            Arrays.stream(Classes.values()).forEach(classes -> list.add(classes.name()));
        }
        if (args.length == 2) {
            list.clear();
            PowermageCore.getInstance().getServer().getOnlinePlayers().forEach(player -> list.add(player.getName()));
        }
        if (args.length == 3) {
            list.clear();
            for (int i = 1; i <= 10; i++) {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }
}
