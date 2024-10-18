package valhalla.graphics;

import valhalla.core.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.logging.Level;

import javafx.scene.Scene;

/**
 * Freja is the graphics engine for Valhalla.
 * It is responsible for rendering the game.
 */

public class Freja {
    StackPane root;
    public SpriteAnimation spriteAnimation; // Lägg till SpriteAnimation

    public void init(StackPane root) {
        Balder.setLevel(Level.INFO);
        Balder.Log.info("Initializing Freja");
        this.root = root;
        this.root.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));

        // Ladda spritesheet
        Image spriteSheet = new Image(getClass().getResourceAsStream("/prtg_roll_spritesheet.png"));
        spriteAnimation = new SpriteAnimation(spriteSheet, 32, 32, 12); // Anta att varje ram är 64x64 och det finns 12

        // Lägg till ImageView till root
        this.root.getChildren().add(spriteAnimation.getImageView());
        spriteAnimation.start(); // Starta animationen

        // Registrera en tangenttryckningslyssnare
        Scene scene = root.getScene();
        Frigg.handleSceneInput(scene);

        Balder.Log.info("Freja initialized");
    }

    public void render(long now, long lastUpdate) {
        this.root.getChildren().clear();
        this.root.getChildren().add(spriteAnimation.getImageView()); // Rendera spriten

        // Calculate FPS
        double fps = 1_000_000_000.0 / (now - lastUpdate);
        Text fpsText = new Text(String.format("FPS: %.2f", fps));
        this.root.getChildren().add(fpsText);
    }
}
