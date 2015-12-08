package ru.tec.database;

import com.google.common.base.Preconditions;
import ru.tec.core.Faction;
import ru.tec.main.Main;

import java.io.*;
import java.util.logging.Level;

/**
 * @author DmitriyMX
 */
public class FileStorage implements IStorage {
    private boolean is_init = false;

    @Override
    public void init() {
        if (is_init) return;

        //TODO необходимо оптимизировать инициализацию

        // User dir

        File file = new File("./users");
        if(file.isDirectory())
        {
            is_init = true;
            Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is true [00001]");
        }else{
            if(file.mkdir())
            {
                is_init = true;
                Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is created [00002]");
            }else{
                Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is nope created {EXCEPTION} [00003]");
            }
        }

        // Faction dir

        file = new File("./faction");
        if(file.isDirectory())
        {
            is_init = true;
            Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is true [00010]");
        }else{
            if(file.mkdir())
            {
                is_init = true;
                Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is created [00011]");
            }else{
                Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is nope created {EXCEPTION} [00012]");
            }
        }
    }

    @Override
    public User loadUser(String name) {
        init();

        User user = null;
        try {
            FileInputStream fis = new FileInputStream("./users/" +name + ".data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            user = (User) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("CompanyInfoSerializeble class not found");
            c.printStackTrace();
        }

        return user;
    }

    @Override
    public void saveFaction(User user) {
        Preconditions.checkNotNull(user, "Name => null !!! This bug");

        init();

        try {
            FileOutputStream fos = new FileOutputStream("./users/" + user.getName() + ".data");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsUser(String name) {
        File file = new File("./users/" +name + ".data");
        return (file.exists() && file.isFile());
    }

    @Override
    public Faction loadFaction(String name) {
        init();

        Faction faction = null;
        try {
            FileInputStream fis = new FileInputStream("./faction/" +name + ".data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            faction = (Faction) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException i) {
            i.printStackTrace();

        } catch (ClassNotFoundException c) {
            System.out.println("CompanyInfoSerializeble class not found");
            c.printStackTrace();
        }

        return faction;
    }

    @Override
    public void saveFaction(Faction faction) {
        Preconditions.checkNotNull(faction, "Name => null !!! This bug [FACTION] ");

        try {
            FileOutputStream fos = new FileOutputStream("./faction/" + faction.getName() + ".data");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsFaction(String name) {
        File file = new File("./faction/" + name + ".data");
        return (file.exists() && file.isFile());
    }

    @Override
    public void deleteFaction(String name) {
        File file = new File("./faction/" + name + ".data");
        if(file.isFile())
        {
            file.delete();
        }
    }
}
