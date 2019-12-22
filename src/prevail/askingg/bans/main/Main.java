package prevail.askingg.bans.main;

import org.bukkit.plugin.java.JavaPlugin;

import prevail.askingg.bans.commands.TempBanCMD;
import prevail.askingg.bans.events.PlayerJoin;

public class Main extends JavaPlugin {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getCommand("tempban").setExecutor(new TempBanCMD());
		Config.load();
		Data.load();
	}
	
	public void onDisable() {
		Data.save();
	}
}
