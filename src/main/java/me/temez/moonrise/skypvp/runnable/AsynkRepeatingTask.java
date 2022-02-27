package me.temez.moonrise.skypvp.runnable;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AsynkRepeatingTask implements Runnable {

    private final int taskId;

    public AsynkRepeatingTask(JavaPlugin plugin, int arg1, int arg2) {
        taskId = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, arg1, arg2).getTaskId();
    }

    public void cancel() {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}
