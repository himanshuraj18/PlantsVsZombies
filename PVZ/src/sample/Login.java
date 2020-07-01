package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Login extends Application implements Serializable {
    private static ArrayList<User> users=new ArrayList<>();
    public static LoginManager l=new LoginManager();
    private transient Stage guiStage;
    private transient Scene scene;
    private transient Parent root;
    public Login() throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        scene=new Scene(root,950,600);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        guiStage=primaryStage;
        primaryStage.setTitle("Plants vs Zombies");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        l.deserialize();
        launch(args);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> u) {
        users = u;
    }
}