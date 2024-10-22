package valhalla.game;

import javafx.scene.image.Image;
import valhalla.core.Entity;
import valhalla.graphics.SpriteAnimationData;
import valhalla.graphics.SpriteData;

public class Player extends Entity {
    public Player() {
        this.addData(new SpriteData(new Image(getClass().getResourceAsStream("/prtg_roll_spritesheet.png")), 32, 32));
        this.addData(new SpriteAnimationData(this.getData(SpriteData.class), 12, 5));
    }
}
