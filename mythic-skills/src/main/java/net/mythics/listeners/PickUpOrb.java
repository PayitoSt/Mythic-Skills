package net.mythics.listeners;



import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.inventories.Helpers;
import net.mythics.utils.ymls.Stats;

public class PickUpOrb implements Listener{


    @EventHandler
    public void onPickUpOrb(EntityPickupItemEvent event){

        FileConfiguration stats = Stats.getStats();
        if(!(event.getEntity() instanceof Player)){
            return;
        }

        Player player = (Player)event.getEntity();
        ItemStack item = event.getItem().getItemStack();
        int amount = item.getAmount();

        for(ItemStack i : Helpers.expOrbs.keySet()){
            if(item.isSimilar(i)){
                int tokens = stats.getInt("Players."+player.getUniqueId()+".skills"+".orbs");
                int newAmount = tokens+(Helpers.expOrbs.get(i)*amount);
                stats.set("Players."+player.getUniqueId()+".skills"+".orbs", newAmount);
                Stats.saveStats();
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Obtuviste &6"+Helpers.expOrbs.get(i)+"(x"+amount+")&7 orbes ancestrales&7. &6("+newAmount+")"));
                event.setCancelled(true);
                event.getItem().remove();
            }
        }


    }
    
}
