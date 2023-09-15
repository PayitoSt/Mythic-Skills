package net.mythics.utils.statsinventories.generalmenuplayer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.Classes;
import net.mythics.utils.Items;
import net.mythics.utils.ymls.Stats;

public class ClassesMenu implements Listener {
    public static void createClasseInventory(Player player){
        Inventory inventory = Bukkit.createInventory(null,27,ChatColor.translateAlternateColorCodes('&', "&d&lCLASES"));
        for(int i=18;i<27;i++){
            inventory.setItem(i, Items.voidSpaceGlass());
        }
        for(int i=0;i<9;i++){
            inventory.setItem(i, Items.voidSpaceGlass());
        }

        inventory.setItem(10, Items.voidSpaceGlass());
        inventory.setItem(11, Items.voidSpaceGlass());
        inventory.setItem(12, Items.voidSpaceGlass());
        inventory.setItem(14, Items.voidSpaceGlass());
        inventory.setItem(15, Items.voidSpaceGlass());   
        inventory.setItem(16, Items.voidSpaceGlass());

        inventory.setItem(9, bloodVampire());
        inventory.setItem(13, helmetKnight());

        player.openInventory(inventory);
        return;
    
    }

    @EventHandler
    public void clickAddInventory(InventoryClickEvent event){
        String originalName = ChatColor.translateAlternateColorCodes('&', "&d&lCLASES");
        String strippedName = ChatColor.stripColor(originalName);
        Player player = (Player)event.getWhoClicked();
        if(!(ChatColor.stripColor(event.getView().getTitle()).equals(strippedName))){
            return;
        }

        if(event.getCurrentItem() == null || event.getSlotType() == null || event.getCurrentItem().getType() == Material.AIR){
            event.setCancelled(true);
            return;
        }

        if(!event.getCurrentItem().hasItemMeta()){
            event.setCancelled(true);
            return;
        }

        event.setCancelled(true);

        FileConfiguration stats = Stats.getStats();
        int slot = event.getSlot();

        String path = "Players."+player.getUniqueId()+".skills"+".class";
        if(stats.contains(path)){
            player.closeInventory();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &7Ya tienes una clase definida."));
            return;
        }
        if(slot == 9){
            if(!(Classes.canSelectClass(player, "vampire"))){
                player.closeInventory();

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &7No cumples los requisitos. &8[&c\u0394&8]"));
                return;
            }
            player.closeInventory();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &7Ahora perteneces a la clase &cVampiro&7."));
            stats.set("Players."+player.getUniqueId()+".skills"+".class", "vampire");
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c&lMYTH&8] &c¡El jugador &f"+player.getDisplayName()+" &cahora es clase &4&lVAMPIRO&c!"));
            for(Player players : Bukkit.getServer().getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.ENTITY_WITHER_SPAWN, 2, 2);
            }
            Stats.saveStats();
            return;
        }

        if(slot == 13){
            if(!(Classes.canSelectClass(player, "knight"))){
                player.closeInventory();

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &7No cumples los requisitos. &8[&6\u2666&8]"));
                return;
            }
            player.closeInventory();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &7Ahora perteneces a la clase &6Caballero&7."));
            stats.set("Players."+player.getUniqueId()+".skills"+".class", "knight");
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a&lMYTH&8] &f¡El jugador &c"+player.getDisplayName()+" &fahora es clase &6&lCABALLERO&f!"));
            for(Player players : Bukkit.getServer().getOnlinePlayers()){
                players.playSound(players.getLocation(), Sound.ENTITY_WITHER_SPAWN, 2, 2);
            }
            Stats.saveStats();
            return;
        }

    }


    private static ItemStack bloodVampire(){
        ItemStack item = new ItemStack(Material.REDSTONE);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&c&lVAMPIRO"));
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Se necesita prestigio en agilidad y fuerza."));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Esta clase es incompatible con el prestigio de resistencia y dureza."));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    private static ItemStack helmetKnight(){
        ItemStack item = new ItemStack(Material.GOLDEN_HELMET);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lCABALLERO"));
        List<String> lore = new ArrayList<String>();

        lore.add(ChatColor.translateAlternateColorCodes('&', "&8» &7Se necesita prestigio en todas las habilidades."));

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }
}
