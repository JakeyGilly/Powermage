package org.distantnetwork.powermagecore.commands.AdminCommands.SetUpgradesCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Enums.ClassesEnum;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SetUpgradesComplete implements TabCompleter {
    List<String> list = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            list.clear();
            Arrays.stream(ClassesEnum.values()).forEach(c -> list.add(c.name()));
        } else if (args.length == 2) {
            list.clear();
            Arrays.stream(ClassesEnum.values()).filter(c -> c.name().equalsIgnoreCase(args[0])).flatMapToInt(c ->
                    IntStream.range(0, getInstance().getConfig().getInt(String.format("upgrades.%s.maxLevel", args[0].toLowerCase())))).forEach(i ->
                    list.add(Integer.toString(i)));
        } else if (args.length == 3) {
            list.clear();
            PowermageCore.getInstance().getServer().getOnlinePlayers().forEach(player -> list.add(player.getName()));
        }
        return list;
    }
}
