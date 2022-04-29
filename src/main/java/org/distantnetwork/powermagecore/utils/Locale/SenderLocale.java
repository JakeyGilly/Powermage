package org.distantnetwork.powermagecore.utils.Locale;

import org.bukkit.command.CommandSender;

import java.util.Locale;

public class SenderLocale {
    CommandSender sender;
    Locale locale;

    public SenderLocale(CommandSender sender, Locale locale) {
        this.sender = sender;
        this.locale = locale;
    }

    public CommandSender getSender() {
        return sender;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
