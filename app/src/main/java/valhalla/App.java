package valhalla;

import valhalla.core.Balder;
import valhalla.core.Oden;

/**
 * App is the entry point for the Valhalla application.
 */
public class App {
    /**
     * Main method to start the Valhalla application.
     *
     * @param args command line arguments
     * @throws Exception if an error occurs during startup
     */
    public static void main(final String[] args) throws Exception {
        Balder.Log.info("Starting Valhalla");
        Oden.run(Oden.class, args);
    }
}
