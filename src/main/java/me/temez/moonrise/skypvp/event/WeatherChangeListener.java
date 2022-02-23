package me.temez.moonrise.skypvp.event;

import me.temez.moonrise.skypvp.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {
    public WeatherChangeListener(Main main) {
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onThunderChange(ThunderChangeEvent event) {
        boolean storm = event.toThunderState();
        if (storm)
            event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent event) {
        boolean rain = event.toWeatherState();
        if (rain)
            event.setCancelled(true);
    }
}


