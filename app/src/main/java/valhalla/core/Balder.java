package valhalla.core;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.LogRecord;

/**
 * Balder is the logger for Valhalla.
 * It is responsible for logging messages to the console.
 */
public final class Balder {
    public static final Logger Log = Logger.getLogger(Balder.class.getName());

    static {
        // Ta bort standard logghanterare
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        // Anpassad formatterare för loggmeddelanden
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public String format(LogRecord record) {
                return String.format("%1$tF %1$tT.%1$tL [%2$s][%3$s]: %4$s%n",
                        record.getMillis(),
                        record.getSourceClassName(),
                        record.getLevel(),
                        record.getMessage());
            }
        });

        Log.addHandler(handler);
        handler.setLevel(Level.ALL);
        Log.setLevel(Level.ALL);
    }

    // TODO(18-10-24: KiOskars) - Fixa så att detta fungerar för de olika klasserna
    public static void setLevel(Level level) {
        for (Handler handler : Log.getHandlers()) {
            handler.setLevel(level);
        }
        Log.setLevel(level);
    }

    public static Balder Instance;

    public Balder() {
        Instance = this;
    }
}
