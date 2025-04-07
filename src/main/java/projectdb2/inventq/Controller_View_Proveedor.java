package projectdb2.inventq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_View_Proveedor implements Initializable {

    @FXML
    private Button bt_back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura el evento para el botón
        bt_back.setOnAction(event -> {
            DBUtils.changeScene(event, "InApp.fxml", "Proveedor", null, null);
        });
    }
}