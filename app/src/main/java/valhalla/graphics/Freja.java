package valhalla.graphics;

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

    public void init(StackPane root) {
        Balder.setLevel(Level.INFO);
        Balder.Log.info("Initializing Freja");
        this.root = root;
        this.root.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));

        this.spriteAnimationSystem = new SpriteAnimationSystem();

        Scene scene = root.getScene();
        Frigg.handleSceneInput(scene);

        Balder.Log.info("Freja initialized");
    }
    
    public void addEntity(Entity p_entity) {
        try {
            this.spriteAnimationSystem.addSpriteAnimation(p_entity.getData(SpriteAnimationData.class));
        } catch (Exception e) {
            Balder.Log.warning("Entity does not have a SpriteAnimationData component");
        }
    }

    public void render(long now, long lastUpdate) {
        this.root.getChildren().clear();
        this.spriteAnimationSystem.update();

        for (SpriteAnimationData animation : this.spriteAnimationSystem.animations) {   
            this.root.getChildren().add(animation.sprite.imageView); // Rendera spriten
        }

        // Calculate FPS
        double fps = 1_000_000_000.0 / (now - lastUpdate);
        Text fpsText = new Text(String.format("FPS: %.2f", fps));
        fpsText.setTranslateX(200);
        fpsText.setTranslateY(200);
        this.root.getChildren().add(fpsText);
    }
}
