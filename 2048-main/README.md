# 2048 Game Implementation

This repository contains the implementation of the classic sliding tile puzzle game **2048** in Java. The project is part of the Programming 2 course at Saarland University, Summer Term 2024.

## Overview

In this project, we implement the 2048 game, where the goal is to slide and merge tiles on a grid to reach the tile with the value **2048**. The game logic, testing, and a simple AI for playing the game are included.

## Project Structure

- **src/**: Contains the source code of the project.
  - **ttfe/**: Main package containing the game logic.
  - **ttfe.tests/**: Package containing the tests for the simulator.
  - **ttfe.gui/**: GUI implementation to play the game.
- **docs/**: Documentation related to the project.
- **README.md**: This file.

## How to Run

### Prerequisites

- Java Development Kit (JDK) 21 (Temurin 21 LTS recommended)
- Visual Studio Code with Extension Pack for Java (recommended for development)

### Running the Game

To play the game, execute the main method in `TTFE.java`. You can control the game via arrow keys, similar to the web version of 2048.

**Command-Line Interface Options**:

- `--seed N`: Sets the seed for the random number generator (Default: N = 4711)
- `--width W`: Sets the width of the board (Default: W = 4)
- `--height H`: Sets the height of the board (Default: H = 4)
- `--player [h|c]`: Sets whether a human (`h`) or computer (`c`) is playing (Default: h)

### Testing

Tests are located in the `ttfe.tests` package. To run the tests, use the following command in your terminal:


License
This project is for educational purposes and does not have a license. Please respect the university's guidelines regarding academic integrity.
