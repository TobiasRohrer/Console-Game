public class Player extends MovingUnit{

    private Item[] inventory;

    public Player(double health, double attack, double defence, double speed, World world){
        super(health, attack, defence, speed, world);
        this.inventory = new Item[5];
        setUnitSymbol('O');
    }

    public boolean attack(){
        System.out.println("Not yet implemented");
        return false;   //to Implement
    }
}
