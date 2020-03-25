package sample;

public class SunToken extends Actor {
    private double lastdropped;
    private int limit;
    private int value;
    public SunToken(int x, int y, int limit) {
        super(x, y, 2);
        this.limit=limit;
        value=25;
    }
    public SunToken(int x, int y) {
        super(x, y, 0);
        limit=0;
        value=50;
    }

    public int getValue(){
        return value;
    }


    @Override
    public void act(){
        if(y<limit) {
            setY(speed);
        }
    }

    public double getLastdropped() {
        return lastdropped;
    }
    public void setLastdropped(double lastdropped) {
        this.lastdropped = lastdropped;
    }
}