/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.events;

import me.rrbk99.hotpotato.HotPotato;
import me.rrbk99.hotpotato.timer.EndTimer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {

		Player p = e.getPlayer();

		e.setQuitMessage(null);

		if (HotPotato.spectators.contains(p)) {
			HotPotato.spectators.remove(p);
		}

		if (HotPotato.online.contains(p)) {
			HotPotato.online.remove(p);
		}

		p.removePotionEffect(PotionEffectType.SPEED);

		if (HotPotato.status.getGameStatus() == 2
				|| HotPotato.status.getGameStatus() == 3) {

			if (HotPotato.online.size() == 1) {
				HotPotato.status.END.setGameStatus();
				EndTimer.startEnd();
			}

		}

	}

}
