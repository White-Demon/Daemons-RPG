package ru.tec.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;

import ru.tec.main.Main;

/**
 * Класс рабочий
 * @author White2Demon
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
	
	private boolean agree;
	
	public User(String name)
	{
		this.setName(name);
	}
	
	public static boolean isUser(String name)
	{
		File file = new File("./users/" +name + ".data");
		return file.isFile();
	}
	
	public static void createDir()
	{
		File file = new File("./users");
		if(file.isDirectory())
		{
			Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is true [00001]");
		}else{
			if(file.mkdir())
			{
				Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is created [00002]");
			}else{
				Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is nope created {EXCEPTION} [00003]");
			}
		}
	}
	
	/**
	 * Загрузка из сериализации
	 * @param name - имя файла
	 */
	public static User load(String name)
	{
		
		createDir();
		
		User obj = null;
			try {
	            FileInputStream fis = new FileInputStream("./users/" +name + ".data");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            obj = (User) ois.readObject();
	            ois.close();
	            fis.close();
	        } catch (IOException i) {
	            i.printStackTrace();
	
	        } catch (ClassNotFoundException c) {
	            System.out.println("CompanyInfoSerializeble class not found");
	            c.printStackTrace();
	        }
		
		return obj;

	}
	/**
	 * Сохранение сериализации
	 * 
	 */
	public void save()
	{
		createDir();
		
		if(name != null)
		{
			
			try {
	            FileOutputStream fos = new FileOutputStream("./users/" + name + ".data");
	            ObjectOutputStream oos = new ObjectOutputStream(fos);
	            oos.writeObject(this); 
	            oos.close();
	            fos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		else{
			throw new NullPointerException("Name => null !!! This bug"); 
		}
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
	}

	public boolean isAgree() {
		return agree;
	}

	public void setAgree(boolean agree) {
		this.agree = agree;
	}

	public String getInvite() {
		return invite;
	}

	public void setInvite(String invite) {
		this.invite = invite;
	}
}
