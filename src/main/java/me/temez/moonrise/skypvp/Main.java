package me.temez.moonrise.skypvp;

import me.temez.moonrise.skypvp.command.SLevelCommand;
import me.temez.moonrise.skypvp.config.ConfigManager;
import me.temez.moonrise.skypvp.event.player.PlayerDeathListener;
import me.temez.moonrise.skypvp.event.player.PlayerJoinListener;
import me.temez.moonrise.skypvp.event.world.BlockListener;
import me.temez.moonrise.skypvp.event.world.WeatherChangeListener;
import me.temez.moonrise.skypvp.placeholders.SPlaceholders;
import me.temez.moonrise.skypvp.spawners.item.ItemSpawnerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Main instance;
    private static final String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "Sky" + ChatColor.GRAY + "PvP" + ChatColor.GRAY + "] ";
    private final Logger log = getLogger();
    private ItemSpawnerManager itemSawnerManager;
    @Override
    public void onEnable() {
        instance = this;

        log.info("Загрузка конфигов...");
        loadConfigs();

        log.info("Загрузка спавнеров предметов...");
        itemSawnerManager = new ItemSpawnerManager(this);
        registerItemSpawn();

        log.info("Загрузка спавнеров мобов...");
        itemSawnerManager = new ItemSpawnerManager(this);
        registerItemSpawn();

        log.info("Registering events...");
        registerEvents();

        log.info("Registering commands...");
        registerCommands();

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            log.info("Registering placeholders...");
            new SPlaceholders(this).register();
        }
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
        new SLevelCommand();
    }

    private void loadConfigs() {
        new ConfigManager("players.yml");
        new ConfigManager("levels.yml");
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerDeathListener((this)), this);
        pm.registerEvents(new PlayerJoinListener((this)), this);
        pm.registerEvents(new WeatherChangeListener((this)), this);
        pm.registerEvents(new BlockListener((this)), this);

    }

    private void registerItemSpawn(){
        ItemStack temp = new ItemStack(Material.COOKED_BEEF);
        temp.setAmount(4);
        itemSawnerManager.addItemToSpawn(temp, new Location(Bukkit.getWorld("skypvp_map"), -511.5, 78, -161.5), 45);
        temp = new ItemStack(Material.COOKED_CHICKEN);
        temp.setAmount(4);
        itemSawnerManager.addItemToSpawn(temp, new Location(Bukkit.getWorld("skypvp_map"), -542.5, 77, -104.5), 45);
    }

    public static Main getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    public ItemSpawnerManager getItemSpawnerManager() {
        return itemSawnerManager;
    }
}
