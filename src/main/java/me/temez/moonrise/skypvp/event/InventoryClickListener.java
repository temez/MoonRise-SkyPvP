package me.temez.moonrise.skypvp.event;

import me.temez.moonrise.skypvp.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClickListener implements Listener {
    public InventoryClickListener(Main main) {

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(InventoryClickEvent e) {
        if (e.getSlotType().equals(InventoryType.SlotType.OUTSIDE)) {
            e.setCancelled(true);
            return;
        }
    }
}
