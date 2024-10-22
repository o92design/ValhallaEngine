# Valhalla Game Engine

Valhalla is a game engine designed for creating 2D games in Java. It provides a framework for rendering graphics, handling input, and managing game entities and animations.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Architecture](#architecture)
- [Classes](#classes)
  - [Oden](#oden)
  - [Freja](#freja)
  - [Entity](#entity)
  - [Player](#player)
  - [SpriteData](#spritedata)
  - [SpriteAnimationData](#spriteanimationdata)
  - [SpriteAnimationSystem](#spriteanimationsystem)
  - [Frigg](#frigg)
  - [Balder](#balder)
  - [IData](#idata)
  - [ISystem](#isystem)
- [License](#license)

## Features

- **Graphics Rendering**: Renders sprites and animations using JavaFX.
- **Input Handling**: Manages keyboard and mouse input.
- **Entity Management**: Supports game entities with various data components.
- **Animation System**: Handles sprite animations with frame management.

## Getting Started

To get started with Valhalla, clone the repository and build the project using Gradle. Ensure you have Java 17 and JavaFX installed.

bash
git clone https://github.com/yourusername/valhalla.git
cd valhalla
./gradlew build

Run the application:
bash
./gradlew run

## Architecture

Valhalla is structured around several key classes that manage different aspects of the game engine. Below are the main classes and their responsibilities.

## Classes

### Oden

The core engine for Valhalla, responsible for initializing the game and managing the game loop.

- **Methods**:
  - `initialize()`: Initializes the game state.
  - `start(Stage primaryStage)`: Starts the application and sets up the game loop.
  - `updateGame(long deltaTime)`: Updates the game state based on the time elapsed.

### Freja

The graphics engine for Valhalla, responsible for rendering the game.

- **Methods**:
  - `init(StackPane root)`: Initializes the Freja graphics engine.
  - `addEntity(Entity p_entity)`: Adds an entity to the graphics engine.
  - `render(long now, long lastUpdate)`: Renders the current frame of the game.

### Entity

Represents a game entity that can hold various data components.

- **Methods**:
  - `addData(T p_component)`: Adds a data component to the entity.
  - `getData(Class<T> p_dataType)`: Retrieves a data component of the specified type.

### Player

Represents a game character that can be controlled by the user.

- **Constructor**: Initializes the player with sprite data and animation data.

### SpriteData

Holds information about a sprite, including its image and dimensions.

- **Constructor**:
  - `SpriteData(Image p_spriteSheet, int p_frameWidth, int p_frameHeight)`: Initializes the sprite data.

### SpriteAnimationData

Holds information about sprite animations, including frames and timing.

- **Constructor**:
  - `SpriteAnimationData(SpriteData p_sprite, int p_totalFrames, int p_delay)`: Initializes the animation data.

### SpriteAnimationSystem

Manages and updates sprite animations.

- **Methods**:
  - `addSpriteAnimation(SpriteAnimationData p_animationComponent)`: Adds a sprite animation to the system.
  - `removeSpriteAnimation(int index)`: Removes a sprite animation from the system by index.
  - `updateFrame(SpriteAnimationData p_animationData)`: Updates the frame of the given sprite animation data.

### Frigg

The input manager for Valhalla, responsible for handling all input events.

- **Methods**:
  - `isKeyDown(KeyCode key)`: Checks if a key is currently pressed down.
  - `handleInput(KeyEvent event)`: Handles keyboard input events.
  - `handleSceneInput(Scene scene)`: Handles input events for the scene.

### Balder

The logger for Valhalla, responsible for logging messages to the console.

- **Methods**:
  - `setLevel(Level level)`: Sets the logging level for all handlers.

### IData

An interface for data components that can be associated with entities.

### ISystem

An interface for systems that can update their state.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.
