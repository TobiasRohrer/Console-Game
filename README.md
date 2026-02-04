# Java Console Dungeon Crawler

> A text-based rogue-like game in Java.

This project was created to demonstrate a strong understanding of **Object-Oriented Programming (OOP)**, focusing on class inheritance and polymorphism without relying on any external game engines.

## Overview
The game runs entirely in the console. The player chooses a class, navigates through randomly generated rooms, fights enemies in turn-based combat, and levels up stats to defeat each room's boss.

## Key Features

* **Room Generation:** Each room has a random size, wall layout, and spawn points.
* **Class System:** Three different classes (**Warrior, Tank, Rogue**), each with different stats, are available to choose from.
* **Turn-Based Combat:** Combat depends on the speed of the player relative to the enemy.
* **Progression:** Killing mobs rewards the player with experience points (XP), which can be used to level up and allocate points to different stats to strengthen the player.

## Implementation

* **Language:** The project is written in standard Java.
* **Entity Management:** Uses abstract classes for shared logic and polymorphism to handle interactions between the Player and different Mob types.
* **Input Handling:** Inputs are registered via numeric inputs into the console.
* **Game Loop:** The main loop uses a `PriorityQueue` to handle the correct movement order of Player and Mob entities.

## How to Run

You can compile and run the code directly from the terminal.

1. **Clone the repository:**
   ```bash
   git clone https://github.com/TobiasRohrer/Console-Game.git
   cd Console-Game
   ```

2. **Compile the Java files:**
   ```bash
   javac *.java
   ```

3. **Run the game:**
   ```bash
   java Game
   ```

## Controls

The game is controlled by entering numbers corresponding to the on-screen menu.

| Input | Action |
| :--- | :--- |
| **1-4** | Select Class / Choose Direction |
| **Menu** | Used to view stats, attack, or flee |

### Map Symbols

| Symbol | Entity |
| :---: | :--- |
| `0` | Player |
| `G` | Goblin |
| `B` | Boss |
| `|` | Exit |

## Future Implementation

* **Inventory System:** The class and menu options exist, but the logic is pending. Future updates will add items such as health potions dropped from monsters or weapons to diversify builds.
* **Map Obscurity:** Currently, the map and mob movements are fully visible. Future implementation will introduce a "Fog of War" where the player sees only a specific range.
* **Map Diversity:** Rooms are currently square with random dimensions. Future updates will introduce new room shapes to improve replayability.
* **Increased Opponent Diversity:** Currently, only Goblin and Boss mobs are implemented. Using the existing `Mob` and `BossMob` parent classes, new types will be added easily.
* **Code Refactoring:** Optimization of the codebase to reduce method length, combine parts, and remove duplicate logic.
