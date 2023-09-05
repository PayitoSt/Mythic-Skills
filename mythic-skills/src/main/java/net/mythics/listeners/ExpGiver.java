package net.mythics.listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.mythics.utils.inventories.AddExpInventory;
import net.mythics.utils.inventories.Helpers;

public class ExpGiver implements Listener {


    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        

        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
            Player player = event.getPlayer();


            for(ItemStack i : Helpers.expOrbs.keySet()){
                if(player.getInventory().getItemInMainHand().isSimilar(i)){
                    AddExpInventory.createInventory(player);
                }
            }

            

        }

        
    }
    
}
