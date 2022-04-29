package org.distantnetwork.powermagecore.utils.Locale;

import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static org.distantnetwork.powermagecore.PowermageCore.getPlugin;

public class LocaleManager {
    public static final String LANG = "lang";
    private String locale;
    private ResourceBundle localeBundle;
    private final ResourceBundle defaultBundle = ResourceBundle.getBundle(LANG, Locale.ENGLISH);

    public String getCurrentLocale() {
        return locale;
    }

    public String getString(String key) {
        try {
            return localeBundle.getString(key);
        } catch (MissingResourceException | NullPointerException ex) {
            return defaultBundle.getString(key);
        }
    }

    public void updateLocale(String loc) {
        try {
            localeBundle = ResourceBundle.getBundle(LANG, Locale.forLanguageTag(new Locale(loc).getLanguage()));// dunno if this works
        } catch (MissingResourceException | NullPointerException ex) {
            getPlugin().getLogger().warning("Could not find locale: " + locale.toString());
        }
        if (localeBundle == null) {
            locale = loc;
        }
    }

    public static String getLanguage(Player p) {
        p.spigot()
        Method ep = null;
        for (Method declaredMethod : p.getClass().getDeclaredMethods()) {
            if (declaredMethod.getName().equals("getHandle")) ep = declaredMethod;
        }
        try {
            Object handle = ep.invoke(p, (Object[]) null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        Field f = null;
        try {
            f = ep.getClass().getDeclaredField("locale");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            return (String) f.get(ep);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
