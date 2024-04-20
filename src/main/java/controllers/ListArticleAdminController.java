package controllers;

import Entities.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.VBox;
import services.ServiceArticle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListArticleAdminController implements Initializable {

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

    private final ServiceArticle serviceArticle = new ServiceArticle();

    @FXML
    public void initialize() {

        try {
            List<Article> articles = serviceArticle.afficher();
            loadArticleCards(articles);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadArticleCards(List<Article> articles) {
        int column = 0;
        int row = 0;
        int cardSpacing = 10;
        int rowSpacing = 10;
        int columnSpacing = 10;
        for (Article article : articles) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/articleCardAdmin.fxml"));
                VBox articleCard = fxmlLoader.load();

                // Get the controller
                articleCardAdminController articleCardController = fxmlLoader.getController();

                // Set article data
                articleCardController.initializeData(article);

                // Add the article card to the grid pane
                ArtListContainer.add(articleCard, column, row);

                // Increment row and column
                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception appropriée ici, par exemple afficher un message d'erreur à l'utilisateur ou journaliser l'erreur
            }
        }
    }

    public void loadArticles() throws IOException, SQLException {
        ArtListContainer.getChildren().clear(); // Nettoyer le contenu actuel

        // Récupérez la liste des articles à partir du service ou du gestionnaire de données
        List<Article> articles = serviceArticle.getAllArticles(); // Par exemple

        // Ajoutez chaque article au GridPane
        int row = 0;
        for (Article article : articles) {
            // Créez un contrôleur de carte d'article pour chaque article
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/articleCardAdmin.fxml"));
            Parent articleCardParent = loader.load();

            // Initialisez les données de l'article dans le contrôleur de carte d'article
            articleCardAdminController articleCardController = loader.getController();
            articleCardController.initializeData(article);

            // Ajoutez la carte d'article au GridPane
            ArtListContainer.addRow(row++, articleCardParent);
        }
    }

    public void refreshArticleList() {
        ArtListContainer.getChildren().clear(); // Nettoyer le contenu actuel

        try {
            List<Article> articles = serviceArticle.afficher();
            loadArticles(articles);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }
    }

    private void loadArticles(List<Article> articles) {
        int row = 0;
        int column = 0;
        int maxColumns = 3; // Nombre maximum de colonnes par ligne

        for (Article article : articles) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/articleCardAdmin.fxml"));
                Parent articleCardParent = loader.load();

                // Set article data in the controller
                articleCardAdminController articleCardController = loader.getController();
                articleCardController.initializeData(article);

                // Add the article card to the grid pane
                ArtListContainer.add(articleCardParent, column, row);

                // Increment column and row
                column++;
                if (column >= maxColumns) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception appropriée ici
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshArticleList();

    }
}