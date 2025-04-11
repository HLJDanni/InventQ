package projectdb2.inventq;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtils {

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = DBUtils.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("No se pudo encontrar el archivo config.properties");
                return null;
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Connection getConnection() throws SQLException {
        Properties properties = loadProperties();
        if (properties == null) {
            throw new SQLException("No se pudieron cargar las propiedades de la base de datos.");
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        String encrypt = properties.getProperty("db.encrypt");

        return DriverManager.getConnection(url + ";user=" + user + ";password=" + password + ";encrypt=" + encrypt);
    }

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username, String favChannel) {
        Parent root = null;

        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
            root = loader.load();

            // Si se proporcionan username y favChannel, configurar el controlador
            if (username != null && favChannel != null) {
                InAppController controller = loader.getController();
                controller.setUserInformation(username, favChannel);
            }

            // Cambiar la escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No se pudo cargar la vista: " + fxmlFile);
            alert.show();
        }
    }

    public static void singUpUser(ActionEvent event, String username, String password, String favChannel) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement psCheckUserExist = connection.prepareStatement("SELECT * FROM usuario WHERE username = ?")) {

            psCheckUserExist.setString(1, username);
            ResultSet rs = psCheckUserExist.executeQuery();

            if (rs.next()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("El usuario ya existe.");
                alert.show();
            } else {
                // Insertar nuevo usuario
                try (PreparedStatement psInsert = connection.prepareStatement("INSERT INTO usuario (username, password, favChannel) VALUES (?, ?, ?)")) {
                    psInsert.setString(1, username);
                    psInsert.setString(2, password);
                    psInsert.setString(3, favChannel);
                    psInsert.executeUpdate();
                    System.out.println("User created");

                    // Cambiar de escena después de registrar al usuario
                    changeScene(event, "/projectdb2/inventq/InApp.fxml", "Invent Q", username, favChannel);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al conectar con la base de datos.");
            alert.show();
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT password, favChannel FROM usuario WHERE username = ?")) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User does not exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedChannel = resultSet.getString("favChannel");

                    if (retrievedPassword.equals(password)) {
                        System.out.println("User logged in");
                        changeScene(event, "/projectdb2/inventq/InApp.fxml", "Invent Q", username, retrievedChannel);
                    } else {
                        System.out.println("Incorrect password");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void testConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try (Connection connection = getConnection()) {
                if (connection != null) {
                    System.out.println("Conexión exitosa a la base de datos!");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar el controlador JDBC de SQL Server.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al conectar a la base de datos.");
        }
    }

    // Métodos para Proveedor
    public static void insertProveedor(String nombre, String telefono, String email, String direccion) throws SQLException {
        String query = "INSERT INTO proveedor (Nombre, Telefono, Email, Direccion) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement psInsert = connection.prepareStatement(query)) {

            psInsert.setString(1, nombre);
            psInsert.setString(2, telefono);
            psInsert.setString(3, email);
            psInsert.setString(4, direccion);
            psInsert.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar el proveedor: " + e.getMessage());
            throw e;
        }
    }

    public static void updateProveedor(int id, String nombre, String telefono, String email, String direccion) throws SQLException {
        String query = "UPDATE proveedor SET Nombre = ?, Telefono = ?, Email = ?, Direccion = ? WHERE ProveedorID = ?";
        try (Connection connection = getConnection();
             PreparedStatement psUpdate = connection.prepareStatement(query)) {
            psUpdate.setString(1, nombre);
            psUpdate.setString(2, telefono);
            psUpdate.setString(3, email);
            psUpdate.setString(4, direccion);
            psUpdate.setInt(5, id);
            psUpdate.executeUpdate();
        }
    }

    public static void deleteProveedor(int id) throws SQLException {
        String query = "DELETE FROM proveedor WHERE ProveedorID = ?";
        try (Connection connection = getConnection();
             PreparedStatement psDelete = connection.prepareStatement(query)) {
            psDelete.setInt(1, id);
            psDelete.executeUpdate();
        }
    }



    //CRUD CONTACTO PROVEEDOR
    public static void insertContactoProveedor(int IDProveedor, String TipoContacto, String DetalleContacto) throws SQLException {
        String query = "INSERT INTO contacto_proveedor (ProveedorID, TipoContacto, DetalleContacto) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement psInsert = connection.prepareStatement(query)) {

            psInsert.setInt(1,IDProveedor);
            psInsert.setString(2, TipoContacto);
            psInsert.setString(3, DetalleContacto);
            psInsert.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar el proveedor: " + e.getMessage());
            throw e;
        }
    }

    public static void updateContactoProveedor(int IDProveedor, String TipoContacto, String DetalleContacto) throws SQLException {
        String query = "UPDATE contacto_proveedor SET TipoContacto = ?, DetalleContacto = ? WHERE ProveedorID = ?";
        try (Connection connection = getConnection();
             PreparedStatement psUpdate = connection.prepareStatement(query)) {
            psUpdate.setString(1, TipoContacto);
            psUpdate.setString(2, DetalleContacto);
            psUpdate.setInt(3, IDProveedor); // Cambiar el orden
            psUpdate.executeUpdate();
        }
    }

    public static void deleteContactoProveedor(int IDProveedor) throws SQLException {
        String query = "DELETE FROM contacto_proveedor WHERE ProveedorID = ?";
        try (Connection connection = getConnection();
             PreparedStatement psDelete = connection.prepareStatement(query)) {
            psDelete.setInt(1, IDProveedor);
            psDelete.executeUpdate();
        }

    }




}