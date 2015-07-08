package com.kingdomhills.nospectp;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.java.JavaPlugin;

public class NoSpecTP extends JavaPlugin implements Listener {
	
	String dnymsg;
	
	@Override
	public void onEnable() {
		saveDefaultConfig();
		dnymsg = getConfig().getString("deny-msg");
	} 
	
	@EventHandler
	public void onTP(PlayerTeleportEvent e) {
		if (e.getCause().equals(TeleportCause.SPECTATE)) {
			Player player = e.getPlayer();
			if (!player.isOp() && !player.hasPermission("nospectp.*") && player.hasPermission("nospectp." + e.getTo().getWorld().getName())) {
				e.setCancelled(true);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', dnymsg));
			}
		}
	}
}