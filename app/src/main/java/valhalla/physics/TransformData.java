package valhalla.physics;

import valhalla.core.IData;

/**
 * TransformData holds the position data for an entity.
 * @param x The x-coordinate of the entity.
 * @param y The y-coordinate of the entity.
 */
public class TransformData implements IData<TransformData> {
    /**
     * The x-coordinate of the entity.
     */
    public float x;

    /**
     * The y-coordinate of the entity.
     */
    public float y;

    public TransformData(float p_x, float p_y) {
        this.x = p_x;
        this.y = p_y;
    }
}
