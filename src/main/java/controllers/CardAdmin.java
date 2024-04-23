package controllers;

import controllers.Cheque.ListeChequeAdmin;
import controllers.Compte.ListeCompteAdmin;
import controllers.Virement.AjouterVirementCard;
import controllers.Virement.ListVirementAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.lang.ClassCastException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


import java.io.IOException;

public class CardAdmin {

    @FXML
    private Pane content_area;

    @FXML
    private Button listC;

    @FXML
    private Button listCh;

    @FXML
    private Button listV;

    @FXML
    private HBox offreModel;

    @FXML
    private VBox reductionForm;


    public void OpenListeCompteAd(MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListCompteAdmin.fxml"));
        Parent addArticleParent = loader.load();

        ListeCompteAdmin listeCompteAdmin;
        listeCompteAdmin = loader.getController();

        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }

    public void OpenListeChequeAd(MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListChequeAdmin.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        ListeChequeAdmin listeChequeAdmin = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }

    public void OpenListeVirAd(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListVirementAdmin.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        ListVirementAdmin listVirementAdmin = loader.<ListVirementAdmin>getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }
}
