package org.distantnetwork.powermagecore.commands.AutoCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PluginCommandCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 0) {
                List<String> list = new ArrayList<>();
                if (player.hasPermission("powermage.reload")) list.add("reload");
                if (player.hasPermission("powermage.version")) list.add("version");
                return list;
            }
        }
        return null;
    }
}
