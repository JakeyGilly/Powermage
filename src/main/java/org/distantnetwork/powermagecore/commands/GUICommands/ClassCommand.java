package org.distantnetwork.powermagecore.commands.GUICommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.GUI.ClassGUI;
import org.distantnetwork.powermagecore.utils.Locale.LocaleManager;
import org.jetbrains.annotations.NotNull;

public class ClassCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        LocaleManager localeManager = new LocaleManager();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            localeManager.updateLocale(LocaleManager.getLanguage(player));
            if (!player.hasPermission("powermage.class")) {
                player.sendMessage(localeManager.getString("noPermission"));
            }
            new ClassGUI(player).open(player);
        } else sender.sendMessage(localeManager.getString("noConsole"));
        return true;
    }
}
