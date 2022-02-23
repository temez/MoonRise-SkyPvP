package me.temez.moonrise.skypvp.inventory;

import me.temez.moonrise.skypvp.Main;
import me.temez.moonrise.skypvp.player.SPlayer;
import me.temez.moonrise.skypvp.utils.BaseUtils;
import me.temez.moonrise.skypvp.utils.SLevel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SLevelInventory implements Listener {

    private SPlayer player;
    private Inventory inventory;

    public SLevelInventory(SPlayer player) {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
        this.player = player;
        this.inventory = Bukkit.createInventory(null, InventoryType.DISPENSER, "Повышение уровня");

        SLevel lvl = player.getNextLevel();
        int kills = lvl.getKillsFor();
        double money = lvl.getMoneyFor();
        ItemStack Planes = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        ItemMeta Meta = Planes.getItemMeta();
        if (player.hasAllForNextLevel()) {
            Planes.setDurability((short) 5);
            Meta.setDisplayName("§aПовышение доступно");
        } else {
            Planes.setDurability((short) 14);

            Meta.setDisplayName("§cПовышение недоступно");
        }
        Planes.setItemMeta(Meta);
        for (int f = 0; f < 9; f++) {
            this.inventory.setItem(f, Planes);
        }
        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add("§7Информация об уровне:");
        lore.add("§7Следующий уровень: §e" + lvl.getLevel());
        lore.add("§7Стоимость следующего уровня: §a" + money + "$");
        lore.add("§7Убийств следующего уровня: §c" + kills);
        this.inventory.setItem(4, BaseUtils.createIs(Material.EXP_BOTTLE, "§aПовысить уровень", lore, 1));
        player.getPlayer().openInventory(inventory);
        player.getPlayer().openInventory(inventory);
    }


    @EventHandler
    public void onEvent(InventoryClickEvent e) {
        if (e.getInventory().equals(this.inventory)) {
            e.setCancelled(true);
            if (e.getRawSlot() == 4) {
                this.player.doLevelUp();
            }
        }
    }

}
