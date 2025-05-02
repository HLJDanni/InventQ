package projectdb2.inventq.Producto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import projectdb2.inventq.DBUtils;
import projectdb2.inventq.proveedor.ContactoProveedor;
import projectdb2.inventq.proveedor.IDInputDialog;
import projectdb2.inventq.proveedor.Proveedor;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller_View_Categoria implements Initializable {

    @FXML
    private TextField txt_IDProveedor;

    @FXML
    private TextField txt_TipoContacto;

    @FXML
    private TextField txt_DetalleContacto;

    @FXML
    private Button bt_insert;

    @FXML
    private Button bt_delete;

    @FXML
    private Button bt_update;

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
    @FXML
    private TableView<Proveedor> table_REFRESHProveedor;

    @FXML
    private Button bt_back;

    private ObservableList<Proveedor> proveedorList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<ContactoProveedor, Integer> col_IDProveedor;
    @FXML
    private TableColumn<ContactoProveedor, String> col_TipoContacto;
    @FXML
    private TableColumn<ContactoProveedor, String> col_DetalleContacto;
    @FXML
    private TableView<ContactoProveedor> table_REFRESHContactoProveedor;

    private ObservableList<ContactoProveedor> ContactoProveedorList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDataContactoProveedor();
        loadData();

        bt_back.setOnAction(event -> {
            DBUtils.changeScene(event, "InApp.fxml", "Proveedor", null, null);
        });

        col_IDProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedorID"));
        col_TipoContacto.setCellValueFactory(new PropertyValueFactory<>("tipoContacto"));
        col_DetalleContacto.setCellValueFactory(new PropertyValueFactory<>("detalleContacto"));

        col_ProveedorID.setCellValueFactory(new PropertyValueFactory<>("proveedorID"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));


        bt_insert.setOnAction(event -> {
            try{
                int ID = Integer.parseInt(txt_IDProveedor.getText());
                String TipoContacto = txt_TipoContacto.getText();
                String DetalleContacto = txt_DetalleContacto.getText();

                if (TipoContacto.isEmpty() || DetalleContacto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar los campos completos");
                System.out.print("Debe ingresar los campos");

                }
                DBUtils.insertContactoProveedor(ID, TipoContacto, DetalleContacto);
                JOptionPane.showMessageDialog(null, "Contacto"+ ID+"agregado exitosamente");
                loadDataContactoProveedor();
                System.out.println("Contacto: " + ID + "se ingresó correctamente");
                loadDataContactoProveedor();
            } catch (SQLException e) {
                System.out.println("Error al insertar contacto");
                e.printStackTrace();
            }

        });

        bt_update.setOnAction(event -> {
            IDInputDialog idDialog = new IDInputDialog();
            String idStr = idDialog.display(); // Obtiene el ID ingresado

            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr); // Convierte el ID de String a int

                try{
                    int ID = Integer.parseInt(txt_IDProveedor.getText());
                    String TipoContacto = txt_TipoContacto.getText();
                    String DetalleContacto = txt_DetalleContacto.getText();

                    if (TipoContacto.isEmpty() || DetalleContacto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar los campos completos");
                        System.out.print("Debe ingresar los campos");

                    }
                    DBUtils.updateContactoProveedor(ID, TipoContacto, DetalleContacto);
                    JOptionPane.showMessageDialog(null, "Contacto"+ ID+"actualizado exitosamente");
                    loadDataContactoProveedor();
                    System.out.println("Contacto: " + ID + "se ingresó correctamente");
                    loadDataContactoProveedor();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al insertar contacto");
                    System.out.println("Error al Actualizar contacto");
                    e.printStackTrace();
                }
            } else {
                System.out.println("No se ha ingresado un ID.");
            }
        });

        bt_delete.setOnAction(actionEvent -> {
            IDInputDialog idDialog = new IDInputDialog();
            String idStr = idDialog.display(); // Obtiene el ID ingresado

            if (idStr != null && !idStr.isEmpty()) {
                int id = Integer.parseInt(idStr); // Convierte el ID de String a int

                try {
                    DBUtils.deleteContactoProveedor(id); // Llama al metodo para eliminar el proveedor
                    JOptionPane.showMessageDialog(null, "Contacto eliminado exitosamente");
                    loadDataContactoProveedor();
                    System.out.println("Contacto eliminado correctamente.");
                    loadDataContactoProveedor() ; // Actualiza la tabla después de eliminar
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar contacto");
                    System.out.println("Error al eliminar el Contacto: " + e.getMessage());
                    e.printStackTrace();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar el ID válido");
                    System.out.println("ID no válido. Por favor, ingrese un número.");
                }
            } else {
                System.out.println("No se ha ingresado un ID.");
            }
        });


    }

    private void loadDataContactoProveedor() {
        String query = "SELECT * FROM contacto_proveedor";
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ContactoProveedorList.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("ProveedorID");
                String tipoContacto = resultSet.getString("TipoContacto");
                String detalleContacto = resultSet.getString("DetalleContacto");
                ContactoProveedorList.add(new ContactoProveedor(id, tipoContacto, detalleContacto));
            }
            table_REFRESHContactoProveedor.setItems(ContactoProveedorList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        String query = "SELECT * FROM proveedor";
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            proveedorList.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("ProveedorID");
                String nombre = resultSet.getString("Nombre");
                String telefono = resultSet.getString("Telefono");
                String email = resultSet.getString("Email");
                String direccion = resultSet.getString("Direccion");

                proveedorList.add(new Proveedor(id, nombre, telefono, email, direccion));
            }

            table_REFRESHProveedor.setItems(proveedorList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}