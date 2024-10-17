package valhalla.core;

import valhalla.graphics.Freja;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Oden is the core engine for Valhalla.
 * It is responsible for initializing the game and managing the game loop.
 */
public class Oden extends Application {
    private AnimationTimer gameLoop;
    private Freja freja;
    private Frigg frigg;

    public Oden() {
    }

    public void initialize() {
        // Initiera spelets tillstånd här
        freja = new Freja();
        frigg = new Frigg();
    }

    public void run(String[] args) {
        Balder.Log.info("Running Oden");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Balder.Log.info("Starting Oden");
        initialize();
        primaryStage.setTitle("Valhalla");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root));

        // Initiera Freja och rendera
        freja.init(root, frigg);

        // Skapa gameloop
        gameLoop = new AnimationTimer() {
            private static final long ONE_SECOND_IN_NANOSECONDS = 1_000_000_000L;
            private static final double FRAME_INTERVAL = ONE_SECOND_IN_NANOSECONDS / 70;
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                updateGame();
                if (now - lastUpdate >= FRAME_INTERVAL) {
                    freja.render(now, lastUpdate); // Rendera med Freja
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();

        Balder.Log.info("Oden started");
        primaryStage.show();
    }

    private void updateGame() {
        // Flytta bilden baserat på tangenttryckningar
        if (Frigg.isKeyDown(KeyCode.W)) { // Flytta upp
            freja.spriteAnimation.getImageView()
                    .setTranslateY(freja.spriteAnimation.getImageView().getTranslateY() - 5);
        }
        if (Frigg.isKeyDown(KeyCode.S)) { // Flytta ner
            freja.spriteAnimation.getImageView()
                    .setTranslateY(freja.spriteAnimation.getImageView().getTranslateY() + 5);
        }
        if (Frigg.isKeyDown(KeyCode.A)) { // Flytta vänster
            freja.spriteAnimation.getImageView()
                    .setTranslateX(freja.spriteAnimation.getImageView().getTranslateX() - 5);
        }
        if (Frigg.isKeyDown(KeyCode.D)) { // Flytta höger
            freja.spriteAnimation.getImageView()
                    .setTranslateX(freja.spriteAnimation.getImageView().getTranslateX() + 5);
        }
    }
}
