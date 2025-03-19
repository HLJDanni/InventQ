package projectdb2.inventq;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button bt_logIn;

    @FXML
    private Button bt_signUp;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bt_logIn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String username = tf_username.getText();
                    String password = tf_password.getText();
                    try {
                        DBUtils.logInUser(event, tf_username.getText(), tf_password.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });


        bt_signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "SignUp.fxml", "Sign Up", null, null);
            }
        });



    }
}
