package sample;

public class Zombie extends Actor {
    private final int damage;
    private int health;
    private long lastattack;
    private int row;
    private int limit;
    private double attack_waiting_time;
    public Zombie(int x, int y, int speed, int damage, int health, long lastattack, int row, double attack_waiting_time) {
        super(x, y, speed);
        this.damage = damage;
        this.health = health;
        this.lastattack = 0;
        this.row=row;
        limit=150;
        this.attack_waiting_time=attack_waiting_time;
    }

    @Override
    public void act() {
        if(x>limit){
            setX(speed);
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }
    public void decreaseHealth(int val) {
        health -=val;
        if(health<=0){
            dead=true;
        }

    }

    public long getLastattack() {
        return lastattack;
    }
    public void setLastattack(long lastattack) {
        this.lastattack = lastattack;
    }

    public double get_attack_waiting_time(){
        return attack_waiting_time;
    }
    public void set_attack_waiting_time(double val){
        attack_waiting_time=val;
    }

    public int getRow(){ return row; }
}