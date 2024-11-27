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
