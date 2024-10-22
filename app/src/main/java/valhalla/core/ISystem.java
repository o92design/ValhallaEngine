package valhalla.core;

public interface ISystem {
    public default void update(float p_deltaTime) {
        update();
    }

    public void update();
}
