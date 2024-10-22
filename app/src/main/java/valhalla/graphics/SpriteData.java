package valhalla.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import valhalla.core.IData;

public class SpriteData implements IData<SpriteData> {
    public ImageView imageView;
    public Image spriteSheet;

    public int frameWidth;
    public int frameHeight;

    public SpriteData(Image p_spriteSheet, int p_frameWidth, int p_frameHeight) {
        this.spriteSheet = p_spriteSheet;
        this.frameWidth = p_frameWidth;
        this.frameHeight = p_frameHeight;

        this.imageView = new ImageView(this.spriteSheet);
    }
}
