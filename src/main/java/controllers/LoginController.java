package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;

public class LoginController {

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

}
   /* void logIn(ActionEvent event) throws IOException {

        String email = emailField.getText();
        String password = passField.getText();

        ServiceUser userService = new ServiceUser();
        User user;

        try {
            user = userService.getOneUser(email);
            if (user.getId() == -999) {
                TrayNotificationAlert.notif("Login", "Invalid credentials.",
                        NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));
            } else {
                // System.out.println(user);
                if (BCrypt.checkpw(password, user.getPassword().replace("$2y$", "$2a$"))) {

                    if (!user.getState()) {
                        TrayNotificationAlert.notif("Login", "Your account is blocked.",
                                NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));
                    } else if (user.getIsVerified()) {
                        TrayNotificationAlert.notif("Login", "logged in successfully.",
                                NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));
                        UserSession.getInstance().setEmail(user.getEmail());
                        System.out.println("to the DASHBOARD");
                        if (user.getRoles().equals("[\"ROLE_USER\"]")
                                || user.getRoles().equals("[\"ROLE_ASSOCIATION\"]")) {
                            System.out.println("to the USERDASHBOARD");
                            Parent root = FXMLLoader.load(getClass().getResource("/gui/UserDashboard.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } else if (user.getRoles().equals("[\"ROLE_ADMIN\"]")) {
                            Parent root = FXMLLoader.load(getClass().getResource("/gui/AdminDashboard.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        }
                    } else {

                        Map<String, String> data = new HashMap<>();
                        data.put("emailSubject", "Confirm your email address for zeroWaste");
                        data.put("titlePlaceholder", "Confirm Your Email Address");
                        data.put("msgPlaceholder", "Here's the code to confirm your email address:");

                        SendMail.send(user, data);

                        TrayNotificationAlert.notif("Login", "Please verify your email.",
                                NotificationType.WARNING, AnimationType.POPUP, Duration.millis(2500));

                        UserSession.getInstance().setEmail(user.getEmail());
                        Parent root = FXMLLoader.load(getClass().getResource("ConfirmEmail.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }
                } else {
                    TrayNotificationAlert.notif("Login", "Invalid credentials.",
                            NotificationType.ERROR, AnimationType.POPUP, Duration.millis(2500));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }*/


