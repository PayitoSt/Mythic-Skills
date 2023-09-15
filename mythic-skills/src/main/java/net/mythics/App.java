package net.mythics;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.mythics.commands.ClassSelector;
import net.mythics.commands.ItemsGet;
import net.mythics.commands.Levels;
import net.mythics.commands.Manager;
import net.mythics.commands.Orbs;
import net.mythics.commands.Transfer;
import net.mythics.listeners.ChatListenerForMenus;
import net.mythics.listeners.ExpGiver;
import net.mythics.listeners.JoinSetup;
import net.mythics.listeners.PickUpOrb;
import net.mythics.listeners.RPGClasses;
import net.mythics.utils.inventories.AddExpInventory;
import net.mythics.utils.inventories.Helpers;
import net.mythics.utils.inventories.SkillsInventory;
import net.mythics.utils.statsinventories.generalmenumanager.GeneralMenuManager;
import net.mythics.utils.statsinventories.generalmenumanager.GeneralMenus;
import net.mythics.utils.statsinventories.generalmenuplayer.ClassesMenu;
import net.mythics.utils.statsinventories.generalmenuplayer.GeneralMenuPlayer;
import net.mythics.utils.ymls.Messages;
import net.mythics.utils.ymls.Settings;
import net.mythics.utils.ymls.Stats;

public class App extends JavaPlugin{
    
    public static Plugin plugin;


    @Override
    public void onEnable(){
        Bukkit.getConsoleSender().sendMessage("Enabled...");
        plugin = JavaPlugin.getPlugin(App.class);
        Stats.registerStats();
        Messages.registerMessages();
        Settings.registerSettings();
        registerEvents();
        registerCommands();
        Helpers.setUpHashMap();
    }

    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ExpGiver(), this);
        pm.registerEvents(new SkillsInventory(), this);
        pm.registerEvents(new AddExpInventory(), this);
        pm.registerEvents(new JoinSetup(), this);
        pm.registerEvents(new GeneralMenuManager(), this);
        pm.registerEvents(new GeneralMenus(), this);
        pm.registerEvents(new GeneralMenuPlayer(), this);
        pm.registerEvents(new ChatListenerForMenus(), this);
        pm.registerEvents(new PickUpOrb(), this);
        pm.registerEvents(new ClassesMenu(), this);
        pm.registerEvents(new RPGClasses(), this);
    }

    public void registerCommands(){
        this.getCommand("itemget").setExecutor(new ItemsGet());
        this.getCommand("stats").setExecutor(new Levels());
        this.getCommand("transfer").setExecutor(new Transfer());
        this.getCommand("orbs").setExecutor(new Orbs());
        this.getCommand("manager").setExecutor(new Manager());
        this.getCommand("class").setExecutor(new ClassSelector());
    }
}
