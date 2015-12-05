package ru.tec.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import ru.tec.core.Faction;
import ru.tec.database.User;
import ru.tec.event.PlayerListener;
import ru.tec.event.SpawnListener;
import ru.tec.utils.Config;
import ru.tec.utils.Utils;

/**
 * 
 * @author White2Demon
 *
 */
public class Main extends JavaPlugin {
	
	public static Logger log = Bukkit.getLogger();
	
	@Override
	public void onEnable()
	{
		Config.load();
		log.info("[RPG] Enabled plugin. [00005]");
		
		Bukkit.getPluginManager().registerEvents(new SpawnListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
	}
	
	@Override
	public void onDisable()
	{
		log.info("[RPG] Disable plugin . [00006]");
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender,Command command,String label,String[] args)
	{
		Player player = null;
		
		if(sender instanceof Player)
		{
			player = (Player) sender;
			User user = Utils.cache.get(player.getName());
			
			if(command.getName().equals("clan"))
			{
				if(args.length > 2)
				{
					if(args[0].equals("invite"))
					{
						if(Utils.cacheFaction.get(user.getFaction()).getLeader().equals(player))
						{
							Utils.cacheFaction.get(user.getFaction()).addPlayer(args[1]);
							player.sendMessage(ChatColor.GREEN + "[RPG] ����� ��������� � ��� ����.");
							return true;
						}
					}
					
					if(args[0].equals("spawn"))
					{
						player.teleport(Utils.cacheFaction.get(user.getFaction()).getWorld().getSpawnLocation());
						player.sendMessage(ChatColor.GREEN + "[RPG] �� ��������������� � ��� �����.");
					}
					
					if(args[0].equals("create"))
					{
						if(user.getMoney() >= Config.countFaction)
						{
							// args[1] = name faction
							if(!Faction.isFaction(args[1]))
							{
								Faction faction = new Faction();
								faction.newFaction(args[1], player, Bukkit.createWorld(new WorldCreator(args[1])));
								player.teleport(faction.getWorld().getSpawnLocation());
								player.sendMessage(ChatColor.GREEN + "[RPG] ��� ���� ������� ������.");
								return true;
							}else{
								player.sendMessage(ChatColor.RED + "[RPG] ����� ���� ��� ������.");
								return true;
							}
						}else{
							player.sendMessage(ChatColor.RED + "[RPG] � ��� ������������ ������� ��� �������� �����");
							player.sendMessage(ChatColor.RED + "[RPG] ��� �� ���������� ��� �������:" + (Config.countFaction - user.getMoney()));
							return true;
						}
					}
				}
			}
			
			if(command.getName().equals("rpg"))
			{
				if(args.length > 0)
				{
					if(args[0].equals("balance"))
					{
						player.sendMessage(ChatColor.GREEN  + "[RPG] ��� ������:" + user.getMoney() + " �������.");
						return true;
					}
					if(args[0].equals("rating"))
					{
						if(args.length > 1)
						{
							// TODO a top rating
						}else{
							player.sendMessage(ChatColor.GREEN + "[RPG] ��� PVE �������:" + user.getRatingPVE() );
							player.sendMessage(ChatColor.GREEN + "[RPG] ��� PVP �������:" + user.getRatingPVP());
							return true;
						}
					}
				}else{
					player.sendMessage(ChatColor.RED + "[RPG] �� �� ����� ��������� �������.");
					return false;
				}
			}
		}
		return false;
	}
	
}