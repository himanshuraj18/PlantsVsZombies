package sample;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

import static javafx.fxml.FXMLLoader.load;

class User implements Serializable {
    private String name;
    private int level;
    public User(String n){
        name=n;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public void StartPage(Stage window) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AfterLogin.fxml"));
        Parent root = loader.load();
        Scene scene=new Scene(root,950,600);
        window.setScene(scene);
        ControllerAfterLogin cc=loader.getController();
        cc.setName(name);
    }

    public void resumeGame() throws IOException {
        Login.l.serialize();
    }
}
