package controllers;

import controllers.Compte.AjouterCompte;
import controllers.Virement.DemandeVirementListClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FrontControlleur {

    @FXML
    private Button CreerCompte;
    @FXML
    private VBox content_area;


    @FXML
    void CreateCompte(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FormAjoutCompte.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        AjouterCompte ajouterCompte = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);

    }

}