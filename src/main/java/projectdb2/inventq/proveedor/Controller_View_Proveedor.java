package projectdb2.inventq.proveedor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projectdb2.inventq.DBUtils;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private TableView<Proveedor> table_REFRESHProveedor; // Asegúrate de tener una clase Proveedor

    // Declaración de las columnas
    @FXML
    private TableColumn<Proveedor, Integer> col_ProveedorID;
    @FXML
    private TableColumn<Proveedor, String> col_nombre;
    @FXML
    private TableColumn<Proveedor, String> col_telefono;
    @FXML
    private TableColumn<Proveedor, String> col_email;
    @FXML
    private TableColumn<Proveedor, String> col_direccion;

    // TEXT DENTRO DE CRUD PROVEEDORES
    @FXML
    private TextField txt_nomproveedor;
    @FXML
    private TextField txt_telefonoproveedor;
    @FXML
    private TextField txt_mailproveedor;
    @FXML
    private TextArea txt_direccionproveedor;

    // Declaración de la lista observable de proveedores
    private ObservableList<Proveedor> proveedorList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        proveedorList = FXCollections.observableArrayList(); // Inicializa la lista
        loadData(); // Carga los datos al iniciar

        // Configuración de las columnas
        col_ProveedorID.setCellValueFactory(new PropertyValueFactory<>("proveedorID"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

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
                loadData(); // Actualiza la tabla después de insertar
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
                    loadData(); // Actualiza la tabla después de la actualización
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





        // Configura el evento para el botón de "Eliminar"
        bt_delete.setOnAction(event -> {
            IDInputDialog idDialog = new IDInputDialog();
            String idStr = idDialog.display(); // Obtiene el ID ingresado

            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr); // Convierte el ID de String a int

                try {
                    DBUtils.deleteProveedor(id); // Llama al metodo para eliminar el proveedor
                    System.out.println("Proveedor eliminado correctamente.");
                    loadData(); // Actualiza la tabla después de eliminar
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

    private void loadData() {
        String query = "SELECT * FROM proveedor";
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            proveedorList.clear(); // Limpia la lista antes de cargar nuevos datos
            while (resultSet.next()) {
                int id = resultSet.getInt("ProveedorID");
                String nombre = resultSet.getString("Nombre");
                String telefono = resultSet.getString("Telefono");
                String email = resultSet.getString("Email");
                String direccion = resultSet.getString("Direccion");

                proveedorList.add(new Proveedor(id, nombre, telefono, email, direccion));
            }

            table_REFRESHProveedor.setItems(proveedorList); // Asigna la lista a la tabla
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}