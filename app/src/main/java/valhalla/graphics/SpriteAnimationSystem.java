package valhalla.graphics;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Rectangle2D;
import valhalla.core.ISystem;

public class SpriteAnimationSystem implements ISystem {
    public List<SpriteAnimationData> animations = new ArrayList<>();

    public void addSpriteAnimation(SpriteAnimationData p_animationComponent) {
        this.animations.add(p_animationComponent);
    }

    public void removeSpriteAnimation(int index) {
        this.animations.remove(index);
    }

    public void setDirection(String direction) {
        // Ändra viewport baserat på riktning
        if (direction.equals("left")) {
            // Ställ in viewport för vänster animation
        } else if (direction.equals("right")) {
            // Ställ in viewport för höger animation
        }
    }

    public void updateFrame(SpriteAnimationData p_animationData) {
        p_animationData.currentFrame += 1;

        if (p_animationData.currentFrame >= p_animationData.delay) {
            p_animationData.currentFrame = p_animationData.nextFrame;
            p_animationData.currentFrame = (p_animationData.currentFrame + 1)
                % p_animationData.totalFrames;
            int x = (int) ((p_animationData.currentFrame * p_animationData.sprite.frameWidth)
                % p_animationData.sprite.spriteSheet.getWidth());
            int y = 0; // Animation går i X led och inte y led
            p_animationData.sprite.imageView
                    .setViewport(new Rectangle2D(x, y, p_animationData.sprite.frameWidth,
                            p_animationData.sprite.frameHeight));

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
