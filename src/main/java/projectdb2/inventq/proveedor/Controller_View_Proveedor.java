package projectdb2.inventq.proveedor;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import projectdb2.inventq.DBUtils;
import projectdb2.inventq.IDInputDialog;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller_View_Proveedor implements Initializable {

    // BOTONES DENTRO DE CRUD PROVEEDORES
    @FXML
    private Button bt_back;

    @FXML
    private Button bt_insert;

    @FXML
    private Button bt_update;

    @FXML
    private Button bt_delete;

    @FXML
    private Button bt_refresh;

    @FXML
    private TableView<Proveedor> table_REFRESHProveedor; // Asegúrate de tener una clase Proveedor

    // TEXT DENTRO DE CRUD PROVEEDORES
    @FXML
    private TextField txt_nomproveedor;
    @FXML
    private TextField txt_telefonoproveedor;
    @FXML
    private TextField txt_mailproveedor;
    @FXML
    private TextArea txt_direccionproveedor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura el evento para el botón de "Volver"
        bt_back.setOnAction(event -> {
            DBUtils.changeScene(event, "InApp.fxml", "Proveedor", null, null);
        });

        // Configura el evento para el botón de "Insertar"
        bt_insert.setOnAction(event -> {
            try {
                String nombre = txt_nomproveedor.getText();
                String telefono = txt_telefonoproveedor.getText();
                String email = txt_mailproveedor.getText();
                String direccion = txt_direccionproveedor.getText();

                if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || direccion.isEmpty()) {
                    System.out.println("Por favor, completa todos los campos.");
                    return; // Salir si hay campos vacíos
                }

                DBUtils.insertProveedor(nombre, telefono, email, direccion);
                System.out.println("Proveedor insertado correctamente.");
                //refreshTable(); // Actualiza la tabla
            } catch (SQLException e) {
                System.out.println("Error al insertar el proveedor: " + e.getMessage());
                e.printStackTrace();
            }
        });

        // Configura el evento para el botón de "Actualizar"
        bt_update.setOnAction(event -> {
            IDInputDialog idDialog = new IDInputDialog();
            String idStr = idDialog.display(); // Obtiene el ID ingresado

            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr); // Convierte el ID de String a int

                try {
                    String nombre = txt_nomproveedor.getText();
                    String telefono = txt_telefonoproveedor.getText();
                    String email = txt_mailproveedor.getText();
                    String direccion = txt_direccionproveedor.getText();

                    if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty() || direccion.isEmpty()) {
                        System.out.println("Por favor, completa todos los campos.");
                        return; // Salir si hay campos vacíos
                    }

                    DBUtils.updateProveedor(id, nombre, telefono, email, direccion);
                    System.out.println("Proveedor actualizado correctamente.");
                  //  refreshTable(); // Actualiza la tabla después de la actualización
                } catch (SQLException e) {
                    System.out.println("Error al actualizar el proveedor: " + e.getMessage());
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.out.println("ID no válido. Por favor, ingrese un número.");
                }
            } else {
                System.out.println("No se ha ingresado un ID.");
            }
        });


        bt_delete.setOnAction(event -> {
            IDInputDialog idDialog = new IDInputDialog();
            String idStr = idDialog.display(); // Obtiene el ID ingresado

            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr); // Convierte el ID de String a int

                try {
                    DBUtils.deleteProveedor(id); // Llama al metodo para eliminar el proveedor
                    System.out.println("Proveedor eliminado correctamente.");
                    //refreshTable(); // Actualiza la tabla después de eliminar
                } catch (SQLException e) {
                    System.out.println("Error al eliminar el proveedor: " + e.getMessage());
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    System.out.println("ID no válido. Por favor, ingrese un número.");
                }
            } else {
                System.out.println("No se ha ingresado un ID.");
            }
        });


    }


}