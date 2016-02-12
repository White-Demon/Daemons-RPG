package ru.tec.classes;

/**
 * @author White2Demon
 * API для создания классов
 * @TODO Не трогать сам сделаю
 */
public interface IClass {
	/**
	 * Атака
	 */
	public int getAttack();
	/**
	 * Вернет уровень защиты
	 */
	public int getProtected();
	/**
	 * Только для лекарей
	 */
	public int onHeal();
}
