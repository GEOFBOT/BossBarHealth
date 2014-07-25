package com.geofbot.bukkit.bossbarhealth;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args[0].equalsIgnoreCase("on")) {
			sender.sendMessage(ChatColor.RED + "[BossBar] " + ChatColor.GOLD + "is now " + ChatColor.GREEN + "ON.");
			BossBarHealth.config.setEnabled(true);
		} else if(args[0].equalsIgnoreCase("off")) {
			sender.sendMessage(ChatColor.RED + "[BossBar] " + ChatColor.GOLD + "is now " + ChatColor.RED + "OFF.");
			BossBarHealth.config.setEnabled(false);
		}
		return true;
	}

}
