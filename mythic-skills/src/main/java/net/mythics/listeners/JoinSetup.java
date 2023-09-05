package net.mythics.listeners;

import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.mythics.utils.Stats;

public class JoinSetup implements Listener{


    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        FileConfiguration stats = Stats.getStats();

        if(!player.hasPlayedBefore()){

            Attributable playerAttributable = player;
            AttributeInstance attributeAttack = playerAttributable.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
            AttributeInstance attributeAgility = playerAttributable.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
            AttributeInstance attributeResistance = playerAttributable.getAttribute(Attribute.GENERIC_MAX_HEALTH);

            attributeAttack.setBaseValue(1);
            attributeAgility.setBaseValue(0.1);
            attributeResistance.setBaseValue(20);

            
            stats.set("Players."+player.getUniqueId()+".skills"+".strength", 0);
            stats.set("Players."+player.getUniqueId()+".skills"+".resistance", 0);
            stats.set("Players."+player.getUniqueId()+".skills"+".agility", 0);
            stats.set("Players."+player.getUniqueId()+".skills"+".attackspeed", 0);
            stats.set("Players."+player.getUniqueId()+".skills"+".toughness", 0);
            stats.set("Players."+player.getUniqueId()+".skills"+".orbs", 0);
            Stats.saveStats();
        }
    }
    
}
