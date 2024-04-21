package controllers;

import controllers.Cheque.DemandeChequeListClient;
import controllers.Credit.DemandeCreditListClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class dashboardClientcreditrdv {

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
    public void listcredit(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeCreditListClient.fxml"));
        System.out.println("e");
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        DemandeCreditListClient demandeCreditListClient = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }

    @FXML
    void listrdv(MouseEvent event) {

    }


}