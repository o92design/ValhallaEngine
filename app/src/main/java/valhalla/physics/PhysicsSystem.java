package valhalla.physics;

import java.util.ArrayList;
import java.util.List;

import valhalla.core.Balder;
import valhalla.core.Entity;

/**
 * PhysicsSystem is responsible for managing and updating physics-related data for entities.
 * <br>
 * <b>Methods:</b>
 * <br>
 * <b>addEntity</b> Adds an entity to the physics system.
 * <br>
 * <b>update</b> Updates the physics for all entities.
 */
public class PhysicsSystem {
    private final List<Entity> entities = new ArrayList<>();

    /**
     * Adds an entity to the physics system.
     *
     * @param entity the entity to add
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Updates the physics for all entities.
     *
     * @param deltaTime the time elapsed since the last update
     */
    public void update(long deltaTime) {
        for (Entity entity : entities) {
            PhysicsData physicsData = entity.getData(PhysicsData.class);
            TransformData transformData = entity.getData(TransformData.class);
            if (physicsData != null) {

                if (physicsData.acceleration > 0) {
                    if(physicsData.velocityY + physicsData.acceleration < 5f) {
                        physicsData.velocityY = physicsData.velocityY + physicsData.acceleration < 5f ? physicsData.velocityY + physicsData.acceleration : 5f;
                    }
                } else {
                    if (physicsData.velocityY + physicsData.acceleration > -5f) {
                        physicsData.velocityY = physicsData.velocityY + physicsData.acceleration > -5f
                                ? physicsData.velocityY + physicsData.acceleration
                                : -5f;
                    }
                }
                
                if ((physicsData.acceleration > -0.3f && physicsData.acceleration < 0.3f) && physicsData.velocityY != 0) {
                    if (physicsData.velocityY > 0) {
                        physicsData.velocityY = Math.clamp(0 , physicsData.velocityY, physicsData.velocityY);
                    } else {
                        physicsData.velocityY = Math.clamp(physicsData.velocityY, 0, 0f);
                    }
                }

                transformData.x += physicsData.velocityX;
                transformData.y += physicsData.velocityY;

                //Balder.Log.info(String.format("Velocity: %f", physicsData.velocityY));
                Balder.Log.info(String.format("Acceleration: %f", physicsData.acceleration));

                // TODO: Add collision detection and response
            }
        }
    }
}
