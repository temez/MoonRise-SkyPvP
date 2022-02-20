package me.temez.moonrise.skypvp.player;

import me.temez.moonrise.skypvp.config.SConfig;
import org.bukkit.entity.Player;

public class SPlayer {
    private Player player;
    private int level, kills, deaths;
    private String name;
    private SConfig config = new SConfig("players.yml");

    public SPlayer(Player player){
        this.player = player;
        this.name = player.getName();
        this.level = config.getConfig().getInt("players." + name + ".level");
        this.kills = config.getConfig().getInt("players." + name + ".kills");
        this.deaths = config.getConfig().getInt("players." + name + ".deaths");
    }

    public Player getPlayer() {
        return player;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getLevel() {
        return level;
    }

    public void addDeath(){
        this.deaths += 1;
        config.getConfig().set("players." + name + ".deaths", deaths);
        config.save();
    }

    public void addKill(){
        this.kills += 1;
        config.getConfig().set("players." + name + ".kills", kills);
        config.save();
    }
}
