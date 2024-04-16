package controllers.demandeStage;

import Entities.DemandeStage;
//import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.ServiceDemandeStage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static java.time.zone.ZoneRulesProvider.refresh;

public class AfficheDemandeController implements Initializable {

    @FXML
    private ListView<String> listViewNom;
    @FXML
    private Button refresh;
    @FXML
    private Button modifier;
    private ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();


    @FXML
    void refreshList(ActionEvent event) {
        RefreshA();
//        try {
//            List<DemandeStage> demandeStages = serviceDemandeStage.afficher();
//            listViewNom.getItems().clear();
////            listViewNom.getItems().add("Nom    | ");
//            StringBuilder Affichage = new StringBuilder();
//            Affichage.append("Nom        ");
//            Affichage.append("Prenom           ");
//            Affichage.append("Email           ");
//            Affichage.append("Numéro de téléphone           ");
//            Affichage.append("CV           ");
//            Affichage.append("Date           ");
//            Affichage.append("Score           ");
//
//
//            listViewNom.getItems().add(Affichage.toString());
//            for (DemandeStage i : demandeStages){
//                StringBuilder nom = new StringBuilder();
////                StringBuilder prenom = new StringBuilder();
//
//                nom.append(i.getNom()).append("     ");
//                nom.append(i.getPrenom()).append("     ");
//                nom.append(i.getEmail()).append("     ");
//                nom.append(i.getNumeroTelephone()).append("     ");
//                nom.append(i.getCv()).append("     ");
//                nom.append(i.getDate()).append("     ");
//                if (i.getScore() == 0 )
//                {
//
//                    nom.append("Demande spontannée");
//                }
//                else {
//                    nom.append(i.getScore());
//
//                }
//                listViewNom.getItems().add(nom.toString());
////                listViewNom.getItems().add(prenom.toString());
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }

    }
    public void RefreshA(){
        try {
            List<DemandeStage> demandeStages = serviceDemandeStage.afficher();
            listViewNom.getItems().clear();
//            listViewNom.getItems().add("Nom    | ");
            StringBuilder Affichage = new StringBuilder();
            Affichage.append("Nom        ");
            Affichage.append("Prenom           ");
            Affichage.append("Email           ");
            Affichage.append("Numéro de téléphone           ");
            Affichage.append("CV           ");
            Affichage.append("Date           ");
            Affichage.append("Score           ");


            listViewNom.getItems().add(Affichage.toString());
            for (DemandeStage i : demandeStages){
                StringBuilder nom = new StringBuilder();
//                StringBuilder prenom = new StringBuilder();

                nom.append(i.getNom()).append("     ");
                nom.append(i.getPrenom()).append("     ");
                nom.append(i.getEmail()).append("     ");
                nom.append(i.getNumeroTelephone()).append("     ");
                nom.append(i.getCv()).append("     ");
                nom.append(i.getDate()).append("     ");
                if (i.getScore() == 0 )
                {

                    nom.append("Demande spontannée");
                }
                else {
                    nom.append(i.getScore());

                }
                listViewNom.getItems().add(nom.toString());
//                listViewNom.getItems().add(prenom.toString());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RefreshA();
    }

    @FXML
    void Edit(ActionEvent event) throws SQLException {
        DemandeStage demande = getSelectedDemande();

        if (demande != null) {
            openEditWindow(demande);
        } else {
            System.out.println("Aucune demande sélectionnée.");
        }
    }

    private DemandeStage getSelectedDemande() throws SQLException {
        int selectedIndex = listViewNom.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Récupérer la demande correspondant à l'index sélectionné
            List<DemandeStage> demandeStages = serviceDemandeStage.afficher();
            return demandeStages.get(selectedIndex);
        }
        return null;
    }

    private void openEditWindow(DemandeStage demande) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/EditDemande.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Get the controller after loading the FXML file
        EditDemandeController editDemandeController = loader.getController();

        // Initialize the data in the editDemandeController
        editDemandeController.initData(demande);

        // Set the scene
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
