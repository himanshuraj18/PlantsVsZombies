package sample;

import java.io.Serializable;

public class Sun_Counter implements Serializable {
    private int count;
    public Sun_Counter(){
        count=0;
    }
    public int getCount(){
        return count;
    }
    public void setCount(int incr){
        count+=incr;
    }
}
