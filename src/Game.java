import java.util.Scanner;

public class Game {

    private World world;
    private boolean isRunning;
    private Scanner scanner;
    private Player player;

    public Game(){
        world = new World();
        scanner = new Scanner(System.in);
        isRunning = true;
    }

    public void startGame(){
        System.out.println(startMessage());
        int classChoice = readInt(0,4, classSelection());
        createPlayer(classChoice);
        if (isRunning) world.spawnPlayer(player);
        while (isRunning){
            world.printWorld();
            System.out.println();
            menuSelection();
            System.out.println();
        }
    }

    public void menuSelection(){
        int menuChoice = readInt(1,5, gameMenu());
        boolean validMove = true;
        do {
            switch (menuChoice){
                case 1 -> {
                    int moveChoice = readInt(1,4, moveMenu());
                    switch (moveChoice){
                        case 1 -> validMove = player.move(Directions.UP);
                        case 2 -> validMove = player.move(Directions.RIGHT);
                        case 3 -> validMove = player.move(Directions.DOWN);
                        case 4 -> validMove = player.move(Directions.LEFT);
                    }
                }
                case 2 -> {
                    System.out.println(getStats());
                }
                case 3 -> {
                    System.out.println(getInventory());   //To implement
                }
                case 4 -> {
                    player.attack(); //To implement
                }
                case 5 -> {
                    System.out.println(getExitMessage());
                    isRunning = false;
                }
            }
        }while (!validMove);
    }

    public String getExitMessage(){
        return "Thank you for playing!\nSee you next time!";
    }

    public String getInventory(){
        return "Inventory not yet implemented";
    }

    public String getStats(){
        return "Player LvL " + player.getLevel() + "\n 1. XP: " + player.xpToString() + "\n 2. Health: " + player.healthToString() + "\n 3. Attack: " + player.attackToString() + "\n 4. Defence: " + player.defenceToString() + "\n 5. Speed: " + player.speedToString();
    }

    public String startMessage(){
        return """
               Welcome to the Console Dungeon!
               Here you will move through the Dungeon rooms, fight monsters, level up and loot chests!
               First you need to select a Class!
               Just type in the number of a class to see an overview!
               """;
    }

    public String classSelection(){
        return """
               1: Warrior
               2: Tank
               3: Rogue
               4: Exit
               """;
    }

    public String moveMenu(){
        return """
                Move:
                1: UP
                2: RIGHT
                3: DOWN
                4: LEFT
                """;
    }

    public String gameMenu(){
        return """
                Menu:
                1: Move
                2: View Stats
                3: Open inventory
                4: Attack
                5: Exit Game
                """;
    }

    public void createPlayer(int classChoice){
        switch (classChoice){
            case 1 -> player = new Player(10, 15, 10, 5, world);
            case 2 -> player = new Player(15, 5, 15, 5, world);
            case 3 -> player = new Player(10, 10, 5, 15, world);
            case 4 -> {
                isRunning = false;
                System.out.println(getExitMessage());
            }
        }
    }

    public int readInt(int min, int max, String prompt){
        while(true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number. Try again.");
            }
        }
    }
}
