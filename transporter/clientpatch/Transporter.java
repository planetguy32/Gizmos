package transporter.clientpatch;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;

public class Transporter{
	private static String host = null;
	private static int port = -1;
	private static final Pattern pattern = Pattern.compile("^\\[([^\\]]+)\\][^:]+:\\s*([a-zA-Z0-9\\-\\.]+):?(\\d+)?");

	public static void detectKick(String s, String s1, Object[] aobj) {
		port = -1;

		if ((s == null) || (aobj == null) || (aobj.length == 0) || (!(aobj[0] instanceof String))) return;
		String reason = (String)aobj[0];
		System.out.println("message: " + reason);
		Matcher match = pattern.matcher(reason);
		if (!match.matches()) return;

		host = match.group(2);
		String portStr = match.group(3);
		if ((portStr != null) && (portStr.length() > 0))
			try {
				port = Integer.parseInt(match.group(3));
			} catch (NumberFormatException e) {
				host = null;
				return;
			}
		else {
			port = 25565;
		}
		System.out.println("[Transporter] detected reconnect for '" + match.group(1) + "' to " + host + ":" + port);
	}

	public static void teleportIfKicked(Minecraft client) {
		if ((port == -1) || (host == null)) return;
		System.out.println("[Transporter] reconnecting...");
		client.displayGuiScreen(new GuiConnecting((GuiScreen)null, client, new ServerData("Remote", host + ":" + port)));
	}
}