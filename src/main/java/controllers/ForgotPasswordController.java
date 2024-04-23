package controllers;

import Entities.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ServiceUser;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;

import javax.mail.MessagingException;

public class ForgotPasswordController {
    @FXML
    private TextField emailField;

    @FXML
    private AnchorPane left;

    @FXML
    private Hyperlink logInLink;

    @FXML
    private Button sendBTN;

    public void sendClicked(ActionEvent actionEvent)  {
        ServiceUser userService = new ServiceUser();
        User user;

        try {
            user = userService.getOneUser(emailField.getText());

            if (!UserControleSaisie.forgetPasswordValidator(emailField.getText(), user)) {
                return;
            }

            int code = new Random().nextInt(900000) + 100000;
            user.setIs_verified(code);

            Map<String, String> data = new HashMap<>();
            data.put("emailSubject", "Reset password request");
            data.put("titlePlaceholder", "Reset password request");
            data.put("msgPlaceholder",
                    "It seems like you forgot your password. If this is true, here's the code to reset your password");

            Mailling.send(user, data);
            System.out.println("sent");

            userService.modifier(user);

            showAlert("Forgot password", "A verification code was sent to you, please check your email.");

            UserSession.getInstance().setEmail(user.getEmail());

            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ForgotPassword_2.fxml"));
            Scene scene = new Scene(root);
          //  Stage stage = (Stage) ((Node) ActionEvent.getSource()).getScene().getWindow();
            //stage.setScene(scene);
            //stage.show();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
// Méthode pour afficher une boîte de dialogue d'alerte
        private static void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    @FXML


    public void toLogin(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
