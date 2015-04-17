/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

							for (Player p : Bukkit.getOnlinePlayers()) {
								p.setLevel(i);
							}

							if (Bukkit.getOnlinePlayers().size() > 0) {
								i--;
							} else {
								i = HotPotato.lobbyTimer;
							}

							if (Bukkit.getOnlinePlayers().size() > 0) {

								switch (i) {

								case 60:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b60 §7Sekunden.");
									break;
								case 30:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b30 §7Sekunden.");
									break;
								case 20:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b20 §7Sekunden.");
									break;
								case 15:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Spielmodus entwickelt von §bmeinkrafter1999§7.");
									break;
								case 10:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b10 §7Sekunden.");
									break;
								case 9:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b9 §7Sekunden.");
									break;
								case 8:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b8 §7Sekunden.");
									break;
								case 7:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b7 §7Sekunden.");
									break;
								case 6:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b6 §7Sekunden.");
									break;
								case 5:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b5 §7Sekunden.");
									break;
								case 4:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b4 §7Sekunden.");
									break;
								case 3:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b3 §7Sekunden.");
									break;
								case 2:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b2 §7Sekunden.");
									break;
								case 1:
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Das Spiel beginnt in §b1 §7Sekunde.");
									break;

								}

							}

							if (i <= 0) {

								if (Bukkit.getOnlinePlayers().size() >= HotPotato.minPlayers) {

									for (Player p : Bukkit.getOnlinePlayers()) {
										p.teleport(HotPotato.gameSpawn);
										p.addPotionEffect(new PotionEffect(
												PotionEffectType.SPEED,
												Integer.MAX_VALUE, 1));
										p.setGameMode(GameMode.SURVIVAL);
										Manager.clearInventory(p);
									}

									Bukkit.broadcastMessage(HotPotato.pr
											+ " Das Spiel beginnt!");
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Map: §bNoname §7- Buildteam: §bSKAN");

									HotPotato.status.SCHUTZ.setGameStatus();
									SchutzTimer.startSchutz();

									Bukkit.getScheduler().cancelTask(
											HotPotato.lobbyTask);
								} else {
									i = HotPotato.lobbyTimer;
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Zu wenige Spieler online.");
									Bukkit.broadcastMessage(HotPotato.pr
											+ " §7Der Countdown wird restartet.");
								}
							}
						}
					}

				}, 20L, 20L);

	}

}
