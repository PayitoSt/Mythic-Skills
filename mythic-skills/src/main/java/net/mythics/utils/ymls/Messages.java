package net.mythics.utils.ymls;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.mythics.App;

public class Messages {


    private static FileConfiguration messages = null;
    private static File messagesFile = null;
    public static FileConfiguration getMessages(){
        if(messages == null){
            reloadMessages();
        }

        return messages;
    }


    public static void reloadMessages(){
        if(messages == null){
            messagesFile = new File(App.plugin.getDataFolder(), "messages.yml");
        }

        messages = YamlConfiguration.loadConfiguration(messagesFile);
        Reader defConfigStream;
        try{
            defConfigStream = new InputStreamReader(App.plugin.getResource("messages.yml"), "UTF8");
            if(defConfigStream != null){
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                messages.setDefaults(defConfig);
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public static void saveMessages(){
        try {
            messages.save(messagesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerMessages(){
        messagesFile = new File(App.plugin.getDataFolder(),"messages.yml");
        if(!messagesFile.exists()){
            getMessages().options().copyDefaults(true);
            saveMessages();
        }
    }
    
}
