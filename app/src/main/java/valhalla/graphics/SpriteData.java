package valhalla.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import valhalla.core.IData;

/**
 * SpriteData holds information about a sprite, including its image and dimensions.
 */
public class SpriteData implements IData<SpriteData> {
    public ImageView imageView;
    public Image spriteSheet;

    public int frameWidth;
    public int frameHeight;
    public float scale;

    /**
     * Constructor for SpriteData.
     *
     * @param p_spriteSheet the image of the sprite sheet
     * @param p_frameWidth  the width of each frame
     * @param p_frameHeight the height of each frame
     */
    public SpriteData(Image p_spriteSheet, int p_frameWidth, int p_frameHeight, float p_scale) {
        this.spriteSheet = p_spriteSheet;
        this.frameWidth = p_frameWidth;
        this.frameHeight = p_frameHeight;
        this.scale = p_scale;
        
        this.imageView = new ImageView(this.spriteSheet);
        this.imageView.setFitWidth(p_frameWidth);
        this.imageView.setFitHeight(p_frameHeight);

        this.imageView.setScaleX(p_scale);
        this.imageView.setScaleY(p_scale);
    }
}
