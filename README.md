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
```

## How to Use Brawtx via JitPack

Add the JitPack repository to your Maven `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add Brawtx as a dependency, specifying the version tag:

```xml
<dependency>
    <groupId>jua.sergi.brawtx</groupId>
    <artifactId>brawtx</artifactId>
    <version>0.0.1</version>
</dependency>
```

Replace `0.0.1` with the tag or release version you want to use.

You can check available versions and build status here:  
https://jitpack.io/#SergiJuan/brawtx

---

## How to Contribute

Feel free to contribute to Brawtx by cloning the repository and submitting pull requests.

### Steps:

```
git clone https://github.com/SergiJuan/brawtx.git
cd brawtx
```

You can build the project locally using Maven:

```
mvn clean install
```

Make your changes, add tests if needed, and open a pull request with a clear description.

---

## License

MIT License © 2025 SergiJuan

Permission is hereby granted, free of charge, to any person obtaining a copy  
of this software and associated documentation files (the "Software"), to deal  
in the Software without restriction, including without limitation the rights  
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
copies of the Software, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all  
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE  
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER  
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,  
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  
SOFTWARE.
