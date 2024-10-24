package valhalla.example.steamship;

import valhalla.example.steamship.entities.Ship;
import valhalla.game.GameData;

/**
 * The SteamshipGame class extends the GameData class and initializes the game data
 * for the Steamship game. It sets the game's name, version, author, and description,
 * and adds a Ship entity to the game.
 */
public class SteamshipGame extends GameData {

    /**
     * Constructor for the SteamshipGame class.
     * Initializes the game data with specific values and adds a Ship entity.
     */
    public SteamshipGame() {
        super();
        GameData.GAME_NAME = "Steamship Game";
        GameData.GAME_VERSION = "0.0.1";
        GameData.GAME_AUTHOR = "o92design";
        GameData.GAME_DESCRIPTION = "A game about a steamship";

        this.addEntity(new Ship());
    }
}
