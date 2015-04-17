/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.listener;

import me.rrbk99.hotpotato.BackToHub;
import me.rrbk99.hotpotato.HotPotato;
import me.rrbk99.hotpotato.manager.GameStatus;
import me.rrbk99.hotpotato.manager.Manager;
import me.rrbk99.hotpotato.timer.EndTimer;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.potion.PotionEffectType;

public class Log implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {

		Player p = e.getPlayer();

		e.setJoinMessage(null);
		p.sendMessage(HotPotato.pr + " §7Willkommen zu Hot Potato!");

		if (HotPotato.status == GameStatus.LOBBY) {

			HotPotato.alive.add(p);
			Manager.clearInventory(p);
			BackToHub.giveItem(p);
			p.teleport(HotPotato.lobbySpawn);
			p.setGameMode(GameMode.ADVENTURE);

		}
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {

		Player p = e.getPlayer();

		e.setQuitMessage(null);

		p.removePotionEffect(PotionEffectType.SPEED);

		HotPotato.alive.remove(p);

		if (HotPotato.status.getGameStatus() == 2
				|| HotPotato.status.getGameStatus() == 3) {

			if (HotPotato.alive.size() == 1) {
				HotPotato.status.END.setGameStatus();
				EndTimer.startEnd();
			}

		}

	}

	@EventHandler
	public void on(ServerListPingEvent e) {
		e.setMaxPlayers(HotPotato.maxPlayers);

		if (HotPotato.status == GameStatus.LOBBY) {
			e.setMotd("§2Warten");
		}

		if (HotPotato.status == GameStatus.SCHUTZ
				|| HotPotato.status == GameStatus.GAME
				|| HotPotato.status == GameStatus.END) {
			e.setMotd("§cInGame");
			;
		}

	}

	@EventHandler
	public void on(PlayerLoginEvent e) {

		if (HotPotato.status != GameStatus.LOBBY) {
			e.disallow(null, "§cInGame");
		}
	}

}
