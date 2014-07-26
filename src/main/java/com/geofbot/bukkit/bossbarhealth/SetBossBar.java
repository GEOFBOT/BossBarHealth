package com.geofbot.bukkit.bossbarhealth;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.confuser.barapi.BarAPI;

public class SetBossBar extends BukkitRunnable {

	private Player attacker;
	private Player victim;
	private String prefix;
	private String suffix;
	
	public SetBossBar(Player attacker, Player victim, String prefix, String suffix) {
		this.attacker = attacker;
		this.victim = victim;
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	@Override
	public void run() {
		BarAPI.setMessage(attacker, prefix + victim.getName() + suffix, (float) (victim.getHealth() / victim.getMaxHealth() * 100));
	}

}
