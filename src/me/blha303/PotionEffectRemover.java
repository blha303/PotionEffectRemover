package me.blha303;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

public class PotionEffectRemover extends JavaPlugin {
	
	public Logger log;

	@Override
	public void onEnable() {
		log = this.getLogger();
		log.info("Enabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean hasarg = false;
		Player p = null;
		if (args.length == 1) {
			hasarg = true;
			p = this.getServer().getPlayer(args[0]);
		}
		
		if (sender instanceof Player) {
			if (!((Player)sender).hasPermission("command.rmeffect")) {
				sender.sendMessage("You can't use this command.");
				return true;
			}
		}
		for (Player player : this.getServer().getOnlinePlayers()) {
			for (PotionEffect effect : player.getActivePotionEffects()) {
				if (hasarg && p != null) {
						p.removePotionEffect(effect.getType());
				} else {
					player.removePotionEffect(effect.getType());
				}
			}
		}
		getServer().dispatchCommand(sender, "effect night_vision all");
		sender.sendMessage("Effects removed.");
		
		return true;
	}
}
