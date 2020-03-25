package sample;

public class Lawnmower extends Actor{
    private int limit;
    private boolean active;
    private int row;
    public Lawnmower(int x, int  y, int row) {
        super(x, y, 10);
        limit=1000;
        this.row=row;
        active=false;
    }

    @Override
    public void act() {
        if(x<limit){
            setX(speed);
        }
        else{
            dead=true;
            active =false;
        }
    }

    public boolean getActive(){
        return active;
    }

    public void setActive(boolean val){
        active=val;
    }

    public int getRow(){
        return row;
    }
}
