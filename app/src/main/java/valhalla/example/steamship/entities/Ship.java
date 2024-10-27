package valhalla.example.steamship.entities;

import javafx.scene.image.Image;
import valhalla.core.Entity;
import valhalla.graphics.SpriteData;
import valhalla.physics.PhysicsData;
import valhalla.physics.TransformData;

/**
 * The Ship class represents a ship entity in the Steamship game.
 * It extends the Entity class and initializes the ship with various data components.
 */
public class Ship extends Entity {

    /**
     * Constructor for the Ship class.
     * Initializes the ship with sprite data, sprite animation data, physics data, and transform data.
     */
    public Ship() {
        this.addData(new SpriteData(new Image(getClass().getResourceAsStream("/ship_fwd.png")), 216, 974, .2f));
        this.addData(new PhysicsData(0f, 0f, 0f, 0f));
        this.addData(new TransformData(0f, 0f));
    }
}