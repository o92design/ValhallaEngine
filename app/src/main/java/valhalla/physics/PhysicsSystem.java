package valhalla.physics;

import java.util.ArrayList;
import java.util.List;

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
                // Update position based on velocity and deltaTime
                transformData.x += physicsData.velocityX;
                transformData.y += physicsData.velocityY;

                if (physicsData.velocityX > 0) {
                    physicsData.velocityX = (float) Math.max(physicsData.velocityX - physicsData.deceleration, 0);
                } else if (physicsData.velocityX < 0) {
                    physicsData.velocityX = (float) Math.min(physicsData.velocityX + physicsData.deceleration, 0);
                }

                if (physicsData.velocityY > 0) {
                    physicsData.velocityY = (float) Math.max(physicsData.velocityY - physicsData.deceleration, 0);
                } else if (physicsData.velocityY < 0) {
                    physicsData.velocityY = (float) Math.min(physicsData.velocityY + physicsData.deceleration, 0);
                }

                // TODO: Add collision detection and response
            }
        }
    }
}
