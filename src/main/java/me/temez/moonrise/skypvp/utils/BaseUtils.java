package me.temez.moonrise.skypvp.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BaseUtils {
    public static ItemStack createIs(Material mat, String name, List<String> lore, Integer amount) {
        ItemStack is = new ItemStack(mat, amount);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(name);
        if (lore != null) {
            meta.setLore(lore);
        }
        is.setItemMeta(meta);
        return is;
    }

    public static boolean inRange(double a, double b, double c) {
        return c >= a && b >= c;
    }

    public static boolean containsInList(String s, List<String> l) {
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).contains(s)) {
                return true;
            }
        }
        return false;
    }

}
