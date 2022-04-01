package org.distantnetwork.powermagecore.utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.Enums.Classes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;
import static org.distantnetwork.powermagecore.utils.Config.ConfigurationManager.*;

public class PowermagePlayer {
    private Player player;

    //? Classes
    private Classes classType;
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
    private Map<UUID, Integer> abilitycooldown = new HashMap<>();
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
        if (config.contains(player.getUniqueId() + ".class"))
            this.classType = Classes.valueOf(config.getString(player.getUniqueId() + ".class"));
        if (config.contains(player.getUniqueId() + ".warriorLevel"))
            this.warriorLevel = config.getInt(player.getUniqueId() + ".warriorLevel");
        if (config.contains(player.getUniqueId() + ".archerLevel"))
            this.archerLevel = config.getInt(player.getUniqueId() + ".archerLevel");
        if (config.contains(player.getUniqueId() + ".wizardLevel"))
            this.wizardLevel = config.getInt(player.getUniqueId() + ".wizardLevel");
        if (config.contains(player.getUniqueId() + ".tankLevel"))
            this.tankLevel = config.getInt(player.getUniqueId() + ".tankLevel");
        if (config.contains(player.getUniqueId() + ".warriorExp"))
            this.warriorExp = config.getInt(player.getUniqueId() + ".warriorExp");
        if (config.contains(player.getUniqueId() + ".archerExp"))
            this.archerExp = config.getInt(player.getUniqueId() + ".archerExp");
        if (config.contains(player.getUniqueId() + ".wizardExp"))
            this.wizardExp = config.getInt(player.getUniqueId() + ".wizardExp");
        if (config.contains(player.getUniqueId() + ".tankExp"))
            this.tankExp = config.getInt(player.getUniqueId() + ".tankExp");
        if (config.contains(player.getUniqueId() + ".strengthUpgrade"))
            this.strengthUpgrade = config.getInt(player.getUniqueId() + ".strengthUpgrade");
        if (config.contains(player.getUniqueId() + ".speedUpgrade"))
            this.speedUpgrade = config.getInt(player.getUniqueId() + ".speedUpgrade");
        if (config.contains(player.getUniqueId() + ".manaUpgrade"))
            this.manaUpgrade = config.getInt(player.getUniqueId() + ".manaUpgrade");
        if (config.contains(player.getUniqueId() + ".healthUpgrade"))
            this.healthUpgrade = config.getInt(player.getUniqueId() + ".healthUpgrade");
        if (config.contains(player.getUniqueId() + ".money"))
            this.money = config.getInt(player.getUniqueId() + ".money");
        if (config.contains(player.getUniqueId() + ".souls"))
            this.souls = config.getInt(player.getUniqueId() + ".souls");
        if (config.contains(player.getUniqueId() + ".kills"))
            this.kills = config.getInt(player.getUniqueId() + ".kills");
        if (config.contains(player.getUniqueId() + ".deaths"))
            this.deaths = config.getInt(player.getUniqueId() + ".deaths");
        if (config.contains(player.getUniqueId() + ".assists"))
            this.assists = config.getInt(player.getUniqueId() + ".assists");
        if (config.contains(player.getUniqueId() + ".mana"))
            this.mana = config.getInt(player.getUniqueId() + ".mana");
        if (config.contains(player.getUniqueId() + ".cooldown"))
            this.abilitycooldown.put(player.getUniqueId(), config.getInt(player.getUniqueId() + ".cooldown"));
        if (config.contains(player.getUniqueId() + ".infinitemana"))
            this.infinitemana = config.getBoolean(player.getUniqueId() + ".infinitemana");
        if (config.contains(player.getUniqueId() + ".combatlog"))
            this.combatlog = config.getBoolean(player.getUniqueId() + ".combatlog");
        if (config.contains(player.getUniqueId() + ".killstreak"))
            this.killstreak = config.getInt(player.getUniqueId() + ".killstreak");

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
        return classType;
    }
    public void setClassType(Classes classType) {
        this.classType = classType;
    }

    public int getWarriorLevel() {
        return warriorLevel;
    }
    public void setWarriorLevel(int warriorLevel) {
        this.warriorLevel = warriorLevel;
    }
    public int getArcherLevel() {
        return archerLevel;
    }
    public void setArcherLevel(int archerLevel) {
        this.archerLevel = archerLevel;
    }
    public int getWizardLevel() {
        return wizardLevel;
    }
    public void setWizardLevel(int wizardLevel) {
        this.wizardLevel = wizardLevel;
    }
    public int getTankLevel() {
        return tankLevel;
    }
    public void setTankLevel(int tankLevel) {
        this.tankLevel = tankLevel;
    }

    public int getWarriorExp() {
        return warriorExp;
    }
    public void setWarriorExp(int warriorExp) {
        this.warriorExp = warriorExp;
    }
    public int getArcherExp() {
        return archerExp;
    }
    public void setArcherExp(int archerExp) {
        this.archerExp = archerExp;
    }
    public int getWizardExp() {
        return wizardExp;
    }
    public void setWizardExp(int wizardExp) {
        this.wizardExp = wizardExp;
    }
    public int getTankExp() {
        return tankExp;
    }
    public void setTankExp(int tankExp) {
        this.tankExp = tankExp;
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
        return abilitycooldown.get(player.getUniqueId());
    }

    public void setCooldown(int cooldown) {
        abilitycooldown.put(player.getUniqueId(), cooldown);
    }

    public boolean hasCooldown() {
        for (UUID key : abilitycooldown.keySet()) {
            if (key.equals(player.getUniqueId())) {
                return abilitycooldown.get(player.getUniqueId()) > 0;
            }
        }
        return false;
    }
    public PowermagePlayer save() {
        File file = getFileFile("playerdata.yml");
        if (file == null) return null;
        FileConfiguration config = getConfig(file);
        if (config == null) return null;
        config.set(String.valueOf(player.getUniqueId()), new HashMap<String, Object>() {{
            put("class", classType.name());
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
            put("cooldown", abilitycooldown.get(player.getUniqueId()));
        }});
        saveConfig(file, config);
        return new PowermagePlayer(player);
    }
}
