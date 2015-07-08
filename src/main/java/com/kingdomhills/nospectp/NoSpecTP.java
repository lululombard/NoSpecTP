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
		getServer().getPluginManager().registerEvents(this, this);
	} 
	
	@EventHandler
	public void onTP(PlayerTeleportEvent e) {
		Player player = e.getPlayer();
		if (e.getCause().equals(TeleportCause.SPECTATE)) {
			if (!player.isOp() && !player.hasPermission("nospectp.*") && !player.hasPermission("nospectp." + e.getTo().getWorld().getName())) {
				e.setCancelled(true);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', dnymsg));
			}
		}
	}
}