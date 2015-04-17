package me.rrbk99.hotpotato;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BackToHub implements Listener {

	public static Integer itemSlot;
	public static ItemStack item = new ItemStack(Material.STAINED_CLAY, 1,
			(short) 3);

	public static void init() {

		itemSlot = 8;

		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName("§3Zurück zum Hub");
		item.setItemMeta(itemMeta);

	}

	@EventHandler
	public void on(PlayerInteractEvent e) {

		Player p = e.getPlayer();

		if (e.getAction().equals(Action.RIGHT_CLICK_AIR)
				|| e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			if (p.getItemInHand().equals(item)) {
				p.chat("/hub");
			}

		}

	}

	public static void giveItem(Player p) {

		p.getInventory().setItem(itemSlot, item);

	}

	@EventHandler
	public void on(BlockPlaceEvent e) {

		Player p = e.getPlayer();

		if (p.getItemInHand().equals(item)) {
			e.setCancelled(true);
		}

	}

}
