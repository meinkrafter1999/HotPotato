/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryManager {

	public static ItemStack potato = new ItemStack(Material.BAKED_POTATO, 1,
			(short) 0);
	public static ItemStack hub = new ItemStack(Material.STAINED_CLAY, 1,
			(short) 14);

	public static void init() {

		ItemMeta potatoMeta = potato.getItemMeta();
		potatoMeta.setDisplayName("§c--] §4Potato §c[--");
		potato.setItemMeta(potatoMeta);
		
		ItemMeta hubMeta = hub.getItemMeta();
		hubMeta.setDisplayName("§cBack to Hub >>>");
		hub.setItemMeta(hubMeta);

	}

	public static void setUpPotatoInventory(Player p) {
		clearInventory(p);
		p.getInventory().setItem(0, potato);
	}

	public static void setUpLobbyInventory(Player p) {
		clearInventory(p);
		p.getInventory().setItem(8, hub);
	}

	
	public static void clearInventory(Player p) {

		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);

		p.getInventory().clear();

		p.updateInventory();

	}

}
