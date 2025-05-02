package projectdb2.inventq.Producto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import projectdb2.inventq.DBUtils;
import projectdb2.inventq.proveedor.IDInputDialog;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller_View_Producto implements Initializable {

    // BOTONES DENTRO DE CRUD PRODUCTO
    @FXML
    private Button bt_back;

    @FXML
    private Button bt_insert;

    @FXML
    private Button bt_update;

    @FXML
    private Button bt_delete;

    @FXML
    private TableView<Producto> table_REFRESHProducto; // Tabla de productos

    // Declaración de las columnas
    @FXML
    private TableColumn<Producto, Integer> col_ProductoID; // Corregido: tipo de Producto
    @FXML
    private TableColumn<Producto, String> col_nombre;
    @FXML
    private TableColumn<Producto, String> col_descripcion;
    @FXML
    private TableColumn<Producto, Double> col_precio; // Corregido: tipo Double

    // TEXT DENTRO DE CRUD PRODUCTO
    @FXML
    private TextField txt_nomproducto;
    @FXML
    private TextField txt_descripcionprod;
    @FXML
    private TextField txt_precioprod;

    // Declaración de la lista observable de productos
    private ObservableList<Producto> productoList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productoList = FXCollections.observableArrayList(); // Inicializa la lista
        loadData(); // Carga los datos al iniciar

        // Configuración de las columnas
        col_ProductoID.setCellValueFactory(new PropertyValueFactory<>("ProductoID"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        col_descripcion.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        col_precio.setCellValueFactory(new PropertyValueFactory<>("Precio"));

        // Configura el evento para el botón de "Volver"
        bt_back.setOnAction(event -> {
            DBUtils.changeScene(event, "InApp.fxml", "Proveedor", null, null);
        });

        // Configura el evento para el botón de "Insertar"
        bt_insert.setOnAction(event -> {
            try {
                String nombre = txt_nomproducto.getText();
                String descripcion = txt_descripcionprod.getText();
                double precio = Double.parseDouble(txt_precioprod.getText());

                if (nombre.isEmpty() || descripcion.isEmpty()) {
                    System.out.println("Por favor, completa todos los campos.");
                    return; // Salir si hay campos vacíos
                }

                DBUtils.insertProducto(nombre, descripcion, precio);
                System.out.println("Producto insertado correctamente.");
                loadData(); // Actualiza la tabla después de insertar
            } catch (SQLException e) {
                System.out.println("Error al insertar el producto: " + e.getMessage());
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Precio no válido. Por favor, ingrese un número.");
            }
        });

        // Configura el evento para el botón de "Actualizar"
        bt_update.setOnAction(event -> {
            IDInputDialog idDialog = new IDInputDialog();
            String idStr = idDialog.display(); // Obtiene el ID ingresado

            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr); // Convierte el ID de String a int

                try {
                    String nombre = txt_nomproducto.getText();
                    String descripcion = txt_descripcionprod.getText();
                    double precio = Double.parseDouble(txt_precioprod.getText());

                    if (nombre.isEmpty() || descripcion.isEmpty()) {
                        System.out.println("Por favor, completa todos los campos.");
                        return; // Salir si hay campos vacíos
                    }

                    DBUtils.updateProducto(id, nombre, descripcion, precio);
                    System.out.println("Producto actualizado correctamente.");
                    loadData(); // Actualiza la tabla después de la actualización
                } catch (SQLException e) {
                    System.out.println("Error al actualizar el producto: " + e.getMessage());
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
                    DBUtils.deleteProducto(id); // Llama al método para eliminar el producto
                    System.out.println("Producto eliminado correctamente.");
                    loadData(); // Actualiza la tabla después de eliminar
                } catch (SQLException e) {
                    System.out.println("Error al eliminar el producto: " + e.getMessage());
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
        String query = "SELECT * FROM producto"; // Nombre de la tabla corregido
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            productoList.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("ProductoID");
                String nombre = resultSet.getString("Nombre");
                String descripcion = resultSet.getString("Descripcion");
                double precio = resultSet.getDouble("Precio");

                productoList.add(new Producto(id, nombre, descripcion, precio));
            }

            table_REFRESHProducto.setItems(productoList); // Asigna la lista a la tabla
        } catch (SQLException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}