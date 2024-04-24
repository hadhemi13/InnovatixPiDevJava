package controllers.Virement;

import Entities.Cheque;
import Entities.Virement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import java.awt.*;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.*;
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

public class AjouterVirementCard implements Initializable {

    @FXML
    private TextField Cin;

    @FXML
    private TextField NometPrenom;

    @FXML
    private Text NometPrenomInputError;

    @FXML
    private HBox NometPrenomInputErrorHbox;

    @FXML
    private TextField Num;

    @FXML
    private Text NumInputError;

    @FXML
    private HBox NumInputErrorHbox;

    @FXML
    private TextField RIB;

    @FXML
    private Button ajouterVirement;

    @FXML
    private TextField benef;

    @FXML
    private Text benefInputError;

    @FXML
    private HBox benefInputErrorHbox;

    @FXML
    private HBox beneficiaireInputErrorHbox;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text cinInputError;

    @FXML
    private HBox cinInputErrorHbox;

    @FXML
    private ImageView imageInput;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private Button imp;

    @FXML
    private TextField montant;

    @FXML
    private Text montantInputError;

    @FXML
    private HBox montantInputErrorHbox;

    @FXML
    private TextField transferez;

    @FXML
    private Text transferezInputError;

    @FXML
    private HBox transferezInputErrorHbox;

    @FXML
    private ComboBox<String > type;

    @FXML
    private Text typeInputError;
    private File selectedImageFile;
    private String imageName = null ;



    @FXML
    public void ajouterVirement(MouseEvent mouseEvent)  throws SQLException {

        ServiceVirement sv = new ServiceVirement();
        if (Cin.getText().isEmpty()) {
            cinInputErrorHbox.setVisible(true);
            if (NometPrenom.getText().isEmpty()) {
                NometPrenomInputErrorHbox.setVisible(true);
                if (Num.getText().isEmpty()) {
                    NumInputErrorHbox.setVisible(true);
                    if (type.getSelectionModel().isEmpty()) {
                        beneficiaireInputErrorHbox .setVisible(true);
                        if (transferez.getText().isEmpty()) {
                            transferezInputErrorHbox.setVisible(true);
                            if (benef.getText().isEmpty()) {
                                benefInputErrorHbox.setVisible(true);
                                if(montant.getText().isEmpty()){
                                    montantInputErrorHbox.setVisible(true);
                                }
                            }
                        }
                    }
                } else {
                    if (Num.getText().isEmpty()) {
                        NumInputErrorHbox.setVisible(true);
                        if (type.getSelectionModel().isEmpty()) {
                            beneficiaireInputErrorHbox.setVisible(true);
                            if (transferez.getText().isEmpty()) {
                                transferezInputErrorHbox.setVisible(true);
                                if (benef.getText().isEmpty()) {
                                    benefInputErrorHbox.setVisible(true);
                                    if(montant.getText().isEmpty()){
                                        montantInputErrorHbox.setVisible(true);
                                    }

                                }
                            }
                        }

                    } else {
                        if (type.getSelectionModel().isEmpty()) {
                            beneficiaireInputErrorHbox.setVisible(true);
                            if (transferez.getText().isEmpty()) {
                                transferezInputErrorHbox.setVisible(true);
                                if (benef.getText().isEmpty()) {
                                    benefInputErrorHbox.setVisible(true);
                                    if(montant.getText().isEmpty()) {
                                        montantInputErrorHbox.setVisible(true);
                                    }
                                }
                            } else {
                                if (transferez.getText().isEmpty()) {
                                    transferezInputErrorHbox.setVisible(true);
                                    if (benef.getText().isEmpty()) {
                                        benefInputErrorHbox.setVisible(true);
                                    } else {
                                        if (montant.getText().isEmpty()) {
                                            montantInputErrorHbox.setVisible(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }else {
            if (!Cin.getText().isEmpty()) {
                cinInputErrorHbox.setVisible(false);
                if (!NometPrenom.getText().isEmpty()) {
                    NometPrenomInputErrorHbox.setVisible(false);
                    if (!Num.getText().isEmpty()) {
                        NumInputErrorHbox.setVisible(false);
                        if (!type.getSelectionModel().isEmpty()) {
                            beneficiaireInputErrorHbox.setVisible(false);
                            if (!transferez.getText().isEmpty()) {
                                transferezInputErrorHbox.setVisible(false);
                                if (!benef.getText().isEmpty()) {
                                    benefInputErrorHbox.setVisible(false);
                                    if(!montant.getText().isEmpty()){
                                        montantInputErrorHbox.setVisible(false);
                                    }

                                }
                            }
                        }
                    }
                }
            }



        }
        // Select for combo
        SingleSelectionModel<String> selectionModel = type.getSelectionModel();

        String selectedType = selectionModel.getSelectedItem();
        // Date Now
        LocalDate selectedDate = LocalDate.now();
        // Create a new instance of cheque from View
        String image="admin";
        Integer Rib = 345678644;
        String type_virement=selectedType;
        System.out.println(type_virement);
        String transf= transferez.getText();
        String benefr=benef.getText();
        String montantv=montant.getText();
        String nom_prenom=NometPrenom.getText();
        int cin;
        try {
            cin = Integer.parseInt(Cin.getText());
        } catch (NumberFormatException e) {
            // Handle the case where Cin is not a valid integer
            // You can show an error message or handle it as per your application logic
            e.printStackTrace(); // Print the stack trace for debugging
            return; // Exit the method if the Cin value is not valid
        }
        String phone_number = Num.getText();
        String decision = "Encours";

        // change the date to sqlDate
        Date sqlDate = java.sql.Date.valueOf(selectedDate);
        //String nomet_prenom, String type_virement, String transferez_a, int num_beneficiare, String montant, int cin, int rib, String decision_v, String photo_cin_v, String phone_number
        Virement virement1=new Virement(nom_prenom,type_virement,transf,Integer.parseInt(benefr),montantv,cin,Rib,decision,image,phone_number);
        ServiceVirement serviceVirement = new ServiceVirement();
        serviceVirement.ajouterV(virement1);

    }
//    @FXML
//    void ajouterVirement(MouseEvent event) throws SQLException {
//        // Vérification des champs obligatoires
//        boolean isValid = true;
//        if (NometPrenom.getText().isEmpty()) {
//            NometPrenomInputError.setVisible(true);
//            isValid = false;
//        }
//        if (type.getSelectionModel().isEmpty()) {
//            typeInputError.setVisible(true);
//            isValid = false;
//        }
//        if (transferez.getText().isEmpty()) {
//            transferezInputError.setVisible(true);
//            isValid = false;
//        }
//        if (benef.getText().isEmpty()) {
//            benefInputError.setVisible(true);
//            isValid = false;
//        }
//        if (montant.getText().isEmpty()) {
//            montantInputError.setVisible(true);
//            isValid = false;
//        }
//        if (Cin.getText().isEmpty()) {
//            cinInputError.setVisible(true);
//            isValid = false;
//        }
//
//        if (Num.getText().isEmpty()) {
//            NumInputError.setVisible(true);
//            isValid = false;
//        }
//
//        if (isValid) {
//            // Création d'une instance de Virement à partir des champs de la vue
//            String nometPrenom = NometPrenom.getText();
//            String typeVirement = String.valueOf(type.getValue());
//            String transferezA = transferez.getText();
//            int numBeneficiaire = Integer.parseInt(Num.getText());
//            String montantText = montant.getText();
//            int cinNum = Integer.parseInt(Cin.getText());
//            int ribNum = Integer.parseInt(RIB.getText());
//            String emailText = benef.getText();
//            String phoneNumber =Num.getText();
//
//            Virement virement = new Virement(nometPrenom, typeVirement, transferezA, numBeneficiaire, montantText,
//                    cinNum, ribNum, emailText, phoneNumber);
//
//            // Appel du service pour ajouter le virement à la base de données
//            ServiceVirement serviceVirement = new ServiceVirement();
//            serviceVirement.ajouterV(virement);
//
//            // Réinitialisation des champs et des messages d'erreur
//            resetFields();
//        }
//    }

    private void resetFields() {
    }


    @Override
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
                "VEcoresponsabilité"
        );
            type.setItems(types);


        }

//    private void hideErrorMessages() {
//        NometPrenomInputError.setVisible(false);
//        typeInputError.setVisible(false);
//        NumInputError.setVisible(false);
//        benefInputError.setVisible(false);
//        montantInputError.setVisible(false);
//        cinInputError.setVisible(false);
//        transferezInputError.setVisible(false);
//
//    }

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

