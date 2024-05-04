package controllers.user;

import Entities.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import services.ServiceUser;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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

            try {
                // Récupérer l'utilisateur à partir de l'adresse e-mail saisie
                User user = userService.getOneUser(emailField.getText());

                // Vérifier si l'utilisateur existe et si son adresse e-mail est valide
                if (user == null || !UserControleSaisie.forgetPasswordValidator(emailField.getText(), user)) {
                    // Afficher un message d'erreur si l'utilisateur n'existe pas ou si l'adresse e-mail est invalide
                    System.out.println("Utilisateur non trouvé ou adresse e-mail invalide");
                    return;
                }

                // Générer un nouveau mot de passe aléatoire
                String newPassword = generateRandomPassword();

                // Mettre à jour le mot de passe de l'utilisateur dans la base de données
                user.setPassword(newPassword);
                userService.modifier(user);

                // Envoyer un e-mail de réinitialisation de mot de passe avec le nouveau mot de passe
                Map<String, String> data = new HashMap<>();
                data.put("emailSubject", "Demande de réinitialisation de mot de passe");
                data.put("titlePlaceholder", "Demande de réinitialisation de mot de passe");
                data.put("msgPlaceholder", "Vous avez demandé à réinitialiser votre mot de passe. Voici votre nouveau mot de passe : " + newPassword);

                Mailling.send(user,data);
                System.out.println("E-mail de réinitialisation de mot de passe envoyé avec succès");

                // Afficher une notification à l'utilisateur
                showAlert("Mot de passe oublié", "Un e-mail de réinitialisation de mot de passe a été envoyé à votre adresse e-mail.");

                // Rediriger l'utilisateur vers une autre page après l'envoi de l'e-mail de réinitialisation
                Parent root = FXMLLoader.load(getClass().getResource("ForgotPassword_3.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (SQLException | MessagingException | IOException e) {
                e.printStackTrace();
                // Gérer les exceptions
            }
        }

        // Méthode pour générer un nouveau mot de passe aléatoire
        private String generateRandomPassword() {
            // Générer un mot de passe aléatoire de 8 caractères
            String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 8; i++) {
                int index = random.nextInt(chars.length());
                sb.append(chars.charAt(index));
            }
            return sb.toString();
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
