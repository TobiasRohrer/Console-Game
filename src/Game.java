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
        System.out.println(world.startMessage());
        int classChoice = readInt(0,4, world.classSelection());
        createPlayer(classChoice);
        world.spawnPlayer(player);

        while (isRunning){
            world.printWorld();
            System.out.println();
            int menuChoice = readInt(1,5, world.gameMenu());
            boolean validMove = true;
            do {
                switch (menuChoice){
                    case 1 -> {
                        int moveChoice = readInt(1,4, world.moveMenu());
                        switch (moveChoice){
                            case 1 -> validMove = player.move(Directions.UP);
                            case 2 -> validMove = player.move(Directions.RIGHT);
                            case 3 -> validMove = player.move(Directions.DOWN);
                            case 4 -> validMove = player.move(Directions.LEFT);
                        }
                    }
                    case 2 -> {
                        System.out.println(world.getStats()); //To Implement
                    }
                    case 3 -> {
                        System.out.println(world.getInventory());
                    }
                    case 4 -> {
                        player.attack(); //To implement
                    }
                    case 5 -> {
                        System.out.println(world.getExitMessage()); //To implement
                        isRunning = false;
                    }
                }
            }while (!validMove);
        }
    }

    public void createPlayer(int classChoice){
        switch (classChoice){
            case 1 -> player = new Player(10, 15, 10, 5, world);
            case 2 -> player = new Player(15, 5, 15, 5, world);
            case 3 -> player = new Player(10, 10, 5, 15, world);
            case 4 -> isRunning = false;
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
