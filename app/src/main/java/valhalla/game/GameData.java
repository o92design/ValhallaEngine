package valhalla.game;

import java.util.ArrayList;

import valhalla.core.Entity;

/**
 * GameData is a class that holds the metadata and entities for a game.
 * It includes information such as the game's name, version, author, and description.
 * It also provides methods to add and remove entities from the game.
 */
public class GameData {
    /**
     * The name of the game.
     */
    public static String GAME_NAME = "ValhallaEngine";

    /**
     * The version of the game.
     */
    public static String GAME_VERSION = "0.0.1";

    /**
     * The author of the game.
     */
    public static String GAME_AUTHOR = "o92design";

    /**
     * The description of the game.
     */
    public static String GAME_DESCRIPTION = "A game engine for the Valhalla project";

    /**
     * The list of entities in the game.
     */
    public ArrayList<Entity> entities = new ArrayList<>();

    /**
     * Gets the name of the game.
     *
     * @return the name of the game
     */
    public String getGameName() {
        return GAME_NAME;
    }

    /**
     * Adds an entity to the game.
     *
     * @param entity the entity to add
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Removes an entity from the game based on its class.
     *
     * @param entityClass the class of the entity to remove
     */
    public void removeEntity(Class<? extends Entity> entityClass) {
        entities.removeIf(entity -> entity.getClass().equals(entityClass));
    }
}