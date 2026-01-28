import java.util.PriorityQueue;
import java.util.Scanner;

public class Game {

    private World world;
    private boolean isRunning;
    private Scanner scanner;
    private Player player;
    private PriorityQueue<Mob> mobQueue = new PriorityQueue<>((mob1, mob2) -> Double.compare(mob2.getSpeed(),mob1.getSpeed()));

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
        spawnGoblin(5);
        while (isRunning){
            menuSelection();
            System.out.println();
            mobQueue.forEach(Mob::tick);
        }
    }

    public void menuSelection(){
        boolean validMove;
        do {
            world.printWorld();
            System.out.println();
            if (player.getNeededXp(player.getLevel()) <= player.getXp()){
                player.levelUp();
                switch (readInt(1,4,getLevelUpMessage())){
                    case 1 -> {
                        player.setMaxHealth(player.getMaxHealth() + 1);
                        player.setCurrentHealth(player.getCurrentHealth() + 1);
                    }
                    case 2 -> player.setAttack(player.getAttack() + 1);
                    case 3 -> player.setDefence(player.getDefence() + 1);
                    case 4 -> player.setSpeed(player.getSpeed() + 1);
                }
            }
            int menuChoice = readInt(1,5, gameMenu());
            validMove = true;
            switch (menuChoice){
                case 1 -> {
                    int moveChoice = readInt(1,4, moveMenu());
                    switch (moveChoice){
                        case 1 -> validMove = player.move(Direction.UP);
                        case 2 -> validMove = player.move(Direction.RIGHT);
                        case 3 -> validMove = player.move(Direction.DOWN);
                        case 4 -> validMove = player.move(Direction.LEFT);
                    }
                }
                case 2 -> {
                    System.out.println(getStats());
                    validMove = false;
                }
                case 3 -> {
                    System.out.println(getInventory());   //To implement
                    validMove = false;
                }
                case 4 -> {
                    player.attack();
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

    public String getLevelUpMessage(){
        return "You leveled Up!\nChoose which Attribute you want to increase!\n\n" + "1. Health: " + player.getMaxHealth() + "\n2. Attack: " + player.attackToString() + "\n3. Defence: " + player.defenceToString() + "\n4. Speed: " + player.speedToString() + "\n";
    }

    public String getStats(){
        return "Player LvL " + player.getLevel() + "\n 1. XP: " + player.xpToString() + "\n 2. Health: " + player.healthToString() + "\n 3. Attack: " + player.attackToString() + "\n 4. Defence: " + player.defenceToString() + "\n 5. Speed: " + player.speedToString() + "\n";
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

    public void spawnGoblin(int amount){
        for (int i = 0; i < amount; i++){
            Goblin goblin = new Goblin(world, 5);
            mobQueue.add(goblin);
            world.spawnMob(goblin);
        }
    }

    public void spawnBossGoblin(int amount){
        for (int i = 0; i < amount; i++){
            BossGoblin bossGoblin = new BossGoblin(world, 10);
            mobQueue.add(bossGoblin);
            world.spawnMob(bossGoblin);
        }
    }

    public void createPlayer(int classChoice){
        switch (classChoice){
            case 1 -> player = new Player(50, 15, 10, 5, world);
            case 2 -> player = new Player(80, 5, 15, 5, world);
            case 3 -> player = new Player(50, 10, 5, 15, world);
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
