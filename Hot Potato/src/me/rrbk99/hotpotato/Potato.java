/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato;

import java.util.Random;

import me.rrbk99.hotpotato.timer.PotatoCountdown;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Potato {

	public static Player hasPotato;

	public static void givePotato(Player p) {

		hasPotato = p;

		for (Player p1 : HotPotato.online) {
			p1.getInventory().setHelmet(null);
		}

		hasPotato.playSound(hasPotato.getLocation(), Sound.GHAST_SCREAM, 2, 0);
		p.getInventory().setHelmet(new ItemStack(Material.TNT, 1));
		InventoryManager.setUpPotatoInventory(p);

	}

	public static void explode() {

		HotPotato.gameWorld.createExplosion(hasPotato.getLocation().getX(),
				hasPotato.getLocation().getY(), hasPotato.getLocation().getZ(),
				4F, true, false);

		InventoryManager.clearInventory(hasPotato);

		for (Player online : HotPotato.online) {
			online.hidePlayer(hasPotato);
		}

		
		HotPotato.online.remove(hasPotato);
		HotPotato.spectators.add(hasPotato);

		HotPotato.online.remove(hasPotato);
		hasPotato.setAllowFlight(true);
		hasPotato.setFlying(true);
		
		hasPotato.teleport(HotPotato.spectateSpawn);

		hasPotato = null;

		if (HotPotato.online.size() > 1) {
			Player randomP = HotPotato.online.get(new Random().nextInt(HotPotato.online
					.size()));

			givePotato(randomP);
			Bukkit.getScheduler().cancelTask(HotPotato.potatoTask);
			PotatoCountdown.startCountdown();
		}

	}

}
