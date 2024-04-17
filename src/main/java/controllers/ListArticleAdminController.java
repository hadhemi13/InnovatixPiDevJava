package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ListArticleAdminController {

    @FXML
    private Text AjouterArtBtn;

    @FXML
    private GridPane ArtListContainer;

    @FXML
    private TextField ArticlesclientsfSearchInput;

    @FXML
    private Text VoirComment;

    @FXML
    private Text artReturnFrontBtn;

    @FXML
    private HBox articleaddBtn;

    @FXML
    private ComboBox<?> categorieInput;

    @FXML
    private Pane content_area;

    @FXML
    private ScrollPane listArtScroll;

    @FXML
    void SearchByImage(MouseEvent event) {

    }


    @FXML
    void searchProduct(KeyEvent event) {

    }

    @FXML
    private void navigateToArticleAdd(MouseEvent event) throws IOException {
        // Chargement de la vue FXML de la page d'ajout d'article
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ajouterArticle.fxml"));
        Parent addArticleParent = loader.load();

        // Récupération du contrôleur de la vue d'ajout d'article
        AjouterArticleController addArticleController = loader.getController();

        // Remplacer le contenu actuel par la vue d'ajout d'article
        content_area.getChildren().clear();
        content_area.getChildren().add(addArticleParent);
    }

    @FXML
    void openListComment(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listCommentAdmin.fxml"));
            Pane listComAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listComAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
