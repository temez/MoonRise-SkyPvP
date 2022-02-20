package me.temez.moonrise.skypvp.inventory;

import me.temez.moonrise.skypvp.player.SPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class SLevelInventory implements Listener {

    private SPlayer player;
    public SLevelInventory(SPlayer player){
        this.player = player;
    }

    @EventHandler
    public void onEvent(InventoryClickEvent e){
        if (e.getSlotType().equals(InventoryType.SlotType.OUTSIDE)) {
            e.setCancelled(true);
            return;
        }


    }

}
