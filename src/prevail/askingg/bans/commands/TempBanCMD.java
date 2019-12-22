package prevail.askingg.bans.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import prevail.askingg.bans.main.Config;
import prevail.askingg.bans.main.Core;
import prevail.askingg.bans.main.Data;

public class TempBanCMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String string, String[] args) {
		// TempBan <Player> <Duration> <TimeForm> <Reason>
		if (s instanceof ConsoleCommandSender || s.hasPermission("prevail.bans.tempban")) {
			if (args.length < 4) {
				Core.message(Config.tempbanUsage, s);
			} else {
				OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
				if (!p.hasPlayedBefore()) {
					Core.message(Config.invalidPlayer.replace("%input%", args[0]), s);
				} else {
					int x = 0;
					try {
						x = Integer.parseInt(args[1]);
					} catch (Exception ex) {
						Core.message(Config.invalidInteger.replace("%input%", args[1]), s);
						return false;
					}
					if (x > 0) {
						if (args[2].equalsIgnoreCase("s") || args[2].equalsIgnoreCase("m")
								|| args[2].equalsIgnoreCase("h") || args[2].equalsIgnoreCase("d")
								|| args[2].equalsIgnoreCase("w")) {
							String reason = "";
							for (int xx = 3; xx < args.length; xx++) {
								if (reason.equals("")) {
									reason = args[xx];
								} else {
									reason += " " + args[xx];
								}
							}
							long l = Core.getTempPunishmentMilis(x, args[2]);
							if (Bukkit.getPlayer(args[0]) != null) {
								Bukkit.getPlayer(args[0]).kickPlayer(Core.color(Config.kickScreen
										.replace("%banner%", s.getName())
										.replace("%expireDate%", Core.longToDate(System.currentTimeMillis() + l))
										.replace("%expireWait%", Core
												.time((int) ((System.currentTimeMillis()+l) - System.currentTimeMillis())
														/ 1000))
										.replace("%reason%", reason)));
							}
							UUID u = p.getUniqueId();
							Data.tempbans.put(u, System.currentTimeMillis() + l);
							Data.reason.put(u, reason);
							Data.banner.put(u, s.getName());
							Core.broadcast(Config.tempban.replace("%banned%", p.getName())
									.replace("%banner%", s.getName()).replace("%reason%", reason)
									.replace("%time%", Core.time((int) (l / 1000))));
							return true;
						} else {
							Core.message(Config.invalidTimeform.replace("%input%", args[2]), s);
						}
					} else {
						Core.message(Config.tempbanTimeLessthanZero, s);
					}
				}
			}
		} else {
			Core.message(Config.noPermission, s);
		}
		return false;
	}
}
