package org.distantnetwork.powermagecore.utils.Enums;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.distantnetwork.powermagecore.utils.Config.ConfigurationManager;
import org.distantnetwork.powermagecore.utils.PowermagePlayer;

import java.util.Arrays;

import static org.distantnetwork.powermagecore.PowermageCore.getInstance;

public enum Classes {
    WARRIOR(0.2f, 20.0, 100, Material.IRON_SWORD,
            Arrays.asList(" ",
                String.format("%sClass Ability: %sCharge", ChatColor.BOLD, ChatColor.GOLD),
                String.format("%sGives player %s+100 %s✦ Speed %sfor %s10 %sseconds.", ChatColor.GRAY, ChatColor.GREEN, ChatColor.GOLD, ChatColor.GRAY, ChatColor.GREEN, ChatColor.GRAY),
                String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW),
                " "
            ).toArray(new String[0]), "\uD83D\uDDE1") {
        @Override
        public void OnAbility(Player player) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            if (pmPlayer.hasCooldown()) {
                player.sendMessage(String.format("%sYou cant use this ability for %s%s seconds!", ChatColor.RED, ChatColor.GOLD, pmPlayer.getCooldown()));
                return;
            }
            pmPlayer.setCooldown(60);
            pmPlayer.save();
            player.setWalkSpeed((float) (pmPlayer.getClassType().getSpeed() + pmPlayer.getSpeedUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel") + 0.2f));
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.setWalkSpeed((float) (pmPlayer.getClassType().getSpeed() + pmPlayer.getSpeedUpgrade() * ConfigurationManager.getDefaultConfig().getDouble("upgrades.speed.speedPerLevel")));
                }
            }.runTaskLater(getInstance(), 200);
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
    ARCHER(0.4f, 10.0, 100, Material.CROSSBOW,
            Arrays.asList(" ",
                    String.format("%sClass Ability: %sQuickshot", ChatColor.BOLD, ChatColor.GOLD),
                    String.format("%sRapid-fires arrows to where the player is looking at for %s3 seconds.", ChatColor.GRAY, ChatColor.GREEN),
                    String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW),
                    " "
            ).toArray(new String[0]), "\uD83C\uDFF9") {

        @Override
        public void OnAbility(Player player) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            if (pmPlayer.hasCooldown()) {
                player.sendMessage(String.format("%sYou cant use this ability for %s%s seconds!", ChatColor.RED, ChatColor.GOLD, pmPlayer.getCooldown()));
                return;
            }
            pmPlayer.setCooldown(60);
            pmPlayer.save();
            for (int i = 0; i < 10; i++) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Arrow arrow = player.launchProjectile(Arrow.class);
                        arrow.setVelocity(arrow.getVelocity().multiply(2));
                        arrow.setShooter(player);
                        arrow.setBounce(false);
                        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                    }
                }.runTaskLater(getInstance(), i * 3);
            }
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
    WIZARD(0.16f, 15.0, 300, Material.POTION,
            Arrays.asList(" ",
                    String.format("%sClass Ability: %sHeart of Magic", ChatColor.BOLD, ChatColor.GOLD),
                    String.format("%sGives you infinite mana for %s5 seconds.", ChatColor.GRAY, ChatColor.GREEN),
                    String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW),
                    " "
            ).toArray(new String[0]), "\uD83E\uDDEA") {

        @Override
        public void OnAbility(Player player) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            if (pmPlayer.hasCooldown()) {
                player.sendMessage(String.format("%sYou cant use this ability for %s%s seconds!", ChatColor.RED, ChatColor.GOLD, pmPlayer.getCooldown()));
                return;
            }
            pmPlayer.setCooldown(60);
            pmPlayer.setInfiniteMana(true);
            pmPlayer.save();
            new BukkitRunnable() {
                @Override
                public void run() {
                    pmPlayer.setInfiniteMana(false);
                    pmPlayer.save();
                }
            }.runTaskLater(getInstance(), 100);
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
    TANK(0.1f, 40.0, 100, Material.IRON_CHESTPLATE,
            Arrays.asList(" ",
                    String.format("%sClass Ability: %sTurtle Up", ChatColor.BOLD, ChatColor.GOLD),
                    String.format("%sGives player %sResistance 2 %sand %sRegen 5 %sfor %s10 seconds.", ChatColor.GRAY, ChatColor.BLUE, ChatColor.GRAY, ChatColor.BLUE, ChatColor.GRAY, ChatColor.GREEN),
                    String.format("%sCooldown: %s30 Seconds", ChatColor.DARK_GRAY, ChatColor.GREEN), String.format("%sLeft Click on your menu to activate!", ChatColor.YELLOW),
                    " "
            ).toArray(new String[0]), "\uD83D\uDEE1") {
        @Override
        public void OnAbility(Player player) {
            PowermagePlayer pmPlayer = new PowermagePlayer(player);
            if (pmPlayer.hasCooldown()) {
                player.sendMessage(String.format("%sYou cant use this ability for %s%s seconds!", ChatColor.RED, ChatColor.GOLD, pmPlayer.getCooldown()));
                return;
            }
            pmPlayer.setCooldown(60);
            pmPlayer.save();
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 2));
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 5));
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
    public abstract int getLvl(Player player);
    public abstract int getExp(Player player);
    public abstract void OnAbility(Player player);
    public abstract void setLvl(Player player, int level);
    public abstract void setExp(Player player, int exp);
    private final float speed;
    private final double maxHealth;
    private final double maxMana;
    private final Material material;
    private final String[] description;
    private final String emoji;

    private Classes(float speed, double maxHealth, double maxMana, Material material, String[] description, String emoji) {
        this.maxHealth = maxHealth;
        this.maxMana = maxMana;
        this.speed = speed;
        this.material = material;
        this.description = description;
        this.emoji = emoji;
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
    public Material getMaterial() {
        return material;
    }
    public String[] getDescription() {
        return description;
    }
    public String getEmoji() {
        return emoji;
    }
}