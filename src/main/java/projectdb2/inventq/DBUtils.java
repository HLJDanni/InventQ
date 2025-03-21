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

    private static Connection getConnection() throws SQLException {
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
                InAppController controller = loader.getController(); // Cambia a InAppController
                controller.setUserInformation(username, favChannel); // Asegúrate de que este método exista en InAppController
            }

            // Cambiar la escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 600, 500));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No se pudo cargar la vista: " + fxmlFile);
            alert.show();
        }
    }

    public static void singUpUser(ActionEvent event, String username, String password, String favChannel) throws SQLException {
        Connection connection = null;
        PreparedStatement psCheckUserExist = null;
        PreparedStatement psInsert = null;
        ResultSet rs = null;

        try {
            connection = getConnection();

            // Verificar si el usuario ya existe
            psCheckUserExist = connection.prepareStatement("SELECT * FROM log_in WHERE username = ?");
            psCheckUserExist.setString(1, username);
            rs = psCheckUserExist.executeQuery();

            if (rs.next()) {
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("El usuario ya existe.");
                alert.show();
            } else {
                // Insertar nuevo usuario (sin incluir la columna 'id')
                psInsert = connection.prepareStatement("INSERT INTO log_in (username, contraseña, favChannel) VALUES (?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, favChannel);
                psInsert.executeUpdate();
                System.out.println("User created");

                // Cambiar de escena después de registrar al usuario
                DBUtils.changeScene(event, "/projectdb2/inventq/InApp.fxml", "Invent Q", username, favChannel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al conectar con la base de datos.");
            alert.show();
        } finally {
            // Cerrar los recursos
            try {
                if (rs != null) rs.close();
                if (psCheckUserExist != null) psCheckUserExist.close();
                if (psInsert != null) psInsert.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement("SELECT contraseña, favChannel FROM log_in WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User does not exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("contraseña");
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
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
            if (rs != null) rs.close();
        }
    }

    public static void testConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al cargar el controlador JDBC de SQL Server.");
            return;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al conectar a la base de datos.");
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}