/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked();

		if (e.getInventory().getName().equalsIgnoreCase("container.inventory")
				&& !p.hasPermission("hotpotato.clickInventory")) {

			e.setCancelled(true);
			p.closeInventory();

		}

	}

}
