/**
 * 
 */
package ru.tec.ui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;

/**
 * @author White2Demon
 * API для создания меню(UI)
 * 
 */
public abstract class IMenu implements Listener{
	
	public IMenu(Plugin p)
	{
		Bukkit.getPluginManager().registerEvents(this, p);
	}
	
	public abstract Inventory getMenu();
	
	/**
	 * Открываем меню игроку
	 * @param player
	 */
	public void show(Player player)
	{
		player.openInventory(getMenu());
	}
	/**
	 * Создаем предмет в меню
	 * @param item
	 * @param name
	 * @param description
	 * @return
	 */
	protected ItemStack addItem(MaterialData item,String name,String description)
	{
		ItemStack items = item.toItemStack();
		
		ItemMeta meta = items.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(description));
		items.setItemMeta(meta);
		
		return items;
	}
	
	
}
