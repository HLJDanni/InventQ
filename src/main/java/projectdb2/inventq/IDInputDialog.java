package projectdb2.inventq;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class IDInputDialog {

    private String inputId;

    public String display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Ingrese el ID del Proveedor");
        window.setMinWidth(250);

        TextField idField = new TextField();
        idField.setPromptText("ID del Proveedor");

        Button submitButton = new Button("Aceptar");
        submitButton.setOnAction(e -> {
            inputId = idField.getText();
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(new Label("Ingrese el ID del Proveedor:"), idField, submitButton);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return inputId; // Devuelve el ID ingresado
    }
}