package valhalla.graphics;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation {
    private ImageView imageView;
    private Image spriteSheet;
    private int frameWidth;
    private int frameHeight;
    private int totalFrames;
    private int currentFrame;
    private Timeline timeline;

    public SpriteAnimation(Image spriteSheet, int frameWidth, int frameHeight, int totalFrames) {
        this.spriteSheet = spriteSheet;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.totalFrames = totalFrames;
        this.currentFrame = 0;

        imageView = new ImageView(spriteSheet);
        imageView.setViewport(new javafx.geometry.Rectangle2D(0, 0, frameWidth, frameHeight));

        // Skapa en tidslinje för animationen
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> updateFrame()));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    private void updateFrame() {
        currentFrame = (currentFrame + 1) % totalFrames;
        int x = (int) ((currentFrame * frameWidth) % spriteSheet.getWidth());
        int y = 0; // Animation går i X led och inte y led
        imageView.setViewport(new javafx.geometry.Rectangle2D(x, y, frameWidth, frameHeight));
    }
}
