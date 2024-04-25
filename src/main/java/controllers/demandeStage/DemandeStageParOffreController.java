//package controllers.demandeStage;
//
//import Entities.DemandeStage;
//import Entities.OffreDeStage;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.layout.VBox;
//import javafx.event.ActionEvent;
//import javafx.stage.FileChooser;
//import services.ServiceDemandeStage;
//import services.Symfony;
//import services.Upload;
//import utils.MyDatabase;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.util.Optional;
//
//public class DemandeStageParOffreController  {
//
//    @FXML
//    private DatePicker date;
//
//    @FXML
//    private TextField LettreDemInput;
//
//    @FXML
//    private TextField EmailDemInput;
//
//    @FXML
//    private TextField cv;
//
//    @FXML
//    private Button upload;
//
//    @FXML
//    private Button ajouterDemande;
//
//    @FXML
//    private TextField PrenomDemInput;
//
//    @FXML
//    private TextField NumeroDemInput;
//
//    @FXML
//    private TextField NomDemInput;
//    @FXML
//    private TextField idOffre;
//
//    @FXML
//    private VBox content_area;
//    private File selectedCvFile;
//    private String fileName;
//    OffreDeStage stageYesser;
//    int id = 0;
//
//    @FXML
//    void Upload(ActionEvent event) {
//
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Choisir votre CV");
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
//        selectedCvFile = fileChooser.showOpenDialog(upload.getScene().getWindow());
//
//        if (selectedCvFile != null) {
//            Upload ServiceUpload = new Upload();
//            fileName = ServiceUpload.Upload(selectedCvFile);
//            cv.setText(fileName);
//        }
//
//    }
//       private OffreDeStage stagek = new OffreDeStage();
//
//    @FXML
//    void ajouterDemande(ActionEvent event) {
//        System.out.println("stageVariable" + stageYesser.getPostePropose());
////        System.out.println("ajouter "   + ajouterDemande.getId());
////        int kh = id;
////        System.out.println("h" + idOffre);
//        if (event.getSource() == ajouterDemande) {
//
//            if (NomDemInput.getText().isEmpty() && LettreDemInput.getText().isEmpty() && EmailDemInput.getText().isEmpty() && PrenomDemInput.getText().isEmpty() && NumeroDemInput.getText().isEmpty() && cv.getText().isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Information manquante");
//                alert.setHeaderText(null);
//                alert.setContentText("Toutes les champs sont vides.");
//                Optional<ButtonType> option = alert.showAndWait();
//            }
//            if (NomDemInput.getText().isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Information manquante");
//                alert.setHeaderText(null);
//                alert.setContentText("Le nom est vide.");
//                Optional<ButtonType> option = alert.showAndWait();
//            } else {
//                String nom = NomDemInput.getText();
//                String prenom = PrenomDemInput.getText();
//                String email = EmailDemInput.getText();
//                String lettre = LettreDemInput.getText();
//                String num = NumeroDemInput.getText();
//                String etat = "encours";
//                String domaine = "Informatique";
//                String cv = fileName;
//                Date date1 = java.sql.Date.valueOf(date.getValue());
//
////                Date date = new Date(128,04,06);
//                MyDatabase db = MyDatabase.getInstance();
//
//                DemandeStage stage = new DemandeStage(nom,prenom,email,lettre,cv,domaine,Integer.parseInt(num),etat,date1);
////                ( int id_offre, String nom, String prenom, String email, int numeroTelephone,String lettremotivation,String cv, String domaine,String etat, Date date, int score)
//                Symfony symfony = new Symfony();
////                int score = symfony.score(id,fileName);
//              //  int score =
////                 DemandeStage Stage = new DemandeStage(id,nom,prenom,email,Integer.parseInt(num),lettre,cv,domaine,etat,date1,score);
//                ServiceDemandeStage demandeStage = new ServiceDemandeStage();
//
//                try {
//                    System.out.println("id button " + id);
//
//                    demandeStage.ajouterParOffre(stage, id);
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setTitle("Ajouté avec succès");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Votre activité a été ajoutée avec succès.");
//                    Optional<ButtonType> option = alert.showAndWait();
//                } catch (SQLException e) {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setTitle("Ajouté Failed");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Failed.");
//                    Optional<ButtonType> option = alert.showAndWait();
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        }
//    }
//    public void initData(OffreDeStage offreDeStage){
//        this.stageYesser = offreDeStage;
//        this.id = offreDeStage.getPostePropose();
//        id = offreDeStage.getPostePropose();
//        System.out.println(id);
////        System.out.println("initData" + offreDeStage.getPostePropose());
////        idOffre.setText(String.valueOf(offreDeStage.getPostePropose()));
//       // System.out.println(id);
////      ajouterDemande.setId(String.valueOf(offreDeStage.getPostePropose()));
//
//
//    }
//}
package controllers.demandeStage;

import Entities.DemandeStage;
import Entities.OffreDeStage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import services.ServiceDemandeStage;
import services.Symfony;
import services.Upload;
import utils.MyDatabase;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;

public class DemandeStageParOffreController {

    @FXML
    private DatePicker date;

    @FXML
    private TextField LettreDemInput;

    @FXML
    private TextField EmailDemInput;

    @FXML
    private TextField cv;

    @FXML
    private Button upload;

    @FXML
    private Button ajouterDemande;

    @FXML
    private TextField PrenomDemInput;

    @FXML
    private TextField NumeroDemInput;

    @FXML
    private TextField NomDemInput;


//    private int idOffre;
    @FXML
    private VBox content_area;

    private File selectedCvFile;
    private String fileName;
    private OffreDeStage stageYesser;

    @FXML
    void Upload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir votre CV");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        selectedCvFile = fileChooser.showOpenDialog(upload.getScene().getWindow());

        if (selectedCvFile != null) {
            Upload ServiceUpload = new Upload();
            fileName = ServiceUpload.Upload(selectedCvFile);
            cv.setText(fileName);
        }
    }

    @FXML
    void ajouterDemande(ActionEvent event) {
        if (event.getSource() == ajouterDemande) {
            // Vérifier si stageYesser n'est pas null avant de l'utiliser
//            if (stageYesser != null) {
                // Le reste de votre code ici...
                String nom = NomDemInput.getText();
                String prenom = PrenomDemInput.getText();
                String email = EmailDemInput.getText();
                String lettre = LettreDemInput.getText();
                String num = NumeroDemInput.getText();
                String etat = "encours";
                String domaine = "Informatique";
                String cv = fileName;
                Date date1 = java.sql.Date.valueOf(date.getValue());

                // Vérifier si fileName n'est pas null
                if (fileName != null) {
                    DemandeStage stage = new DemandeStage(nom, prenom, email, lettre, cv, domaine, Integer.parseInt(num), etat, date1);
                    ServiceDemandeStage demandeStage = new ServiceDemandeStage();

                    try {
                        demandeStage.ajouterParOffre(stage, 1);
                        showAlert(Alert.AlertType.CONFIRMATION, "Ajouté avec succès", null, "Votre activité a été ajoutée avec succès.");
                    } catch (SQLException | IOException | InterruptedException e) {
                        showAlert(Alert.AlertType.ERROR, "Ajouté Failed", null, "Failed.");
                        e.printStackTrace();
                    }
                } else {
                    showAlert(Alert.AlertType.WARNING, "Information manquante", null, "Veuillez sélectionner un fichier CV.");
                }
////            } else {
//                showAlert(Alert.AlertType.WARNING, "Information manquante", null, "L'offre de stage n'a pas été correctement initialisée.");
//            }
        }
    }

    public void initData(OffreDeStage offreDeStage) {
        this.stageYesser = offreDeStage;
        int yy = offreDeStage.getPostePropose();
        System.out.println(yy);

//        idOffre.setText(String.valueOf(offreDeStage.getPostePropose()));
//        System.out.println("offre fel init   " + idOffre.getText());
    }


    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

