/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Geoffrey "GEOFBOT" Mon
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *******************************************************************************/
package com.geofbot.bukkit.bossbarhealth;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Geoffrey "GEOFBOT" Mon (based on CommandManager in Torch-of-Notch by
 *         CaptainBern)
 *
 */

public class CommandManager implements CommandExecutor {

	private static HashMap<String, CommandExecutor> commands = new HashMap<String, CommandExecutor>();

	public void newCommand(String name, CommandExecutor cmdxc) {
		commands.put(name, cmdxc);
	}

	public CommandExecutor getExecutor(String name) {
		return commands.get(name);
	}

	public boolean commandExists(String name) {
		return commands.containsKey(name);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender.hasPermission("bossbar.config")) {
			if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
				getExecutor("help").onCommand(sender, cmd, label, args);
				return true;
			} else if (commandExists(args[0])) {
				getExecutor(args[0]).onCommand(sender, cmd, label, args);
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "[BossBar] "
						+ ChatColor.GOLD + "Invalid command.  Try "
						+ ChatColor.GREEN + "/bossbar help" + ChatColor.GOLD
						+ "to see a list of valid commands!");
			}
			return false;
		} else {
			sender.sendMessage(ChatColor.RED + "[BossBar] " + ChatColor.GOLD + "You don't have permission to configure BossBarHealth!");
			return true;
		}

	}

}
