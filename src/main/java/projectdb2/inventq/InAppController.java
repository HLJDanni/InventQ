package projectdb2.inventq;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InAppController {

    @FXML
    private Label usernameLabel; // Coincide con fx:id="usernameLabel" en el FXML

    @FXML
    private Label favChannelLabel; // Coincide con fx:id="favChannelLabel" en el FXML

    public void setUserInformation(String username, String favChannel) {
        usernameLabel.setText(username); // Asigna el nombre de usuario a la etiqueta
        favChannelLabel.setText(favChannel); // Asigna el canal favorito a la etiqueta
    }
}