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

    public static Float getWalkSpeed(Classes classes) {
        switch(classes) {
            case TANK:
                return 0.1f;
            case ARCHER:
                return 0.4f;
            case WIZARD:
                return 0.16f;
            default:
                return 0.2f;
        }
    }

    public static Integer getHealth(Classes classes) {
        switch(classes) {
            case TANK:
                return 40;
            case ARCHER:
                return 10;
            case WIZARD:
                return 15;
            default:
                return 20;
        }
    }

    public static Integer getMana(Classes classes) {
        if (classes == Classes.WIZARD) return 300;
        return 100;
    }
}
