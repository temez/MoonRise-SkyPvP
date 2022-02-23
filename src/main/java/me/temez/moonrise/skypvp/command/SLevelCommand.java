package me.temez.moonrise.skypvp.command;

import me.temez.moonrise.skypvp.inventory.SLevelInventory;
import me.temez.moonrise.skypvp.player.SPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SLevelCommand extends AbstractCommand {
    public SLevelCommand() {
        super("level");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (args.length == 0 && sender instanceof Player) {
            new SLevelInventory(new SPlayer((Player) sender));
        }
    }
}
