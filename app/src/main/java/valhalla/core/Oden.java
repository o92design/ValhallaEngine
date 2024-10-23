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
import valhalla.physics.PhysicsData;
import valhalla.physics.PhysicsSystem;
import valhalla.physics.TransformData;

/**
 * Oden is the core engine for Valhalla.
 * It is responsible for initializing the game and managing the game loop.
 */
public class Oden extends Application {
    private AnimationTimer gameLoop;
    private Freja freja;
    private PhysicsSystem physicsSystem;

    private final float maxSpeed = 5f; // Maximum speed in pixels per second
    private final float acceleration = 1.5f; // Acceleration in pixels per second^2
    private final float deceleration = 1.0f; // Deceleration in pixels per second^2

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

        physicsSystem = new PhysicsSystem();
        physicsSystem.addEntity(player);
        physicsSystem.addEntity(player2);

        player.getData(PhysicsData.class).acceleration = acceleration;
        player.getData(PhysicsData.class).deceleration = deceleration;
        player2.getData(PhysicsData.class).acceleration = acceleration;
        player2.getData(PhysicsData.class).deceleration = deceleration;

        // Create game loop
        gameLoop = new AnimationTimer() {
            private static final long ONE_SECOND_IN_NANOSECONDS = 1_000_000_000L;
            private static final double FRAME_INTERVAL = ONE_SECOND_IN_NANOSECONDS / 70;
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                handleInput(now);
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
        
        physicsSystem.update(deltaTime);

        playerSprite.setTranslateX(player.getData(TransformData.class).x);
        playerSprite.setTranslateY(player.getData(TransformData.class).y);
    }
    
    public void handleInput(long deltaTime) {
        // Move the image based on key presses
        if (Frigg.isKeyDown(KeyCode.W)) { // Move up
            player.getData(PhysicsData.class).velocityY = (float) Math.max(player.getData(PhysicsData.class).velocityY - player.getData(PhysicsData.class).acceleration, -maxSpeed);
        }
        if (Frigg.isKeyDown(KeyCode.S)) { // Move down
            player.getData(PhysicsData.class).velocityY = (float) Math.min(player.getData(PhysicsData.class).velocityY + player.getData(PhysicsData.class).acceleration, +maxSpeed);
        }
        if (Frigg.isKeyDown(KeyCode.A)) { // Move left
            player.getData(PhysicsData.class).velocityX = (float) Math.max(player.getData(PhysicsData.class).velocityX - player.getData(PhysicsData.class).acceleration, -maxSpeed);
            // Change animation to left
        }

        if (Frigg.isKeyDown(KeyCode.D)) { // Move right
            player.getData(PhysicsData.class).velocityX = (float) Math.min(player.getData(PhysicsData.class).velocityX + player.getData(PhysicsData.class).acceleration, maxSpeed);
            // Change animation to right
        }
    }
}
