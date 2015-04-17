/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.listener;

import me.rrbk99.hotpotato.HotPotato;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Events implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("hotpotato.break")) {
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (!p.hasPermission("hotpotato.place")) {
			e.setCancelled(true);
			return;
		}
	}

	@EventHandler
	public void playerChat(AsyncPlayerChatEvent e) {

		Bukkit.broadcastMessage("§9" + e.getPlayer().getDisplayName()
				+ " §8|| §f" + e.getMessage());
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		e.getPlayer().setItemInHand(e.getItemDrop().getItemStack());
		e.getItemDrop().remove();
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent e) {
		e.setFoodLevel(20);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getInventory().getName().equalsIgnoreCase("container.inventory")
				&& !p.hasPermission("hotpotato.clickInventory")) {
			e.setCancelled(true);
			p.closeInventory();
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {

		if (e.getCause() == DamageCause.VOID && e.getEntity() instanceof Player) {
			if (HotPotato.status.getGameStatus() == 1) {
				e.getEntity().teleport(HotPotato.lobbySpawn);
			} else {
				e.getEntity().teleport(HotPotato.gameSpawn);
			}
		}

		e.setCancelled(true);

	}

	

}
