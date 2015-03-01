/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener{
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		
		Player p = e.getPlayer();
		
		if(!p.hasPermission("hotpotato.place")){
			
			e.setCancelled(true);
			return;
			
		}
		
	}

}
