package controllers.demandeStage;

import Entities.DemandeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
//import javafx.scene.web.WebView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceDemandeStage;
import services.Upload;
import services.ValidatorOffre;
import utils.MyDatabase;

import java.net.URL;
import java.nio.file.Path;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
//import javafx.scene.web.WebView;

public class DemandeStageController implements Initializable {
//    @FXML
//    private WebView CvDemInput;

    @FXML
    private TextField LettreDemInput;

    @FXML
    private TextField EmailDemInput;

    @FXML
    private TextField PrenomDemInput;

    @FXML
    private TextField NumeroDemInput;

    @FXML
    private TextField NomDemInput;
    @FXML
    private Button upload;
    @FXML
    private DatePicker date;
    @FXML
    private TextField cv;
    private File selectedCvFile;
    private String fileName;
//    @FXML
//    private Text dateError;
//    @FXML
//    private Text prenomError;
//
//    @FXML
//    private Text NumeroError;
//
//    @FXML
//    private Text CvError;
//
//    @FXML
//    private Text nomError;
//    @FXML
//    private Text domainError;
//    @FXML
//    private Text EmailError;
//
//    @FXML
//    private Text lettreError;


    @FXML
    private Button ajouterDemande;
//    private int i = 0;

    @FXML
    void Upload(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir votre CV");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        selectedCvFile = fileChooser.showOpenDialog(upload.getScene().getWindow());

        if (selectedCvFile != null) {
            Upload ServiceUpload = new Upload();
           fileName =  ServiceUpload.Upload(selectedCvFile);
           cv.setText(fileName);
        }
    }
    @FXML
    void ajouterDemande(ActionEvent event) {
//        ValidatorOffre validatorOffre = new ValidatorOffre();
//        if (!validatorOffre.isValidEmpty(NomDemInput.getText())){
//            nomError.setVisible(true);
//        }
//        if (!validatorOffre.isValidEmpty(PrenomDemInput.getText())){
//            prenomError.setVisible(true);
//        }
//        if (!validatorOffre.isValidEmail(EmailDemInput.getText())){
//            EmailError.setVisible(true);
//        }





        if (event.getSource() == ajouterDemande){


            if (NomDemInput.getText().isEmpty() &&  LettreDemInput.getText().isEmpty() && EmailDemInput.getText().isEmpty() && PrenomDemInput.getText().isEmpty() && NumeroDemInput.getText().isEmpty() && cv.getText().isEmpty() ){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Toutes les champs sont vides.");
                Optional<ButtonType> option = alert.showAndWait();
            }
            if (NomDemInput.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information manquante");
                alert.setHeaderText(null);
                alert.setContentText("Le nom est vide.");
                Optional<ButtonType> option = alert.showAndWait();
            } else {
                String nom = NomDemInput.getText();
                String prenom = PrenomDemInput.getText();
                String email = EmailDemInput.getText();
                String lettre = LettreDemInput.getText();
                String num = NumeroDemInput.getText();
                String etat = "encours";
                String domaine = "Informatique";
                String cv = fileName;
                Date date1 = java.sql.Date.valueOf(date.getValue());

//                Date date = new Date(128,04,06);
                MyDatabase db = MyDatabase.getInstance();

                DemandeStage  stage = new DemandeStage(nom,prenom,email,lettre,cv,domaine,Integer.parseInt(num),etat,date1);
                ServiceDemandeStage demandeStage = new ServiceDemandeStage();
                try {
                    demandeStage.ajouter(stage);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Ajouté avec succès");
                    alert.setHeaderText(null);
                    alert.setContentText("Votre activité a été ajoutée avec succès.");
                    Optional<ButtonType> option = alert.showAndWait();
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Ajouté Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed.");
                    Optional<ButtonType> option = alert.showAndWait();

                }
            }
    }
}

    private void AjoutDem(){
        String nom = NomDemInput.getText();
        String prenom = PrenomDemInput.getText();
        String email = EmailDemInput.getText();
        String lettre = LettreDemInput.getText();
        String num = NumeroDemInput.getText();
        String etat = "encours";
        String domaine = "Informatique";
        String cv = "fileName";
        Date date = new Date(128,04,06);
        MyDatabase db = MyDatabase.getInstance();
        DemandeStage demandeStage = new DemandeStage(nom,prenom,email,lettre,cv,domaine,Integer.parseInt(num),etat,date);
        ServiceDemandeStage serviceDemandeStage =new ServiceDemandeStage();
        try {
            serviceDemandeStage.ajouter(demandeStage);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void initData(int postePropose) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        @FXML
//        private Text dateError;
//        dateError.setVisible(false);
////        @FXML
////        private Text prenomError;
//        prenomError.setVisible(false);
////
////        @FXML
////        private Text NumeroError;
//        NumeroError.setVisible(false);
////
////        @FXML
////        private Text CvError;
//        CvError.setVisible(false);
////
////        @FXML
//        private Text nomError;
//        nomError.setVisible(false);
////        @FXML
////        private Text domainError;
//        domainError.setVisible(false);
////        @FXML
////        private Text EmailError;
//        EmailError.setVisible(false);
////
////        @FXML
////        private Text lettreError;
//        lettreError.setVisible(false);



    }
}
