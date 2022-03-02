package org.distantnetwork.powermagecore.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            TextComponent link = new TextComponent("&eClick here to join our &bDISCORD &eto get help and support!");
            link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/QWQWQWQ"));
            player.spigot().sendMessage(link);
        }
        return true;
    }
}
