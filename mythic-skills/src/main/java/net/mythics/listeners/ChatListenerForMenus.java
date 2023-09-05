package net.mythics.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.Items;
import net.mythics.utils.Stats;
import net.mythics.utils.inventories.Helpers;
import net.mythics.utils.statsinventories.generalmenumanager.GeneralMenuManager;

public class ChatListenerForMenus implements Listener{


    @EventHandler
    public void onChatWhileChangingStats(AsyncPlayerChatEvent event){


        Player player = event.getPlayer();
        FileConfiguration stats = Stats.getStats();
        String message = event.getMessage();
        if(!(Helpers.managerMessages.containsKey(player))){

            event.setCancelled(true);
            String path = "Players."+player.getUniqueId()+".skills"+".prestiges";
            if(!(stats.contains(path))){
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&7"+player.getDisplayName()+" &8» &f"+message));
                return;
            }

            
            List<String> prestiges = stats.getStringList(path);
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Helpers.prestigeList(prestiges)+"&7 "+player.getDisplayName()+" &8» &f"+message));
            return;
        }

        
        Helpers.managerMessages.put(player, message);

        event.setCancelled(true);


        if(Helpers.isNumber(message)){

            Player targetPlayer = Helpers.targetPlayer.get(player);


            
            
            String finalString = null;

            switch(Helpers.managerStatsSetup.get(player)){
                case "strength":
                    finalString = "fuerza";
                    break;
                case "resistance":
                    finalString = "resistencia";
                    break;
                case "agility":
                    finalString = "agilidad";
                    break;
                case "orbs":
                    finalString = "orbes";
                    break;
            }

            /*
             * MODO JUGADOR
             */
            if(!(player.isOp())){
                int orbs = stats.getInt("Players."+player.getUniqueId()+".skills"+".orbs");
                int inputOrbs = Integer.valueOf(message);
                int previousStats = stats.getInt("Players."+player.getUniqueId()+".skills"+"."+Helpers.managerStatsSetup.get(player));

                /*
                 * Detecta si el jugador tiene los orbes que ingresó.
                 */

                int integerPath = stats.getInt("Players."+player.getUniqueId()+".skills"+"."+Helpers.managerStatsSetup.get(player));
                int previousLevel = Items.generalLevel(integerPath);

                if(orbs < inputOrbs){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fNo tienes suficientes orbes."));
                    Helpers.managerMessages.remove(player);
                    Helpers.managerStatsSetup.remove(player);
                    return;
                }
                stats.set("Players."+player.getUniqueId()+".skills"+"."+Helpers.managerStatsSetup.get(player), previousStats+Integer.valueOf(message));
                stats.set("Players."+player.getUniqueId()+".skills"+".orbs", orbs-inputOrbs);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fAgregaste &6"+message+" &fpuntos de &6"+finalString+"&f."));
                Stats.saveStats();
                int newIntegerPath = stats.getInt("Players."+player.getUniqueId()+".skills"+"."+Helpers.managerStatsSetup.get(player));
                int newLevel = Items.generalLevel(newIntegerPath);

                if(integerPath >= 2000){
                    if(!(stats.contains("Players."+player.getUniqueId()+".skills"+".prestiges"))){
                        String[] prestige = {Helpers.managerStatsSetup.get(player)};
                        stats.set("Players."+player.getUniqueId()+".skills"+".prestiges", prestige);
                        Stats.saveStats();
                    }

                    List<String> checkPrestiges = stats.getStringList("Players."+player.getUniqueId()+".skills"+".prestiges");

                    checkPrestiges.add(Helpers.managerStatsSetup.get(player));
                    stats.set("Players."+player.getUniqueId()+".skills"+".prestiges", checkPrestiges);
                    Stats.saveStats();

                }


                if(Helpers.leveledUp(previousLevel, newLevel)){
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &7El jugador &d"+player.getDisplayName()+" &7subió a nivel &d"+newLevel+" &7en &a"+finalString+"&7."));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, null, 1.0f, 1.0f);
                    
                    Attributable playerAttributable = player;
                    AttributeInstance attributeInstance = null;
                    int differenceBetweenLevel = newLevel - previousLevel;
                    switch(Helpers.managerStatsSetup.get(player)){
                        case "strength":
                            attributeInstance = playerAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                            attributeInstance.setBaseValue(attributeInstance.getValue()+Helpers.improvementLevel(differenceBetweenLevel, 0.2));
                            break;
                        case "resistance":
                            attributeInstance = playerAttributable.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                            attributeInstance.setBaseValue(attributeInstance.getValue()+Helpers.improvementLevel(differenceBetweenLevel, 2.0));
                            break;
                        case "agility":
                            attributeInstance = playerAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                            attributeInstance.setBaseValue(attributeInstance.getValue()+Helpers.improvementLevel(differenceBetweenLevel, 0.005));
                            break;
                        default:
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &7El jugador &d"+player.getDisplayName()+" &7subió a nivel &d"+newLevel+" &7en &a"+finalString+"&7."));
                            break;
                    }
                }

                Helpers.managerMessages.remove(player);
                Helpers.managerStatsSetup.remove(player);
                return;
            }   

            /*
             * MODO ADMINISTRADOR
             */
            
            int previousStats = stats.getInt("Players."+targetPlayer.getUniqueId()+".skills"+"."+Helpers.managerStatsSetup.get(player));
            int previousLevel = Items.generalLevel(previousStats);
            
            
            int integerPath = stats.getInt("Players."+player.getUniqueId()+".skills"+"."+Helpers.managerStatsSetup.get(player));
            stats.set("Players."+targetPlayer.getUniqueId()+".skills"+"."+Helpers.managerStatsSetup.get(player), (previousStats+Integer.valueOf(message)));
            
            Stats.saveStats();
            
            int newIntegerPath = stats.getInt("Players."+targetPlayer.getUniqueId()+".skills"+"."+Helpers.managerStatsSetup.get(player));
            int newLevel = Items.generalLevel(newIntegerPath);


            if(integerPath >= 2000){
                    if(!(stats.contains("Players."+player.getUniqueId()+".skills"+".prestiges"))){
                        String[] prestige = {Helpers.managerStatsSetup.get(player)};
                        stats.set("Players."+player.getUniqueId()+".skills"+".prestiges", prestige);
                        Stats.saveStats();
                    }

                    List<String> checkPrestiges = stats.getStringList("Players."+player.getUniqueId()+".skills"+".prestiges");

                    checkPrestiges.add(Helpers.managerStatsSetup.get(player));
                    stats.set("Players."+player.getUniqueId()+".skills"+".prestiges", checkPrestiges);
                    Stats.saveStats();

                }
            if(Helpers.leveledUp(previousLevel, newLevel)){
                    Bukkit.broadcastMessage(String.valueOf(previousLevel));
                    Bukkit.broadcastMessage(String.valueOf(newLevel));
                    Attributable playerAttributable = targetPlayer;
                    AttributeInstance attributeInstance = null;
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &7El jugador &d"+player.getDisplayName()+" &7subió a nivel &d"+newLevel+" &7en &a"+finalString+"&7."));
                    targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, null, 1.0f, 1.0f);
                    int differenceBetweenLevel = newLevel - previousLevel;
                    switch(Helpers.managerStatsSetup.get(player)){
                        case "strength":
                            attributeInstance = playerAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                            attributeInstance.setBaseValue(attributeInstance.getValue()+Helpers.improvementLevel(differenceBetweenLevel, 0.2));
                            break;
                        case "resistance":
                            attributeInstance = playerAttributable.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                            attributeInstance.setBaseValue(attributeInstance.getValue()+Helpers.improvementLevel(differenceBetweenLevel, 2.0));
                            break;
                        case "agility":
                            attributeInstance = playerAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                            attributeInstance.setBaseValue(attributeInstance.getValue()+Helpers.improvementLevel(differenceBetweenLevel, 0.005));
                            break;
                    }
                }
            Helpers.managerMessages.remove(player);
            Helpers.managerStatsSetup.remove(player);
            if(finalString == "orbes"){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fAgregaste &6"+message+" "+finalString+"&f."));
                return;
            }


            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fAgregaste &6"+message+" &fpuntos de &6"+finalString+"&f."));
            return;
        }

        if(message.equalsIgnoreCase("cancelar") || message.equalsIgnoreCase("cancel")){
            Helpers.managerMessages.remove(player);
            Helpers.managerStatsSetup.remove(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fProceso cancelado."));
            GeneralMenuManager.createInventory(player, Helpers.targetPlayer.get(player));
        }




    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        if(Helpers.managerMessages.containsKey(event.getPlayer())){
            Helpers.managerMessages.remove(event.getPlayer());
            Helpers.managerStatsSetup.remove(event.getPlayer());
        }
    }
    
}
