package net.mythics.utils.ymls;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.mythics.App;

public class Settings {


    private static FileConfiguration settings = null;
    private static File settingsFile = null;
    public static FileConfiguration getSettings(){

		if(settings == null){
			reloadSettings();
		}
		return settings;
	}
 
	public static void reloadSettings(){
		if(settings == null){
			settingsFile = new File(App.plugin.getDataFolder(),"settings.yml");
		}
		settings = YamlConfiguration.loadConfiguration(settingsFile);
		Reader defConfigStream;
		try{
			defConfigStream = new InputStreamReader(App.plugin.getResource("settings.yml"),"UTF8");
			if(defConfigStream != null){
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				settings.setDefaults(defConfig);
			}			
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
 
	public static void saveSettings(){
		try{
			settings.save(settingsFile);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
 
	public static void registerSettings(){
		settingsFile = new File(App.plugin.getDataFolder(),"settings.yml");
		if(!settingsFile.exists()){
			getSettings().options().copyDefaults(true);
			saveSettings();
		}
	}
    
}
    
