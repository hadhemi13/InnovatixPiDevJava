package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;

import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import Entities.User;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceUser;

import java.util.ResourceBundle;

public class UpdateUserController  implements Initializable{

    @FXML
    private Text AdresseError;

    @FXML
    private TextField AdresseInput;

    @FXML
    private Text CinError;

    @FXML
    private TextField CinInput;

    @FXML
    private Text EmailError;

    @FXML
    private TextField EmailInput;

    @FXML
    private Text NameError;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private TextField fullnameInput;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text imageInputError;

    @FXML
    private Text telError;

    @FXML
    private TextField telInput;

    @FXML
    private Button update_userBtn;
    @FXML
    private User user;
    @FXML
    private File selectedImageFile;
    private String imageName;


    @FXML
    void ajouter_image(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "Images", imageName);
            try {
                Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("non");
            }
        }

    }

    public void initData(User user) {
        CinInput.setText(String.valueOf(user.getCin()));
        fullnameInput.setText(user.getName());
        telInput.setText(String.valueOf(user.getTel()));
        EmailInput.setText(user.getEmail());
        AdresseInput.setText(user.getAdresse());

        if (user != null) {
            if (user.getPhoto() != null && !user.getPhoto().isEmpty()) {
                Image image = new Image(user.getPhoto());
                imageInput.setImage(image);
            }
        }
    }
    public void initializeVirementFields(){
        if (user != null){
            CinInput.setText(String.valueOf(user.getCin()));
            fullnameInput.setText(user.getName());
            telInput.setText(user.getTel());
            EmailInput.setText(user.getEmail()); // S'il s'agit d'une liste déroulante, assurez-vous qu'elle est correctement liée dans le FXML.
            AdresseInput.setText(user.getAdresse());

            // Chargement de l'image
            if(user.getPhoto() != null && !user.getPhoto().isEmpty()){
                Image image = new Image(user.getPhoto());
                imageInput.setImage(image);
            }
        }
    }
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        imageInputError.setVisible(false);
        telError.setVisible(false);
        EmailError.setVisible(false);
        AdresseError.setVisible(false);
        CinError.setVisible(false);
        NameError.setVisible(false);




    }

    @FXML
    void updateUser(MouseEvent mouseEvent) {
        try {
            if (user != null) {
                user.setName(fullnameInput.getText());
                user.setCin(CinInput.getText());
                user.setEmail(EmailInput.getText());
                user.setTel(telInput.getText());
                user.setAdresse(AdresseInput.getText());




                // Appeler la méthode de service pour effectuer la mise à jour du virement dans la base de données
                ServiceUser serviceUser = new ServiceUser();
                serviceUser.modifier(user);

                // Fermer la fenêtre après la mise à jour
                Stage stage = (Stage) update_userBtn.getScene().getWindow();
                stage.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }
    }
}
