package controllers;

import Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceUser;
import javafx.stage.StageStyle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
    @FXML
    private ImageView imageView;
    private String imagePath;
    @FXML
    void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg", "*.jpeg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
             imagePath = selectedFile.getAbsolutePath(); // Stockez le chemin de l'image sélectionnée dans la variable de classe
            // Chargez l'image dans l'ImageView si nécessaire
            imageView.setImage(new Image(selectedFile.toURI().toString()));
        } }
    public void signUp(ActionEvent actionEvent) {
        String name = fullnameField.getText();
        String email = EmailField.getText();
        String tel = phoneField1.getText();
        String password = passField.getText();
        String repassword = repassField.getText();
        String image = imagePath;
        String roles = "ROLE_CLIENT";

        if (name.isEmpty() || !name.matches("[a-zA-Z]+")) {
            showAlert(Alert.AlertType.ERROR, "Alert", "Erreur Saisie" , "Veuillez saisir une adresse email valide.", StageStyle.DECORATED);

            return;
        }

        // Vérifier si l'email est vide ou n'est pas au bon format
        if (email.isEmpty() || !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            showAlert(Alert.AlertType.ERROR, "Alert", "Erreur Saisie", "Veuillez saisir une adresse email valide.", StageStyle.DECORATED);

            return;
        }

// Vérifier si le nom contient uniquement des lettres


// Vérifier si le mot de passe est vide ou a moins de 6 caractères
        if (password.isEmpty() || password.length() < 6) {
            showAlert(Alert.AlertType.ERROR, "Alert", "Erreur Saisie", "Veuillez saisir une Mot de passe minimum 6 caractères.", StageStyle.DECORATED);

            return;
        }

// Vérifier si le numéro de téléphone est un nombre valide
        int number;
        try {
            number= Integer.parseInt(tel);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Alert", "Erreur Saisie", "Veuillez saisir une Numero De Telephone valide.", StageStyle.DECORATED);

            return;
        }





        // Ajouter l'utilisateur à la base de données ou effectuer toute autre action nécessaire
        addUserToDatabase(name,email, tel, password,image,roles);
        clearInputFields();
    }

    // Méthode pour afficher une alerte d'erreur
    private void showAlert(Alert.AlertType alertType, String title, String header, String message, StageStyle stageStyle) {
        Alert alert = new Alert(alertType);
        alert.initStyle(stageStyle);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);

        // Ajouter une icône personnalisée à l'alerte (optionnel)
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logo.png")));

        // Personnaliser les boutons de l'alerte (optionnel)
        ButtonType buttonType = new ButtonType("Retour", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonType);

        // Afficher l'alerte et attendre la réponse de l'utilisateur
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonType) {
            // Action à effectuer si l'utilisateur clique sur le bouton OK
        }
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("Alert.css").toExternalForm());
        dialogPane.getStyleClass().add("alert");


        Scene scene = alert.getDialogPane().getScene();
        scene.getStylesheets().add(getClass().getResource("Alert.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/CustomAlertStyle.css").toExternalForm()); // Ajoutez le chemin vers le fichier CSS contenant le style personnalisé

        alert.getDialogPane().getStyleClass().add("custom-alert");
    }



    // Méthode pour ajouter l'utilisateur à la base de données(name, lastname, roles,password, email, image, number, is_verified, datenaissance)
    private void addUserToDatabase(String name, String email, String tel,  String password, String photo ,String roles) {
        // Créer un nouvel utilisateur avec les valeurs saisies
        User user = new User(name, roles, email,password, photo,tel);

        // Utiliser le service utilisateur pour inscrire l'utilisateur
        ServiceUser userService = new ServiceUser();
        userService.signUp(user); // Appel de la méthode signUp pour ajouter l'utilisateur à la base de données
    }

    // Méthode pour effacer les champs de saisie
    private void clearInputFields() {
        fullnameField.clear();
        EmailField.clear();
        phoneField1.clear();
        passField.clear();
        repassField.clear();
        imageView.setImage(null);
        imagePath = null;



    }
    @FXML
    void toLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        

    }


