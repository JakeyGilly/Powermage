package org.distantnetwork.powermagecore.commands.AdminCommands.SetUpgradesCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.PowermageCore;
import org.distantnetwork.powermagecore.utils.Config.MainConfigManager;

import java.util.ArrayList;
import java.util.List;

public class SetUpgradesComplete implements TabCompleter {
    List<String> list = new ArrayList<String>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            list.clear();
            list.add("MANA");
            list.add("HEALTH");
            list.add("STRENGTH");
            list.add("SPEED");
        } else if (args.length == 2) {
            list.clear();
            if (args[0].equalsIgnoreCase("MANA") || args[0].equalsIgnoreCase("HEALTH") || args[0].equalsIgnoreCase("STRENGTH") || args[0].equalsIgnoreCase("SPEED")) {
                for (int i = 0; i < MainConfigManager.config.getInt("upgrades." + args[0].toLowerCase() + ".maxLevel") +1; i++) {
                    list.add(String.valueOf(i));
                }
            }
        } else if (args.length == 3) {
            list.clear();
            for (Player player : PowermageCore.getInstance().getServer().getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        return list;
    }
}
