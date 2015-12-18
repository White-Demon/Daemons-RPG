package ru.tec.database;

import com.google.common.base.Preconditions;
import ru.tec.core.Faction;
import ru.tec.core.User;
import ru.tec.main.Main;

import java.io.*;
import java.util.logging.Level;

/**
 * @author DmitriyMX
 */
public class FileStorage implements IStorage {
    private boolean is_init = false;
    private final File usersFile = new File("./users");
    private final File factionsFile = new File("./faction");

    @Override
    public void init() {
        if (is_init) return;

        //TODO необходимо оптимизировать инициализацию

        // User dir

        if(usersFile.isDirectory())
        {
            is_init = true;
            Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is true [00001]");
        }else{
            if(usersFile.mkdir())
            {
                is_init = true;
                Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is created [00002]");
            }else{
                Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is not created {EXCEPTION} [00003]");
            }
        }

        // Faction dir

        if(factionsFile.isDirectory())
        {
            is_init = true;
            Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is true [00010]");
        }else{
            if(factionsFile.mkdir())
            {
                is_init = true;
                Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is created [00011]");
            }else{
                Main.log.log(Level.INFO, "[RPG_CONSOLE] Dir is not created {EXCEPTION} [00012]");
            }
        }
    }

    @Override
    public User loadUser(String name) {
        init();

        User user = null;
        try (FileInputStream fis = new FileInputStream(new File(usersFile, name + ".data"))) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            user = (User) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException i) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Failed to load user", i);
        }

        return user;
    }

    @Override
    public void saveUser(User user) {
        Preconditions.checkNotNull(user, "Name => null !!! This is bug");

        init();

        try (FileOutputStream fos = new FileOutputStream(new File(usersFile, user.getName() + ".data"))) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Failed to save user", e);
        }
    }

    @Override
    public boolean existsUser(String name) {
        File file = new File(usersFile, name + ".data");
        return (file.exists() && file.isFile());
    }

    @Override
    public Faction loadFaction(String name) {
        init();

        Faction faction = null;
        try (FileInputStream fis = new FileInputStream(new File(factionsFile, name + ".data"))) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            faction = (Faction) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException i) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Failed to load faction", i);
        }

        return faction;
    }

    @Override
    public void saveFaction(Faction faction) {
        Preconditions.checkNotNull(faction, "Name => null !!! This bug [FACTION] ");

        try (FileOutputStream fos = new FileOutputStream(new File(factionsFile, faction.getName() + ".data"))) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Failed to save faction", e);
        }
    }

    @Override
    public boolean existsFaction(String name) {
        File file = new File(factionsFile, name + ".data");
        return (file.exists() && file.isFile());
    }

    @Override
    public void deleteFaction(String name) {
        File file = new File(factionsFile, name + ".data");
        if(file.isFile())
        {
            file.delete();
        }
    }

    @Override
    public void close() {
        // ignore
    }
}
