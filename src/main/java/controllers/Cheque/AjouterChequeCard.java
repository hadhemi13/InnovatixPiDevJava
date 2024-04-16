package controllers.Cheque;

import Entities.Cheque;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceCheque;
import utils.MyDatabase;
import javafx.stage.FileChooser.ExtensionFilter;
import services.ServiceCheque;


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
       /* FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir votre CV");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.png","*.jpg"));
        selectedCvFile = fileChooser.showOpenDialog(ChequeImg.getScene().getWindow());

        if (selectedCvFile != null) {
            // Charger le PDF dans la WebView
//            String url = selectedCvFile.toURI().toString();
//            cv.getEngine().load(url);
            Image cva = new Image(selectedCvFile.toURI().toString());
            imageInput.setText(String.valueOf(cva));
            // Générer un nom de fichier unique pour le CV
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedCvFile.getName().substring(selectedCvFile.getName().lastIndexOf(".")); // L'extension est déjà spécifiée dans le filtre de l'FileChooser
            fileName = uniqueID + extension;

            // Copier le fichier PDF vers le répertoire de destination
            Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", fileName);
            try {
                Files.copy(selectedCvFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }*/
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
    public void ajouterCheque(MouseEvent mouseEvent)  throws SQLException {

        ServiceCheque sc = new ServiceCheque();
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
        String image="admin";
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
        Cheque cheque = new Cheque(beneficiairee,montantn,aa,email,Cin,Nom, (java.sql.Date) sqlDate,imageName,decision);



// Convertir le texte en un entier
       // int telInteger = Integer.parseInt(telText);

        ServiceCheque serviceCheque = new ServiceCheque();
        serviceCheque.ajouterS(cheque);

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


    public void ajouter_image(MouseEvent mouseEvent) {
    }

    public void UpdateCheque(MouseEvent mouseEvent) {
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
