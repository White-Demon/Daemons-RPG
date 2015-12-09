package ru.tec.utils;

import java.io.File;
import java.util.HashMap;

import ru.tec.core.Faction;
import ru.tec.database.FileStorage;
import ru.tec.database.IStorage;
import ru.tec.core.User;

/**
 * 
 * @author White2Demon
 * @author DmitriyMX
 *
 */
public class Utils {
	private static IStorage storage;
	
	public static final HashMap<String,User> cache = new HashMap<>();
	
	public static final HashMap<String,Faction> cacheFaction = new HashMap<>();
	
	public static void loadFaction()
	{
		File list = new File("./faction/");
		
		for(int i=0;i<list.listFiles().length;++i)
		{
			cacheFaction.put(getStorage().loadFaction(list.listFiles()[i].getName()).getName(), getStorage().loadFaction(list.listFiles()[i].getName()));
			
		}
		
	}
	
	public static IStorage getStorage() {
		if (storage == null) {
			storage = new FileStorage();
		}

		return storage;
	}
}
