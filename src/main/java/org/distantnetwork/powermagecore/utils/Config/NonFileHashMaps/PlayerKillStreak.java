package org.distantnetwork.powermagecore.utils.Config.NonFileHashMaps;

import org.distantnetwork.powermagecore.PowermageCore;

import java.util.UUID;

public class PlayerKillStreak {

    public static void setKillStreak(UUID player, int killStreak) {
        PowermageCore.playerKillStreak.put(player, killStreak);
    }

    public static int getKillStreak(UUID player) {
        if (PowermageCore.playerKillStreak.containsKey(player)) {
            return PowermageCore.playerKillStreak.get(player);
        }
        return 0;
    }

    public static void removeKillStreak(UUID player) {
        PowermageCore.playerKillStreak.remove(player);
    }

    public static void incrementKillStreak(UUID player) {
        if (PowermageCore.playerKillStreak.containsKey(player)) {
            PowermageCore.playerKillStreak.put(player, PowermageCore.playerKillStreak.get(player) + 1);
        } else {
            PowermageCore.playerKillStreak.put(player, 1);
        }
    }
}
