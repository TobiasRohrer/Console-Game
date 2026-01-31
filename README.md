A Java console dungeon crawler
  A text-based rogue-like game in java. Created to show my understanding of OOP, focusing on class inheritance, polymorphism without any external game engines.

Overview:
  The game runs entirely in the console. The player chooses a class, navigates through randomly generated rooms, fights enemies in turn-based combat and levels up stats to defeat each rooms boss.

  Key features:
    Room generation: Each room has a random size, wall layout and spawn points
    Class system: Three different classes (Warrior, Tank, Rogue) whith different stats are available to choose from
    Turn based combat: A turn based combat, depending of the speed of the player and its enemy
    Progression: Killing mobs rewards the player with experience, which can be used to level up and allocate points to different stats to strengthen the player

Implementation:
  The project is written in standart Java
    Entity Management: Used abstract classes for shared logic and polymorphism to handle interactions between Player and different Mob types
    Input handling: Inputs registered via numeric inputs into the console
    Game Loop: The main loop uses a PriorityQueue to handle  correct movement order of Player and Mob entities

How to Run
  You can compile and run the code directly from the terminal
  
  1. Clone the repository:
     git clone https://github.com/TobiasRohrer/Console-Game.git
     cd Console-Game
     
  2. Compile the java files:
     javac *.java

  3. Run the game:
     java Game

Controls
  The game is controlled by entering numbers corresponding to the on-screen menu:
    1-4: Select Class/Direction
    Menu: Used to view stats, attack, or flee
    Map Symbols:
      0: Player
      G: Goblin
      B: Boss
      |: Exit
      
Future Implementation
  Inventory system: 
    The class and option in the menu are already there, with the logic missing
    Future implementation would add items such as health potions dropped from killed monsters or weapons to increase the players capabilities and diversify builds
    
  Map obscurity:
    Currently the player is able to see the whole map and all the movements of the present mobs as well as the exit
    Future implementation would see the player only being able to see a specific range around him
    
  Map diversity:
    Currently each room is just a square with randomly generated length and width
    Future implementation could introduce new room shapes to improve replayability
    
  Increased opponent diversity:
    In the current state the is only the Goblin mob and boss implemented
    Future implementation could add several new types of mobs by just using the fully functional Mob and Bossmob parent classes for easy addition
    
  Code Refactoring:
    The current state of the code is a bit all over the place, with several methods that could be shortened or removed by combining different parts and removing duplicates
