package me.rrbk99.hotpotato;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Spectator implements Listener {
	private static List<Player> spectators = new ArrayList<Player>();
	private static Location spectatorSpawn;

	public Spectator() {
		spectatorSpawn = new Location(HotPotato.gameWorld, 263.5, 8, -1234.5, 0, 0);
	}

	public static void make(Player p) {
		for (Player on : Bukkit.getOnlinePlayers()) {
			on.hidePlayer(p);
		}
		spectators.add(p);
		p.setGameMode(GameMode.CREATIVE);
		p.setAllowFlight(true);
		p.setFlying(true);
		p.teleport(Spectator.spectatorSpawn);
		Manager.clearInventory(p);
	}

	public static void remove(Player p) {
		for (Player on : Bukkit.getOnlinePlayers()) {
			on.showPlayer(p);
		}
		spectators.remove(p);
		p.setGameMode(GameMode.SURVIVAL);
		p.setAllowFlight(false);
		p.setFlying(false);
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		String p = e.getPlayer().getName();
		if (spectators.contains(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		String p = e.getPlayer().getName();
		if (spectators.contains(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		String p = e.getPlayer().getName();
		if (spectators.contains(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onItemPickUp(PlayerPickupItemEvent e) {
		String p = e.getPlayer().getName();
		if (spectators.contains(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		String p = e.getPlayer().getName();
		if (spectators.contains(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityInteract(PlayerInteractEntityEvent e) {
		String p = e.getPlayer().getName();
		if (spectators.contains(p)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (spectators.contains(p))
				e.setCancelled(true);
		} else if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (spectators.contains(p))
				e.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (spectators.contains(p))
				e.setCancelled(true);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (HotPotato.status == GameStatus.GAME
				|| HotPotato.status == GameStatus.END) {
			Spectator.make(e.getPlayer());
			e.setJoinMessage(null);
			e.getPlayer().sendMessage(
					HotPotato.pr + " §7Du bist nun §bSpectator§7!");
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (HotPotato.status == GameStatus.GAME
				|| HotPotato.status == GameStatus.END) {
			if (Spectator.spectators.contains(e.getPlayer())) {
				Spectator.remove(e.getPlayer());
				e.setQuitMessage(null);
			}
		}
	}
}
