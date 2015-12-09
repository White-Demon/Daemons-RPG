package ru.tec.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import ru.tec.database.User;
import ru.tec.utils.Utils;

/**
 * Класс для кланов
 * @author White2Demon
 * @author DmitriyMX
 *
 */
public class Faction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private Player lead; // Глава клана
	private World world; // Мир клана
	private Location location; // spawn
	
	private List<Player> player = new ArrayList<>();
	private List<Faction> statusWar = new ArrayList<>();
	private List<Faction> statusFriend = new ArrayList<>();
	
	public void newFaction(String name, Player lead,World world)
	{
		this.name = name;
		this.lead = lead;
		this.world = world;
	}
	
	public void delFaction(String name)
	{
		for(int i=0;i<player.size();i++)
		{
			User user = Utils.cache.get(player.get(i).getName());
			user.setFaction(null);
		}
		Utils.cache.get(lead.getName()).setFaction(null);;
		
		Utils.getStorage().deleteFaction(name);
		
	}
	
	public void setLocation(Location location)
	{
		this.location = location;
	}
	
	public void addPlayer(String name)
	{
		Player player = Bukkit.getPlayer(name);
		
		//User user = Utils.cache.get(player.getName());
		
		this.player.add(player);
		
	}
	
	public void delPlayer(String name)
	{
		Player player = Bukkit.getPlayer(name);
		Utils.cache.get(player.getName()).setFaction(null);
		this.player.remove(player);
	}

	public void war(Faction faction)
	{
		this.statusWar.add(faction);
	}
	
	public void unWar(Faction faction)
	{
		this.statusWar.remove(faction);
	}
	
	public void friend(Faction faction)
	{
		this.statusFriend.add(faction);
	}
	
	public void unFriend(Faction faction)
	{
		this.statusFriend.remove(faction);
	}

	public void teleportToFactionWorld(Player player)
	{
		player.teleport(location);
	}
	
	public World getWorld()
	{
		return this.world;
	}
	
	public Player getLeader()
	{
		return lead;
	}
	
	public String getName()
	{
		return name;
	}
}
