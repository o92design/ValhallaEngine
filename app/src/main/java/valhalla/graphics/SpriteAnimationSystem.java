package valhalla.graphics;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Rectangle2D;
import valhalla.core.ISystem;

public class SpriteAnimationSystem implements ISystem {
    public List<SpriteAnimationData> animations = new ArrayList<>();

    /**
     * Adds a sprite animation to the system.
     *
     * @param p_animationComponent the sprite animation component to add
     */
    public void addSpriteAnimation(SpriteAnimationData p_animationComponent) {
        this.animations.add(p_animationComponent);
    }

    /**
     * Removes a sprite animation from the system by index.
     *
     * @param index the index of the animation to remove
     */
    public void removeSpriteAnimation(int index) {
        this.animations.remove(index);
    }

    /**
     * Sets the direction for the sprite animations.
     *
     * @param direction the direction to set (e.g., "left" or "right")
     */
    public void setDirection(String direction) {
        // Change viewport based on direction
        if (direction.equals("left")) {
            // Set viewport for left animation
        } else if (direction.equals("right")) {
            // Set viewport for right animation
        }
    }

    /**
     * Updates the frame of the given sprite animation data.
     *
     * @param p_animationData the sprite animation data to update
     */
    public void updateFrame(SpriteAnimationData p_animationData) {
        p_animationData.currentFrame += 1;

        if (p_animationData.currentFrame >= p_animationData.delay) {
            p_animationData.currentFrame = p_animationData.nextFrame;
            p_animationData.currentFrame = (p_animationData.currentFrame + 1) % p_animationData.totalFrames;
            int x = (int) ((p_animationData.currentFrame * p_animationData.sprite.frameWidth) % p_animationData.sprite.spriteSheet.getWidth());
            int y = 0; // Animation goes in the X direction and not Y direction
            p_animationData.sprite.imageView.setViewport(new Rectangle2D(x, y, p_animationData.sprite.frameWidth, p_animationData.sprite.frameHeight));

            p_animationData.nextFrame += 1;
            p_animationData.currentFrame = 0;
        }
    }

    @Override
    public void update() {
        for (SpriteAnimationData animation : this.animations) {
            updateFrame(animation);
        }
    }
}
