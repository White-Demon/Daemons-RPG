package ru.tec.event;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import ru.tec.main.Main;
/**
 * 
 * @author White2Demon
 *
 * Обработка спавна мобов
 */
public class SpawnListener implements Listener{
	
	private final Random rnd = new Random();
	
	/**
	 * Главное событие
	 * @param spawn 
	 */
	@EventHandler
	public void onSpawn(EntitySpawnEvent spawn)
	{
		Entity ent = spawn.getEntity();
		
		switch(ent.getType())
		{
			case ZOMBIE:
				Zombie zom = (Zombie) ent;
				this.modZombie(zom);
			break;
			case SKELETON:
				Skeleton sk = (Skeleton) ent;
				this.modSkeleton(sk);
			break;
			case BLAZE:
				
				break;
			case BOAT:
				
				break;
			case CAVE_SPIDER:
				
				break;
			case CREEPER:
				break;
			case ENDERMAN:
				Enderman ender = (Enderman) ent;
				this.modEnder(ender);
				break;
			case ENDER_DRAGON:
				
				break;
			case GHAST:
				
				break;
			case GIANT:
				
				break;
			case IRON_GOLEM:
				
				break;
			case PIG_ZOMBIE:
				
				break;
			case SPIDER:
				
				break;
			case WEATHER:
				
				break;
			case WITCH:
				
				break;
	
			case WOLF:
				
				break;
			default:
				
				break;
			}
		
	}
	
	private void modZombie(Zombie zombie)
	{
		int id = rnd.nextInt(5);
		
		switch(id)
		{
			case 0:
				zombie.setCustomName("Зомби" + ChatColor.AQUA + " [1 уровень]");
				zombie.getEquipment().setItemInHand(new ItemStack(Material.STONE_SWORD,1));
				zombie.setVillager(true);
			break;
			case 1:
				zombie.setCustomName("Беглый зомби" + ChatColor.GRAY + " [3 уровень]");
				zombie.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD,1));
				zombie.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET,1));
				zombie.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS,1));
				zombie.setVillager(true);
			break;
			case 2:
				zombie.setCustomName("Умный зомби" + ChatColor.YELLOW + " [6 уровень]");
				zombie.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD,1));
				zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET,1));
				zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS,1));
				zombie.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE,1));
				zombie.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS,1));
				zombie.setVillager(true);
			break;
			case 3:
				zombie.setCustomName("Паразоид" + ChatColor.GOLD + " [9 уровень]");
				zombie.getEquipment().setItemInHand(new ItemStack(Material.GOLD_SWORD,1));
				zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET,1));
				zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS,1));
				zombie.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE,1));
				zombie.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS,1));
				zombie.setVillager(true);
			break;
			
			case 4:
				ItemStack bow = new ItemStack(Material.DIAMOND_SWORD,1);
				ItemMeta met = bow.getItemMeta();
				met.addEnchant(Enchantment.KNOCKBACK, 3 ,true);
				met.addEnchant(Enchantment.FIRE_ASPECT,	 3, true);
				met.setDisplayName(ChatColor.RED + "Лук огня Лорда.");
				bow.setItemMeta(met);
				
				zombie.setCustomName("Героидон" + ChatColor.BLACK + " [БОСС]");
				zombie.getEquipment().setItemInHand(bow);
				zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET,1));
				zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS,1));
				zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE,1));
				zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS,1));
				zombie.setVillager(true);
			break;
			default:
				Main.log.info("[RPG] Bug spawn zombie 5 > id < 0");
			break;
		}
		
		
	}
	
	private void modSkeleton(Skeleton skeleton)
	{
		int id = rnd.nextInt(5);
		
		switch(id)
		{
			case 0:
				skeleton.setCustomName("Скелет " + ChatColor.AQUA + "[1 уровень]");
				skeleton.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET,1));
				skeleton.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS,1));
			break;
			case 1:
				skeleton.setCustomName("Скелет мечник" + ChatColor.GRAY + "[4 уровень]");
				skeleton.getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET,1));
				skeleton.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS,1));
				skeleton.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE,1));
				skeleton.getEquipment().setItemInHand(new ItemStack(Material.IRON_SWORD,1));
			break;
			case 2:
				skeleton.setCustomName("Скелет ордена " + ChatColor.YELLOW + "[8 уровень]");
				skeleton.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET,1));
				skeleton.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS,1));
				skeleton.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE,1));
				skeleton.getEquipment().setItemInHand(new ItemStack(Material.GOLD_SWORD,1));
			break;
			case 3:
				skeleton.setCustomName("Скелет ордена лучник " + ChatColor.GOLD + "[12 уровень]");
				skeleton.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET,1));
				skeleton.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS,1));
				skeleton.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS,1));
				skeleton.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE,1));
				skeleton.getEquipment().setItemInHand(new ItemStack(Material.BOW,1));
			break;
			case 4:
				
				ItemStack bow = new ItemStack(Material.BOW,1);
				ItemMeta met = bow.getItemMeta();
				met.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
				met.addEnchant(Enchantment.ARROW_FIRE,	 2, true);
				met.setDisplayName(ChatColor.RED + "Лук огня Лорда.");
				bow.setItemMeta(met);
				
				skeleton.setCustomName("Лорд ордена " + ChatColor.BLACK + "[БОСС]");
				skeleton.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET,1));
				skeleton.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS,1));
				skeleton.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS,1));
				skeleton.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE,1));
				skeleton.getEquipment().setItemInHand(bow);
			break;
			default:
				Main.log.info("[RPG] Bug spawn skeleton 5 > id < 0");
			break;
		}
		
	}
	
	private void modEnder(Enderman ender)
	{
		int id = rnd.nextInt(5);
		
		switch(id)
		{
			case 0:
				ender.setCustomName("Темный " + ChatColor.AQUA +" [1 уровень]");
				ender.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET,1));
				ender.getEquipment().setItemInHand(new ItemStack(Material.IRON_AXE,1));
			break;
			case 1:
				ender.setCustomName("Темный ИО " + ChatColor.GRAY + "[3 уровень]");
				ender.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET,1));
				ender.getEquipment().setItemInHand(new ItemStack(Material.IRON_AXE,1));
				ender.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS,1));
			break;
			case 2:
				
			break;
			case 3:
				
			break;
			case 4:
				
			break;
			default:
				Main.log.info("[RPG] Bug spawn enderman 5 > id < 0");
			break;
		}
	}
	
	private void modSpider(Spider spider)
	{
		int id = rnd.nextInt(5);
		
		switch(id)
		{
			case 0:
			
			break;
			case 1:
				
			break;
			case 2:
				
			break;
			case 3:
				
			break;
			case 4:
				
			break;
			default:
				Main.log.info("[RPG] Bug spawn sprider 5 > id < 0");
			break;
		}
	}
	
	private void modZombiePig(PigZombie zp)
	{
		int id = rnd.nextInt(5);
		
		switch(id)
		{
			case 0:
			
			break;
			case 1:
				
			break;
			case 2:
				
			break;
			case 3:
				
			break;
			case 4:
				
			break;
			default:
				Main.log.info("[RPG] Bug spawn zombie pig 5 > id < 0");
			break;
		}
	}
	
	private void modDog(Wolf dog)
	{
		int id = rnd.nextInt(5);
		
		switch(id)
		{
			case 0:
			
			break;
			case 1:
				
			break;
			case 2:
				
			break;
			case 3:
				
			break;
			case 4:
				
			break;
			default:
				Main.log.info("[RPG] Bug spawn wolf 5 > id < 0");
			break;
		}
	}
}
