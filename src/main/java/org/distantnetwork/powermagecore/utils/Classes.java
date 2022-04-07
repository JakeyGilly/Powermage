package org.distantnetwork.powermagecore.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Classes {
    private String name;
    private int classMenuSlot;
    private float baseSpeed;
    private int baseMana;
    private double baseHealth;
    private int baseDamage;
    private List<String> description;
    private String emoji;
    private ItemStack weaponIcon;
    private static List<Classes> classes = new ArrayList<>();
    public abstract void onAbility(Player player);
    public abstract WeaponItem getWeaponItem();

    public Classes(String name, int classMenuSlot, float baseSpeed, int baseMana, double baseHealth, int baseDamage, List<String> description, String emoji, ItemStack weaponIcon) {
        this.name = name;
        this.classMenuSlot = classMenuSlot;
        this.baseSpeed = baseSpeed;
        this.baseMana = baseMana;
        this.baseHealth = baseHealth;
        this.baseDamage = baseDamage;
        this.description = description;
        this.emoji = emoji;
        this.weaponIcon = weaponIcon;
        classes.add(this);
    }

    public float getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(float baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getBaseMana() {
        return baseMana;
    }

    public void setBaseMana(int baseMana) {
        this.baseMana = baseMana;
    }

    public double getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(double baseHealth) {
        this.baseHealth = baseHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public ItemStack getWeaponIcon() {
        return weaponIcon;
    }

    public void setWeaponIcon(ItemStack weaponIcon) {
        this.weaponIcon = weaponIcon;
    }

    public static List<Classes> getClasses() {
        return Classes.classes;
    }

    public static void setClasses(List<Classes> classes) {
        Classes.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassMenuSlot() {
        return classMenuSlot;
    }

    public void setClassMenuSlot(int classMenuSlot) {
        this.classMenuSlot = classMenuSlot;
    }
}
