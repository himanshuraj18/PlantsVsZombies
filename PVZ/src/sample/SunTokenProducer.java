package sample;

public abstract class SunTokenProducer extends Plant{
    private final double tokenwaitingtime;
    private long lasttokenadded;
    private final int sunproduction;
    public SunTokenProducer(int x, int y, int speed, int price, int health, long waitingtime, double tokenwaitingtime, int sunproduction, int row, int col) {
        super(x, y, speed, price, health, waitingtime, row, col);
        this.tokenwaitingtime = tokenwaitingtime;
        this.lasttokenadded=0;
        this.sunproduction=sunproduction;
    }

    @Override
    public abstract void act();

    public double getTokenwaitingtime() {
        return tokenwaitingtime;
    }

    public long getLasttokenadded() {
        return lasttokenadded;
    }
    public void setLasttokenadded(long lasttokenadded) {
        this.lasttokenadded = lasttokenadded;
    }

    public int getSunproduction() {
        return sunproduction;
    }
}
