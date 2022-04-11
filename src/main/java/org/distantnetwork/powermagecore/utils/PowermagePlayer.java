package org.distantnetwork.powermagecore.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.Classes.Classes;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;
import static org.distantnetwork.powermagecore.utils.ConfigurationManager.*;

public class PowermagePlayer {
    private Player player;

    //? Classes
    private Classes classesType;
    private int warriorLevel;
    private int archerLevel;
    private int wizardLevel;
    private int tankLevel;
    private int warriorExp;
    private int archerExp;
    private int wizardExp;
    private int tankExp;


    //? Stats Upgrades
    private int strengthUpgrade;
    private int speedUpgrade;
    private int manaUpgrade;
    private int healthUpgrade;

    //? Stats
    private int money;
    private int souls;
    private int kills;
    private int deaths;
    private int assists;
    private int mana;
    private int abilitycooldown;
    private boolean infinitemana;

    //? Not saving Stats
    private boolean combatlog;
    private int killstreak;

    //* Methods
    public PowermagePlayer(Player player) {
        this.player = player;
        File file = getFileFile("playerdata.yml");
        if (file == null) return;
        FileConfiguration config = getConfig(file);
        if (config == null) return;
        if (!config.contains(player.getUniqueId().toString())) return;
        if (config.contains(String.format("%s.class", player.getUniqueId())))
            Classes.getClasses().forEach(aClass -> {
                if (aClass.getName().equalsIgnoreCase(config.getString(String.format("%s.class", player.getUniqueId())))) {
                    this.classesType = aClass;
                }
            });
        if (config.contains(String.format("%s.warriorLevel", player.getUniqueId())))
            this.warriorLevel = config.getInt(String.format("%s.warriorLevel", player.getUniqueId()));
        if (config.contains(String.format("%s.archerLevel", player.getUniqueId())))
            this.archerLevel = config.getInt(String.format("%s.archerLevel", player.getUniqueId()));
        if (config.contains(String.format("%s.wizardLevel", player.getUniqueId())))
            this.wizardLevel = config.getInt(String.format("%s.wizardLevel", player.getUniqueId()));
        if (config.contains(String.format("%s.tankLevel", player.getUniqueId())))
            this.tankLevel = config.getInt(String.format("%s.tankLevel", player.getUniqueId()));
        if (config.contains(String.format("%s.warriorExp", player.getUniqueId())))
            this.warriorExp = config.getInt(String.format("%s.warriorExp", player.getUniqueId()));
        if (config.contains(String.format("%s.archerExp", player.getUniqueId())))
            this.archerExp = config.getInt(String.format("%s.archerExp", player.getUniqueId()));
        if (config.contains(String.format("%s.wizardExp", player.getUniqueId())))
            this.wizardExp = config.getInt(String.format("%s.wizardExp", player.getUniqueId()));
        if (config.contains(String.format("%s.tankExp", player.getUniqueId())))
            this.tankExp = config.getInt(String.format("%s.tankExp", player.getUniqueId()));
        if (config.contains(String.format("%s.strengthUpgrade", player.getUniqueId())))
            this.strengthUpgrade = config.getInt(String.format("%s.strengthUpgrade", player.getUniqueId()));
        if (config.contains(String.format("%s.speedUpgrade", player.getUniqueId())))
            this.speedUpgrade = config.getInt(String.format("%s.speedUpgrade", player.getUniqueId()));
        if (config.contains(String.format("%s.manaUpgrade", player.getUniqueId())))
            this.manaUpgrade = config.getInt(String.format("%s.manaUpgrade", player.getUniqueId()));
        if (config.contains(String.format("%s.healthUpgrade", player.getUniqueId())))
            this.healthUpgrade = config.getInt(String.format("%s.healthUpgrade", player.getUniqueId()));
        if (config.contains(String.format("%s.money", player.getUniqueId())))
            this.money = config.getInt(String.format("%s.money", player.getUniqueId()));
        if (config.contains(String.format("%s.souls", player.getUniqueId())))
            this.souls = config.getInt(String.format("%s.souls", player.getUniqueId()));
        if (config.contains(String.format("%s.kills", player.getUniqueId())))
            this.kills = config.getInt(String.format("%s.kills", player.getUniqueId()));
        if (config.contains(String.format("%s.deaths", player.getUniqueId())))
            this.deaths = config.getInt(String.format("%s.deaths", player.getUniqueId()));
        if (config.contains(String.format("%s.assists", player.getUniqueId())))
            this.assists = config.getInt(String.format("%s.assists", player.getUniqueId()));
        if (config.contains(String.format("%s.mana", player.getUniqueId())))
            this.mana = config.getInt(String.format("%s.mana", player.getUniqueId()));
        if (config.contains(String.format("%s.cooldown", player.getUniqueId())))
            this.abilitycooldown = config.getInt(String.format("%s.cooldown", player.getUniqueId()));
        if (config.contains(String.format("%s.infinitemana", player.getUniqueId())))
            this.infinitemana = config.getBoolean(String.format("%s.infinitemana", player.getUniqueId()));
        if (config.contains(String.format("%s.combatlog", player.getUniqueId())))
            this.combatlog = config.getBoolean(String.format("%s.combatlog", player.getUniqueId()));
        if (config.contains(String.format("%s.killstreak", player.getUniqueId())))
            this.killstreak = config.getInt(String.format("%s.killstreak", player.getUniqueId()));
    }
    public PowermagePlayer(UUID uuid) {
        this.player = getInstance().getServer().getPlayer(uuid);
    }

    //* Getters, Setters, Adders, Subtracters, Increasers, Decreasers
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Classes getClassType() {
        return classesType;
    }
    public void setClassType(Classes classesType) {
        this.classesType = classesType;
    }

    public int getClassLvl(Classes classes) {
        switch (classes.getName()) {
            case "Warrior":
                return warriorLevel;
            case "Archer":
                return archerLevel;
            case "Wizard":
                return wizardLevel;
            case "Tank":
                return tankLevel;
            default:
                return 0;
        }
    }

    public int getClassExp(Classes classes) {
        switch (classes.getName()) {
            case "Warrior":
                return warriorExp;
            case "Archer":
                return archerExp;
            case "Wizard":
                return wizardExp;
            case "Tank":
                return tankExp;
            default:
                return 0;
        }
    }

    public void setClassesLvl(Classes classes, int level) {
        switch (classes.getName()) {
            case "Warrior":
                warriorLevel = level;
                break;
            case "Archer":
                archerLevel = level;
                break;
            case "Wizard":
                wizardLevel = level;
                break;
            case "Tank":
                tankLevel = level;
                break;
        }
    }

    public void setClassesExp(Classes classes, int exp) {
        switch (classes.getName()) {
            case "Warrior":
                warriorExp = exp;
                break;
            case "Archer":
                archerExp = exp;
                break;
            case "Wizard":
                wizardExp = exp;
                break;
            case "Tank":
                tankExp = exp;
                break;
        }
    }

    public int getArcherExp() {
        return archerExp;
    }

    public int getArcherLevel() {
        return archerLevel;
    }

    public int getTankExp() {
        return tankExp;
    }

    public int getTankLevel() {
        return tankLevel;
    }

    public int getWarriorExp() {
        return warriorExp;
    }

    public int getWarriorLevel() {
        return warriorLevel;
    }

    public int getWizardExp() {
        return wizardExp;
    }

    public int getWizardLevel() {
        return wizardLevel;
    }
    public void setArcherExp(int exp) {
        archerExp += exp;
    }
    public void setArcherLevel(int level) {
        archerLevel = level;
    }
    public void setTankExp(int exp) {
        tankExp += exp;
    }
    public void setTankLevel(int level) {
        tankLevel = level;
    }
    public void setWarriorExp(int exp) {
        warriorExp += exp;
    }
    public void setWarriorLevel(int level) {
        warriorLevel = level;
    }
    public void setWizardExp(int exp) {
        wizardExp += exp;
    }
    public void setWizardLevel(int level) {
        wizardLevel = level;
    }

    public int getStrengthUpgrade() {
        return strengthUpgrade;
    }
    public void setStrengthUpgrade(int strengthUpgrade) {
        this.strengthUpgrade = strengthUpgrade;
    }

    public int getSpeedUpgrade() {
        return speedUpgrade;
    }
    public void setSpeedUpgrade(int speedUpgrade) {
        this.speedUpgrade = speedUpgrade;
    }

    public int getManaUpgrade() {
        return manaUpgrade;
    }
    public void setManaUpgrade(int manaUpgrade) {
        this.manaUpgrade = manaUpgrade;
    }

    public int getHealthUpgrade() {
        return healthUpgrade;
    }
    public void setHealthUpgrade(int healthUpgrade) {
        this.healthUpgrade = healthUpgrade;
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

    public int getSouls() {
        return souls;
    }
    public void setSouls(int souls) {
        this.souls = souls;
    }

    public int getKills() {
        return kills;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getKillStreak() {
        return killstreak;
    }
    public void setKillStreak(int killStreak) {
        killstreak = killStreak;
    }

    public boolean isCombatLog() {
        return combatlog;
    }
    public void setCombatLog(boolean combatLog) {
        combatlog = combatLog;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setInfiniteMana(boolean infiniteMana) {
        this.infinitemana = infiniteMana;
    }

    public boolean isInfiniteMana() {
        return infinitemana;
    }

    public Integer getCooldown() {
        return abilitycooldown;
    }

    public void setCooldown(int cooldown) {
        abilitycooldown = cooldown;
    }

    public boolean hasCooldown() {
        return abilitycooldown != 0;
    }
    public PowermagePlayer save() {
        File file = getFileFile("playerdata.yml");
        if (file == null) return null;
        FileConfiguration config = getConfig(file);
        if (config == null) return null;
        config.set(String.valueOf(player.getUniqueId()), new HashMap<String, Object>() {{
            if (classesType != null) {
                put("class", classesType.getName());
            }
            put("warriorLevel", warriorLevel);
            put("archerLevel", archerLevel);
            put("wizardLevel", wizardLevel);
            put("tankLevel", tankLevel);
            put("warriorExp", warriorExp);
            put("archerExp", archerExp);
            put("wizardExp", wizardExp);
            put("tankExp", tankExp);

            put("healthUpgrade", healthUpgrade);
            put("strengthUpgrade", strengthUpgrade);
            put("manaUpgrade", manaUpgrade);
            put("speedUpgrade", speedUpgrade);

            put("money", money);
            put("souls", souls);
            put("kills", kills);
            put("deaths", deaths);
            put("assists", assists);
            put("mana", mana);
            put("infinitemana", infinitemana);
            put("killstreak", killstreak);
            put("combatlog", combatlog);
            put("cooldown", abilitycooldown);
        }});
        saveConfig(file, config);
        return new PowermagePlayer(player);
    }
}
