package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.security.cert.PolicyNode;

public class ListeRecClientController {
    @FXML
    private VBox RecListContainer;


    @FXML
    private HBox articleaddBtn;

    @FXML
    private ComboBox<?> inputRectrie;

    @FXML
    private Pane content_area;

    @FXML
    private HBox recTableHead;
    public void AjouterRec(MouseEvent mouseEvent) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ajouterReclamation.fxml"));
        Parent addRecParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        AjouterReclamationController addRecController = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addRecParent);

    }
}
