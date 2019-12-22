package prevail.askingg.bans.main;

import org.bukkit.configuration.ConfigurationSection;

public class Config {

	public static String prefix = "";
	public static String tempbanUsage = "";
	public static String tempbanTimeLessthanZero = "";
	public static String tempban = "";
	public static String kickScreen = "";

	public static String noPermission = "";
	public static String invalidPlayer = "";
	public static String invalidInteger = "";
	public static String invalidTimeform = "";

	public static void load() {
		Files.base();
		ConfigurationSection conf = Files.config.getConfigurationSection("");
		prefix = conf.getString("prefix");
		tempbanTimeLessthanZero = conf.getString("tempbanTimeLessthanZero");

		if (conf.getStringList("kickScreen").size() > 0) {
			for (String s : conf.getStringList("kickScreen")) {
				if (kickScreen.equals("")) {
					kickScreen = s;
				} else {
					kickScreen += "\n" + s;
				}
			}
		}

		conf = Files.config.getConfigurationSection("messages");
		tempban = conf.getString("tempban");
		noPermission = conf.getString("noPermission");
		tempbanUsage = conf.getString("usage.tempban");
		invalidPlayer = conf.getString("invalidInput.player");
		invalidInteger = conf.getString("invalidInput.integer");
		invalidTimeform = conf.getString("invalidInput.timeform");
	}

	public static void reload() {
		prefix = "";
		tempbanUsage = "";
		tempbanTimeLessthanZero = "";
		tempban = "";
		kickScreen = "";

		noPermission = "";
		invalidPlayer = "";
		invalidInteger = "";
		invalidTimeform = "";

		load();
	}
}
