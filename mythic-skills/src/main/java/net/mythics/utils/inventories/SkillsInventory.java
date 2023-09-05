package net.mythics.utils.inventories;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.Items;

public class SkillsInventory implements Listener{


    public static void createInventory(Player player){
        Inventory inventory = Bukkit.createInventory(null,45,ChatColor.translateAlternateColorCodes('&', "&d&lHABILIDADES"));

        inventory.setItem(9, Items.strengthItem(player));
        inventory.setItem(11, Items.attackVelocityItem(player));
        inventory.setItem(13, Items.enduranceItem(player));
        inventory.setItem(15, Items.toughnessItem(player));
        inventory.setItem(17, Items.agilityItem(player));

        inventory.setItem(4, Items.playerHead(player));


        player.openInventory(inventory);
        return;
    }

    public static void createInventory(Player player, Player playerTarget){
        Inventory inventory = Bukkit.createInventory(null,45,ChatColor.translateAlternateColorCodes('&', "&d&lHABILIDADES"));

        inventory.setItem(9, Items.strengthItem(playerTarget));
        inventory.setItem(11, Items.attackVelocityItem(playerTarget));
        inventory.setItem(13, Items.enduranceItem(playerTarget));
        inventory.setItem(15, Items.toughnessItem(playerTarget));
        inventory.setItem(17, Items.agilityItem(playerTarget));

        inventory.setItem(4, Items.playerHead(playerTarget));


        player.openInventory(inventory);
        return;
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent event){
        String originalName = ChatColor.translateAlternateColorCodes('&', "&d&lHABILIDADES");
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
                case 9:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Tu fuerza actual es de &c"+(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue())+" &7unidades."));
                    player.closeInventory();
                    break;
                case 11:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Tu velocidad de ataque actual es de &9"+(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getValue())+" &7unidades."));
                    player.closeInventory();
                    break;
                case 13:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Tu dureza actual es de &6"+(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())+" &7unidades."));
                    player.closeInventory();
                    break;
                case 15:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Tu dureza actual es de &6"+(player.getAttribute(Attribute.GENERIC_ARMOR).getValue())+" &7unidades."));
                    player.closeInventory();
                    break;
                case 17:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Tu agilidad actual es de &3"+(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue()*100)+" &7unidades."));
                    player.closeInventory();
                    break;
            }
            
        }

    }
    
}
