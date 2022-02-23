package me.temez.moonrise.skypvp;

import me.temez.moonrise.skypvp.command.SLevelCommand;
import me.temez.moonrise.skypvp.config.ConfigManager;
import me.temez.moonrise.skypvp.event.player.PlayerDeathListener;
import me.temez.moonrise.skypvp.event.player.PlayerJoinListener;
import me.temez.moonrise.skypvp.placeholders.SPlaceholders;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {

    public static Main instance;
    private static final String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "Sky" + ChatColor.GRAY + "PvP" + ChatColor.GRAY + "] ";
    private final Logger log = getLogger();

    @Override
    public void onEnable() {
        instance = this;

        log.info("Loading configs...");
        loadConfigs();

        log.info("Registering events...");
        registerEvents();

        log.info("Registering commands...");
        registerCommands();

        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            log.info("Registering placeholders...");
            new SPlaceholders(this).register();
        }
    }

    private void registerCommands() {
        new SLevelCommand();
    }


    @Override
    public void onDisable() {
    }

    public static Main getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }

    private void loadConfigs() {
        new ConfigManager("players.yml");
        new ConfigManager("levels.yml");
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerDeathListener((this)), this);
        pm.registerEvents(new PlayerJoinListener((this)), this);

    }
}
