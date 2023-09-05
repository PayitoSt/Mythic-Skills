package net.mythics.utils.statsinventories.generalmenuplayer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.Items;
import net.mythics.utils.Stats;
import net.mythics.utils.inventories.Helpers;

public class GeneralMenuPlayer implements Listener{
    public static void createInventory(Player player){
        Inventory inventory = Bukkit.createInventory(null,27,ChatColor.translateAlternateColorCodes('&', "&d&lHABILIDADES DE JUGADOR"));

        inventory.setItem(9, Items.strengthItem(player));
        inventory.setItem(11, Items.attackVelocityItem(player));
        inventory.setItem(13, Items.enduranceItem(player));
        inventory.setItem(15, Items.toughnessItem(player));
        inventory.setItem(17, Items.agilityItem(player));

        inventory.setItem(4, Items.playerHead(player));
        
        inventory.setItem(0, Items.voidSpaceGlass());
        inventory.setItem(1, Items.voidSpaceGlass());
        inventory.setItem(2, Items.voidSpaceGlass());
        inventory.setItem(3, Items.voidSpaceGlass());
        inventory.setItem(5, Items.voidSpaceGlass());
        inventory.setItem(6, Items.voidSpaceGlass());
        inventory.setItem(7, Items.voidSpaceGlass());
        inventory.setItem(8, Items.voidSpaceGlass());
        inventory.setItem(10, Items.voidSpaceGlass());
        inventory.setItem(12, Items.voidSpaceGlass());
        inventory.setItem(14, Items.voidSpaceGlass());  
        inventory.setItem(16, Items.voidSpaceGlass());

        for(int i=18;i<27;i++){
            inventory.setItem(i, Items.voidSpaceGlass());
        }

        player.openInventory(inventory);
        return;


        
    }


    @EventHandler
    public void onClickGeneralMenuPlayer(InventoryClickEvent event){
        String originalName = ChatColor.translateAlternateColorCodes('&', "&d&lHABILIDADES DE JUGADOR");
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
            FileConfiguration stats = Stats.getStats();
            int orbs = stats.getInt("Players."+player.getUniqueId()+".skills"+".orbs");
            if(orbs == 0){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fNo tienes orbes disponibles."));
                return;
            }
            switch(slot){
                case 9:
                    player.closeInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fEscribe la cantidad de puntos que deseas añadir a tu fuerza."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &fEscribe &cCANCELAR &fpara salir del proceso."));
                    Helpers.managerMessages.put(player, null);
                    Helpers.managerStatsSetup.put(player, "strength");
                    event.setCancelled(true);
                    break;
                case 13:
                    player.closeInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fEscribe la cantidad de puntos que deseas añadir a tu resistencia."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &fEscribe &cCANCELAR &fpara salir del proceso."));
                    Helpers.managerMessages.put(player, null);
                    Helpers.managerStatsSetup.put(player, "resistance");
                    event.setCancelled(true);
                    break;
                case 17:
                    player.closeInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fEscribe la cantidad de puntos que deseas añadir a tu agilidad."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &fEscribe &cCANCELAR &fpara salir del proceso."));
                    Helpers.managerMessages.put(player, null);
                    Helpers.managerStatsSetup.put(player, "agility");
                    event.setCancelled(true);
                    break;
            }
        }
    }
}
