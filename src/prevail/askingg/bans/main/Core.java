 package prevail.askingg.bans.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Core {

	public static String uuidToName(UUID uuid) {
		return Bukkit.getOfflinePlayer(uuid).getName();
	}

	public static Player uuidToPlayer(UUID uuid) {
		return Bukkit.getPlayer(uuid);
	}

	public static String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public static String decimals(Integer decimalSpaces, Double number) {
		return String.format("%1$,." + decimalSpaces + "f", number);
	}

	public static String decimals(Integer decimalSpaces, int number) {
		return String.format("%1$,." + decimalSpaces + "f", 0.0 + number);
	}

	public static String time(int seconds) {
		if (seconds < 60) {
			return seconds + "s";
		}
		int minutes = seconds / 60;
		int s = 60 * minutes;
		int secondsLeft = seconds - s;
		if (minutes < 60) {
			if (secondsLeft > 0) {
				return String.valueOf(minutes + "m " + secondsLeft + "s");
			}
			return String.valueOf(minutes + "m");
		}
		if (minutes < 1440) {
			String time = "";
			int hours = minutes / 60;
			time = hours + "h";
			int inMins = 60 * hours;
			int leftOver = minutes - inMins;
			if (leftOver >= 1) {
				time = time + " " + leftOver + "m";
			}
			if (secondsLeft > 0) {
				time = time + " " + secondsLeft + "s";
			}
			return time;
		}
		String time = "";
		int days = minutes / 1440;
		time = days + "d";
		int inMins = 1440 * days;
		int leftOver = minutes - inMins;
		if (leftOver >= 1) {
			if (leftOver < 60) {
				time = time + " " + leftOver + "m";
			} else {
				int hours = leftOver / 60;
				time = time + " " + hours + "h";
				int hoursInMins = 60 * hours;
				int minsLeft = leftOver - hoursInMins;
				if (leftOver >= 1) {
					time = time + " " + minsLeft + "m";
				}
			}
		}
		if (secondsLeft > 0) {
			time = time + " " + secondsLeft + "s";
		}
		return time;
	}

	public static long getTempPunishmentMilis(int duration, String timeform) {
		long l = -1;
		if (timeform.equalsIgnoreCase("s")) {
			l = duration * 1000;
		} else if (timeform.equals("m")) {
			l = duration * 60000;
		} else if (timeform.equalsIgnoreCase("h")) {
			l = duration * 3600000;
		} else if (timeform.equalsIgnoreCase("d")) {
			l = duration * 86400000;
		} else if (timeform.equalsIgnoreCase("w")) {
			l = duration * 604800000;
		} else if (timeform.equals("M")) {
			l = (duration * 4) * 604800000;
		}
		return l;
	}
	
	public static String longToDate(long milis) {
		Date d = new Date(milis);
	       DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
	       return df.format(d);
	}

	public static void console(String msg) {
		if (!msg.equals("")) {
			Bukkit.getConsoleSender().sendMessage(color(msg.replace("%prefix%", Config.prefix).replace("\\n", System.lineSeparator())));
		}
	}

	public static void broadcast(String msg) {
		if (!msg.equals("")) {
			Bukkit.broadcastMessage(color(msg.replace("%prefix%", Config.prefix).replace("\\n", System.lineSeparator())));
		}
	}

	public static void message(String msg, Player p) {
		if (!msg.equals("")) {
			p.sendMessage(color(msg.replace("%prefix%", Config.prefix).replace("\\n", System.lineSeparator())));
		}
	}

	public static void message(String msg, CommandSender sender) {
		if (!msg.equals("")) {
			sender.sendMessage(color(msg.replace("%prefix%", Config.prefix).replace("\\n", System.lineSeparator())));
		}
	}
}
