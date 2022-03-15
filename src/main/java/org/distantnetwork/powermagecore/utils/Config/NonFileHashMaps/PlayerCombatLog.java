package org.distantnetwork.powermagecore.utils.Config.NonFileHashMaps;

import org.distantnetwork.powermagecore.PowermageCore;

import java.util.UUID;

public class PlayerCombatLog {
    public static void set(UUID player) {
        PowermageCore.playerCombatLog.put(player, true);
    }

    public static void unset(UUID player) {
        PowermageCore.playerCombatLog.remove(player);
    }

    public static boolean get(UUID player) {
        if (PowermageCore.playerCombatLog.containsKey(player)) {
            return PowermageCore.playerCombatLog.get(player);
        }
        return false;
    }
}
