
public class World {

    private char[][] world;

    public World(){
        world = new char[10][10];
        for (int i = 0; i < world[0].length; i++){
            for (int j = 0; j < world.length; j++){
                if (i == 0 || i == world[0].length-1){
                    world[i][j] = '#';
                } else if (j == 0 || j == world.length-1) {
                    world[i][j] = '#';
                }else{
                    world[i][j] = ' ';
                }
            }
        }
    }

    public World(char[][] world){
        this.world = world;
    }

    public void spawnPlayer(Player player){
        if (isFieldEmpty(1, 1)){
            setAField('O', 1, 1);
            player.setX(1);
            player.setY(1);
        }
    }
    public boolean customSpawnPlayer(Player player, int x, int y){
        if (isFieldEmpty(x, y)){
            setAField('O', x, y);
            return true;
        }
        return false;
    }

    public boolean isFieldEmpty(int x, int y){
        return getFieldValue(x, y) == ' ';
    }

    public char getFieldValue(int x, int y){
        if (isCoordinateInBounds(x, y)) return world[y][x];
        return '/';
    }

    public boolean isCoordinateInBounds(int x, int y){
        return x >= 0 && x < world[0].length && y >= 0 && y < world.length;
    }

    public void printWorld(){
        System.out.println("Map:");
        for (char[] height : world){
            String row = "";
            for (char width : height){
                row += width;
            }
            System.out.println(row);
        }
    }

    public void setAField(char newField, int x, int y){
        if (isCoordinateInBounds(x,y)) world[y][x] = newField;
    }

    public char[][] getWorld() {
        return world;
    }

    public void setWorld(char[][] world) {
        this.world = world;
    }
}
