package prevail.askingg.bans.main;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Files {

	public static File configFile;
	public static FileConfiguration config;
	public static File dataFile;
	public static FileConfiguration data;

	public static void base() {
		Main main = Main.getPlugin(Main.class);
		if (!main.getDataFolder().exists()) {
			main.getDataFolder().mkdirs();
			Core.console("&b &b &l»&7 Created the '&b" + main.getDataFolder().getName().toString() + "&7' folder");
		}
		configFile = new File(main.getDataFolder(), "config.yml");
		if (!configFile.exists()) {
			main.saveResource("config.yml", false);
			Core.console("&b &b &l»&7 Created the '&bconfig.yml&7' file");
		}
		config = YamlConfiguration.loadConfiguration(configFile);
		dataFile = new File(main.getDataFolder(), "data.yml");
		if (!dataFile.exists()) {
			main.saveResource("data.yml", false);
			Core.console("&b &b &l»&7 Created the '&bdata.yml&7' file");
		}
		data = YamlConfiguration.loadConfiguration(dataFile);
	}
}