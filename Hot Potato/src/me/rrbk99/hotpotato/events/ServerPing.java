/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.events;

import me.rrbk99.hotpotato.GameStatus;
import me.rrbk99.hotpotato.HotPotato;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerPing implements Listener {

	@EventHandler
	public void on(ServerListPingEvent e) {
		e.setMaxPlayers(HotPotato.maxPlayers);

		if (HotPotato.status == GameStatus.LOBBY) {
			e.setMotd("§2Warten");
		}

		if (HotPotato.status == GameStatus.SCHUTZ || HotPotato.status == GameStatus.GAME
				|| HotPotato.status == GameStatus.END) {
			e.setMotd("§cInGame");;
		}

	}

	@EventHandler
	public void on(PlayerLoginEvent e) {

		if (HotPotato.status != GameStatus.LOBBY) {
			e.disallow(null, "§cInGame");
		}
	}
}
