package prevail.askingg.bans.events;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import prevail.askingg.bans.main.Config;
import prevail.askingg.bans.main.Core;
import prevail.askingg.bans.main.Data;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		UUID u = p.getUniqueId();
		if (Data.tempbans.size() > 0 && Data.tempbans.containsKey(u)) {
			long l = Data.tempbans.get(u);
			if (System.currentTimeMillis() < l) {
				e.setKickMessage(Core.color(Config.kickScreen.replace("%banner%", Data.banner.get(u))
						.replace("%expireDate%", Core.longToDate(System.currentTimeMillis() + l))
						.replace("%expireWait%",
								Core.time((int) (l - System.currentTimeMillis()) / 1000))
						.replace("%reason%", Data.reason.get(u))));
				e.setResult(PlayerLoginEvent.Result.KICK_OTHER);
			} else {
				Data.tempbans.remove(u);
				Data.banner.remove(u);
				Data.reason.remove(u);
			}
		}
	}
}
