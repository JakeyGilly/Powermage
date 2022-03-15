package org.distantnetwork.powermagecore.commands.AdminCommands.GiveWeaponsCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;

import java.util.ArrayList;
import java.util.List;

public class GiveWeaponsCompleter implements TabCompleter {
    List<String> list = new ArrayList<String>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            list.clear();
            for (String weapon : WeaponConfigManager.loadWeaponNames()) {
                list.add(weapon);
            }
        }
        if (args.length == 2) {
            list.clear();
            list.add("64");
            list.add("32");
            list.add("16");
            list.add("8");
            list.add("4");
            list.add("2");
            list.add("1");
        }
        if (args.length == 3) {
            list.clear();
            for (Player player : sender.getServer().getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        return list;
    }
}
