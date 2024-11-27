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
