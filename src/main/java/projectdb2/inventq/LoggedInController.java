package projectdb2.inventq;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {



    @FXML
    private Button bt_logout;

    @FXML
    private Label lb_welcome;
    @FXML
    private Label lb_favChannel;




    @Override
    public void initialize(URL location, ResourceBundle resources) {



        bt_logout.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "LogIn.fxml", "Invent Q", null, null);
            }


        });
    }

    public void setUserInformation(String user, String favChannel){
        lb_welcome.setText("Welcome "+user+ "!");
        lb_favChannel.setText("The best app for you is: "+favChannel);
    }

}
