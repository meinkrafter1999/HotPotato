/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.timer;

import me.rrbk99.hotpotato.HotPotato;
import me.rrbk99.hotpotato.Potato;
import me.rrbk99.hotpotato.manager.GameStatus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SchutzTimer {

	private static int i;

	public static void startSchutz() {

		i = HotPotato.schutzTimer;

		HotPotato.schutzTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(
				HotPotato.getInstance(), new Runnable() {

					@Override
					public void run() {

						if (HotPotato.status == GameStatus.SCHUTZ) {

							i--;

							for (Player p : Bukkit.getOnlinePlayers()) {
								p.setLevel(i);
							}

							if (i <= 0) {

								HotPotato.status.GAME.setGameStatus();

								Potato.giveRandom();
								Bukkit.getScheduler().cancelTask(
										HotPotato.schutzTask);
							}
						}
					}

				}, 0L, 20L);

	}

}
