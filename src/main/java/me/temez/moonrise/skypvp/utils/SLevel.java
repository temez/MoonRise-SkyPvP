package me.temez.moonrise.skypvp.utils;

import me.temez.moonrise.skypvp.config.SConfig;

public class SLevel {
    private static final SConfig config = new SConfig("levels.yml");
    private int kills, level;
    private double experience, money;
    private String msg;

    public SLevel(int level) {
        this.level = level;
        this.experience = config.getInt("levels." + level + ".exp");
        this.money = config.getDouble("levels." + level + ".money");
        this.kills = config.getInt("levels." + level + ".kills");
        this.msg = config.getString("levels." + level + ".msg");
    }

    public int getKillsFor() {
        return this.kills;
    }

    public double getExperienceFor() {
        return this.experience;
    }

    public double getMoneyFor() {
        return this.money;
    }

    public String getMsg() {
        return this.msg;
    }
    
    public int getLevel() {
        return this.level;
    }

}
