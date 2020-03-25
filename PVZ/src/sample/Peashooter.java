package sample;

public class Peashooter extends Shooter{
    private static long lastadded;
    public Peashooter(int x, int y, int row, int col) {
        super(x, y, 0, 100, 100, 10, 25, 2, row, col);
    }

    @Override
    public void act() {
        //shoot karana hai yaha pe
    }

    public static long getLastadded() {
        return lastadded;
    }

    public static void setLastadded(long lastadded) {
        Peashooter.lastadded = lastadded;
    }
}
