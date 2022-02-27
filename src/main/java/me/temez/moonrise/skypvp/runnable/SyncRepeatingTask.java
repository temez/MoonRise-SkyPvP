package me.temez.moonrise.skypvp.runnable;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class SyncRepeatingTask implements Runnable {
    private final int taskId;

    public SyncRepeatingTask(JavaPlugin plugin, long initialDelay, long delay) {
        taskId = Bukkit.getScheduler().runTaskTimer(plugin, this, initialDelay, delay).getTaskId();
    }

    public void cancel() {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}
