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
        this.msg = config.getConfig().getString("levels." + level + ".msg");
    }

    public int getKillsFor() {
        return this.kills;
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
