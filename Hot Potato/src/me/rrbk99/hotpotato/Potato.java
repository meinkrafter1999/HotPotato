/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato;

import java.util.Random;

import me.rrbk99.hotpotato.manager.GameStatus;
import me.rrbk99.hotpotato.manager.Manager;
import me.rrbk99.hotpotato.timer.EndTimer;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Potato implements Listener {

	public static ItemStack item = new ItemStack(Material.BAKED_POTATO, 1,
			(short) 0);
	public static Player hasPotato;
	public static int i;

	public static void init() {
		ItemMeta potatoMeta = item.getItemMeta();
		potatoMeta.setDisplayName("§c--] §4Potato §c[--");
		item.setItemMeta(potatoMeta);
		startParticles();
	}

	public static void givePotato(Player p) {

		hasPotato = p;
		hasPotato.playSound(hasPotato.getLocation(), Sound.GHAST_SCREAM, 2, 0);
		p.getInventory().setHelmet(new ItemStack(Material.TNT, 1));
		p.getInventory().setItem(0, item);

	}

	public static void explode() {
		/*
		 * HotPotato.gameWorld.createExplosion(hasPotato.getLocation().getX(),
		 * hasPotato.getLocation().getY(), hasPotato.getLocation().getZ(), 4F,
		 * true, false);
		 */

		Manager.playAll(Sound.EXPLODE);

		HotPotato.alive.remove(hasPotato);
		Spectator.make(hasPotato);

		hasPotato = null;

		if (HotPotato.alive.size() > 1) {
			giveRandom();
		} else {
			HotPotato.status.END.setGameStatus();
			EndTimer.startEnd();
		}

	}

	public static void startCountdown() {

		Random rdm = new Random();
		int a = rdm.nextInt(15);
		i = a + 15;

		HotPotato.potatoTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(
				HotPotato.getInstance(), new Runnable() {

					@Override
					public void run() {

						if (HotPotato.status == GameStatus.GAME) {

							i--;

							if (i <= 0) {
								Potato.explode();
							}
						}
					}

				}, 0L, 20L);

	}

	public static void startParticles() {

		Bukkit.getScheduler().scheduleSyncRepeatingTask(
				HotPotato.getInstance(), new Runnable() {

					@SuppressWarnings("deprecation")
					@Override
					public void run() {

						if (Potato.hasPotato != null) {

							for (Player p : Bukkit.getOnlinePlayers()) {
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

	@EventHandler
	public void on(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			Player damager = (Player) e.getDamager();
			Player damaged = (Player) e.getEntity();
			if (Potato.hasPotato == damager) {
				Manager.clearInventory(damager);
				givePotato(damaged);
			}

		}
		e.setCancelled(true);
	}

	public static void giveRandom() {
		Player randomP = HotPotato.alive.get(new Random()
				.nextInt(HotPotato.alive.size()));
		Potato.givePotato(randomP);
		Bukkit.getScheduler().cancelTask(HotPotato.potatoTask);
		startCountdown();
	}

}
