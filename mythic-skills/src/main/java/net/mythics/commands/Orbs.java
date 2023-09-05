package net.mythics.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.Stats;

public class Orbs implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return true;
        }

        Player player = (Player) sender;

        FileConfiguration stats = Stats.getStats();
        int playerOrbs = stats.getInt("Players."+player.getUniqueId()+".skills"+".orbs");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &fTus orbes actuales son &6"+playerOrbs+"&f."));
        return true;
    }
    
}
