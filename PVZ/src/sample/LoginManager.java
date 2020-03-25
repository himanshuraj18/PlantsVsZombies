package sample;

import java.io.*;
import java.util.ArrayList;

public class LoginManager implements Serializable {
    public ArrayList<User> u=new ArrayList<>();
    public void serialize() throws IOException {
        ObjectOutputStream out = null;
        try {
            out=new ObjectOutputStream(new FileOutputStream("saved_game.txt"));
            out.writeObject(this);
        }
        finally {
            out.close();
            System.exit(1);
        }
    }

    public ArrayList<User> deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        LoginManager l;
        try{
            in=new ObjectInputStream(new FileInputStream("saved_game.txt"));
            l=(LoginManager) in.readObject();
            return l.u;
        }
        catch(IOException e){
            return new ArrayList<>();
        }
        finally {
            try{
                in.close();
            }
            catch(Exception e){
                return new ArrayList<>();
            }
        }
    }
}
