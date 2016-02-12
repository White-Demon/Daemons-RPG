package ru.tec.classes;

/**
 * 
 * @author White2Demon
 *
 */
public class HealerClass implements IClass {
	
	public HealerClass()
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
