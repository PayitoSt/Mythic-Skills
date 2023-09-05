package net.mythics.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.mythics.utils.statsinventories.generalmenumanager.GeneralMenuManager;
import net.mythics.utils.statsinventories.generalmenuplayer.GeneralMenuPlayer;

public class Levels implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            return true;
        }

        Player player = (Player) sender;
        if(player.hasPermission("mythic.admin")){

            if(args.length <= 0){
                GeneralMenuManager.createInventory(player, player);
                return true;
            }

            String playerName = args[0];
            Player playerEx = Bukkit.getPlayerExact(playerName);
            if(playerEx == null){
                ChatColor.translateAlternateColorCodes('&', "&8» &7Ese jugador no está conectado.");
                return true;
            }
            GeneralMenuManager.createInventory(player, playerEx);
            return true;
        }

        GeneralMenuPlayer.createInventory(player);
        return true;
    }


    
}
