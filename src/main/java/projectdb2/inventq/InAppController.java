package projectdb2.inventq;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.OpenOption;
import java.util.ResourceBundle;

public class InAppController implements Initializable {

    @FXML
    private Label usernameLabel; // Coincide con fx:id="usernameLabel" en el FXML

    @FXML
    private Label favChannelLabel; // Coincide con fx:id="favChannelLabel" en el FXML

    @FXML
    private Button bt_proveedor;

    @FXML
    private Button bt_contactProveedor;

    @FXML
    private Button bt_Producto;

    @FXML
    private Button PBI;

    @FXML
    private Button bt_Cat_Prod;

    public void setUserInformation(String username, String favChannel) {
        usernameLabel.setText(username); // Asigna el nombre de usuario a la etiqueta
        favChannelLabel.setText(favChannel); // Asigna el canal favorito a la etiqueta
    }


    public void initialize(URL location, ResourceBundle resources){
        bt_proveedor.setOnAction(event -> {
            DBUtils.changeScene(event,"View_Proveedor.fxml","Proveedor",null,null);
        });

        //bt_contactProveedor

        bt_contactProveedor.setOnAction(event -> {
            DBUtils.changeScene(event,"View_ContactoProveedor.fxml","Contacto Proveedor",null,null);
        });

        bt_Producto.setOnAction(event -> {
            DBUtils.changeScene(event,"View_Producto.fxml","Producto",null,null);
        });

        bt_Cat_Prod.setOnAction( event -> {
            DBUtils.changeScene(event,"View_Prodcuto_Categoria.fxml","Categoría de Productos",null,null);
        });

        PBI.setOnAction(event -> {
            openFileInSameDirectory("C:/Users/hljda/OneDrive/Escritorio/InventQ/DASHBOARD.pbix");
        });

    }

    private void openFileInSameDirectory(String filePath) {
        try {
            // Crea un objeto File con la ruta absoluta del archivo
            File file = new File(filePath);

            // Verifica si el archivo existe antes de intentar abrirlo
            if (file.exists()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file); // Abre el archivo con la aplicación predeterminada
            } else {
                System.out.println("El archivo no existe en la ruta especificada.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

