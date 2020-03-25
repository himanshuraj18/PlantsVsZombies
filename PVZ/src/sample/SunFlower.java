package sample;

public class SunFlower extends SunTokenProducer {
    private static long lastadded;
    public SunFlower(int x, int y, int row, int col) {
        super(x, y, 0, 50, 100, 10, 15, 50, row, col);
    }

    @Override
    public void act() {

    }

    public static long getLastadded() {
        return lastadded;
    }

    public static void setLastadded(long lastadded) {
        SunFlower.lastadded = lastadded;
    }

}