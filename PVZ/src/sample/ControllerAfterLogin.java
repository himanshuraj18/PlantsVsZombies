package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ControllerAfterLogin {
    @FXML
    transient TextField tf_heading;
    @FXML
    transient ImageView savegame_bg;
    @FXML
    transient  Button back_button;
    @FXML
    transient Button sg1;
    @FXML
    transient  Button sg2;
    @FXML
    transient  Button sg3;
    @FXML
    transient Button sg4;
    @FXML
    transient  Button newg;
    @FXML
    transient Button load;
    @FXML
    transient Button choose;
    @FXML
    transient Button exit;
    @FXML
    transient Pane pane;

    transient Stage window;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void start_new_game() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root= loader.load();
        Level l=loader.getController();
        l.set_window(window);
        l.set_level(1);
        l.set_root(root);
        l.setName(name);
        l.startGame();
    }

    public void load_saved_game() throws IOException {
        pane.setVisible(true);
        pane.setDisable(false);
        newg.setVisible(false);
        newg.setDisable(true);
        load.setVisible(false);
        load.setDisable(true);
        choose.setVisible(false);
        choose.setDisable(true);
        exit.setVisible(false);
        exit.setDisable(true);
    }

    public void choose_level() throws IOException {
        ArrayList<Integer> temp=new ArrayList<>();
        temp.add(1);
        temp.add(2);
        temp.add(3);
        temp.add(4);
        temp.add(5);
        ChoiceDialog d = new ChoiceDialog(temp.get(0), temp);
        d.setHeaderText("Select a User");
        d.setContentText("Please select a User to play");
        d.showAndWait();
        if(d.getResult()==null){
            System.out.println("back");
        }
        else{
            int i= Integer.parseInt(d.getResult().toString());
            System.out.println(i);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root= loader.load();
            Level l=loader.getController();
            l.set_window(window);
            l.set_level(i);
            l.set_root(root);
            l.setName(name);
            l.startGame();
        }
    }

    public void exit_button(){
        Alert alert = new Alert(Alert.AlertType.NONE, "Do you want to exit the game?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.exit(0);
        }
    }

    public void back(){
        pane.setVisible(false);
        pane.setDisable(true);
        newg.setVisible(true);
        newg.setDisable(false);
        load.setVisible(true);
        load.setDisable(false);
        choose.setVisible(true);
        choose.setDisable(false);
        exit.setVisible(true);
        exit.setDisable(false);
    }

    public void mouse_click(MouseEvent mouseEvent) {
        window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        System.out.println("received");
    }

    public void deserialize(String filename) throws IOException {
        ObjectInputStream in = null;
        try{
            in=new ObjectInputStream(new FileInputStream(filename));
            User u= (User) in.readObject();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Game.fxml"));
            Parent root = loader.load();
            Scene scene=new Scene(root,950,600);
            window.setScene(scene);
            u.resumeGame();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally {
            in.close();
        }
    }

    public void load_sg1(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        deserialize(name+"_sg1.txt");
    }

    public void load_sg2(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        deserialize(name+"_sg2.txt");
    }

    public void load_sg3(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        deserialize(name+"_sg3.txt");
    }

    public void load_sg4(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        deserialize(name+"_sg4.txt");
    }
}