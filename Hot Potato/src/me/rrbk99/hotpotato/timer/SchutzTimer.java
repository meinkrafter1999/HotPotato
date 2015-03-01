/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.timer;

import java.util.Random;

import me.rrbk99.hotpotato.HotPotato;
import me.rrbk99.hotpotato.Potato;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SchutzTimer {
	
	private static int i;

	public static void startSchutz() {
		
		i = HotPotato.schutzTimer;

		HotPotato.schutzTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(HotPotato.getInstance(),
				new Runnable() {

			
					@Override
					public void run() {

						if (HotPotato.status.getGameStatus() == 2) {

							for (Player p : HotPotato.online) {
								p.setLevel(i);
							}

							i--;

							if (i <= 0) {
								HotPotato.status.GAME.setGameStatus();
								
								for(Player p : HotPotato.online){
									p.setLevel(0);
								}

								Player randomP = HotPotato.online.get(new Random()
										.nextInt(HotPotato.online.size()));

								Potato.givePotato(randomP);
								PotatoCountdown.startCountdown();
								Bukkit.getScheduler().cancelTask(HotPotato.schutzTask);
							}
						}
					}

				}, 0L, 20L);

	}

}
