package me.temez.moonrise.skypvp.spawners.item;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.temez.moonrise.skypvp.Main;
import me.temez.moonrise.skypvp.runnable.SyncRepeatingTask;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;


public class Spawner implements Listener {

    private final Location location;
    private final long initialDelay;
    private long actualDelay;
    private final ItemStack item;
    private SpawnerState state;
    private final Main instance;
    private Hologram hologram;

    public Spawner(ItemStack item, Location location, long initialDelay){
        instance = Main.getInstance();
        Bukkit.getPluginManager().registerEvents(this, instance);
        instance.getItemSpawnerManager().addToSpawnerList(this);

        this.location = location;
        this.initialDelay = initialDelay * 20;
        this.item = item;
        this.state = SpawnerState.INITIALIZED;
    }

    enum SpawnerState{
        SPAWNED,
        DELAYED,
        INITIALIZED
    }

    @EventHandler
    public void onChestClick(PlayerInteractEvent e){
        if(e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            EquipmentSlot hand = e.getHand();

            if (hand != null && !hand.equals(EquipmentSlot.HAND)) {
                return;
            }
            if(e.getClickedBlock().getLocation().equals(location.getBlock().getLocation())){
                if(state.equals(SpawnerState.SPAWNED)){
                    World world = location.getWorld();
                    e.setCancelled(true);
                    world.playSound(location, Sound.BLOCK_CHEST_OPEN, 1, 1);
                    world.dropItemNaturally(location, item);
                    location.getBlock().setType(Material.AIR);
                    startSpawn();
                }
            }
        }
    }

    public void startSpawn(){
        this.actualDelay = initialDelay;
        state = SpawnerState.DELAYED;

        if(hologram != null){
            hologram.delete();
        }
        location.getBlock().setType(Material.AIR);
        hologram = HologramsAPI.createHologram(instance, location.add(0, 1.7, 0));
        hologram.appendItemLine(item);
        updateTimeToRespawn();

        new SyncRepeatingTask(instance, 100, actualDelay - 100) {
            @Override
            public void run() {
                if(actualDelay - 100 > 0) {
                    actualDelay -= 100;
                    updateTimeToRespawn();
                }
                else {
                    cancel();
                    spawnChest();
                }
            }
        };
    }

    public void spawnChest(){
        state = SpawnerState.SPAWNED;

        hologram.removeLine(hologram.size() - 1);
        hologram.removeLine(hologram.size() - 1);
        location.add(0, -1.7, 0);
        location.getBlock().setType(Material.CHEST);
    }


    public void updateTimeToRespawn(){
        if(hologram.size() > 2) {
            hologram.removeLine(hologram.size() - 1);
            hologram.removeLine(hologram.size() - 1);
        }
        hologram.appendTextLine("До спавна предмета");
        hologram.appendTextLine("примерно " + ChatColor.GREEN +actualDelay / 20 + ChatColor.WHITE + " секунд");
    }

    public ItemStack getItem() {
        return item;
    }

    public Location getLocation() {
        return location;
    }

    public double getInitialDelay() {
        return initialDelay;
    }

    public long getActualDelay() {
        return actualDelay;
    }

    public SpawnerState getState() {
        return state;
    }

    public Hologram getHologram() {
        return hologram;
    }
}
