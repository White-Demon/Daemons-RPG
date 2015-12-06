package ru.tec.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import ru.tec.database.User;
import ru.tec.main.Main;
import ru.tec.utils.Utils;

/**
 * Класс для кланов
 * @author White2Demon
 *
 */
public class Faction implements Serializable{
	
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
		
		File file = new File("./faction/" + name + ".data");
		if(file.isFile())
		{
			file.delete();
		}
		
	}
	
	public void setLocation(Location location)
	{
		this.location = location;
	}
	
	public void addPlayer(String player)
	{
		this.player.add(Bukkit.getPlayer(player));
	}
	
	public void delPlayer(String player)
	{
		this.player.remove(Bukkit.getPlayer(player));
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
	
	public static boolean isFaction(String name)
	{
		File file = new File("./faction/" + name + ".data");
		return file.isFile();
	}
	
	public static void createDir()
	{
		File file = new File("./faction");
		if(file.isDirectory())
		{
			Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is true [00010]");
		}else{
			if(file.mkdir())
			{
				Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is created [00011]");
			}else{
				Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is nope created {EXCEPTION} [00012]");
			}
		}
	}
	
	public static Faction load(String name)
	{
		
		createDir();
		
		Faction obj = null;
			try {
	            FileInputStream fis = new FileInputStream("./faction/" +name + ".data");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            obj = (Faction) ois.readObject();
	            ois.close();
	            fis.close();
	        } catch (IOException i) {
	            i.printStackTrace();
	
	        } catch (ClassNotFoundException c) {
	            System.out.println("CompanyInfoSerializeble class not found");
	            c.printStackTrace();
	        }
		
		return obj;

	}
	/**
	 * Сохранение сериализации
	 * 
	 */
	public void save()
	{
		createDir();
		
		if(name != null)
		{
			
			try {
	            FileOutputStream fos = new FileOutputStream("./faction/" + name + ".data");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(this); 
	            oos.close();
	            fos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		else{
			throw new NullPointerException("Name => null !!! This bug [FACTION] "); 
		}
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
	
}
