/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato.timer;

import me.rrbk99.hotpotato.HotPotato;
import me.rrbk99.hotpotato.Potato;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class PotatoEffects {

	public static void startCounter() {

		Bukkit.getScheduler().scheduleSyncRepeatingTask(
				HotPotato.getInstance(), new Runnable() {

					@Override
					public void run() {

						if (Potato.hasPotato != null) {

							for (Player p : HotPotato.online) {
								p.playSound(Potato.hasPotato.getLocation(),
										Sound.CREEPER_HISS, 2, 0);
								p.playSound(Potato.hasPotato.getLocation(),
										Sound.FIRE, 2, 0);
							}

							Potato.hasPotato.playEffect(
									Potato.hasPotato.getLocation(),
									Effect.MOBSPAWNER_FLAMES, 0);

							Potato.hasPotato.playEffect(
									Potato.hasPotato.getLocation(),
									Effect.SMOKE, 0);

							final Firework firework = Potato.hasPotato
									.getWorld().spawn(
											Potato.hasPotato.getLocation(),
											Firework.class);
							FireworkMeta data = (FireworkMeta) firework
									.getFireworkMeta();
							data.addEffects(FireworkEffect.builder()
									.withColor(Color.RED).with(Type.BALL)
									.build());
							data.setPower(2);
							firework.setFireworkMeta(data);

							Bukkit.getScheduler().scheduleSyncDelayedTask(
									HotPotato.getInstance(), new Runnable() {

										public void run() {
											firework.detonate();
										}
									}, 5L);

						}

					}

				}, 20L, 10L);

	}

}
