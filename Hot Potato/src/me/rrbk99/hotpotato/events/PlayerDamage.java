/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.events;

import me.rrbk99.hotpotato.HotPotato;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class PlayerDamage implements Listener {

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {

		if (e.getCause() == DamageCause.VOID && e.getEntity() instanceof Player) {
			if(HotPotato.status.getGameStatus() == 1){
				e.getEntity().teleport(HotPotato.lobbySpawn);
			} else{
				e.getEntity().teleport(HotPotato.gameSpawn);
			}
		}

		e.setCancelled(true);

	}

}
