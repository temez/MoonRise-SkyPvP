package me.temez.moonrise.skypvp.player;

import com.earth2me.essentials.api.Economy;
import me.temez.moonrise.skypvp.Main;
import me.temez.moonrise.skypvp.config.ConfigManager;
import me.temez.moonrise.skypvp.utils.SChatMessage;
import me.temez.moonrise.skypvp.utils.SLevel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;


public class SPlayer {
    public static final java.util.Collection<? extends Player> ONLINE_PLAYERS = Bukkit.getOnlinePlayers();
    private Player player;
    private double balance;
    private int kills, deaths;
    private SLevel level;
    private String name;
    private ConfigManager config = new ConfigManager("players.yml");

    public SPlayer(Player player) {
        this.player = player;
        this.name = player.getName();
        this.level = new SLevel(config.getConfig().getInt("players." + name + ".level"));
        this.kills = config.getConfig().getInt("players." + name + ".kills");
        this.deaths = config.getConfig().getInt("players." + name + ".deaths");
        try {
            this.balance = Economy.getMoney(name);
        } catch (Exception ignored) {
        }
    }

    public Player getPlayer() {
        return player;
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        this.kills += 1;
        config.getConfig().set("players." + name + ".kills", kills);
        config.save();
    }

    public int getDeaths() {
        return deaths;
    }


    public void addDeath() {
        this.deaths += 1;
        config.getConfig().set("players." + name + ".deaths", deaths);
        config.save();
    }

    public SLevel getLevel() {
        return level;
    }

    public void setLevel(SLevel l) {
        this.level = l;
        config.getConfig().set("players." + name + ".level", l.getLevel());
        config.save();
    }

    public SLevel getNextLevel() {
        return new SLevel(this.level.getLevel() + 1);
    }

    public boolean hasAllForNextLevel() {
        SLevel level = new SLevel(getNextLevel().getLevel());
        return getBalance() >= level.getRequiredMoney() && getKills() >= level.getRequiredKills();
    }

    public double getBalance() {
        return this.balance;
    }


    public void addMoney(Double amount) {
        try {
            Economy.add(name, amount);
            this.balance = getBalance();
        } catch (Exception ignored) {
        }
    }

    public void takeMoney(Double amount) {
        try {
            if (!Economy.hasMore(name, amount)) {
                Economy.setMoney(name, 0.0);
            } else {
                Economy.setMoney(name, Economy.getMoney(name) - amount);
            }
            this.balance = getBalance();
        } catch (Exception ignored) {
        }
    }

    public void doLevelUp() {
        SLevel lvl = getNextLevel();
        if (hasAllForNextLevel()) {
            takeMoney(lvl.getRequiredMoney());
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 2.0f);
            setLevel(getNextLevel());
            player.sendMessage(SChatMessage.NEW_LEVEL.getMessage());
            player.setMaxHealth(20.0 + (getLevel().getLevel()));
            player.closeInventory();
            if (getLevel().getLevel() == 30 || getLevel().getLevel() == 45 || getLevel().getLevel() == 60) {
                for (Player pl : ONLINE_PLAYERS) {
                    pl.sendMessage(Main.getPrefix() + ChatColor.GRAY + "Игрок " + ChatColor.YELLOW + name + ChatColor.GRAY + " достиг " + ChatColor.YELLOW + getLevel().getLevel() + "-го " + ChatColor.GRAY + "уровня, поздравляем!");
                }
            }
        } else {
            player.closeInventory();
            player.sendMessage(SChatMessage.CANT_LEVELUP.getMessage());
        }
    }
}
