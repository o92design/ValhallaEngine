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

    /**
     * Checks if a key is currently pressed down.
     *
     * @param key the key to check
     * @return true if the key is down, false otherwise
     */
    public static boolean isKeyDown(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

    /**
     * Sets the state of a key (pressed or released).
     *
     * @param key    the key to set
     * @param isDown true if the key is pressed down, false if released
     */
    public static void setKeyDown(KeyCode key, boolean isDown) {
        keys.put(key, isDown);
    }

    /**
     * Handles keyboard input events.
     *
     * @param event the key event to handle
     */
    public static void handleInput(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            setKeyDown(event.getCode(), true);
        } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
            setKeyDown(event.getCode(), false);
        }
    }

    /**
     * Handles mouse input events.
     *
     * @param event the mouse event to handle
     */
    public static void handleInput(MouseEvent event) {
        setMouseButton(event.getButton(), event.getEventType() == MouseEvent.MOUSE_PRESSED);
        if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            setMouseButton(event.getButton(), false);
        }
    }

    /**
     * Sets the state of a mouse button (pressed or released).
     *
     * @param button the mouse button to set
     * @param isDown true if the button is pressed down, false if released
     */
    public static void setMouseButton(MouseButton button, boolean isDown) {
        mouseButtons.put(button, isDown);
    }

    /**
     * Checks if a mouse button is currently pressed down.
     *
     * @param button the mouse button to check
     * @return true if the button is down, false otherwise
     */
    public static boolean isMouseButtonDown(MouseButton button) {
        return mouseButtons.getOrDefault(button, false);
    }

    /**
     * Handles input events for the scene.
     *
     * @param scene the scene to handle input for
     */
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
