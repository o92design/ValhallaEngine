package valhalla.core;

/**
 * ISystem is an interface for systems that can update their state.
 */
public interface ISystem {
    /**
     * Updates the system with the given delta time.
     *
     * @param p_deltaTime the time elapsed since the last update
     */
    public default void update(float p_deltaTime) {
        update();
    }

    /**
     * Updates the system.
     */
    public void update();
}
