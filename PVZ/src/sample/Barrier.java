package sample;

public abstract class Barrier extends Plant {

    public Barrier(int x, int y, int speed, int price, long waitingtime, int row, int col) {
        super(x, y, speed, price, 100, waitingtime, row, col);
    }

    @Override
    public abstract void act();
}
