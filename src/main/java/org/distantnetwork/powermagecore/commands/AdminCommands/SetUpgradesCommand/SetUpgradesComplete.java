package org.distantnetwork.powermagecore.commands.AdminCommands.SetUpgradesCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SetUpgradesComplete implements TabCompleter {
    List<String> list = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            list.clear();
            list.add("speed");
            list.add("strength");
            list.add("health");
            list.add("mana");
        }
        return list;
    }
}
