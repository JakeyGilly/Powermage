package org.distantnetwork.powermagecore.utils.Enums;

import org.bukkit.entity.Player;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

public enum Classes {
    WARRIOR(0.2f, 20.0, 100){
        @Override
        public void OnAbility(Player player) {
            player.sendMessage("Warrior ability");
        }
        private int wlevel = 0;
        private int wexp = 0;
        @Override
        public void setLvl(Player player, int level) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            pmPlayer.setWarriorLevel(level);
            pmPlayer.save();
            wlevel = level;
        }
        @Override
        public void setExp(Player player, int exp) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            pmPlayer.setWarriorExp(exp);
            pmPlayer.save();
            wexp = exp;
        }
        @Override
        public int getLvl(Player player) {
            if (wlevel == 0) {
                PowermagePlayer pmPlayer = new PowermagePlayer(player);
                wlevel = pmPlayer.getWarriorLevel();
            }
            return wlevel;
        }
        @Override
        public int getExp(Player player) {
            if (wexp == 0) {
                PowermagePlayer pmPlayer = new PowermagePlayer(player);
                wexp = pmPlayer.getWarriorExp();
            }
            return wexp;
        }
    },
    ARCHER(0.4f, 10.0, 100) {
        @Override
        public void OnAbility(Player player) {
            player.sendMessage("Archer ability");
        }
        private int alevel;
        private int aexp;
        public void setLvl(Player player, int level) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            pmPlayer.setArcherLevel(level);
            pmPlayer.save();
            alevel = level;
        }
        @Override
        public void setExp(Player player, int exp) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            pmPlayer.setArcherExp(exp);
            pmPlayer.save();
            aexp = exp;
        }
        @Override
        public int getLvl(Player player) {
            if (alevel == 0) {
                PowermagePlayer pmPlayer = new PowermagePlayer(player);
                alevel = pmPlayer.getArcherLevel();
            }
            return alevel;
        }
        @Override
        public int getExp(Player player) {
            if (aexp == 0) {
                PowermagePlayer pmPlayer = new PowermagePlayer(player);
                aexp = pmPlayer.getArcherExp();
            }
            return aexp;
        }
    },
    WIZARD(0.16f, 15.0, 300) {
        @Override
        public void OnAbility(Player player) {
            player.sendMessage("Wizard ability");
        }
        private int wilevel;
        private int wiexp;
        public void setLvl(Player player, int level) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            pmPlayer.setWizardLevel(level);
            pmPlayer.save();
            wilevel = level;
        }
        @Override
        public void setExp(Player player, int exp) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            pmPlayer.setWizardExp(exp);
            pmPlayer.save();
            wiexp = exp;
        }
        @Override
        public int getLvl(Player player) {
            if (wilevel == 0) {
                PowermagePlayer pmPlayer = new PowermagePlayer(player);
                wilevel = pmPlayer.getWizardLevel();
            }
            return wilevel;
        }
        @Override
        public int getExp(Player player) {
            if (wiexp == 0) {
                PowermagePlayer pmPlayer = new PowermagePlayer(player);
                wiexp = pmPlayer.getWizardExp();
            }
            return wiexp;
        }
    },
    TANK(0.1f, 40.0, 100) {
        @Override
        public void OnAbility(Player player) {
            player.sendMessage("Tank ability");
        }
        private int tlevel;
        private int texp;
        public void setLvl(Player player, int level) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            pmPlayer.setTankLevel(level);
            pmPlayer.save();
            tlevel = level;
        }
        @Override
        public void setExp(Player player, int exp) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            pmPlayer.setTankExp(exp);
            pmPlayer.save();
            texp = exp;
        }
        @Override
        public int getLvl(Player player) {
            if (tlevel == 0) {
                PowermagePlayer pmPlayer = new PowermagePlayer(player);
                tlevel = pmPlayer.getTankLevel();
            }
            return tlevel;
        }
        @Override
        public int getExp(Player player) {
            if (texp == 0) {
                PowermagePlayer pmPlayer = new PowermagePlayer(player);
                texp = pmPlayer.getTankExp();
            }
            return texp;
        }
    };

    public abstract void OnAbility(Player player);
    public abstract void setLvl(Player player, int level);
    public abstract void setExp(Player player, int exp);
    public abstract int getLvl(Player player);
    public abstract int getExp(Player player);

    private final float speed;
    private final double maxHealth;
    private final double maxMana;

    private Classes(float speed, double maxHealth, double maxMana) {
        this.maxHealth = maxHealth;
        this.maxMana = maxMana;
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getMaxMana() {
        return maxMana;
    }
}