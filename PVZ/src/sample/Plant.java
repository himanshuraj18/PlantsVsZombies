package sample;

public abstract class Plant extends Actor {
    private final int price;
    private int health;
    private static long waitingtime;
    private int row;
    private int col;

    public Plant(int x, int y, int speed, int price, int health, long waitingtime, int row, int col) {
        super(x, y, speed);
        this.price = price;
        this.health=health;
        this.waitingtime=waitingtime;
        this.row = row;
        this.col=col;
    }

    @Override
    public abstract void act();

    public int getPrice() {
        return price;
    }

    public int getHealth() {
        return health;
    }
    public void decreaseHealth(int damage) {
        health -= damage;
        if(health<=0){
            dead=true;
        }
    }

    public static long getWaitingtime() {
        return waitingtime;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }
}
