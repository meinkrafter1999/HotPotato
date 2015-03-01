/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.events;

import me.rrbk99.hotpotato.InventoryManager;
import me.rrbk99.hotpotato.Potato;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {

	@EventHandler
	public void onEntityDamagebyEntity(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {

			Player damager = (Player) e.getDamager();
			Player damaged = (Player) e.getEntity();

			if (Potato.hasPotato == damager) {

				InventoryManager.clearInventory(damager);
				Potato.givePotato(damaged);

			}

		}

		e.setCancelled(true);

	}

}
