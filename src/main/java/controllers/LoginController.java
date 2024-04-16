package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import Entities.User;
import javafx.util.Duration;
import services.Iservice;
import services.ServiceUser;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.awt.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

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
}