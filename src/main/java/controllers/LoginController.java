package controllers;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.event.ActionEvent;
import org.mindrot.jbcrypt.BCrypt;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import controllers.UserControleSaisie;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import services.ServiceUser;



public class LoginController implements Initializable {

    @FXML
    private TextField emailField;

    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private AnchorPane left;

    @FXML
    private Button loginBTN;

    @FXML
    private PasswordField passField;

    @FXML
    private Button signUpLink;


    public void ToSignUp(MouseEvent mouseEvent) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SignUp.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Si nécessaire, récupérer le contrôleur associé à l'interface SignUp.fxml
            // SignUpController signUpController = loader.getController();

            // Set the scene
            Stage stage = new Stage(); // Crée une nouvelle fenêtre pour l'interface d'inscription
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    public void toForgotPassword(ActionEvent actionEvent)  throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ForgotPassword.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void logIn(ActionEvent actionEvent) {
        String email = emailField.getText();
        String password = passField.getText();
        UserControleSaisie userControleSaisie = new UserControleSaisie();

        // Vérification des champs email et mot de passe avec la fonction loginInputValidator
        if (!userControleSaisie.loginInputValidator(email, password)) {
            return; // Sortir de la méthode si la validation échoue
        }

        ServiceUser userService = new ServiceUser();
        User user;

        try {
            user = userService.getOneUser(email);
            if (user.getId() == -999) {
                AlertUtil.showAlert("Login", "Invalid credentials.", Alert.AlertType.INFORMATION);
            } else {
                if (BCrypt.checkpw(password, user.getPassword().replace("$2y$", "$2a$"))) {
                    if (user.getIs_blocked() == 1) {
                        AlertUtil.showAlert("Login", "Your account is blocked.", Alert.AlertType.ERROR);
                    } else {
                        AlertUtil.showAlert("Login", "Logged in successfully.", Alert.AlertType.INFORMATION);
                        UserSession.getInstance().setEmail(user.getEmail());
                        System.out.println("to the DASHBOARD");
                        if (user.getRoles().equals("[\"ROLE_CLIENT\"]")
                                || user.getRoles().equals("[\"ROLE_EMPLOYEE\"]")) {
                            System.out.println("to the USERDASHBOARD");
                            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SideNavBarUser.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } else if (user.getRoles().equals("[\"ROLE_ADMIN\"]")) {
                            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SideNavBar.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                } else {
                    AlertUtil.showAlert("Login", "Invalid credentials.", Alert.AlertType.INFORMATION);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserSession userSession = new UserSession();
        userSession.email = emailField.getText();
        System.out.println(userSession.email);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserSession userSession = new UserSession();
        if (userSession.email != null){
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/SideNavBarUser.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            //Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            ServiceUser serviceUser  = new ServiceUser();
            try {
                User user = serviceUser.getOneUser(userSession.email);
                SideNavBarController sideNavBarController = new SideNavBarController();
                sideNavBarController.initData(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public class AlertUtil {

        public static void showAlert(String title, String message, AlertType alertType) {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
}




