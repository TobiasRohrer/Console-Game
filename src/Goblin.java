public class Goblin extends Mob{

    public Goblin(World world, int level){
        super(40 + level, 5 + level, 5 + level, 5 + level, world, level);
        setUnitSymbol('G');
    }
}
