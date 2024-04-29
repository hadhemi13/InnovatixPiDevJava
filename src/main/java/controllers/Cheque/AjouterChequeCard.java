package controllers.Cheque;

import Entities.Cheque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceCheque;
import services.ValidSaisie;
import utils.MyDatabase;
import javafx.stage.FileChooser.ExtensionFilter;
import services.ServiceCheque;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.cert.PolicyNode;
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

public class AjouterChequeCard implements Initializable {

    @FXML
    private TextField Cin;

    @FXML
    private TextField Email;

    @FXML
    private Text EmailInputError;

    @FXML
    private HBox EmailInputErrorHbox;

    @FXML
    private TextField NometPrenom;
    @FXML
    private Pane content_area;

    @FXML
    private Text NometPrenomInputError;

    @FXML
    private Text DateInputError;

    @FXML
    private HBox DateInputErrorHbox;



    @FXML
    private HBox NometPrenomInputErrorHbox;

    @FXML
    private TextField RIB;

    @FXML
    private Button add_new_chequetBtn;

    @FXML
    private ComboBox<String> beneficiaire;

    @FXML
    private Text beneficiaireInputError;

    @FXML
    private HBox beneficiaireInputErrorHbox;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text cinInputError;

    @FXML
    private HBox cinInputErrorHbox;

    @FXML
    private DatePicker date;

    @FXML
    private Button ChequeImg;


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
    private TextField tel;

    @FXML
    private Text telInputError;

    @FXML
    private HBox telInputErrorHbox;
    private Button ajouterCheque;
    Image selectedCvFile ;

    private File selectedImageFile;
    private String imageName = null ;


    @FXML
    void ImporterImg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
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


    @FXML
    public void ajouterCheque(MouseEvent mouseEvent) throws SQLException, IOException {

        ServiceCheque sc = new ServiceCheque();
        boolean champsInvalides = false;

        // Vérification du numéro de CIN
        if (Cin.getText().isEmpty() || !ValidSaisie.isValidCin(Cin.getText())) {
            cinInputErrorHbox.setVisible(true);
            cinInputError.setText("Le numéro de cin doit contenir 8 chiffres qui commence par 0 ou 1");
            champsInvalides = true; // Marquer le champ comme invalide
        } else {
            cinInputErrorHbox.setVisible(false);
        }

        // Vérification du nom et prénom
        if (NometPrenom.getText().isEmpty()) {
            NometPrenomInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            NometPrenomInputErrorHbox.setVisible(false);
        }

        // Vérification du bénéficiaire
        if (beneficiaire.getSelectionModel().isEmpty()) {
            beneficiaireInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            beneficiaireInputErrorHbox.setVisible(false);
        }

        // Vérification de l'email
        if (Email.getText().isEmpty() || !ValidSaisie.isValidEmail(Email.getText())) {
            EmailInputErrorHbox.setVisible(true);
            EmailInputError.setText("Adresse email invalide");
            champsInvalides = true;

        } else {
            EmailInputErrorHbox.setVisible(false);
        }

        // Vérification du montant
        if (montant.getText().isEmpty()) {
            montantInputErrorHbox.setVisible(true);
            champsInvalides = true;
        } else {
            montantInputErrorHbox.setVisible(false);
        }

        // Vérification du numéro de téléphone
        if (tel.getText().isEmpty() || !ValidSaisie.isValidNumber(tel.getText())) {
            telInputErrorHbox.setVisible(true);
            telInputError.setText("Le numéro de téléphone doit commencer par 2 ou 5 ou 9 et contenir 8 chiffres");
            champsInvalides = true;
        } else {
            telInputErrorHbox.setVisible(false);
        }

        // Vérification si au moins un champ est invalide
        if (champsInvalides) {
            return; // Arrêter le traitement si des champs sont invalides
        }

        if (Cin.getText().isEmpty()) {
            cinInputErrorHbox.setVisible(true);
            if (NometPrenom.getText().isEmpty()) {
                NometPrenomInputErrorHbox.setVisible(true);
                if (beneficiaire.getSelectionModel().isEmpty()) {
                    beneficiaireInputErrorHbox.setVisible(true);
                    if (Email.getText().isEmpty()) {
                        EmailInputErrorHbox.setVisible(true);
                        if (montant.getText().isEmpty()) {
                            montantInputErrorHbox.setVisible(true);
                            if (tel.getText().isEmpty()) {
                                telInputErrorHbox.setVisible(true);
                            }
                        }
                    }
                } else {
                    if (beneficiaire.getSelectionModel().isEmpty()) {
                        beneficiaireInputErrorHbox.setVisible(true);
                        if (Email.getText().isEmpty()) {
                            EmailInputErrorHbox.setVisible(true);
                            if (montant.getText().isEmpty()) {
                                montantInputErrorHbox.setVisible(true);
                                if (tel.getText().isEmpty()) {
                                    telInputErrorHbox.setVisible(true);
                                }
                            }
                        }

                    } else {
                        if (Email.getText().isEmpty()) {
                            EmailInputErrorHbox.setVisible(true);
                            if (montant.getText().isEmpty()) {
                                montantInputErrorHbox.setVisible(true);
                                if (tel.getText().isEmpty()) {
                                    telInputErrorHbox.setVisible(true);
                                }
                            } else {
                                if (montant.getText().isEmpty()) {
                                    montantInputErrorHbox.setVisible(true);
                                    if (tel.getText().isEmpty()) {
                                        telInputErrorHbox.setVisible(true);
                                    } else {
                                        if (tel.getText().isEmpty()) {
                                            telInputErrorHbox.setVisible(true);
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
                        if (!beneficiaire.getSelectionModel().isEmpty()) {
                            beneficiaireInputErrorHbox.setVisible(false);
                            if (!Email.getText().isEmpty()) {
                                EmailInputErrorHbox.setVisible(false);
                                if (!montant.getText().isEmpty()) {
                                    montantInputErrorHbox.setVisible(false);
                                    if (!tel.getText().isEmpty()) {
                                        telInputErrorHbox.setVisible(false);

                                    }
                                }
                            }
                        }
                    }
                }



        }
        // Select for combo
        SingleSelectionModel<String> selectionModel = beneficiaire.getSelectionModel();

        String selectedBeneficiaire = selectionModel.getSelectedItem();
        // Date Now
        LocalDate selectedDate = LocalDate.now();
        // Create a new instance of cheque from View
        String image=imageName;
        Integer Rib = 345678644;
        String telText = tel.getText();
        Integer aa = Integer.parseInt(telText);
        String cin = Cin.getText();
        Integer Cin = Integer.parseInt(cin);
        String Nom = NometPrenom.getText();
        String beneficiairee = beneficiaire.getValue();
        Double montantn = Double.valueOf(montant.getText());
        String email = Email.getText();
        String decision = "Encours";
        // change the date to sqlDate
        Date sqlDate = java.sql.Date.valueOf(selectedDate);

        Cheque cheque = new Cheque(beneficiairee,montantn,aa,email,Cin,Nom, (java.sql.Date) sqlDate,imageName,decision,1,1);


        ServiceCheque serviceCheque = new ServiceCheque();
        serviceCheque.ajouterS(cheque);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
        Pane demandeChequeListParent = loader.load();

        // Remplacer le contenu de content_area par le contenu de la liste des demandes de chèques
        content_area.getChildren().setAll(demandeChequeListParent);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageInputErrorHbox.setVisible(false);
        telInputErrorHbox.setVisible(false);
        montantInputErrorHbox.setVisible(false);
        EmailInputErrorHbox.setVisible(false);
        cinInputErrorHbox.setVisible(false);
        NometPrenomInputErrorHbox.setVisible(false);
        beneficiaireInputErrorHbox.setVisible(false);
        DateInputErrorHbox.setVisible(false);

        ObservableList<String> beneficiaires = FXCollections.observableArrayList(
                "Paiement",
                "PaiementEco",
                "Personne"
        );

        beneficiaire.setItems(beneficiaires);


    }



}



    // Méthode pour ajouter une image au chèque
   // public void ajouter_image(ActionEvent event)  throws IOException {
      //  FileChooser fileChooser=new FileChooser();
        //fileChooser.setTitle("Choisir une image");
        //fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images","*.png","*.jpg","*.jpeg","*.gif"));
        //selectedImageFile= fileChooser.showOpenDialog(ImageInput.getScene().getWindow()):
        //if (selectedImageFile != null) {
           // Image image = new (selectedImageFile.toURI().toString());
           // imageInput.setImage(image);

           // String uniqueID = UUID.randomUUID().toString();
            //String extension =selectedImageFile.getName

        //}
    //}
//}
