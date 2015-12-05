package ru.tec.event;

import java.util.Random;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import net.md_5.bungee.api.ChatColor;

import ru.tec.database.User;
import ru.tec.utils.Utils;
/**
 * 
 * @author White2Demon
 *
 */
public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GREEN  + "[RPG] Загружаем ваш аккаунт...");
		
		if(User.isUser(player.getName()))
		{
			Utils.cache.put(player.getName(), User.load(player.getName()));
		}else{
			Utils.cache.put(player.getName(), new User(player.getName()));
		}
		
		player.sendMessage(ChatColor.GREEN  + "[RPG] Ваш аккаунт успешно загружен!");
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		
		Utils.cache.get(player.getName()).save();
		Utils.cache.remove(player.getName());
		
	}
	
	@EventHandler
	public void onKilledEntity(EntityDeathEvent event)
	{
		if(event.getEntity() instanceof Monster)
		{
			if(event.getEntity().getKiller() instanceof Player)
			{
				User user = Utils.cache.get(event.getEntity().getKiller().getName());
				user.addMoney(new Random().nextInt(10));
				user.addRating(1, 2);
				
				event.getEntity().getKiller().sendMessage(ChatColor.GREEN + "[RPG] Вам начислили рейтинг и дали золото.");
			}
		}
	}
	
	
}
