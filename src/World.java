import java.util.Random;

public class World {

    private Unit[][] world;

    public World(){
        world = new Unit[10][10];
        for (int i = 0; i < world[0].length; i++){
            for (int j = 0; j < world.length; j++){
                if (i == 0 || i == world[0].length-1){
                    world[i][j] = new Wall(this);
                } else if (j == 0 || j == world.length-1) {
                    world[i][j] = new Wall(this);
                }else{
                    world[i][j] = null;
                }
            }
        }
    }

    public World(Unit[][] world){
        this.world = world;
    }

    public void spawnPlayer(Player player){
        if (isFieldEmpty(1, 1)){
            setAField(player, 1, 1);
            player.setX(1);
            player.setY(1);
        }
    }

    public void spawnMob(Mob mob){
        int x,y;
        do {
            x = getRandomXValue();
            y = getRandomYValue();
        } while (!isFieldEmpty(x, y));
        setAField(mob, x, y);
        mob.setX(x);
        mob.setY(y);
    }

    private int getRandomXValue(){
        Random random = new Random();
        return random.nextInt(1, getWorld()[0].length-1);
    }

    private int getRandomYValue(){
        Random random = new Random();
        return random.nextInt(1,getWorld().length);
    }

    public boolean customSpawnPlayer(Player player, int x, int y){
        if (isFieldEmpty(x, y)){
            setAField(player, x, y);
            return true;
        }
        return false;
    }

    public boolean isFieldEmpty(int x, int y){
        return getFieldValue(x, y) == ' ';
    }

    public Unit getField(int x, int y){
        if (isCoordinateInBounds(x,y)) return world[y][x];
        return null;
    }

    public char getFieldValue(int x, int y){
        if (isCoordinateInBounds(x,y)){
            if (world[y][x] != null){
                return world[y][x].getUnitSymbol();
            }else {
                return ' ';
            }
        }
        return '/';
    }

    public boolean isCoordinateInBounds(int x, int y){
        return x >= 0 && x < world[0].length && y >= 0 && y < world.length;
    }

    public void printWorld(){
        System.out.println("Map:");
        for (Unit[] height : world){
            String row = "";
            for (Unit width : height){
                if (width != null){
                    row += width.getUnitSymbol();
                }else {
                    row += ' ';
                }
            }
            System.out.println(row);
        }
    }

    public void setAField(MovingUnit movingUnit, int x, int y){
        if (isCoordinateInBounds(x,y)){
            world[y][x] = movingUnit;
        }
    }

    public Unit[][] getWorld() {
        return world;
    }

    public void setWorld(Unit[][] world) {
        this.world = world;
    }
}
