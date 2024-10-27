package valhalla.graphics;

import java.util.ArrayList;
import java.util.logging.Level;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import valhalla.core.Balder;
import valhalla.core.Entity;
import valhalla.core.Frigg;

/**
 * Freja is the graphics engine for Valhalla.
 * It is responsible for rendering the game.
 */
public class Freja {
    StackPane root;
    public SpriteAnimationSystem spriteAnimationSystem;
    public ArrayList<SpriteData> spriteData;

    /**
     * Initializes the Freja graphics engine.
     *
     * @param root the root pane for rendering
     */
    public void init(StackPane root) {
        Balder.setLevel(Level.INFO);
        Balder.Log.info("Initializing Freja");
        this.root = root;
        this.root.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));

        this.spriteAnimationSystem = new SpriteAnimationSystem();
        this.spriteData = new ArrayList<>();

        Scene scene = root.getScene();
        Frigg.handleSceneInput(scene);

        Balder.Log.info("Freja initialized");
    }

    /**
     * Adds an entity to the Freja graphics engine.
     *
     * @param p_entity the entity to add
     */
    public void addEntity(Entity p_entity) {
        try {
            this.spriteAnimationSystem.addSpriteAnimation(p_entity.getData(SpriteAnimationData.class));
        } catch (Exception e) {
            Balder.Log.warning("Entity does not have a SpriteAnimationData component");
        }

        try {
            if (p_entity.getData(SpriteData.class) != null) {
                this.spriteData.add(p_entity.getData(SpriteData.class));
            }
        } catch (Exception e) {
            Balder.Log.warning("Entity does not have a SpriteData component");
        }
    }

    /**
     * Renders the current frame of the game.
     *
     * @param now        the current time in nanoseconds
     * @param lastUpdate the last update time in nanoseconds
     */
    public void render(long now, long lastUpdate) {
        this.root.getChildren().clear();
        this.spriteAnimationSystem.update();

        for (SpriteAnimationData animation : this.spriteAnimationSystem.animations) {
            this.root.getChildren().add(animation.sprite.imageView); // Render the sprite
        }

        for (SpriteData sprite : this.spriteData) {
            this.root.getChildren().add(sprite.imageView); // Render the sprite
        }

        // Calculate FPS
        double fps = 1_000_000_000.0 / (now - lastUpdate);
        Text fpsText = new Text(String.format("FPS: %.2f", fps));
        fpsText.setTranslateX(200);
        fpsText.setTranslateY(200);
        this.root.getChildren().add(fpsText);
    }
}
