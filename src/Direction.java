import java.util.Random;

public enum Direction {

    UP,
    RIGHT,
    DOWN,
    LEFT;

    public static final Random random = new Random();

    public static Direction randomDirection(){
        Direction[] directions = values();
        return directions[random.nextInt(directions.length)];
    }
}
