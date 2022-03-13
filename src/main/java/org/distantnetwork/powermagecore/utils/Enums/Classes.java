package org.distantnetwork.powermagecore.utils.Enums;

public enum Classes {
    WARRIOR,
    TANK,
    ARCHER,
    WIZARD;

    public static String getEmojiName(Classes classes) {
        switch(classes) {
            case WARRIOR:
                return "warriorEmoji";
            case TANK:
                return "tankEmoji";
            case ARCHER:
                return "archerEmoji";
            case WIZARD:
                return "wizardEmoji";
            default:
                return null;
        }
    }
}
