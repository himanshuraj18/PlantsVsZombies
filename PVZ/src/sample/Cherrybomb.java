package sample;

public class Cherrybomb extends Bomb {
    private static long lastadded=0;
    public Cherrybomb(int x, int y, int row, int col, long last_used) {
        super(x, y, 0, 150, 15, 1, row, col, last_used);
    }

    @Override
    public void act() {

    }

    public static long getLastadded() {
        return lastadded;
    }

    public static void setLastadded(long lastadded) {
        Cherrybomb.lastadded = lastadded;
    }
}
