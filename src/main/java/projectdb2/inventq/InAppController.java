package projectdb2.inventq;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InAppController implements Initializable {

    @FXML
    private Label usernameLabel; // Coincide con fx:id="usernameLabel" en el FXML

    @FXML
    private Label favChannelLabel; // Coincide con fx:id="favChannelLabel" en el FXML

    @FXML
    private Button bt_proveedor;



    public void setUserInformation(String username, String favChannel) {
        usernameLabel.setText(username); // Asigna el nombre de usuario a la etiqueta
        favChannelLabel.setText(favChannel); // Asigna el canal favorito a la etiqueta
    }


    public void initialize(URL location, ResourceBundle resources){
        bt_proveedor.setOnAction(event -> {
            DBUtils.changeScene(event,"View_Proveedor.fxml","Proveedor",null,null);
        });
    }



}