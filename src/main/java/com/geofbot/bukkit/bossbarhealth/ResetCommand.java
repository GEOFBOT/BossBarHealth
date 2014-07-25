package com.geofbot.bukkit.bossbarhealth;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResetCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args[0].equalsIgnoreCase("resetconfig")) {
			sender.sendMessage(ChatColor.RED + "[BossBar] " + ChatColor.GOLD + "config has been " + ChatColor.GREEN + "RESET.");
			BossBarHealth.config.resetConfig();
		}
		return true;
	}

}
