/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener{
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		
		Player p = e.getPlayer();
		
		if(!p.hasPermission("hotpotato.break")){
			
			e.setCancelled(true);
			return;
			
		}
		
	}

}
