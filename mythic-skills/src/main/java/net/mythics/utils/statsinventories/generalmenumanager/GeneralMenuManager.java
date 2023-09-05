package net.mythics.utils.statsinventories.generalmenumanager;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.Items;
import net.mythics.utils.inventories.Helpers;
import net.mythics.utils.statsinventories.items.GeneralItems;

public class GeneralMenuManager implements Listener{

    public static void createInventory(Player player, Player targetPlayer){
        Inventory inventory = Bukkit.createInventory(null,45,ChatColor.translateAlternateColorCodes('&', "&d&lMODIFICAR HABILIDADES"));
    
        //Cabeza jugador
        inventory.setItem(4, Items.playerHead(targetPlayer));

        //Agregar orbes a una estadistica
        inventory.setItem(20, GeneralItems.addExpItem());

        //Resetear Stats
        inventory.setItem(22, GeneralItems.resetExpItem());

        //Eliminar orbes de una estadística
        inventory.setItem(24, GeneralItems.removeExpItem());

        //Cerrar inventario
        inventory.setItem(40, GeneralItems.cancelBarrier());
    
        player.openInventory(inventory);

        Helpers.targetPlayer.put(player, targetPlayer);
        return;
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent event){
        String originalName = ChatColor.translateAlternateColorCodes('&', "&d&lMODIFICAR HABILIDADES");
        String strippedName = ChatColor.stripColor(originalName);
        Player player = (Player) event.getWhoClicked();
        if(ChatColor.stripColor(event.getView().getTitle()).equals(strippedName)){
            if(event.getCurrentItem() == null || event.getSlotType() == null || event.getCurrentItem().getType() == Material.AIR){
                event.setCancelled(true);
                return;
            }

            if(!event.getCurrentItem().hasItemMeta()){
                event.setCancelled(true);
                return;
            }

            event.setCancelled(true);

            int slot = event.getSlot();
            switch(slot){
                case 20:
                    GeneralMenus.createAddInventory(player, Helpers.targetPlayer.get(player));
                    break;
                case 22:
                    GeneralMenus.createResetInventory(player, Helpers.targetPlayer.get(player));
                    break;
                case 24:
                    GeneralMenus.createRemoveInventory(player, Helpers.targetPlayer.get(player));
                    break;
                case 40:
                    player.closeInventory();
            }
        }

    }
}
