package net.mythics.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.ymls.Stats;

public class Items {

    public static int generalLevel(int exp){


        if(exp <=9){
            return 1;
        }else if(exp <=24){
            return 2;
        }else if(exp <= 49){
            return 3;
        }else if(exp <= 99){
            return 4;
        }else if(exp <= 174){
            return 5;
        }else if(exp <= 249){
            return 6;
        }else if(exp <= 399){
            return 7;
        }else if(exp <= 599){
            return 8;
        }else if(exp <= 899){
            return 9;
        }else{
            return 10;
        }
    }

    public static int levelStrength(Player player){
        FileConfiguration stats = Stats.getStats();

        int value = stats.getInt("Players."+player.getUniqueId()+".skills"+".strength");

        return generalLevel(value);
        
    }
    public static int levelAgility(Player player){
        FileConfiguration stats = Stats.getStats();

        int value = stats.getInt("Players."+player.getUniqueId()+".skills"+".agility");

        return generalLevel(value);
    }
    public static int levelResistance(Player player){
        FileConfiguration stats = Stats.getStats();

        int value = stats.getInt("Players."+player.getUniqueId()+".skills"+".resistance");

        return generalLevel(value);

    }

    private static int strength(Player player){
        FileConfiguration stats = Stats.getStats();
        return stats.getInt("Players."+player.getUniqueId()+".skills"+".strength");
    }

    private static int agility(Player player){
        FileConfiguration stats = Stats.getStats();
        return stats.getInt("Players."+player.getUniqueId()+".skills"+".agility");
    }

    private static int resistance(Player player){
        FileConfiguration stats = Stats.getStats();
        return stats.getInt("Players."+player.getUniqueId()+".skills"+".resistance");
    }


    public static ItemStack expOrbSmall(){
        ItemStack item = new ItemStack(Material.SLIME_BALL);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fOrbe de experiencia &6(pequeño)"));

        meta.setCustomModelData(1001);

        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Calidad: &fComún."));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Click derecho para reclamar."));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack expOrbMedium(){
        ItemStack item = new ItemStack(Material.SLIME_BALL);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fOrbe de experiencia &8(mediano)"));

        meta.setCustomModelData(1002);

        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Calidad: &8Inusual."));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Click derecho para reclamar."));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack expOrbLarge(){
        ItemStack item = new ItemStack(Material.SLIME_BALL);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fOrbe de experiencia &c(grande)"));

        meta.setCustomModelData(1003);

        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Calidad: &cRaro."));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Click derecho para reclamar."));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack expOrbMega(){
        ItemStack item = new ItemStack(Material.SLIME_BALL);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fOrbe de experiencia &d(mega)"));

        meta.setCustomModelData(1004);

        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Calidad: &dDesconocida."));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Click derecho para reclamar."));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack playerHead(Player player){
        FileConfiguration stats = Stats.getStats();
        int orbs = stats.getInt("Players."+player.getUniqueId()+".skills"+".orbs", 0);
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(player);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&d&lMYTH &8\u00BB &f"+player.getDisplayName()));
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Tu fuerza actual es de &c"+(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue())+" &7unidades. "+
        "&8(&6"+strength(player)+" EXP&8)"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Tu vida máxima actual es de &a"+(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())+" &7unidades. "+
        "&8(&6"+resistance(player)+" EXP&8)"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Tu agilidad actual es de &3"+(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue()*100)+" &7unidades. "+
        "&8(&6"+agility(player)+" EXP&8)"));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Tus orbes disponibles &6"+(orbs)+"&7."));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }   

    public static ItemStack agilityItem(Player player){

        ItemStack item = new ItemStack(Material.FEATHER);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&3Agilidad"));

        List<String> lore = new ArrayList<String>();

        NumberFormat formatter = new DecimalFormat("#0");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8[&7Lv."+levelAgility(player)+"&8] &8» &7Tu agilidad actual es de &3"+(formatter.format(player.getWalkSpeed()*100))+" &7unidades. "+
        "&8(&6"+agility(player)+" EXP&8)"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        
        return item;
    }


    public static ItemStack strengthItem(Player player){
        ItemStack item = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&cFuerza"));
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8[&7Lv."+levelStrength(player)+"&8] &8» &7Tu fuerza actual es de &c"+(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue())+" &7unidades. "+
        "&8(&6"+strength(player)+" EXP&8)"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack enduranceItem(Player player){
        ItemStack item = new ItemStack(Material.SHIELD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&aResistencia"));
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8[&7Lv."+levelResistance(player)+"&8] &8» &7Tu vida máxima actual es de &a"+(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())+" &7unidades. "+
        "&8(&6"+resistance(player)+" EXP&8)"));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack attackVelocityItem(Player player){
        ItemStack item = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&9Velocidad de ataque"));
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Tu velocidad de ataque actual es de &9"+(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getValue())+" &7unidades."));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&cPróximamente..."));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack toughnessItem(Player player){
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Dureza"));
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Tu dureza actual es de &6"+(player.getAttribute(Attribute.GENERIC_ARMOR).getValue())+" &7unidades."));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&cPróximamente..."));
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack voidSpaceGlass(){
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        return item;
    }
    
}
