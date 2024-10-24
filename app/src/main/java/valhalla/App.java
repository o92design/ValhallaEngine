package valhalla;

import valhalla.core.Balder;
import valhalla.core.Oden;
import valhalla.example.steamship.SteamshipGame;
import valhalla.game.GameData;

/**
 * App is the entry point for the Valhalla application.
 */
public class App {
    public static GameData gameData = new SteamshipGame();
    /**
     * Main method to start the Valhalla application.
     *
     * @param args command line arguments
     * @throws Exception if an error occurs during startup
     */
    public static void main(final String[] args) throws Exception {
        Balder.Log.info("Starting Valhalla");
        if (gameData == null) {
            gameData = new GameData();
        }
        Oden.initialize(gameData);
        Oden.run(Oden.class, args);
    }
}
