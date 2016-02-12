package ru.tec.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
/**
 * 
 * @author White2Demon
 * Обработчик событий получение урона 
 * 
 */
public class DamageListener implements Listener{
	
	/**
	 * Formule  d((damage + crit + damageProtect) - (protect / 10))
	 * @param event
	 */
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event)
	{
		
		
		
	}
	/**
	 * 
	 * @param damage
	 * @param protect
	 * @param crit
	 * @return
	 */
	public int getFormule(int damage,int protect,int crit)
	{
		return (damage*2 / 10) - (protect / 10);
	}
	
}
