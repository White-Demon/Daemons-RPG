package ru.tec.database;

import ru.tec.core.Faction;
import ru.tec.core.User;

import java.io.Serializable;

/**
 * @author DmitriyMX
 */
public interface IStorage extends Serializable{
    /**
     * Инициализация
     */
    void init();

    /**
     * Десериализация игрока
     * @param name имя игрока
     * @return     данные по игроку
     */
    User loadUser(String name);

    /**
     * Сериализация игрока
     * @param user данные по игроку
     */
    void saveFaction(User user);

    /**
     * Существует ли игрок в базе
     * @param name имя игрока
     * @return     true, если существует
     */
    boolean existsUser(String name);

    /**
     * Десериализация клана
     * @param name название клана
     * @return     данные клана
     */
    Faction loadFaction(String name);

    /**
     * Сериализация клана
     * @param faction данные клана
     */
    void saveFaction(Faction faction);

    /**
     * Существует ли клан в базе
     * @param name название клана
     * @return     true, если существует
     */
    boolean existsFaction(String name);

    /**
     * Удалить из базы клан
     * @param name
     */
    void deleteFaction(String name);
}
