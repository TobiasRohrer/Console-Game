import java.util.Random;

public class Mob extends MovingUnit{

    private boolean isDead = false;

    public Mob(double health, int attack, int defence, int speed, World world, int level){
        super(health, attack, defence, speed, world);
        setLevel(level);
    }

    public MovingUnit attack(){
        Unit[] surroundings = {world.getField(x+1,y), world.getField(x-1,y), world.getField(x,y+1), world.getField(x,y-1)};
        Player player = null;
        for (Unit unit : surroundings){
            if (unit instanceof Player) player = (Player) unit;
        }
        System.out.println("You have been attacked!");
        if (player != null){
            MovingUnit movingUnit = player.fight(this);
            if (movingUnit == this) world.removeMob(this);
            return movingUnit;
        }
        return null;
    }

    private int dealDamage(Player player){
        //Provisionary Formulas
        int damageDealt;
        if (playerDodged(player)) return -1;
        if (attack > player.getDefence()){
            damageDealt = attack - defence;
        }else {
            damageDealt = 1;
        }
        player.setCurrentHealth(player.getCurrentHealth() - damageDealt);
        return damageDealt;
    }

    public void fight(Player player){
        int damageDealt = dealDamage(player);
        if (damageDealt == -1){
            System.out.println("You dodged the enemy's attack!");
            System.out.println();
        } else if (damageDealt == 0) {
            System.out.println("The enemy dealt you 0 damage!");
            System.out.println("Your health is now: " + player.healthToString());
            System.out.println();
        }else {
            System.out.println("The enemy dealt " + damageDealt + " damage!");
            System.out.println("Your health is now: " + player.healthToString());
            System.out.println();
        }
    }

    public void tick(){
        //Check if Xp is higher than the threshold to level up
        if (getXp() >= getNeededXp(getLevel())) levelUp();

        if (getCurrentHealth() <= getMaxHealth() * 0.8){
            heal();
        } else if (isPlayerClose()) {
            attack();
        }else{
            int walkCount = 0;
            //Loops until a random Direction is found that is empty
            while (true){
                if (move(Direction.randomDirection())) break;
                walkCount++;
                //Prevent infinite loop in case there is no space to move
                if (walkCount > 100) break;
            }
        }
        setXp(getXp() + getNeededXp(getLevel()) * 0.1);
    }

    public boolean isPlayerClose(){
        //Checks the fields directly next to the mob for the player (not diagonally)
        return getWorld().getFieldValue(getX() + 1, getY()) == 'O' || getWorld().getFieldValue(getX() + 1, getY() + 1) == 'O' || getWorld().getFieldValue(getX() - 1, getY()) == 'O' || getWorld().getFieldValue(getX() - 1, getY() - 1) == 'O';
    }

    private boolean playerDodged(Player player){
        Random random = new Random();
        if (player.getSpeed() >= speed){
            return random.nextInt(speed*4) > player.getSpeed();
        }
        return false;
    }

    public void heal(){
        setCurrentHealth(getCurrentHealth() + getMaxHealth() * 0.2);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
