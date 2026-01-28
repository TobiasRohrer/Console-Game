public class BossGoblin extends BossMob{

    public BossGoblin(World world, int level){
        super(100 + level, 15 + level, 15 + level, 5 + (level / 5), world, level);
        setUnitSymbol('B');
    }
}
