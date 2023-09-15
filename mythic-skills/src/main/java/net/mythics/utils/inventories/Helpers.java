package net.mythics.utils.inventories;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.mythics.utils.Items;

public class Helpers {

    public static HashMap<ItemStack, Integer> expOrbs = new HashMap<ItemStack, Integer>();
    public static HashMap<Player, String> managerMessages = new HashMap<Player, String>();
    public static HashMap<Player, String> managerStatsSetup = new HashMap<Player, String>();
    public static HashMap<Player, Player> targetPlayer = new HashMap<Player, Player>();

    public static void setUpHashMap(){
        expOrbs.put(Items.expOrbSmall(), 1);
        expOrbs.put(Items.expOrbMedium(), 5);
        expOrbs.put(Items.expOrbLarge(), 10);
        expOrbs.put(Items.expOrbMega(), 20);
    }

    public static void removeOrb(ItemStack itemStack){

        int amount = itemStack.getAmount();
        if(amount == 1){
            itemStack.setAmount(0);
        }

        if(amount > 1){
            itemStack.setAmount(amount-1);
        }

    }


    public static boolean leveledUp(int previousLevel, int newLevel){
        if(previousLevel != newLevel){
            return true;
        }

        return false;
    }

    public static boolean playerIsEditing(Player player){
        if(Helpers.managerMessages.containsKey(Helpers.targetPlayer.get(player))){
            return true;
        }
        return false;
    }

    public static boolean isNumber(String input){
        try{
            Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }


    public static boolean hasOrbs(int orbs, int inputOrbs){

        if(orbs >= inputOrbs){
            return true;
        }
        
        return false;
        
    }

    public static double improvementLevel(int differenceBetweenLevel, double amount){
        return (Double.valueOf(differenceBetweenLevel))*amount;
    }

    public static String prestigeList(List<String> list){
        StringBuilder newString = new StringBuilder();
        newString.append("&8[");
        for(String i : list){
            switch(i){
                case "strength":
                    newString.append("&c★");
                    break;
                case "resistance":
                    newString.append("&a★");
                    break;
                case "agility":
                    newString.append("&b★");
                    break;
                case "attack_speed":
                    newString.append("&4★");
                    break;
                case "toughness":
                    newString.append("&9★");
                    break;
          }
        }
        newString.append("&8]");
        return newString.toString();
    }
}
