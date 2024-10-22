package valhalla.core;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    private final List<IData<?>> components = new ArrayList<>();
   
    public <T extends IData<?>> int addData(T p_component) {
        components.add(p_component);
        return components.size() - 1;
    }

    public <T extends IData<?>> T getData(Class<T> p_dataType) {
        for (int i = 0; i < components.size(); i++) {
            if (p_dataType.isInstance(components.get(i))) {
                return p_dataType.cast(components.get(i));
            }
        }
        return null;
    }
}
