package org.distantnetwork.powermagecore.commands.AdminCommands.GiveWeaponsCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.WeaponConfigManager;

import java.util.ArrayList;
import java.util.List;

public class GiveWeaponsCompleter implements TabCompleter {
    List<String> list = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            list.clear();
            list.addAll(WeaponConfigManager.loadWeaponNames());
        }
        if (args.length == 2) {
            list.clear();
            for (int i = 1; i <= 64; i*=2) list.add(String.valueOf(i));
        }
        if (args.length == 3) {
            list.clear();
            PowermageCore.getInstance().getServer().getOnlinePlayers().forEach(player -> list.add(player.getName()));
        }
        return list;
    }
}
