package net.mythics.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.ymls.Stats;

public class Manager implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if(!(sender instanceof Player)){
            Stats.reloadStats();
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &dConfig reiniciada."));
            return true;
        }
        Player player = (Player)sender;
        if(!player.hasPermission("mythic.admin")){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &dUso incorrecto."));
            return true;
        }


        Stats.reloadStats();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &dEstadísticas reiniciadas."));
        return true;        
    }
    
}
