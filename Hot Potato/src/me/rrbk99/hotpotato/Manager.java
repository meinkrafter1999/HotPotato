/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Manager {

	public static void clearInventory(Player p) {

		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);

		p.getInventory().clear();

	}

	public static void playAll(Sound sound) {

		for (Player p : Bukkit.getOnlinePlayers()) {

			p.playSound(p.getLocation(), sound, 1, 0);

		}

	}

}
