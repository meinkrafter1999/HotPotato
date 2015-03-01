/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.timer;

import java.util.Random;

import me.rrbk99.hotpotato.HotPotato;
import me.rrbk99.hotpotato.Potato;

import org.bukkit.Bukkit;

public class PotatoCountdown {

	private static int i;

	public static void startCountdown() {

		Random rdm = new Random();
		int a = rdm.nextInt(10);
		i = a + 10;

		HotPotato.potatoTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(HotPotato.getInstance(),
				new Runnable() {

					@Override
					public void run() {

						if (HotPotato.status.getGameStatus() == 3) {

							i--;

							if (i <= 0) {
								Potato.explode();
							}
						}
					}

				}, 0L, 20L);

	}

}
