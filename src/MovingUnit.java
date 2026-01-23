public abstract class MovingUnit extends Unit{

    protected int level, attack, defence, speed;
    protected double xp, currentHealth, maxHealth;
    protected int x,y;

    public MovingUnit(double health, int attack, int defence, int speed, World world){
        super(world);
        this.level = 1;
        this.xp = 0;
        this.maxHealth = health;
        this.currentHealth = health;
        this.attack = attack;
        this.defence = defence;
        this.speed = speed;
    }

    public abstract boolean attack();

    public void levelUp(){
        setXp(getXp() - getNeededXp(getLevel()));
        setLevel(getLevel() + 1);
        maxHealth++;
        setCurrentHealth(getCurrentHealth() + 1);
        attack++;
        defence++;
        speed++;
    }

    public boolean move(Direction direction){
        switch (direction){
            case UP -> {
                if(world.isFieldEmpty(x, y-1)){
                    world.setAField(null, x,y);
                    y--;
                    world.setAField(this, x,y);
                    return true;
                }else {
                    return false;
                }
            }
            case RIGHT -> {
                if(world.isFieldEmpty(x+1, y)){
                    world.setAField(null, x,y);
                    x++;
                    world.setAField(this, x,y);
                    return true;
                }else {
                    return false;
                }
            }
            case DOWN -> {
                if(world.isFieldEmpty(x, y+1)){
                    world.setAField(null, x,y);
                    y++;
                    world.setAField(this, x,y);
                    return true;
                }else {
                    return false;
                }
            }
            case LEFT -> {
                if(world.isFieldEmpty(x-1, y)){
                    world.setAField(null, x,y);
                    x--;
                    world.setAField(this, x,y);
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

    public String xpToString(){
        return xp + "/" + getNeededXp(getLevel());
    }

    public String healthToString(){
        return currentHealth + "/" + maxHealth;
    }

    public String attackToString(){
        return "" + attack;
    }

    public String defenceToString(){
        return "" + defence;
    }

    public String speedToString(){
        return "" + speed;
    }

    public double getNeededXp(int level){
        return 10 + ((level-1) * 5);
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

    public void setMaxHealth(double health) {
        this.maxHealth = health;
    }

    public void setCurrentHealth(double currentHealth){
        this.currentHealth = currentHealth;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }

    public double getXp() {
        return xp;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getCurrentHealth(){
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
    }
}
