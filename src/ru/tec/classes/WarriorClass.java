package ru.tec.classes;

import org.bukkit.entity.Player;

/**
 * Варвар
 * @author White2Demon
 *
 */
public class WarriorClass implements IClass {
	
	public WarriorClass(Player player)
	{
		
	}

	@Override
	public int getAttack() {
		return 0;
	}

	@Override
	public int getProtected() {
		return 0;
	}

	@Override
	public int onHeal() {
		return 0;
	}

}
