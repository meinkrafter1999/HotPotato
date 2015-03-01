/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.timer;

import me.rrbk99.hotpotato.HotPotato;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LobbyTimer {

	private static int i;

	public static void startLobby() {

		i = HotPotato.lobbyTimer;

		HotPotato.lobbyTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(
				HotPotato.getInstance(), new Runnable() {

					@Override
					public void run() {

						if (HotPotato.status.getGameStatus() == 1) {

							for (Player p : HotPotato.online) {
								p.setLevel(i);
							}

							if (HotPotato.online.size() > 0) {
								i--;
							} else {
								i = HotPotato.lobbyTimer;
							}

							if (i == 60 || i == 30 || i == 20 || i == 10
									|| i == 9 || i == 8 || i == 7 || i == 6
									|| i == 5 || i == 4 || i == 3 || i == 2
									|| i == 1) {

								for (Player p : HotPotato.online) {
									p.playSound(p.getLocation(),
											Sound.NOTE_PIANO, 1, 0);
									if (i != 1) {
										p.sendMessage(HotPotato.pr
												+ " §7Das Spiel startet in §b"
												+ i + " §7Sekunden.");
									} else {
										p.sendMessage(HotPotato.pr
												+ " §7Das Spiel startet in §b"
												+ i + " §7Sekunde.");
									}
								}

							}

							if (i <= 0) {

								if (HotPotato.online.size() >= HotPotato.minPlayers) {
									HotPotato.status.SCHUTZ.setGameStatus();

									for (Player p : HotPotato.online) {
										p.teleport(HotPotato.gameSpawn);
										p.addPotionEffect(new PotionEffect(
												PotionEffectType.SPEED,
												Integer.MAX_VALUE, 1));
									}

									SchutzTimer.startSchutz();
									PotatoEffects.startCounter();

									Bukkit.getScheduler().cancelTask(
											HotPotato.lobbyTask);
								} else {
									i = HotPotato.lobbyTimer;
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Zu wenige Spieler online.");
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Der Countdown wurde neu gestartet.");
								}
							}
						}
					}

				}, 20L, 20L);

	}

}
