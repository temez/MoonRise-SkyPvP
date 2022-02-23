package me.temez.moonrise.skypvp.event.player;

import me.temez.moonrise.skypvp.Main;
import me.temez.moonrise.skypvp.config.ConfigManager;
import me.temez.moonrise.skypvp.player.SPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    public PlayerJoinListener(Main main) {

    }

    @EventHandler
    public void onEvent(PlayerJoinEvent e) {
        SPlayer p = new SPlayer(e.getPlayer());
        if (!p.getPlayer().hasPlayedBefore()) {
            ConfigManager config = new ConfigManager("players.yml");
            config.getConfig().set("players." + p.getPlayer().getName() + ".level", 1);
            config.getConfig().set("players." + p.getPlayer().getName() + ".kills", 0);
            config.getConfig().set("players." + p.getPlayer().getName() + ".deaths", 0);
            config.save();
        }

        p.getPlayer().setMaxHealth(20.0 + (p.getLevel().getLevel()));
    }

}
