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
}
