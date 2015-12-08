package ru.tec.database;

import java.io.Serializable;

import ru.tec.main.Main;
import ru.tec.utils.Utils;

/**
 * Класс рабочий
 * @author White2Demon
 * @author DmitriyMX
 *
 */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	private int money=0; // standart var
	
	private int ratingPVP=0,ratingPVE=0;
	
	private String faction = null, invite = null;
	
	public User(String name)
	{
		this.setName(name);
	}
	
	public void addMoney(int money)
	{
		this.money += money;
	}
	
	public void addRating(int type,int value)
	{
		if(type == 1)
		{
			this.ratingPVE += value;
		}
		else if(type == 2)
		{
			this.ratingPVP += value;
		}else{
			Main.log.info("[RPG] ERROR!!!!!!!!!! RATING TYPE IS " + type);
		}
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	@Override
	public String toString()
	{
		return "NAME:" + name + " MONEY:" + money;
	}

	public int getRatingPVP() {
		return ratingPVP;
	}

	public void setRatingPVP(int ratingPVP) {
		this.ratingPVP = ratingPVP;
	}

	public int getRatingPVE() {
		return ratingPVE;
	}

	public void setRatingPVE(int ratingPVE) {
		this.ratingPVE = ratingPVE;
	}

	public String getFaction() {
		return faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
		Utils.cacheFaction.get(faction).addPlayer(this.name);
	}

	public String getInvite() {
		return invite;
	}

	public void setInvite(String invite) {
		this.invite = invite;
	}
}
