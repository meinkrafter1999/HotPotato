/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato;

import java.util.ArrayList;
import java.util.List;

import me.rrbk99.hotpotato.cmd.LobbyCommand;
import me.rrbk99.hotpotato.events.BlockBreak;
import me.rrbk99.hotpotato.events.BlockPlace;
import me.rrbk99.hotpotato.events.Drop;
import me.rrbk99.hotpotato.events.EntityDamageByEntity;
import me.rrbk99.hotpotato.events.FoodLevelChange;
import me.rrbk99.hotpotato.events.InventoryClick;
import me.rrbk99.hotpotato.events.PlayerDamage;
import me.rrbk99.hotpotato.events.PlayerInteract;
import me.rrbk99.hotpotato.events.PlayerJoin;
import me.rrbk99.hotpotato.events.PlayerQuit;
import me.rrbk99.hotpotato.events.ServerPing;
import me.rrbk99.hotpotato.timer.LobbyTimer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HotPotato extends JavaPlugin {

	public static String pr;

	public static World lobbyWorld;
	public static World gameWorld;

	public static Location spectateSpawn;
	public static Location gameSpawn;
	public static Location lobbySpawn;

	public static int minPlayers;
	public static int maxPlayers;

	public static int lobbyTimer;
	public static int schutzTimer;
	public static int gameTimer;
	public static int endTimer;

	public static GameStatus status;

	public static List<Player> online = new ArrayList<Player>();
	public static List<Player> spectators = new ArrayList<Player>();
	public static List<Player> wasInGame = new ArrayList<Player>();

	public static int potatoTask;
	public static int lobbyTask;
	public static int schutzTask;
	public static int gameTask;
	public static int endTask;

	public static HotPotato plugin;

	@Override
	public void onEnable() {

		plugin = this;

		loadConfig();

		InventoryManager.init();
		Potato.hasPotato = null;
		status.LOBBY.setGameStatus();
		LobbyTimer.startLobby();

		pr = "§7[§3HotPotato§7]";
		
		WorldCreator wcLobby = new WorldCreator("world_lobby");
		WorldCreator wcGame = new WorldCreator("world_game");
		lobbyWorld = getServer().createWorld(wcLobby);
		gameWorld = getServer().createWorld(wcGame);
		
		lobbySpawn = new Location(lobbyWorld, -7, 72, 145);
		gameSpawn = new Location(gameWorld, 32, 33, 34);
		spectateSpawn = new Location(gameWorld, 32, 46, 68);

		minPlayers = getConfig().getInt("Hotpotato.minplayers");
		maxPlayers = getConfig().getInt("Hotpotato.maxplayers");
		lobbyTimer = getConfig().getInt("Hotpotato.lobbytimer");
		schutzTimer = getConfig().getInt("Hotpotato.schutztimer");
		gameTimer = getConfig().getInt("Hotpotato.gametimer");
		endTimer = getConfig().getInt("Hotpotato.endtimer");

		this.getCommand("lobby").setExecutor(new LobbyCommand());
		this.getCommand("hub").setExecutor(new LobbyCommand());
		this.getCommand("leave").setExecutor(new LobbyCommand());

		Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
		Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
		Bukkit.getPluginManager().registerEvents(new Drop(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamageByEntity(),
				this);
		Bukkit.getPluginManager().registerEvents(new FoodLevelChange(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDamage(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
		Bukkit.getPluginManager().registerEvents(new ServerPing(), this);

		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		System.out.println("[Hotpotato] Das Plugin wurde geladen!");

	}

	@Override
	public void onDisable() {

		System.out.println("[Hotpotato] Das Plugin wurde gestoppt!");

	}

	public static HotPotato getInstance() {
		return plugin;
	}

	public void loadConfig() {

		getConfig().addDefault("Hotpotato.minplayers", 6);
		getConfig().addDefault("Hotpotato.maxplayers", 12);

		getConfig().addDefault("Hotpotato.lobbytimer", 120);
		getConfig().addDefault("Hotpotato.schutztimer", 12);
		getConfig().addDefault("Hotpotato.gametimer", 900);
		getConfig().addDefault("Hotpotato.endtimer", 12);

		getConfig().options().copyDefaults(true);
		saveConfig();

	}

	public void restart() {

		Bukkit.shutdown();

	}

}
