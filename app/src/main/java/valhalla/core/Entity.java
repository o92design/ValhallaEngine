package valhalla.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity represents a game entity that can hold various data components.
 */
public class Entity {
    private final List<IData<?>> components = new ArrayList<>();

    /**
     * Adds a data component to the entity.
     *
     * @param p_component the component to add
     * @param <T>        the type of the component
     * @return the index of the added component
     */
    public <T extends IData<?>> int addData(T p_component) {
        components.add(p_component);
        return components.size() - 1;
    }

    /**
     * Retrieves a data component of the specified type.
     *
     * @param p_dataType the class of the data type to retrieve
     * @param <T>       the type of the data
     * @return the data component, or null if not found
     */
    public <T extends IData<?>> T getData(Class<T> p_dataType) {
        for (int i = 0; i < components.size(); i++) {
            if (p_dataType.isInstance(components.get(i))) {
                return p_dataType.cast(components.get(i));
            }
        }
        return null;
    }
}
