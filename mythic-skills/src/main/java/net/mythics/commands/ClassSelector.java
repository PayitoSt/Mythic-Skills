package net.mythics.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.mythics.utils.statsinventories.generalmenuplayer.ClassesMenu;

public class ClassSelector implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if(!(sender instanceof Player)){
            return true;
        }

        Player player = (Player) sender;

        ClassesMenu.createClasseInventory(player);
        return true;
    }
    
}
