package valhalla.physics;

import valhalla.core.IData;

/**
 * PhysicsData holds the physics-related properties for an entity.
 * @param velocityX The velocity of the entity along the X-axis.
 * @param velocityY The velocity of the entity along the Y-axis.
 * @param acceleration The acceleration of the entity.
 * @param deceleration The deceleration of the entity.
 */
public class PhysicsData implements IData<PhysicsData> {
    /**
     * The velocity of the entity along the X-axis.
     */
    public float velocityX;

    /**
     * The velocity of the entity along the Y-axis.
     */
    public float velocityY;

    /**
     * The acceleration of the entity.
     */
    public float acceleration;

    /**
     * The deceleration of the entity.
     */
    public float deceleration;

    public PhysicsData(float p_velocityX, float p_velocityY, float p_acceleration, float p_deceleration) {
        this.velocityX = p_velocityX;
        this.velocityY = p_velocityY;
        this.acceleration = p_acceleration;
        this.deceleration = p_deceleration;
    }
}
