package net.mythics.utils.statsinventories.items;


import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class GeneralItems {
    

    public static ItemStack voidConfirmGlass(){
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lCONFIRMAR"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack voidCancelGlass(){
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lCANCELAR"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack cancelBarrier(){
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lCANCELAR"));
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack addExpItem(){
        ItemStack item = new ItemStack(Material.SLIME_BALL);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fAñadir experiencia &a(\u25B2)"));

        meta.setCustomModelData(1010);

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack removeExpItem(){
        ItemStack item = new ItemStack(Material.SLIME_BALL);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRemover experiencia &c(\u25BC)"));

        meta.setCustomModelData(1011);

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack resetExpItem(){
        ItemStack item = new ItemStack(Material.SLIME_BALL);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fResetear experiencia &9(\u25AC)"));

        meta.setCustomModelData(1012);

        item.setItemMeta(meta);

        return item;
    }
}
