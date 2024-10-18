package valhalla;

import valhalla.core.*;

public class App {
    public static void main(final String[] args) throws Exception {
        Balder.Log.info("Starting Valhalla");
        Oden.run(Oden.class, args);
    }
}
