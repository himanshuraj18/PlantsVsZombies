package sample;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;

public class ControllerLogin {
    transient Stage window;
    @FXML
    transient Button newu;
    @FXML
    transient Button existingu;
    @FXML
    transient Button exit;
    @FXML
    transient Pane pane;
    @FXML
    transient TextField tf_input;
    @FXML
    transient TextField tf_error;

    public void new_user_button() throws IOException {
        newu.setDisable(true);
        existingu.setDisable(true);
        exit.setDisable(true);
        newu.setVisible(false);
        existingu.setVisible(false);
        exit.setVisible(false);
        pane.setVisible(true);
        pane.setDisable(false);
    }

    public void submit() throws IOException {
        tf_error.setVisible(false);
        tf_error.setText("");
        String name=tf_input.getText();
        if(name.equals("") || !Character.isLetter(name.charAt(0))){
            try{
                throw new InvalidUsernameException();
            }
            catch (Exception e){
                tf_error.setVisible(true);
                tf_error.setText("Error: Invalid Username.");
                tf_input.setText("");
            }
        }
        else{
            Boolean f=true;
            for(User u:Login.getUsers()){
                if(u.getName().equals(name)){
                    f=false;
                    try{
                        throw new UserAlreadyExistsException();
                    }
                    catch (Exception e){
                        tf_error.setVisible(true);
                        tf_error.setText("ERROR: User Already Exists.");
                        tf_input.setText("");
                    }
                    break;
                }
            }
            if(f){
                User u=new User(name);
                ArrayList<User> temp=Login.getUsers();
                temp.add(u);
                for(User i: temp){
                    if(name.equals(i.getName())){
                        i.StartPage(window);
                    }
                }
                Login.setUsers(temp);
                cancel();
            }
        }
    }

    public void cancel() {
        newu.setDisable(false);
        existingu.setDisable(false);
        exit.setDisable(false);
        newu.setVisible(true);
        existingu.setVisible(true);
        exit.setVisible(true);
        pane.setVisible(false);
        pane.setDisable(true);
    }

    public void existing_user_button() throws IOException {
        ArrayList<User> temp=Login.getUsers();
        if(temp.size()==0){
            Alert alert = new Alert(Alert.AlertType.NONE, "No User Saved Currently", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        ChoiceDialog d = new ChoiceDialog(temp.get(0), temp);
        d.setHeaderText("Select a User");
        d.setContentText("Please select a User to play");
        d.showAndWait();
        if(d.getResult()==null){
            System.out.println("back");
        }
        else{
            String name= d.getResult().toString();
            for(User i: temp){
                if(name.equals(i.getName())){
                    i.StartPage(window);
                }
            }
            Login.setUsers(temp);
        }
    }

    public void exit_button(){
        Alert alert = new Alert(Alert.AlertType.NONE, "Do you want to exit the game?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.exit(0);
        }
    }

    public void mouse_click(MouseEvent mouseEvent) {
        window = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        System.out.println("received");
    }


}