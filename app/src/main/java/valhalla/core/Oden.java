package valhalla.core;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import valhalla.game.GameData;
import valhalla.graphics.Freja;
import valhalla.graphics.SpriteData;
import valhalla.physics.PhysicsData;
import valhalla.physics.PhysicsSystem;
import valhalla.physics.TransformData;

/**
 * Oden is the core engine for Valhalla.
 * It is responsible for initializing the game and managing the game loop.
 */
public class Oden extends Application {
    private AnimationTimer gameLoop;
    private static Freja freja;
    private static PhysicsSystem physicsSystem;
    private static GameData gameData;

    /**
     * Constructor for Oden.
     */
    public Oden() {
    }

    /**
     * Initializes the game state.
     */
    public static void initialize(GameData gameData) {
        freja = new Freja();
        physicsSystem = new PhysicsSystem();
        Oden.gameData = gameData;
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
        primaryStage.setTitle(gameData.getGameName());
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root));

        // Initialize Freja and render
        freja.init(root);
        for (Entity entity : gameData.entities) {
            freja.addEntity(entity);
        }

        for (Entity entity : gameData.entities) {
            physicsSystem.addEntity(entity);
        }

        // Create game loop
        gameLoop = new AnimationTimer() {
            private static final long ONE_SECOND_IN_NANOSECONDS = 1_000_000_000L;
            private static final double FRAME_INTERVAL = ONE_SECOND_IN_NANOSECONDS / 70;
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                for (Entity entity : gameData.entities) {
                    handleInput(now, entity);
                }
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
        for (Entity entity : gameData.entities) {
            ImageView playerSprite = entity.getData(SpriteData.class).imageView;
            
            physicsSystem.update(deltaTime);

            playerSprite.setTranslateX(entity.getData(TransformData.class).x);
            playerSprite.setTranslateY(entity.getData(TransformData.class).y);
        }
    }
    
    public void handleInput(long deltaTime, Entity entity) {
        // Move the image based on key presses
        PhysicsData physicsData = entity.getData(PhysicsData.class);

        if (Frigg.isKeyDown(KeyCode.W)) {
            physicsData.acceleration = physicsData.acceleration - 0.1f > 1f ? physicsData.acceleration - 0.1f : -1f;
        }
        
        if(Frigg.isKeyDown(KeyCode.S)) {
            physicsData.acceleration = physicsData.acceleration + 0.1f < 1f ? physicsData.acceleration + 0.1f : 1f;
        }
    }
}
