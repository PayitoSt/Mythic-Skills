package net.mythics.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ItemsGet implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        if (args.length <= 0)
            return false;

        Player player = (Player) sender;
        if (!(player.isOp()))
            return false;

        if(args[0].equalsIgnoreCase("values")) {
            StringBuilder names = new StringBuilder();
            for(ItemsEnum itemGet : ItemsEnum.values()) {
                names.append(itemGet.name() + ", ");
            }
            player.sendMessage(names.toString().substring(0, names.length() - 2));
            return true;
        }

        try {
            ItemsEnum itemGet = ItemsEnum.valueOf(args[0].toUpperCase());
            player.getInventory().addItem(itemGet.getItem());

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &7Se ha otorgado un &d"+args[0].toUpperCase()+"&7."));
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lMYTH&8] &7Item desconocido."));
        }
        return true;
    }
    
}
