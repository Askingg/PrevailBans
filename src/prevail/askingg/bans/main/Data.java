package prevail.askingg.bans.main;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;

public class Data {

	public static HashMap<UUID, Long> tempbans = new HashMap<UUID, Long>();
	public static HashMap<UUID, String> reason = new HashMap<UUID, String>();
	public static HashMap<UUID, String> banner = new HashMap<UUID, String>();

	public static void load() {
		ConfigurationSection conf = Files.data.getConfigurationSection("");
		if (conf != null) {
			for (String s : conf.getKeys(false)) {
				UUID u = UUID.fromString(s);
				tempbans.put(u, conf.getLong(u + ".expire"));
				banner.put(u, conf.getString(u + ".banner"));
				reason.put(u, conf.getString(u + ".reason"));
			}
		}
	}

	public static void save() {
		if (tempbans.size() > 0) {
			for (UUID u : tempbans.keySet()) {
				Files.data.set(u.toString() + ".expire", tempbans.get(u));
				Files.data.set(u.toString() + ".banner", banner.get(u));
				Files.data.set(u.toString() + ".reason", reason.get(u));
			}
			try {
				Files.data.save(Files.dataFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
