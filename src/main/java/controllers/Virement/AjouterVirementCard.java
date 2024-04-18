package controllers.Virement;

import Entities.Cheque;
import Entities.Virement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceCheque;
import services.ServiceVirement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

public class AjouterVirementCard {

    @FXML
    private TextField Cin;

    @FXML
    private TextField NometPrenom;

    @FXML
    private Text NometPrenomInputError;
    @FXML
    private HBox beneficiaireInputErrorHbox;
    private File selectedImageFile;

    @FXML
    private HBox NometPrenomInputErrorHbox;


    @FXML
    private TextField Num;

    @FXML
    private Text typeInputError;

    @FXML
    private Text NumInputError;

    @FXML
    private HBox NumInputErrorHbox;

    @FXML
    private TextField RIB;

    @FXML
    private Button add_new_VirementtBtn;

    @FXML
    private TextField benef;

    @FXML
    private Text benefInputError;

    @FXML
    private HBox benefInputErrorHbox;


    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text cinInputError;

    @FXML
    private HBox cinInputErrorHbox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text imageInputError;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private TextField montant;

    @FXML
    private Text montantInputError;

    @FXML
    private HBox montantInputErrorHbox;

    @FXML
    private TextField transferez;


    @FXML
    private HBox typeInputErrorHbox;

    @FXML
    private Text transferezInputError;

    @FXML
    private HBox transferezInputErrorHbox;

    @FXML
    private ComboBox<String> type;

    private String imageName = null ;

   // @FXML
   // private Button add_new_VirementtBtn;

    @FXML
    void ajouterVirement(ActionEvent event) throws SQLException {
        ServiceVirement sv = new ServiceVirement();

        // cinInputErrorHbox.setVisible(false);
        //NometPrenomInputErrorHbox.setVisible(false);
        //typeInputErrorHbox.setVisible(false);
        //beneficiaireInputErrorHbox.setVisible(false);
        //NumInputErrorHbox.setVisible(false);
        //montantInputErrorHbox.setVisible(false);
        //transferezInputErrorHbox.setVisible(false);
        //benefInputErrorHbox.setVisible(false);

        // Vérifier si tous les champs sont remplis
        boolean champsVides = false;
        if (Cin.getText().isEmpty()) {
            cinInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {

            cinInputErrorHbox.setVisible(false);
        }
        if (NometPrenom.getText().isEmpty()) {
            NometPrenomInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {

            NometPrenomInputErrorHbox.setVisible(false);
        }
        if (type.getSelectionModel().isEmpty()) {
            beneficiaireInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {

            beneficiaireInputErrorHbox.setVisible(false);
        }
        if (Num.getText().isEmpty()) {
            NumInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {

            NumInputErrorHbox.setVisible(false);
        }
        if (montant.getText().isEmpty()) {
            montantInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {

            montantInputErrorHbox.setVisible(false);
        }
        if (transferez.getText().isEmpty()) {
            transferezInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {

            transferezInputErrorHbox.setVisible(false);
        }
        if (benef.getText().isEmpty()) {
            benefInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {

            benefInputErrorHbox.setVisible(false);
        }

        if (champsVides) {
            return;
        }
        // Select for combo
        SingleSelectionModel<String> selectionModel = type.getSelectionModel();

        String selectedtype = selectionModel.getSelectedItem();
        // Create a new instance of cheque from View
        String image = "admin";
        Integer Rib = 345678644;
        String telText = Num.getText();
        //String aa = ;
        String cin = Cin.getText();
        Integer Cin = Integer.parseInt(cin);
        String Nom = NometPrenom.getText();
        String transferer = benef.getText();
        String typee = "type.getValue()";
        String montantb = montant.getText();
        String email = transferez.getText();
        String decisionV = "Encours";
        // public Virement(String type_virement, int montant, String phone_number,String transferez_a, int cin,  String nomet_prenom ,String photo_cin_v, String decision_v ){
        Virement virement = new Virement(typee, montantb, telText, transferer, Cin, Nom, image, decisionV);
        // Virement virement = new Virement(typee,montant,aa,transferez,Cin,Nom,image,decisionV);


// Convertir le texte en un entier
        // int telInteger = Integer.parseInt(telText);

        ServiceVirement ServiceVirement = new ServiceVirement();
        //ServiceVirement.ajouterV(virement);
        ServiceVirement.ajouterV(new Virement(typee, montantb, telText, transferer, Cin, Nom, image, decisionV));
        System.out.println(virement);

    }


        public void initialize (URL url, ResourceBundle resourceBundle){
            imageInputErrorHbox.setVisible(false);
            NumInputErrorHbox.setVisible(false);
            montantInputErrorHbox.setVisible(false);
            transferezInputErrorHbox.setVisible(false);
            cinInputErrorHbox.setVisible(false);
            NometPrenomInputErrorHbox.setVisible(false);
            beneficiaireInputErrorHbox.setVisible(false);
            benefInputErrorHbox.setVisible(false);


            ObservableList<String> types = FXCollections.observableArrayList(
                    "Personne",
                    "VEcoresponsabilité",
                    "hhhhhh",
                    "rrrrrr"
            );

            type.setItems(types);

        }

        public void UpdateVirement (MouseEvent mouseEvent){
        }

        public void ImporterImageV (ActionEvent event){
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

        public void ajouter_imageV (MouseEvent mouseEvent){
        }
    }

