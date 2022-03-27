package org.distantnetwork.powermagecore.utils.Enums;

import org.bukkit.entity.Player;

public enum Classes {
    WARRIOR(0.2f, 20.0, 100){
        @Override
        public void OnAbility(Player player) {
            player.sendMessage("Warrior ability");
        }
        private int level;
        private int exp;
        public void setLevel(int level){
            this.level = level;
        }
        public void setExp(int exp){
            this.exp = exp;
        }
        public int getLevel(){
            return level;
        }
        public int getExp(){
            return exp;
        }
    },
    ARCHER(0.4f, 10.0, 100) {
        @Override
        public void OnAbility(Player player) {
            player.sendMessage("Archer ability");
        }
        private int level;
        private int exp;
        public void setLevel(int level){
            this.level = level;
        }
        public void setExp(int exp){
            this.exp = exp;
        }
        public int getLevel(){
            return level;
        }
        public int getExp(){
            return exp;
        }
    },
    WIZARD(0.16f, 15.0, 300) {
        @Override
        public void OnAbility(Player player) {
            player.sendMessage("Wizard ability");
        }
        private int level;
        private int exp;
        public void setLevel(int level){
            this.level = level;
        }
        public void setExp(int exp){
            this.exp = exp;
        }
        public int getLevel(){
            return level;
        }
        public int getExp(){
            return exp;
        }
    },
    TANK(0.1f, 40.0, 100) {
        @Override
        public void OnAbility(Player player) {
            player.sendMessage("Tank ability");
        }
        private int level;
        private int exp;
        public void setLevel(int level){
            this.level = level;
        }
        public void setExp(int exp){
            this.exp = exp;
        }
        public int getLevel(){
            return level;
        }
        public int getExp(){
            return exp;
        }
    };

    public abstract void OnAbility(Player player);

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