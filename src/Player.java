public class Player {

    private World world;
    private int level;
    private double xp, health, attack, defence, speed;
    private Item[] inventory;
    private int x,y;

    public Player(double health, double attack, double defence, double speed, World world){
        this.level = 1;
        this.xp = 0;
        this.health = health;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
        this.inventory = new Item[5];
        this.world = world;
    }

    public boolean attack(){
        return false;   //to Implement
    }

    public boolean move(Directions direction){
        switch (direction){
            case UP -> {
                if(world.isFieldEmpty(x, y-1)){
                    world.setAField(' ', x,y);
                    y--;
                    world.setAField('O', x,y);
                    return true;
                }else {
                    return false;
                }
            }
            case RIGHT -> {
                if(world.isFieldEmpty(x+1, y)){
                    world.setAField(' ', x,y);
                    x++;
                    world.setAField('O', x,y);
                    return true;
                }else {
                    return false;
                }
            }
            case DOWN -> {
                if(world.isFieldEmpty(x, y+1)){
                    world.setAField(' ', x,y);
                    y++;
                    world.setAField('O', x,y);
                    return true;
                }else {
                    return false;
                }
            }
            case LEFT -> {
                if(world.isFieldEmpty(x-1, y)){
                    world.setAField(' ', x,y);
                    x--;
                    world.setAField('O', x,y);
                    return true;
                }else {
                    return false;
                }
            }
            default -> {
                return false;
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setDefence(double defence) {
        this.defence = defence;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }

    public int getLevel() {
        return level;
    }

    public double getXp() {
        return xp;
    }

    public double getHealth() {
        return health;
    }

    public double getAttack() {
        return attack;
    }

    public double getDefence() {
        return defence;
    }

    public double getSpeed() {
        return speed;
    }

    public Item[] getInventory() {
        return inventory;
    }
}
