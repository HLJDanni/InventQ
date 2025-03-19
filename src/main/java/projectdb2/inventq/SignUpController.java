package projectdb2.inventq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private Button bt_Sing_Up;

    @FXML
    private Button bt_LogIn;

    @FXML
    private RadioButton rb_InventQ;

    @FXML
    private RadioButton rb_Someone_Else;

    @FXML
    private TextField tf_Username;

    @FXML
    private TextField tf_Password;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura el ToggleGroup para los RadioButtons
        ToggleGroup toggleGroup = new ToggleGroup();
        rb_InventQ.setToggleGroup(toggleGroup);
        rb_Someone_Else.setToggleGroup(toggleGroup);
        rb_InventQ.setSelected(true);

        // Configura el evento para el botón de registro
        bt_Sing_Up.setOnAction(event -> {
            String toggleName = ((RadioButton) toggleGroup.getSelectedToggle()).getText();
            if (tf_Username != null && tf_Password != null &&
                    !tf_Username.getText().trim().isEmpty() && !tf_Password.getText().trim().isEmpty()) {
                try {
                    DBUtils.singUpUser(event, tf_Username.getText(), tf_Password.getText(), toggleName);
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Error al registrar el usuario.");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, complete todos los campos.");
                alert.show();
            }
        });

        // Configura el evento para el botón de inicio de sesión
        bt_LogIn.setOnAction(event -> {
            DBUtils.changeScene(event, "LogIn.fxml", "Log In!", null, null);
        });
    }
}