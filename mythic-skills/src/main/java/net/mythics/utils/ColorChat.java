package net.mythics.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.md_5.bungee.api.ChatColor;

public class ColorChat {

    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String formatHex(String msg){
        Matcher match = pattern.matcher(msg);
        while(match.find()){
            String color = msg.substring(match.start(), match.end());
            msg = msg.replace(color, ChatColor.of(color)+ "");
            match = pattern.matcher(msg);
        }

        return ChatColor.translateAlternateColorCodes('&',msg);
    }


    
}
