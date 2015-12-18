package ru.tec.database;

import com.google.common.base.Preconditions;
import ru.tec.core.Faction;
import ru.tec.core.User;
import ru.tec.main.Main;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;

/**
 * @author DmitriyMX
 */
public class SqliteStorage implements IStorage {
    private boolean is_init = false;
    private Connection db;

    @Override
    public void init() {
        if(is_init) return;

        try {
            Class.forName("org.sqlite.JDBC");
            db = DriverManager.getConnection("jdbc:sqlite:database.db");

            Statement statement = db.createStatement();
            //TODO необходимо избавится от бинарных данных
            statement.execute("CREATE TABLE IF NOT EXISTS `user`    (name TEXT, bindata BLOB)");
            statement.execute("CREATE TABLE IF NOT EXISTS `faction` (name TEXT, bindata BLOB)");

            is_init = true;
        } catch (ClassNotFoundException | SQLException e) {
            Main.log.log(Level.INFO, "[RPG_CONSOLE] Error sqlite init {EXCEPTION} [00100]", e);
        }
    }

    @Override
    public User loadUser(String name) {
        init();

        User user = null;
        String sql = "SELECT bindata FROM `user` WHERE name LIKE ? LIMIT 1";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();

            // предполагаем только одно совпадение
            if (result.next()) {
                ObjectInputStream ois = new ObjectInputStream(result.getBlob("bindata").getBinaryStream());
                user = (User) ois.readObject();
                ois.close();
            }

            result.close();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Error load user {EXCEPTION} [00101]", e);
        }

        return user;
    }

    @Override
    public void saveUser(User user) {
        Preconditions.checkNotNull(user, "Name => null !!! This bug");
        init();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(user);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

            String sql = "INSERT INTO `user` (name, bindata) VALUES (?, ?)";
            PreparedStatement statement = db.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setBlob(2, bais);
            statement.execute();

            statement.close();
            bais.close();
        } catch (SQLException | IOException e) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Error save user {EXCEPTION} [00102]", e);
        }
    }

    @Override
    public boolean existsUser(String name) {
        boolean exists = false;
        String sql = "SELECT name FROM `user` WHERE name LIKE ? LIMIT 1";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            exists = result.next();
            result.close();
        } catch (SQLException e) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Error load user {EXCEPTION} [00103]", e);
        }

        return exists;
    }

    @Override
    public Faction loadFaction(String name) {
        init();

        Faction faction = null;
        String sql = "SELECT bindata FROM `faction` WHERE name LIKE ? LIMIT 1";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();

            // предполагаем только одно совпадение
            if (result.next()) {
                ObjectInputStream ois = new ObjectInputStream(result.getBlob("bindata").getBinaryStream());
                faction = (Faction) ois.readObject();
                ois.close();
            }

            result.close();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Error load faction {EXCEPTION} [00104]", e);
        }

        return faction;
    }

    @Override
    public void saveFaction(Faction faction) {
        Preconditions.checkNotNull(faction, "Name => null !!! This bug");
        init();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(faction);
            oos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

            String sql = "INSERT INTO `faction` (name, bindata) VALUES (?, ?)";
            PreparedStatement statement = db.prepareStatement(sql);
            statement.setString(1, faction.getName());
            statement.setBlob(2, bais);
            statement.execute();

            statement.close();
            bais.close();
        } catch (SQLException | IOException e) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Error save faction {EXCEPTION} [00105]", e);
        }
    }

    @Override
    public boolean existsFaction(String name) {
        boolean exists = false;
        String sql = "SELECT name FROM `faction` WHERE name LIKE ? LIMIT 1";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            exists = result.next();
            result.close();
        } catch (SQLException e) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Error load faction {EXCEPTION} [00106]", e);
        }
        return exists;
    }

    @Override
    public void deleteFaction(String name) {
        String sql = "DELETE FROM `faction` WHERE name LIKE ?";
        try (PreparedStatement statement = db.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.execute();
        } catch (SQLException e) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Error delete faction {EXCEPTION} [00107]", e);
        }
    }

    @Override
    public void close() {
        if (!is_init) return;

        try {
            db.close();
        } catch (SQLException e) {
            Main.log.log(Level.WARNING, "[RPG_CONSOLE] Failed to close connection [0010?]", e);
        } finally {
            db = null;
        }
    }
}
