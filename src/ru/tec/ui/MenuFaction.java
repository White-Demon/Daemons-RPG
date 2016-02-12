package ru.tec.ui;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

/**
 * 
 * @author White2Demon
 * Меню для кланов
 */
public class MenuFaction extends IMenu {
	
	
	private Inventory inv;
	
	public MenuFaction(Plugin p) {
		super(p);
		inv = Bukkit.getServer().createInventory(null, 9,"Управление кланом");
		
		// TODO Продолжить
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if(!event.getInventory().equals(inv)) return;
		if(event.getCurrentItem().getItemMeta() == null) return;
	}

	@Override
	public Inventory getMenu() {
		return inv;
	}
	
}
