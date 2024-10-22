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

    private final double maxSpeed = 10; // Maximum speed in pixels per second
    private final double acceleration = 0.5; // Acceleration in pixels per second^2
    private final double deceleration = 0.5; // Deceleration in pixels per second^2
    private double currentSpeedX = 0; // Current speed in the X direction
    private double currentSpeedY = 0; // Current speed in the Y direction

    public Player player = new Player();
    public Player player2 = new Player();

    /**
     * Constructor for Oden.
     */
    public Oden() {
    }

    /**
     * Initializes the game state.
     */
    public void initialize() {
        freja = new Freja();
    }

    /**
     * Runs the Oden application.
     *
     * @param appClass the class of the application
     * @param args     command line arguments
     */
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

        // Initialize Freja and render
        freja.init(root);
        freja.addEntity(player);
        freja.addEntity(player2);

        // Create game loop
        gameLoop = new AnimationTimer() {
            private static final long ONE_SECOND_IN_NANOSECONDS = 1_000_000_000L;
            private static final double FRAME_INTERVAL = ONE_SECOND_IN_NANOSECONDS / 70;
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                updateGame(now);
                if (now - lastUpdate >= FRAME_INTERVAL) {
                    freja.render(now, lastUpdate); // Render with Freja
                    lastUpdate = now;
                }
            }
        };
        gameLoop.start();

        Balder.Log.info("Oden started");
        primaryStage.show();
    }

    /**
     * Updates the game state based on the time elapsed.
     *
     * @param deltaTime the time elapsed since the last update
     */
    private void updateGame(long deltaTime) {
        SpriteAnimationData spriteAnimationComponent = player.getData(SpriteAnimationData.class);
        ImageView playerSprite = spriteAnimationComponent.sprite.imageView;

        // Move the image based on key presses
        if (Frigg.isKeyDown(KeyCode.W)) { // Move up
            currentSpeedY = Math.max(currentSpeedY - acceleration * deltaTime, -maxSpeed);
            playerSprite.setTranslateY(playerSprite.getTranslateY() + currentSpeedY);
        }
        if (Frigg.isKeyDown(KeyCode.S)) { // Move down
            currentSpeedY = Math.min(currentSpeedY + acceleration * deltaTime, +maxSpeed);
            playerSprite.setTranslateY(playerSprite.getTranslateY() + currentSpeedY);
        }
        if (Frigg.isKeyDown(KeyCode.A)) { // Move left
            currentSpeedX = Math.max(currentSpeedX - acceleration * deltaTime, -maxSpeed);
            playerSprite.setTranslateX(playerSprite.getTranslateX() + currentSpeedX);
            // Change animation to left
        }

        if (Frigg.isKeyDown(KeyCode.D)) { // Move right
            currentSpeedX = Math.min(currentSpeedX + acceleration * deltaTime, maxSpeed);
            playerSprite.setTranslateX(playerSprite.getTranslateX() + currentSpeedX);
            // Change animation to right
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
