package sample;

public abstract class Bomb extends Plant{
    private final int effectedtiles;
    private long last_used;

    public Bomb(int x, int y, int speed, int price, long waitingtime, int effectedtiles, int row, int col, long last_used) {
        super(x, y, speed, price, 100, waitingtime, row, col);
        this.effectedtiles = effectedtiles;
        this.last_used=last_used;
    }

    @Override
    public abstract void act();

    public int getEffectedtiles() {
        return effectedtiles;
    }
    public long get_last_used(){
        return last_used;
    }
    public void set_last_used(long val){
        last_used = val;
    }
}
