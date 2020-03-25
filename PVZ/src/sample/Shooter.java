package sample;

public abstract class Shooter extends Plant{
    private final int damage;
    private final double bulletwaitingtime;
    private long lastbulletfired;

    public Shooter(int x, int y, int speed, int price, int health, long waitingtime, int damage, double bulletwaitingtime, int row, int col) {
        super(x, y, speed, price,health, waitingtime, row, col);
        this.damage = damage;
        this.bulletwaitingtime=bulletwaitingtime;
        this.lastbulletfired=0;
    }

    public int getDamage() {
        return damage;
    }

    public double getBulletwaitingtime() {
        return bulletwaitingtime;
    }

    public long getLastbulletfired() {
        return lastbulletfired;
    }
    public void setLastbulletfired(long lastbulletfired) {
        this.lastbulletfired = lastbulletfired;
    }

    @Override
    public abstract void act();
}