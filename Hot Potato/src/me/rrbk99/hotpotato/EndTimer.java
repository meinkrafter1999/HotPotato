/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EndTimer {

	private static int i;

	public static void startEnd() {

		i = HotPotato.endTimer;

		Player winner = HotPotato.alive.get(0);

		Bukkit.broadcastMessage(HotPotato.pr + " §3" + winner.getDisplayName()
				+ " §7hat das Spiel gewonnen.");
		Bukkit.broadcastMessage(HotPotato.pr
				+ " §7Der Server startet in §b10 §7Sekunden neu.");

		HotPotato.endTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(
				HotPotato.getInstance(), new Runnable() {

					@Override
					public void run() {

						if (HotPotato.status.getGameStatus() == 4) {

							i--;

							if (i == 5) {

								for (Player p : Bukkit.getOnlinePlayers()) {
									p.chat("/hub");
								}

							}

							if (i == 0) {
								HotPotato.getInstance().restart();
								Bukkit.getScheduler().cancelTask(HotPotato.endTask);
							}
						}
					}

				}, 20L, 20L);

	}
}
