 package me.temez.moonrise.skypvp.event.player;

 import me.temez.moonrise.skypvp.Main;
 import me.temez.moonrise.skypvp.config.SConfig;
 import org.bukkit.entity.Player;
 import org.bukkit.event.EventHandler;
 import org.bukkit.event.Listener;
 import org.bukkit.event.player.PlayerJoinEvent;

 public class PlayerJoinListener implements Listener {
    public PlayerJoinListener(Main main){

    }

    @EventHandler
    public void onEvent(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()){
            SConfig config = new SConfig("players.yml");
            config.getConfig().set("players." + p.getName() + ".level", 1);
            config.getConfig().set("players." + p.getName() + ".kills", 0);
            config.getConfig().set("players." + p.getName() + ".deaths", 0);
            config.save();
        }
    }

}
