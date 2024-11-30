# Boiler Plate code for Spigot Plugins
![MIT License](https://img.shields.io/badge/License-MIT-blue.svg)  ![Spigot 1.21.3](https://img.shields.io/badge/Spigot-1.21.3-orange)  ![Latest Java](https://img.shields.io/badge/Java-21-brightgreen)  ![YAML Latest](https://img.shields.io/badge/YAML-Latest-lightgrey)

![Untitled design(4)](https://github.com/user-attachments/assets/a023b076-dc6f-484b-a4fd-516890e964ea)

This project is boilerplate code for a Spigot plugin, providing the basic structure necessary for creating a plugin. It includes a simple command that responds to user input via the /boilerplate syntax, handling interaction through the BoilerPlateCommand method. Additionally, it features an event listener that captures player interactions, such as player joins, and triggers actions using the @EventHandler annotation, demonstrating core functionality for event handling in Spigot plugins.

## plugin.yml
```yml
name: BoilerPlate
main: net.ddns.vcccd.Main
version: 1.0.0
api-version: '1.21.3'
commands:
  boilerplate:
    description: An example command
    usage: /<command> <args>
    permission: boilerplate.example
```
The plugin.yml file is essential for Spigot Java plugins as it provides the necessary metadata for the server to recognize and properly load the plugin. It includes key information such as the plugin's name, version, main class, and dependencies, ensuring compatibility and functionality. Without it, the server cannot identify or initialize the plugin, resulting in loading errors.

# config.yml
```yml
Parameter: 1
```
The config.yml file is an optional file that allows user configuration for the plugin. (Although the file is technically optional, it is highly encouraged as the user base is critical of plugins devoid of user configuration.)

# Main.java
```java
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
```
This is the "Main" class used for plugin initialization on the server and includes methods for command and event listening as well as configuration methods for the aforementioned configuration file. The code is explained through the comments. If you feel as though you can give a better explanation feel free to fork and make corrections and I will be happy to merge them.

## Events
```java
package net.ddns.vcccd;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BoilerPlateEvent implements Listener {
	
	// Private references to main and configuration to use configuration file and console access
	private final Main main;
	private FileConfiguration config;
	
	// Initialize main and configuration when command is initialized with main as argument
	public BoilerPlateEvent(Main main) {
		this.main = main;
		this.config = main.getConfig();
	}
	
	// When a player joins for example
	@EventHandler
	public void ExampleMethod(PlayerJoinEvent event) {
		
		// We send the player a message
		Player player = event.getPlayer();
		player.sendMessage(String.format("Parameter in config is: %d", config.get("Parameter")));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lPlugin Working..."));
		
		// And we send the console a message that the player received the message
		main.console.sendMessage("Player recieved message!");
	}

}
```
Used for handling events, please reffer to code comments...

```java
package net.ddns.vcccd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BoilerPlateCommand implements CommandExecutor {
	
	// Private main to be initialized when initializer method called
	private final Main main;
	
	// Initializer method with main parameter to be passed
	public BoilerPlateCommand(Main main) {
		this.main = main;
	}

	// Boilerplate command code
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		// Check if the sender is a player
		if(arg0 instanceof Player) {
			
			// Type casting
			Player player = (Player) arg0;
			
			// For-each loop sending arguments to player
			for(String args: arg3) {
				player.sendMessage(args);
			}
			
		} else {
			
			// Assuming it will be console as it is not a player
			main.console.sendMessage("Command Sent from entity other than player...");
			
			// For-each loop sending arguments to console
			for(String args: arg3) {
				main.console.sendMessage(args);
			}
		}
		return true;
	}

}
```
Used for handling commands, I will probably expand on the comments but if you feel like giving a more accurate description via comments in the code fork the repository and make the corrections (I would be stoked!)
