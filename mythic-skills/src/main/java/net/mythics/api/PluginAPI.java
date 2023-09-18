package net.mythics.api;

import org.bukkit.entity.Player;

import net.mythics.utils.inventories.Helpers;

public class PluginAPI {

    public int getPlayerLevel(Player player){
        return Helpers.generalLevelPlayer(player);
    }
    
    
}
