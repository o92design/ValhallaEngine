package valhalla.core;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Frigg is the input manager for Valhalla.
 * It is responsible for handling all input events.
 */

public class Frigg {
    private static final Map<KeyCode, Boolean> keys = new HashMap<>();
    private static final Map<MouseButton, Boolean> mouseButtons = new HashMap<>();

    public static boolean isKeyDown(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    public static void setKeyDown(KeyCode key, boolean isDown) {
        keys.put(key, isDown);
    }

    public static void handleInput(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            setKeyDown(event.getCode(), true);
        } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            setKeyDown(event.getCode(), false);
        }
    }

    public static void handleInput(MouseEvent event) {
        setMouseButton(event.getButton(), event.getEventType() == MouseEvent.MOUSE_PRESSED);
        if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            setMouseButton(event.getButton(), false);
        }
    }

    public static void setMouseButton(MouseButton button, boolean isDown) {
        mouseButtons.put(button, isDown);
    }

    public static boolean isMouseButtonDown(MouseButton button) {
        return mouseButtons.getOrDefault(button, false);
    }

    public static void handleSceneInput(Scene scene) {
        scene.setOnKeyPressed(event -> {
            handleInput(event);
        });

        scene.setOnKeyReleased(event -> {
            handleInput(event);
        });

        scene.setOnMousePressed(event -> {
            handleInput(event);
        });

        scene.setOnMouseReleased(event -> {
            handleInput(event);
        });
    }

}
