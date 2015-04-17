/*******************************************************************************
 * Copyright (c) 2015 Robin Roeder.
 * All rights reserved.
 *******************************************************************************/
package me.rrbk99.hotpotato;

import java.util.ArrayList;
import java.util.List;

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

	public static Location gameSpawn;
	public static Location lobbySpawn;

	public static int minPlayers;
	public static int maxPlayers;

	public static int lobbyTimer;
	public static int schutzTimer;
	public static int endTimer;

	public static GameStatus status;

	public static List<Player> alive = new ArrayList<Player>();

	public static int potatoTask;
	public static int lobbyTask;
	public static int schutzTask;
	public static int endTask;

	public static HotPotato plugin;

	@Override
	public void onEnable() {

		plugin = this;

		loadConfig();

		Potato.init();
		BackToHub.init();
		Potato.hasPotato = null;
		status.LOBBY.setGameStatus();
		LobbyTimer.startLobby();

		pr = "§7[§3HotPotato§7]";

		WorldCreator wcLobby = new WorldCreator("world_lobby");
		WorldCreator wcGame = new WorldCreator("world_game");
		lobbyWorld = getServer().createWorld(wcLobby);
		gameWorld = getServer().createWorld(wcGame);

		lobbyWorld.setAutoSave(false);
		gameWorld.setAutoSave(false);

		lobbySpawn = new Location(lobbyWorld, 453.5, 5.5, -583.5, -90, 0);
		gameSpawn = new Location(gameWorld, 263.5, 4.5, -1234.5, 0, 0);

		minPlayers = getConfig().getInt("Hotpotato.minplayers");
		maxPlayers = getConfig().getInt("Hotpotato.maxplayers");
		lobbyTimer = getConfig().getInt("Hotpotato.lobbytimer");
		schutzTimer = getConfig().getInt("Hotpotato.schutztimer");
		endTimer = getConfig().getInt("Hotpotato.endtimer");

		this.getCommand("lobby").setExecutor(new LobbyCommand());
		this.getCommand("hub").setExecutor(new LobbyCommand());
		this.getCommand("leave").setExecutor(new LobbyCommand());

		Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
		Bukkit.getPluginManager().registerEvents(new Log(), this);
		Bukkit.getPluginManager().registerEvents(new BackToHub(), this);
		Bukkit.getPluginManager().registerEvents(new Potato(), this);
		Bukkit.getPluginManager().registerEvents(new Spectator(), this);

		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		System.out.println("[Hotpotato] Das Plugin wurde geladen!");

	}

	@Override
	public void onDisable() {

		Bukkit.getServer().unloadWorld("world_game", true);
		Bukkit.getServer().unloadWorld("world_lobby", true);

		System.out.println("[Hotpotato] Das Plugin wurde gestoppt!");

	}

	public static HotPotato getInstance() {
		return plugin;
	}

	public void loadConfig() {

		getConfig().addDefault("Hotpotato.minplayers", 2);
		getConfig().addDefault("Hotpotato.maxplayers", 12);

		getConfig().addDefault("Hotpotato.lobbytimer", 60);
		getConfig().addDefault("Hotpotato.schutztimer", 10);
		getConfig().addDefault("Hotpotato.endtimer", 15);

		getConfig().options().copyDefaults(true);
		saveConfig();

	}

	public void restart() {

		Bukkit.shutdown();

	}

}
