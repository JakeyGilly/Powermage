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
    private final ResourceBundle defaultBundle = ResourceBundle.getBundle(LANG);

    public String getCurrentLocale() {
        return locale;
    }

    public String getString(String key) {
        try {
            return localeBundle.getString(key);
        } catch (final MissingResourceException ex) {
            return defaultBundle.getString(key);
        }
    }

    public void updateLocale(String loc) {
        try {
            localeBundle = ResourceBundle.getBundle(LANG, new Locale(loc));// dunno if this works
        } catch (final MissingResourceException ex) {
            getPlugin().getLogger().warning("Could not find locale: " + locale.toString());
        }
        if (localeBundle == null) {
            locale = loc;
        }
    }

    public static String getLanguage(Player p) {
        Method ep = null;
        for (Method declaredMethod : p.getClass().getDeclaredMethods()) {
            if (declaredMethod.getName().equals("getHandle")) ep = declaredMethod;
        }
        try {
            Object handle = ep.invoke(p, (Object[]) null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
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
    }
}
