package valhalla.game;

import javafx.scene.image.Image;
import valhalla.core.Entity;
import valhalla.graphics.SpriteAnimationData;
import valhalla.graphics.SpriteData;

/**
 * Player represents a game character that can be controlled by the user.
 */
public class Player extends Entity {
    /**
     * Constructor for Player.
     * Initializes the player with sprite data and animation data.
     */
    public Player() {
        this.addData(new SpriteData(new Image(getClass().getResourceAsStream("/prtg_roll_spritesheet.png")), 32, 32));
        this.addData(new SpriteAnimationData(this.getData(SpriteData.class), 12, 5));
    }
}
