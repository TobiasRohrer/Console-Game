public class Mob extends MovingUnit{

    public Mob(double health, int attack, int defence, int speed, World world, int level){
        super(health, attack, defence, speed, world);
        setLevel(level);
    }

    public boolean attack(){
        System.out.println("Attack not implemented yet");
        return false; //To implement
    }

    public void fight(){
        System.out.println("Fight not implemented yet");
    }

    public void tick(){
        //Check if Xp is higher than the threshold to level up
        if (getXp() >= getNeededXp(getLevel())) levelUp();
        //Heal if Mob Health is lower than 80%
        if (getCurrentHealth() <= getMaxHealth() * 0.8){
            heal();
        } else if (isPlayerClose()) {
            attack(); //To Implement
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
        //Returns whether the Player is UP,DOWN,LEFT or RIGHT of the mob
        return getWorld().getFieldValue(getX() + 1, getY()) == 'O' || getWorld().getFieldValue(getX() + 1, getY() + 1) == 'O' || getWorld().getFieldValue(getX() - 1, getY()) == 'O' || getWorld().getFieldValue(getX() - 1, getY() - 1) == 'O';
    }

    public void heal(){
        setCurrentHealth(getCurrentHealth() + getMaxHealth() * 0.2);
    }
}
