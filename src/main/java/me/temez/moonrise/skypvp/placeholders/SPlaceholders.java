package me.temez.moonrise.skypvp.placeholders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.temez.moonrise.skypvp.Main;
import me.temez.moonrise.skypvp.player.SPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SPlaceholders extends PlaceholderExpansion {
    public Main instance;

    public SPlaceholders(Main main) {
        this.instance = main;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "temez";
    }

    @Override
    public String getIdentifier() {
        return "spvp";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        SPlayer p = new SPlayer(player);
        if (p == null) {
            return "";
        }
        if (identifier.equals("level")) {
            return ChatColor.GOLD + "" + p.getLevel();
        }
        if (identifier.equals("deaths")) {
            return ChatColor.RED +  "" +p.getDeaths() + "";
        }
        if (identifier.equals("kills")) {
            return ChatColor.GREEN + "" + p.getKills() + "";
        }

        return null;
    }
}
