package controllers;

import controllers.Cheque.AjouterChequeCard;
import controllers.Cheque.DemandeChequeListClient;
import controllers.Virement.DemandeVirementListClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class dashboardClient {

    @FXML
    private Button ListeCheque;

    @FXML
    private Button ListeVirement;

    @FXML
    private Pane content_area;

    @FXML
    private VBox fundListContainer;

    @FXML
    private Text userPointText;

    @FXML
    private HBox userTableHead;

    @FXML
    void ListVirement(ActionEvent event) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeVirementListClient.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            DemandeVirementListClient demandeVirementListClient = loader.getController();

            // Set the scene
            Stage stage = (Stage) ListeVirement.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }



    @FXML
    void listCheque(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DemandeChequeListClient demandeChequeListClient = loader.getController();

        // Set the scene
        Stage stage = (Stage) ListeCheque.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
