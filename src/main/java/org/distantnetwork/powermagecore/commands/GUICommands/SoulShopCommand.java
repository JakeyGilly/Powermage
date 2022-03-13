package org.distantnetwork.powermagecore.commands.GUICommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.commands.GUICommands.GUI.SoulShopGUI;

public class SoulShopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("powermage.soulshop")) {
//                if (Integer.getInteger(PowermageCore.playerLevels.get(player.getUniqueId()).get(PowermageCore.playerClasses.get(player.getUniqueId())).values().toArray()[0].toString()) > 4) {
                new SoulShopGUI(player).open(player);
//                } else {
//                    player.sendMessage("You need to be level 5 in any class to use this shop!");
//                }
            }
        }
        return true;
    }
}
