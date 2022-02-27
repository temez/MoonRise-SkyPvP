package me.temez.moonrise.skypvp.utils;

import me.temez.moonrise.skypvp.config.ConfigManager;

public class SLevel {
    private static final ConfigManager config = new ConfigManager("levels.yml");
    private int kills, level;
    private double money;
    private String msg;

    public SLevel(int level) {
        this.level = level;
        this.money = config.getConfig().getDouble("levels." + level + ".money");
        this.kills = config.getConfig().getInt("levels." + level + ".kills");
    }

    public int getRequiredKills() {
        return kills;
    }

    public double getRequiredMoney() {
        return money;
    }

    public int getLevel() {
        return level;
    }

}
