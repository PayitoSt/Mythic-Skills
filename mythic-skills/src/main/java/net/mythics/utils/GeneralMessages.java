package net.mythics.utils;

import org.bukkit.configuration.file.FileConfiguration;

import net.mythics.utils.ymls.Messages;

public class GeneralMessages {

    public static String prefix = null;
    public static String invalidUse = null;


    public static void setUpMessages(){
        FileConfiguration messages = Messages.getMessages();
        prefix = messages.getString("prefix");
        
        



    }
    
}
