package valhalla.core;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import valhalla.game.Player;
import valhalla.graphics.Freja;
import valhalla.graphics.SpriteAnimationData;

/**
 * Oden is the core engine for Valhalla.
 * It is responsible for initializing the game and managing the game loop.
 */
public class Oden extends Application {
    private AnimationTimer gameLoop;
    private Freja freja;

    private final double maxSpeed = 10; // Max hastighet i pixlar per sekund
    private final double acceleration = 0.5; // Acceleration i pixlar per sekund^2
    private final double deceleration = 0.5; // Deceleration i pixlar per sekund^2
    private double currentSpeedX = 0; // Aktuell hastighet i X-led
    private double currentSpeedY = 0; // Aktuell hastighet i Y-ledr

    public Player player = new Player();
    public Player player2 = new Player();

    public Oden() {
    }

    public void initialize() {
        // Initiera spelets tillstånd här
        freja = new Freja();
    }

    public static void run(Class<Oden> appClass, String[] args) {
        Balder.Log.info("Running Oden");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialize();
        primaryStage.setTitle("Valhalla");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root));

        // Initiera Freja och rendera
        freja.init(root);
        freja.addEntity(player);
        freja.addEntity(player2);

        // Skapa gameloop
        gameLoop = new AnimationTimer() {
            private static final long ONE_SECOND_IN_NANOSECONDS = 1_000_000_000L;
            private static final double FRAME_INTERVAL = ONE_SECOND_IN_NANOSECONDS / 70;
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                updateGame(now);
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

    private void updateGame(long deltaTime) {
        SpriteAnimationData spriteAnimationComponent = player.getData(SpriteAnimationData.class);
        ImageView playerSprite = spriteAnimationComponent.sprite.imageView;

        // Flytta bilden baserat på tangenttryckningar
        if (Frigg.isKeyDown(KeyCode.W)) { // Flytta upp
            currentSpeedY = Math.max(currentSpeedY - acceleration * deltaTime, -maxSpeed);
            playerSprite.setTranslateY(playerSprite.getTranslateY() + currentSpeedY);
        }
        if (Frigg.isKeyDown(KeyCode.S)) { // Flytta ner
            currentSpeedY = Math.min(currentSpeedY + acceleration * deltaTime, +maxSpeed);
            playerSprite.setTranslateY(playerSprite.getTranslateY() + currentSpeedY);
        }
        if (Frigg.isKeyDown(KeyCode.A)) { // Flytta vänster
            currentSpeedX = Math.max(currentSpeedX - acceleration * deltaTime, -maxSpeed);
            playerSprite.setTranslateX(playerSprite.getTranslateX() + currentSpeedX);
            // Ändra animation till vänster
        }
        
        if (Frigg.isKeyDown(KeyCode.D)) { // Flytta höger
            currentSpeedX = Math.min(currentSpeedX + acceleration * deltaTime, maxSpeed);
            playerSprite.setTranslateX(playerSprite.getTranslateX() + currentSpeedX);
            // Ändra animation till höger
        }
        if (currentSpeedX > 0) {
            currentSpeedX = Math.max(currentSpeedX - deceleration * deltaTime, 0);
        } else if (currentSpeedX < 0) {
            currentSpeedX = Math.min(currentSpeedX + deceleration * deltaTime, 0);
        }

        if (currentSpeedY > 0) {
            currentSpeedY = Math.max(currentSpeedY - deceleration * deltaTime, 0);
        } else if (currentSpeedY < 0) {
            currentSpeedY = Math.min(currentSpeedY + deceleration * deltaTime, 0);
        }
    }
}
