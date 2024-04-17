package controllers;

import Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import services.ServiceUser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class SignUpController {


    @FXML
    private TextField EmailField;

    @FXML
    private TextField fullnameField;

    @FXML
    private AnchorPane left;

    @FXML
    private PasswordField passField;

    @FXML
    private TextField phoneField1;

    @FXML
    private PasswordField repassField;

    @FXML
    private Button signUpBTN;


    public void signUp(ActionEvent actionEvent) {
        // Génération du token et du code (non utilisés dans ce code)
        String token = UUID.randomUUID().toString();
        int code = new Random().nextInt(900000) + 100000;

        // Récupération des valeurs des champs de saisie utilisateur
        String fullname = fullnameField.getText();
        String email = EmailField.getText();
        String phone = phoneField1.getText();
        String password = passField.getText();
        String repassword = repassField.getText();

        // Création d'un objet User avec les valeurs saisies par l'utilisateur
        User user = new User(fullname, email, phone, password);

        // Initialisation du service utilisateur
        ServiceUser userService = new ServiceUser();

        // Vérification de la validité des données saisies par l'utilisateur
        if (UserControleSaisie.signUpValidator(user, repassword, userService)) {
            // Hashage du mot de passe avant de l'ajouter à la base de données
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));

            try {
                // Ajout de l'utilisateur à la base de données
                userService.ajouter(user);

                // Affichage d'un message de succès
                System.out.println("User added successfully");

                // Stockage de l'email de l'utilisateur dans la session
                UserSession.getInstance().setEmail(user.getEmail());

                // Redirection vers l'écran de confirmation de l'email (à décommenter lorsque prêt)
                // FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmEmail.fxml"));
                // Parent root = loader.load();
                // Scene scene = new Scene(root);
                // Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                // stage.setScene(scene);
                // stage.show();

            } catch (SQLException e) {
                // Gestion des exceptions SQL
                e.printStackTrace();
                System.out.println("Error adding user: " + e.getMessage());
                // Afficher un message d'erreur à l'utilisateur si nécessaire
            }
        }
    }}


