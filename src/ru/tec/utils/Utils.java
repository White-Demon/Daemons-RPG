package ru.tec.utils;

import java.io.File;
import java.util.HashMap;

import ru.tec.core.Faction;
import ru.tec.database.User;

/**
 * 
 * @author White2Demon
 *
 */
public class Utils {	
	
	public static final HashMap<String,User> cache = new HashMap<>();
	
	public static final HashMap<String,Faction> cacheFaction = new HashMap<>();
	
	public static void loadFaction()
	{
		File list = new File("./faction/");
		
		for(int i=0;i<list.listFiles().length;++i)
		{
			cacheFaction.put(Faction.load(list.listFiles()[i].getName()).getName(), Faction.load(list.listFiles()[i].getName()));
			
		}
		
	}
	

}
