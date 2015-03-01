/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.events;

import me.rrbk99.hotpotato.HotPotato;
import me.rrbk99.hotpotato.InventoryManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {

		Player p = e.getPlayer();

		e.setJoinMessage(null);
		p.sendMessage(HotPotato.pr + " §7Willkommen zu Hot Potato!");

		if (HotPotato.status.getGameStatus() == 1) {

			HotPotato.online.add(p);

			int needed = HotPotato.minPlayers - HotPotato.online.size();

			if (needed > 0) {
				if (needed != 1) {
					p.sendMessage(HotPotato.pr + " §b" + needed
							+ " §7Spieler werden noch gebraucht.");
				} else {
					p.sendMessage(HotPotato.pr + " §b" + needed
							+ " §7Spieler wird noch gebraucht.");
				}
			}

			InventoryManager.setUpLobbyInventory(p);
			p.teleport(HotPotato.lobbySpawn);

		} else {

			p.sendMessage(HotPotato.pr + " §7Du bist nun Spectator.");

			HotPotato.spectators.add(p);
			InventoryManager.setUpLobbyInventory(p);
			p.teleport(HotPotato.spectateSpawn);

			p.setAllowFlight(true);
			p.setFlying(true);

			for (Player online : HotPotato.online) {
				online.hidePlayer(p);
			}

		}

	}

}
