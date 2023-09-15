package net.mythics.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.mythics.utils.ymls.Stats;

public class RPGClasses implements Listener{

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player)){
            return;
        }

        FileConfiguration stats = Stats.getStats();
        Player player = (Player)event.getDamager();
        String path = "Players."+player.getUniqueId()+".skills"+".class";
        if(!(stats.contains(path))){
            return;
        }

        if(!(stats.getString(path).equalsIgnoreCase("vampire"))){
            return;
        }

        double currentHealth = player.getHealth();

        Random randomNumber = new Random();
        double randomNumberDouble = randomNumber.nextDouble();
        if(randomNumberDouble<0.5){
            return;
        }

        double operation = currentHealth+0.5;

        if(((operation) > player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue())){
            return;
        }else{
            player.setHealth(currentHealth+0.5);
        }

    }
    
}
