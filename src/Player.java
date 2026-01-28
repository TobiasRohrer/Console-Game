import java.util.Random;
import java.util.Scanner;

public class Player extends MovingUnit{

    private Item[] inventory;
    Scanner scanner = new Scanner(System.in);

    public Player(double health, int attack, int defence, int speed, World world){
        super(health, attack, defence, speed, world);
        this.inventory = new Item[5];
        setUnitSymbol('O');
    }

    public boolean attack(){
        int attackDirection = readInt(1,5, attackMessage());
        switch (attackDirection){
            case 1 -> {
                if (isHereAnEnemy(x,y-1)) return fight((Mob) world.getField(x,y+1));
                System.out.println("You tried attacking Air!");
                return false;
            }
            case 2 -> {
                if (isHereAnEnemy(x+1,y)) return fight((Mob) world.getField(x+1,y));
                System.out.println("You tried attacking Air!");
                return false;
            }
            case 3 -> {
                if (isHereAnEnemy(x,y+1)) return fight((Mob) world.getField(x,y-1));
                System.out.println("You tried attacking Air!");
                return false;
            }
            case 4 -> {
                if (isHereAnEnemy(x-1,y)) return fight((Mob) world.getField(x-1,y));
                System.out.println("You tried attacking Air!");
                return false;
            }
            default -> {
                return false;
            }
        }
    }

    public boolean fight(Mob mob){
        while(true){
            if (mob.getCurrentHealth() <= 0){
                System.out.println("You killed the enemy!");
                if (mob instanceof BossMob){
                    world.setBossKilled(true);
                }
                return true;   // Drop Loot missing
            }
            if (currentHealth <= 0){
                System.out.println("You died to the enemy!");
                return false;   //Death missing
            }
            if (speed >= mob.getSpeed()){
                System.out.println("You are quicker so you attack first!");
                if (!fightHelper(mob)) return false;
                if (mob.getCurrentHealth() > 0) mob.fight(this);
            }else {
                System.out.println("The enemy is quicker so he attacks first!");
                mob.fight(this);
                if (!fightHelper(mob)) return false;
            }

        }
    }

    private boolean fightHelper(Mob mob){
        switch (readInt(1,3,fightMenu())){
            case 1 -> {
                int damageDealt = dealDamage(mob);
                if (damageDealt == -1){
                    System.out.println("The enemy dodged your attack!");
                    System.out.println();
                } else if (damageDealt == 0) {
                    System.out.println("You dealt 0 damage!");
                    System.out.println("The enemies health is now: " + mob.healthToString());
                    System.out.println();
                }else {
                    System.out.println("You dealt " + damageDealt + " damage!");
                    System.out.println("The enemies health is now: " + mob.healthToString());
                    System.out.println();
                }
                return true;
            }
            case 2 -> {
                //Use item, to Implement
                System.out.println("Item usage not implemented yet!");
                return false;
            }
            case 3 -> {
                if (tryToFlee(mob)){
                    System.out.println("You successfully fled!");
                    return false;
                }else {
                    System.out.println("The enemy is quicker than you!");
                    System.out.println("You can not flee!");
                    System.out.println();
                }
            }
        }
        return true;
    }

    private int dealDamage(Mob mob){
        //Provisionary Formulas
        int damageDealt;
        if (mobDodged(mob)) return -1;
        if (attack > mob.getDefence()){
            damageDealt = attack - defence;
        }else {
            damageDealt = 1;
        }
        mob.setCurrentHealth(mob.getCurrentHealth() - damageDealt);
        return damageDealt;
    }

    private boolean tryToFlee(Mob mob){
        Random random = new Random();
        if (mob.getSpeed() <= speed){
            return random.nextInt(mob.getSpeed()*3) > speed;
        }
        return false;
    }

    private boolean mobDodged(Mob mob){
        Random random = new Random();
        if (mob.getSpeed() >= speed){
            return random.nextInt(speed*4) > mob.getSpeed();
        }
        return false;
    }

    private boolean isHereAnEnemy(int x, int y){
        return getWorld().getField(x,y) instanceof Mob;
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

    private String fightMenu(){
        return "Player LvL " + getLevel() + " " + healthToString() + "HP\n" + """
                What do you want to do:
                1:Attack
                2:Use Item
                3:Try to flee (Chance depends on both of your speeds)
                """;
    }

    private String attackMessage(){
        return """
                Which Direction do you want to attack in:
                1: UP
                2: RIGHT
                3: DOWN
                4: LEFT
                """;
    }
}
