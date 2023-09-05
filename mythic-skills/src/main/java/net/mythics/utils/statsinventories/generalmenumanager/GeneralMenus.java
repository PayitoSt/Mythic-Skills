package net.mythics.utils.statsinventories.generalmenumanager;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
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
import net.mythics.utils.statsinventories.items.GeneralItems;

public class GeneralMenus implements Listener{

    public static void createResetInventory(Player player, Player targetPlayer){
        Inventory inventory = Bukkit.createInventory(null,9,ChatColor.translateAlternateColorCodes('&', "&d&lRESETEAR HABILIDADES"));
        for(int i=0;i<4;i++){
            inventory.setItem(i, GeneralItems.voidConfirmGlass());
        }

        for(int i=5;i<9;i++){
            inventory.setItem(i, GeneralItems.voidCancelGlass());
        }

        inventory.setItem(4, GeneralItems.cancelBarrier());

        player.openInventory(inventory);
        return;
    }

    @EventHandler
    public void clickResetInventory(InventoryClickEvent event){
        FileConfiguration stats = Stats.getStats();
        String originalName = ChatColor.translateAlternateColorCodes('&', "&d&lRESETEAR HABILIDADES");
        String strippedName = ChatColor.stripColor(originalName);
        Player player = (Player) event.getWhoClicked();
        Player targetPlayer = Helpers.targetPlayer.get(player);
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
            if(slot == 0 || slot == 1 || slot == 2 || slot == 3){
                Attributable playerAttributable = targetPlayer;
                AttributeInstance attributeAttack = playerAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                AttributeInstance attributeAgility = playerAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                AttributeInstance attributeResistance = playerAttributable.getAttribute(Attribute.GENERIC_MAX_HEALTH);

                attributeAttack.setBaseValue(1);
                attributeAgility.setBaseValue(0.1);
                attributeResistance.setBaseValue(20);


                String[] prestiges = {"strength", "resistance", "agility","attackspeed","toughness"};
                stats.set("Players."+player.getUniqueId()+".skills"+".strength", 0);
                // stats.set("Players."+player.getUniqueId()+".skills"+".strength"+".prestige", false);
                stats.set("Players."+player.getUniqueId()+".skills"+".resistance", 0);
                // stats.set("Players."+player.getUniqueId()+".skills"+".resistance"+".prestige", false);
                stats.set("Players."+player.getUniqueId()+".skills"+".agility", 0);
                // stats.set("Players."+player.getUniqueId()+".skills"+".agility"+".prestige", false);
                stats.set("Players."+player.getUniqueId()+".skills"+".attackspeed", 0);
                // stats.set("Players."+player.getUniqueId()+".skills"+".attackspeed"+".prestige", false);
                stats.set("Players."+player.getUniqueId()+".skills"+".toughness", 0);
                // stats.set("Players."+player.getUniqueId()+".skills"+".toughness"+".prestige", false);
                stats.set("Players."+player.getUniqueId()+".skills"+".orbs", 0);
                stats.set("Players."+player.getUniqueId()+".skills"+".prestiges", prestiges);
                Stats.saveStats();
                GeneralMenuManager.createInventory(player, targetPlayer);
                return;
            }

            if(slot == 5 || slot == 6 || slot == 7 || slot == 8){
                GeneralMenuManager.createInventory(player, targetPlayer);
                return;
            }
            if(slot == 4){
                player.closeInventory();
                return;
            }
        }

    }
    public static void createAddInventory(Player player, Player targetPlayer){
        Inventory inventory = Bukkit.createInventory(null,27,ChatColor.translateAlternateColorCodes('&', "&d&lAGREGAR ESTADISTICAS"));

        inventory.setItem(9, Items.strengthItem(targetPlayer));
        inventory.setItem(11, Items.attackVelocityItem(targetPlayer));
        inventory.setItem(13, Items.enduranceItem(targetPlayer));
        inventory.setItem(15, Items.toughnessItem(targetPlayer));
        inventory.setItem(17, Items.agilityItem(targetPlayer));

        inventory.setItem(4, Items.playerHead(targetPlayer));
        
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
    public void clickAddInventory(InventoryClickEvent event){
        String originalName = ChatColor.translateAlternateColorCodes('&', "&d&lAGREGAR ESTADISTICAS");
        String strippedName = ChatColor.stripColor(originalName);
        Player player = (Player) event.getWhoClicked();
        Player targetPlayer = Helpers.targetPlayer.get(player);
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
                case 9:
                    player.closeInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fEscribe la cantidad de puntos que deseas añadir a la fuerza del jugador &6"+targetPlayer.getDisplayName()+"&f."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &fEscribe &cCANCELAR &fpara salir del proceso."));
                    Helpers.managerMessages.put(player, null);
                    Helpers.managerStatsSetup.put(player, "strength");
                    event.setCancelled(true);
                    break;
                case 13:
                    player.closeInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fEscribe la cantidad de puntos que deseas añadir a la resistencia del jugador &6"+targetPlayer.getDisplayName()+"&f."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &fEscribe &cCANCELAR &fpara salir del proceso."));
                    Helpers.managerMessages.put(player, null);
                    Helpers.managerStatsSetup.put(player, "resistance");
                    event.setCancelled(true);
                    break;
                case 17:
                    player.closeInventory();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fEscribe la cantidad de puntos que deseas añadir a la agilidad del jugador &6"+targetPlayer.getDisplayName()+"&f."));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &fEscribe &cCANCELAR &fpara salir del proceso."));
                    Helpers.managerMessages.put(player, null);
                    Helpers.managerStatsSetup.put(player, "agility");
                    event.setCancelled(true);
                    break;
            }
        }

    }

    public static void createRemoveInventory(Player player, Player targetPlayer){
        Inventory inventory = Bukkit.createInventory(null,27,ChatColor.translateAlternateColorCodes('&', "&d&lREMOVER ESTADISTICAS"));

        inventory.setItem(9, Items.strengthItem(targetPlayer));
        inventory.setItem(11, Items.attackVelocityItem(targetPlayer));
        inventory.setItem(13, Items.enduranceItem(targetPlayer));
        inventory.setItem(15, Items.toughnessItem(targetPlayer));
        inventory.setItem(17, Items.agilityItem(targetPlayer));

        inventory.setItem(4, Items.playerHead(targetPlayer));
        
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





}
