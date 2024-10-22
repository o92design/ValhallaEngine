package valhalla.graphics;

import valhalla.core.IData;

public class SpriteAnimationData implements IData<SpriteAnimationData> {
    public SpriteData sprite;
    public int totalFrames;
    public int currentFrame;
    public int delay;
    public boolean isLooping;
    public int nextFrame;

    public SpriteAnimationData(SpriteData p_sprite, int p_totalFrames, int p_delay) {
        this.sprite = p_sprite;
        this.totalFrames = p_totalFrames;
        this.currentFrame = this.nextFrame = 0;
        this.delay = p_delay;
    }
}
