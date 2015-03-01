/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.cmd;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import me.rrbk99.hotpotato.HotPotato;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {

		Player p = (Player) cs;

		if (cmd.getName().equalsIgnoreCase("lobby")
				|| cmd.getName().equalsIgnoreCase("hub")
				|| cmd.getName().equalsIgnoreCase("leave")) {

			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);

			try {

				out.writeUTF("Connect");
				out.writeUTF("lobby");

			} catch (IOException e) {
				e.printStackTrace();

			}

			p.sendPluginMessage(HotPotato.plugin, "BungeeCord", b.toByteArray());
			p.sendMessage(HotPotato.pr + " §7Du wirst nun zum Hub teleportiert");

			return true;

		}

		return false;

	}

}
