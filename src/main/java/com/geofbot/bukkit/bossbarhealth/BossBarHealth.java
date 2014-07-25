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

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Geoffrey "GEOFBOT" Mon
 *
 */

public class BossBarHealth extends JavaPlugin implements Listener {
	private HashMap<String, Integer> timer = new HashMap<String, Integer>();
	private HashMap<String, DecreaseTimer> timers = new HashMap<String, DecreaseTimer>();

	private HashMap<String, String> victims = new HashMap<String, String>();

	private static Integer expireTicks;
	private static Boolean enabled;
	private static String prefix;
	private static String suffix;

	public static Plugin plugin;
	public static ConfigHandler config;

	public static BarAPI barapi;

	public static void setExpireTicks(Integer expireTicks) {
		BossBarHealth.expireTicks = expireTicks;
	}

	public static void setEnabled(Boolean enabled) {
		BossBarHealth.enabled = enabled;
	}

	public static void setPrefix(String prefix) {
		BossBarHealth.prefix = prefix;
	}

	public static void setSuffix(String suffix) {
		BossBarHealth.suffix = suffix;
	}
	
	@Override
	public void onEnable() {
		plugin = this;

		config = new ConfigHandler();
		config.loadConfig();
		
		Bukkit.getPluginManager().registerEvents(this, this);

		CommandManager handler = new CommandManager();
		handler.newCommand("help", new HelpCommand());
		handler.newCommand("on", new ToggleCommand());
		handler.newCommand("off", new ToggleCommand());
		handler.newCommand("resetconfig", new ResetCommand());
		getCommand("bossbar").setExecutor(new CommandManager());
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (enabled) {
			if (event.getEntity() instanceof Player) {
				if (victims.containsValue(((Player) event.getEntity()).getName())) {
					for (String name : victims.keySet()) {
						Player attacker = Bukkit.getPlayer(name);
						Player victim = Bukkit.getPlayer(victims.get(name));
						if (victim == event.getEntity()) {
							if (attacker.hasPermission("bossbar.use") && BarAPI.hasBar(attacker)) {
								BarAPI.setMessage(attacker, prefix + victim.getName() + suffix, (float) ((victim.getHealth() - event.getDamage()) / victim.getMaxHealth() * 100));
							}
						}
					}
				}
				if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
					Player attacker = (Player) event.getDamager();
					Player victim = (Player) event.getEntity();
					victims.put(attacker.getName(), victim.getName());
					if (attacker.hasPermission("bossbar.use"))
					{
						BarAPI.setMessage(attacker, prefix + victim.getName() + suffix, (float) ((victim.getHealth() - event.getDamage()) / victim.getMaxHealth() * 100));
					}
					timer.put(attacker.getName(), expireTicks);
					if (!timers.containsKey(attacker.getName())) {
						timers.put(attacker.getName(), new DecreaseTimer(attacker, timer));
					} else {
						timers.get(attacker.getName()).cancel();
						timers.put(attacker.getName(), new DecreaseTimer(attacker, timer));
					}
					timers.get(attacker.getName()).runTaskTimer(this, 1L, 1L);
				} else if (event.getEntity() instanceof Player && event.getDamager() instanceof Projectile && ((Projectile) event.getDamager()).getShooter() instanceof Player) {
					Player attacker = (Player) ((Projectile) event.getDamager()).getShooter();
					Player victim = (Player) event.getEntity();
					victims.put(attacker.getName(), victim.getName());
					if (attacker.hasPermission("bossbar.use"))
					{
						BarAPI.setMessage(attacker, prefix + victim.getName() + suffix, (float) ((victim.getHealth() - event.getDamage()) / victim.getMaxHealth() * 100));
					}
					timer.put(attacker.getName(), expireTicks);
					if (!timers.containsKey(attacker.getName())) {
						timers.put(attacker.getName(), new DecreaseTimer(attacker, timer));
					} else {
						timers.get(attacker.getName()).cancel();
						timers.put(attacker.getName(), new DecreaseTimer(attacker, timer));
					}
					timers.get(attacker.getName()).runTaskTimer(this, 1L, 1L);
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityRegainHealth(EntityRegainHealthEvent event) {
		if (enabled) {
			if (event.getEntity() instanceof Player) {
				if (victims.containsValue(((Player) event.getEntity()).getName())) {
					for (String name : victims.keySet()) {
						Player attacker = Bukkit.getPlayer(name);
						Player victim = Bukkit.getPlayer(victims.get(name));
						if (victim == event.getEntity()) {
							if (attacker.hasPermission("bossbar.use") && BarAPI.hasBar(attacker)) {
								BarAPI.setMessage(attacker, prefix + victim.getName() + suffix, (float) ((victim.getHealth() + event.getAmount()) / victim.getMaxHealth() * 100));
							}
						}
					}
				}
			}
		}
	}
}
