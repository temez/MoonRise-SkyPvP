package me.temez.moonrise.skypvp.utils;

import me.temez.moonrise.skypvp.Main;
import org.bukkit.ChatColor;

public enum SChatMessage {
    NEW_LEVEL(Main.getPrefix() + ChatColor.GREEN + "Вы получили новый уровень!"),
    CANT_LEVELUP(Main.getPrefix() + ChatColor.RED + "Вы не можете повысить уровень!");


    private String message;

    SChatMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
