public class Unit {

    protected char unitSymbol;
    protected World world;

    public Unit(World world){
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public char getUnitSymbol() {
        return unitSymbol;
    }

    public void setUnitSymbol(char unitSymbol) {
        this.unitSymbol = unitSymbol;
    }
}
