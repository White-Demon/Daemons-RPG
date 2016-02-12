package ru.tec.ui;

import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class MenuSelectedClass extends IMenu {

	private Inventory inv;
	
	public MenuSelectedClass(Plugin p) {
		super(p);
	}

	@Override
	public Inventory getMenu() {
		return inv;
	}

}
