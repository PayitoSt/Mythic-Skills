package net.mythics.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.mythics.utils.ymls.Settings;
import net.mythics.utils.ymls.Stats;

public class Classes{

    public static boolean canSelectClass(Player player, String selectedClass){

        FileConfiguration settings = Settings.getSettings();
        FileConfiguration stats = Stats.getStats();

        List<String> playerPrestiges = stats.getStringList("Players." + player.getUniqueId() + ".skills" + ".prestiges");
        if(!(stats.contains("Players." + player.getUniqueId() + ".skills" + ".prestiges"))){
            return false;
        }
        List<String> invalidPrestiges = settings.getStringList("Classes."+selectedClass+".invalid_prestiges");
        List<String> validPrestiges = settings.getStringList("Classes."+selectedClass+".required_prestiges");

        if(!(selectedClass.equalsIgnoreCase("knight"))){
            for(int i=0;i<invalidPrestiges.size();i++){
                if(playerPrestiges.contains(invalidPrestiges.get(i))){
                    return false;
                }
            }
        }
        int requiredAmountPrestiges = 0;
        int amountPrestiges = 0;
        for(int i=0;i<validPrestiges.size();i++){
            requiredAmountPrestiges++;
            if(playerPrestiges.contains(validPrestiges.get(i))){
                amountPrestiges++;
            }
        }

        if(amountPrestiges == requiredAmountPrestiges){
            return true;
        }
        return false;
    }
    
}
