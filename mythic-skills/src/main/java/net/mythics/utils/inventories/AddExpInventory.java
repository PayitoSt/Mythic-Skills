package net.mythics.utils.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.Items;
import net.mythics.utils.Stats;

public class AddExpInventory implements Listener{


    public static void createInventory(Player player){
        Inventory inventory = Bukkit.createInventory(null,27,ChatColor.translateAlternateColorCodes('&', "&d&lMEJORA DE HABILIDADES"));

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
    public void clickInventory(InventoryClickEvent event){
        String originalName = ChatColor.translateAlternateColorCodes('&', "&d&lMEJORA DE HABILIDADES");
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

            ItemStack itemInHand = player.getInventory().getItemInMainHand();

            FileConfiguration stats = Stats.getStats();
            int strength = stats.getInt("Players."+player.getUniqueId()+".skills"+".strength");
            int resistance = stats.getInt("Players."+player.getUniqueId()+".skills"+".resistance");
            int agility = stats.getInt("Players."+player.getUniqueId()+".skills"+".agility");

            int expToGive = Helpers.expOrbs.get(itemInHand);
            switch(slot){
                case 9:
                    int previousLevelStrength = Items.generalLevel(strength);
                    Helpers.removeOrb(itemInHand);
                    stats.set("Players."+player.getUniqueId()+".skills"+".strength", strength+expToGive);
                    Stats.saveStats();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Ahora tienes &c"+stats.getInt("Players."+player.getUniqueId()+".skills"+".strength")+"&7 puntos de exp en fuerza."));
                    player.closeInventory();
                    int newLevelStrength = Items.generalLevel(stats.getInt("Players."+player.getUniqueId()+".skills"+".strength"));
                    
                    if(Helpers.leveledUp(previousLevelStrength, newLevelStrength)){
                        Attributable playerAttributable = player;
                        AttributeInstance attributeAttack = playerAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                        attributeAttack.setBaseValue(playerAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()+0.1);
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &7El jugador &d"+player.getDisplayName()+" &7subió a nivel &d"+newLevelStrength+" &7en &cFuerza."));
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, null, 1.0f, 1.0f);
                    }
                    break;
                case 11:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Próximamente..."));
                    player.closeInventory();
                    break;
                case 13:
                    int previousLevelResistance = Items.generalLevel(resistance);
                    Helpers.removeOrb(itemInHand);
                    stats.set("Players."+player.getUniqueId()+".skills"+".resistance", resistance+expToGive);
                    Stats.saveStats();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Ahora tienes &6"+stats.getInt("Players."+player.getUniqueId()+".skills"+".resistance")+"&7 puntos de exp en resistencia."));
                    player.closeInventory();

                    int newLevelResistance = Items.generalLevel(stats.getInt("Players."+player.getUniqueId()+".skills"+".resistance"));

                    if(Helpers.leveledUp(previousLevelResistance, newLevelResistance)){
                        Attributable playerAttributable = player;
                        AttributeInstance attributeAttack = playerAttributable.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                        attributeAttack.setBaseValue(playerAttributable.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()+2.0);
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &7El jugador &d"+player.getDisplayName()+" &7subió a nivel &d"+newLevelResistance+" &7en &aResistencia."));
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, null, 1.0f, 1.0f);
                    }
                    break;
                case 15:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Próximamente..."));
                    player.closeInventory();
                    break;
                case 17:
                    int previousLevelAgility = Items.generalLevel(agility);
                    Helpers.removeOrb(itemInHand);
                    stats.set("Players."+player.getUniqueId()+".skills"+".agility", agility+expToGive);
                    Stats.saveStats();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Ahora tienes &3"+stats.getInt("Players."+player.getUniqueId()+".skills"+".agility")+"&7 puntos de exp en agilidad."));
                    
                    player.closeInventory();

                    int newLevelAgility = Items.generalLevel(stats.getInt("Players."+player.getUniqueId()+".skills"+".agility"));
                    if(Helpers.leveledUp(previousLevelAgility, newLevelAgility)){
                        Attributable playerAttributable = player;
                        AttributeInstance attributeAttack = playerAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                        attributeAttack.setBaseValue(playerAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue()+0.005);
                        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &7El jugador &d"+player.getDisplayName()+" &7subió a nivel &d"+newLevelAgility+" &7en &3Agilidad."));
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, null, 1.0f, 1.0f);
                    }
                    break;
            }
            
        }

    }

    
    
}
