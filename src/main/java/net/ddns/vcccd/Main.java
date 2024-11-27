package net.ddns.vcccd;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	
	// Creates a variable so you can reference the console (Should make private and an accessor method)
	public ConsoleCommandSender console = getServer().getConsoleSender();
	
	// Code executed when plugin is enabled 
	@Override
	public void onEnable() {
		
		// Grabs the configuration file and stores it in the local scope
		FileConfiguration config = this.getConfig();
		
		// Adds the default value for the parameter in the YML
		// You can add more configuration parameters below
		config.addDefault("Parameter", 1);
		
		// Saves the configuration
		this.saveDefaultConfig();
		
		// Used to register events
		getServer().getPluginManager().registerEvents(new BoilerPlateEvent(this), this);
		
		// Used to register commands
		this.getCommand("boilerplate").setExecutor(new BoilerPlateCommand(this));
		
		// Should give us a colored working message (color may not work on Windows)
		console.sendMessage(ChatColor.GREEN + "BoilerPlate code working!");
		
	}
	
	@Override
	public void onDisable() {
		
		// Should let us know the plugin is disabled
		console.sendMessage(ChatColor.RED + "BoilerPlate code disabled!");
	}
}
