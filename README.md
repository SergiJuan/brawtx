# Brawtx

**A lightweight Java framework providing a fixed timestep loop, rendering abstraction, and window management — simple, general purpose, and in active development.**

---

## Overview

Brawtx is a minimalistic Java framework designed for simple 2D graphical applications or any project that requires a controlled update-render loop. It is not limited to games but can be used for general real-time rendering needs. The project is still in active development but is functional and can be integrated into your projects.

Features include:

- A **core engine** with a fixed FPS loop supporting pause, resume, and frame skipping.
- An **abstract renderer** system based on `BufferStrategy` for efficient rendering.
- A **window wrapper** around `JFrame` and `Canvas` managing the rendering surface.
- Utility classes for FPS tracking and delta time measurement.

---

## Features

- Fixed timestep loop targeting smooth 60 FPS by default (configurable).
- Pause and resume support.
- Triple buffering rendering with abstracted graphics context.
- Simple window creation with customizable size and title.
- FPS and delta time tracking utilities.

---

## Getting Started

### Prerequisites

- Java 17 or later
- Maven (build tool)

### Project Structure

- `jua.sergi.brawtx.core` - Core classes (`BrEngine`, `BrRenderer`, `BrWindow`).
- `jua.sergi.brawtx.utils` - Utility classes (`BrTimer`).
- `jua.sergi.brawtx.examples` - Example implementations demonstrating basic usage.

---

## Example Usage

```java
public static void main(String[] args) {
    BrWindow window = new BrWindow("Brawtx Test", 800, 600);
    SimpleRenderer renderer = new SimpleRenderer(window);
    SimpleEngine engine = new SimpleEngine(renderer, 60); // Target 60 FPS loop
    engine.start();
}
