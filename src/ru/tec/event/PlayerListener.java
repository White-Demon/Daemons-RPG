package ru.tec.event;

import java.util.Random;
import org.bukkit.ChatColor;

import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import ru.tec.core.User;
import ru.tec.utils.Utils;
/**
 * 
 * @author White2Demon
 * @author DmitriyMX
 *
 */
public class PlayerListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GREEN  + "[RPG] Загружаем ваш аккаунт...");
		
		if(Utils.getStorage().existsUser(player.getName()))
		{
			Utils.cache.put(player.getName(), Utils.getStorage().loadUser(player.getName()));
		}else{
			Utils.cache.put(player.getName(), new User(player.getName()));
		}
		
		player.sendMessage(ChatColor.GREEN  + "[RPG] Ваш аккаунт успешно загружен!");
		
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();

		Utils.getStorage().saveFaction(Utils.cache.get(player.getName()));
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
