package valhalla.graphics;

import valhalla.core.IData;

/**
 * SpriteAnimationData holds information about sprite animations, including frames and timing.
 */
public class SpriteAnimationData implements IData<SpriteAnimationData> {
    public SpriteData sprite;
    public int totalFrames;
    public int currentFrame;
    public int delay;
    public boolean isLooping;
    public int nextFrame;

    /**
     * Constructor for SpriteAnimationData.
     *
     * @param p_sprite      the sprite data associated with the animation
     * @param p_totalFrames the total number of frames in the animation
     * @param p_delay       the delay between frames
     */
    public SpriteAnimationData(SpriteData p_sprite, int p_totalFrames, int p_delay) {
        this.sprite = p_sprite;
        this.totalFrames = p_totalFrames;
        this.currentFrame = this.nextFrame = 0;
        this.delay = p_delay;
    }
}
