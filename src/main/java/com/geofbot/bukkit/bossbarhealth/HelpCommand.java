package com.geofbot.bukkit.bossbarhealth;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		sender.sendMessage(ChatColor.GOLD + "==========" + ChatColor.WHITE + "[" + ChatColor.GREEN + "BossBarHealth" + ChatColor.WHITE + "]" + ChatColor.GOLD + "==========");
		sender.sendMessage(ChatColor.GOLD + "BossBarHealth v" + BossBarHealth.plugin.getDescription().getVersion() + " by GEOFBOT");
		sender.sendMessage(ChatColor.GREEN + "/bossbar help       " + ChatColor.WHITE + " - " + ChatColor.GOLD + "Displays this help message.");
		sender.sendMessage(ChatColor.GREEN + "/bossbar on         " + ChatColor.WHITE + " - " + ChatColor.GOLD + "Enables BossBarHealth globally.");
		sender.sendMessage(ChatColor.GREEN + "/bossbar off        " + ChatColor.WHITE + " - " + ChatColor.GOLD + "Disables BossBarHealth globally.");
		sender.sendMessage(ChatColor.GREEN + "/bossbar resetconfig" + ChatColor.WHITE + " - " + ChatColor.GOLD + "Resets BossBarHealth config to defaults.");
		return true;
	}

}
