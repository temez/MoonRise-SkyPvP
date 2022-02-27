package me.temez.moonrise.skypvp.spawners.item;

import me.temez.moonrise.skypvp.Main;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemSpawnerManager {

    private final List<Spawner> spawnersList = new ArrayList<>();
    private Main instance;

    public ItemSpawnerManager(Main instance){
        this.instance = instance;
    }

    public void addItemToSpawn(ItemStack item, Location location, long delay){
        new Spawner(item, location, delay).startSpawn();
    }

    public void addToSpawnerList(Spawner spawner){
        spawnersList.add(spawner);
    }
}
