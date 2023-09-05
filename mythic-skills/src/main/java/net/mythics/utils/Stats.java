package net.mythics.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.mythics.App;

public class Stats {


    private static FileConfiguration stats = null;
    private static File statsFile = null;
    public static FileConfiguration getStats(){

		if(stats == null){
			reloadStats();
		}
		return stats;
	}
 
	public static void reloadStats(){
		if(stats == null){
			statsFile = new File(App.plugin.getDataFolder(),"stats.yml");
		}
		stats = YamlConfiguration.loadConfiguration(statsFile);
		Reader defConfigStream;
		try{
			defConfigStream = new InputStreamReader(App.plugin.getResource("stats.yml"),"UTF8");
			if(defConfigStream != null){
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				stats.setDefaults(defConfig);
			}			
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
 
	public static void saveStats(){
		try{
			stats.save(statsFile);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
 
	public static void registerStats(){
		statsFile = new File(App.plugin.getDataFolder(),"stats.yml");
		if(!statsFile.exists()){
			getStats().options().copyDefaults(true);
			saveStats();
		}
	}
    
}

