package controllers;

import Entities.User;
import controllers.UserControleSaisie;
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

        // Créer un objet User avec les informations saisies
        User newUser = new User(name, email, tel, password, image, roles);
        UserControleSaisie userControleSaisie = new UserControleSaisie();
        ServiceUser userService = new ServiceUser();

        // Appel à la fonction signUpValidator pour valider les saisies
        if (!userControleSaisie.signUpValidator(newUser, repassword, userService)) {
            return; // Sortir de la méthode si la validation échoue
        }

        // Ajouter l'utilisateur à la base de données ou effectuer toute autre action nécessaire
        addUserToDatabase(name,email,tel,password,image,roles);
        clearInputFields();
    }

    // Méthode pour afficher une alerte d'erreur
    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    private void addUserToDatabase(String name, String email, String tel,  String password, String photo ,String roles) {
        // Créer un nouvel utilisateur avec les valeurs saisies
        User user = new User(name,email,tel,password, photo,roles);

        // Utiliser le service utilisateur pour inscrire l'utilisateur
        ServiceUser userService = new ServiceUser();
        userService.signUp(user);// Appel de la méthode signUp pour ajouter l'utilisateur à la base de données
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


