package me.temez.moonrise.skypvp.event.player;

import me.temez.moonrise.skypvp.Main;
import me.temez.moonrise.skypvp.player.SPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private Main instance;
    public PlayerDeathListener(Main main){
        this.instance = Main.getInstance();
    }

    @EventHandler
    public void onEvent(PlayerDeathEvent e){
        SPlayer player = new SPlayer(e.getEntity());

        e.setDroppedExp(0);
        player.addDeath();
        player.getPlayer().getInventory().clear();
        player.getPlayer().spigot().respawn();

        if(player.getPlayer().getKiller() != null){
            e.getEntity().sendMessage(Main.getPrefix() + "Вы были убиты игроком " + ChatColor.YELLOW +  player.getPlayer().getKiller().getName() + ChatColor.GRAY + "(" + ChatColor.GOLD + new SPlayer(player.getPlayer().getKiller()).getLevel()+ ChatColor.GRAY + ")");
        }
        else{
            e.getEntity().sendMessage(Main.getPrefix() + "Вы были убиты");
        }




    }
}
