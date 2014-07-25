package com.geofbot.bukkit.bossbarhealth;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {
	private FileConfiguration conf = BossBarHealth.plugin.getConfig();
	public void loadConfig() {
		File file = new File(BossBarHealth.plugin.getDataFolder(), "config.yml");
		if(!file.exists()) {
			BossBarHealth.plugin.saveDefaultConfig();
			BossBarHealth.plugin.saveConfig();
		}
		BossBarHealth.setExpireTicks(conf.getInt("expireticks"));
		BossBarHealth.setEnabled(conf.getBoolean("enabled"));
		if(conf.getString("prefix") != null)
			BossBarHealth.setPrefix(conf.getString("prefix"));
		else
			BossBarHealth.setPrefix("");
		if(conf.getString("suffix") != null)
			BossBarHealth.setSuffix(conf.getString("suffix"));
		else
			BossBarHealth.setSuffix("");
	}

	public void resetConfig() {
		File file = new File(BossBarHealth.plugin.getDataFolder(), "config.yml");
		if(file.exists()) {
			file.delete();
			BossBarHealth.plugin.saveDefaultConfig();
		}
	}
	
	public void setEnabled(Boolean enabled) {
		BossBarHealth.setEnabled(enabled);
		conf.set("enabled", enabled);
		BossBarHealth.plugin.saveConfig();
	}
}
