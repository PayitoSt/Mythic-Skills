package net.mythics.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.Stats;
import net.mythics.utils.inventories.Helpers;

public class Transfer implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if(!(sender instanceof Player)){
            return true;
        }

        Player player = (Player) sender;

        if(args.length <= 0){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fEl uso correcto es &6/transfer <jugador> <cantidad de orbes>"));
            return true;
        }

        String playerName = args[0];
        Player playerExact = Bukkit.getPlayerExact(playerName);

        if(playerExact == null){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7Ese jugador no está conectado."));
            return true;
        }

        if(args.length == 1){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fEl uso correcto es &6/transfer <jugador> <cantidad de orbes>"));
            return true;
        }

        if(args[1] == null){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fEl uso correcto es &6/transfer <jugador> <cantidad de orbes>"));
            return true;
        }

        if(!(Helpers.isNumber(args[1]))){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&6Mythic&8] &fIngresa un número entero válido."));
            return true;
        }

        int amountOrbs = Integer.valueOf(args[1]);

        FileConfiguration stats = Stats.getStats();

        int playerOrbs = stats.getInt("Players."+player.getUniqueId()+".skills"+".orbs");
        int playerTargetOrbs = stats.getInt("Players."+playerExact.getUniqueId()+".skills"+".orbs");

        if(playerOrbs >= amountOrbs){

            if(player == playerExact){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &7No puedes enviarte orbes a ti mismo."));
                return true;
            }
            stats.set("Players."+player.getUniqueId()+".skills"+".orbs", playerOrbs-amountOrbs);
            stats.set("Players."+playerExact.getUniqueId()+".skills"+".orbs", playerTargetOrbs+amountOrbs);

            Stats.saveStats();

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &fEnviaste &6"+amountOrbs+" &forbes al jugador &6"+playerExact.getDisplayName()+"&f."));
            playerExact.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8\u00BB &fRecibiste &6"+amountOrbs+" &forbes del jugador &6"+player.getDisplayName()+"&f."));
            return true;
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8» &fNo tienes suficientes orbes"));

        
        return true;
    }
    
}
