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
    private TextField txt_nombreCategoria;

    @FXML
    private Button bt_insert;

    @FXML
    private Button bt_delete;

    @FXML
    private Button bt_update;

    @FXML
    private TableColumn<Categoria, Integer> col_ID_Categoria;
    @FXML
    private TableColumn<Categoria, String> col_nombre;

    @FXML
    private TableView<Categoria> table_REFRESHCategoria;

    @FXML
    private Button bt_back;

    private ObservableList<Categoria> categoriaList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();


        bt_back.setOnAction(event -> {
            DBUtils.changeScene(event, "InApp.fxml", "Categoría de Productos", null, null);
        });

        col_ID_Categoria.setCellValueFactory(new PropertyValueFactory<>("CategoriaID"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));



        bt_insert.setOnAction(event -> {
            try{

                String Nombre = txt_nombreCategoria.getText();


                if (Nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar los campos completos");
                System.out.print("Debe ingresar los campos");

                }
                DBUtils.insertCategoria(Nombre);
                JOptionPane.showMessageDialog(null, "Categoría"+ Nombre+"agregado exitosamente");
                loadData();
                System.out.println("Categoría: " + Nombre + "se ingresó correctamente");
                loadData();
            } catch (SQLException e) {
                System.out.println("Error al insertar Categoria");
                e.printStackTrace();
            }

        });

        bt_update.setOnAction(event -> {
            IDInputDialog idDialog = new IDInputDialog();
            String idStr = idDialog.display(); // Obtiene el ID ingresado

            if (idStr != null && !idStr.isEmpty()) {
                int CategoriaID = Integer.parseInt(idStr); // Convierte el ID de String a int

                try{

                    String Nombre = txt_nombreCategoria.getText();


                    if (Nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar los campos completos");
                        System.out.print("Debe ingresar los campos");

                    }
                    DBUtils.updateCategoria(CategoriaID,Nombre);
                    JOptionPane.showMessageDialog(null, "Categoria"+ CategoriaID+"actualizado exitosamente");
                    loadData();
                    System.out.println("Categoria: " + CategoriaID + "se ingresó correctamente");
                    loadData();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al insertar Categoria");
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
                int CategoriaID = Integer.parseInt(idStr); // Convierte el ID de String a int

                try {
                    DBUtils.deleteCategoria(CategoriaID); // Llama al metodo para eliminar el proveedor
                    JOptionPane.showMessageDialog(null, "Categoria eliminada exitosamente");
                    loadData();
                    System.out.println("Categoria eliminada correctamente.");
                    loadData() ; // Actualiza la tabla después de eliminar
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar Categoria");
                    System.out.println("Error al eliminar el Categoria: " + e.getMessage());
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

    private void loadData() {
        String query = "SELECT * FROM categoria";
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            categoriaList.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("CategoriaID");
                String Nombre = resultSet.getString("Nombre");
                categoriaList.add(new Categoria(id, Nombre));
            }

            table_REFRESHCategoria.setItems(categoriaList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}