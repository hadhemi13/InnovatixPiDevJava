package controllers;

import Entities.Article;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private HBox updateArticleModel;

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
    private static int updateArticleModelShow = 0;
    private static int ShowProjectModelShow = 0;
    private static int ArticleIdToUpdate = 0;

    public static void setupdateArticleModelShow(int updateArticleModelShow) {
        ListArticleAdminController.updateArticleModelShow = updateArticleModelShow;
    }
    public static void setShowArticleModelShow(int ShowArticleModelShow) {
        ListArticleAdminController.ShowProjectModelShow = ShowArticleModelShow;
    }
    public static void setArticleEmailToUpdate(int ArticleIdToUpdate) {
        ListArticleAdminController.ArticleIdToUpdate = ArticleIdToUpdate;
    }
    public static int getupdateArticleModelShow() {
        return updateArticleModelShow;
    }
    @FXML
    void close_updateProjectModel(MouseEvent event) {
        updateArticleModel.setVisible(false);
        updateArticleModelShow = 0;
    }
    @FXML
    private VBox updateArticleModelContent;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Article article;

        if (ListArticleAdminController.getUpdateArticleModelShow() == 0) {
            updateArticleModel.setVisible(false);
        } else if (ListArticleAdminController.getupdateArticleModelShow() == 1) {
            updateArticleModel.setVisible(true);
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("/FXML/updateProjectCard.fxml"));
            VBox updateProjectform;
            try {
                updateProjectform = fxmlLoader1.load();
                UpdateArtcileCardController updateUserCardController = fxmlLoader1.getController();
                UpdateArtcileCardController.setFxmlToLoad("listArticleAdmin.fxml");
                article = serviceArticle.getOneProject(ArticleIdToUpdate);
                updateUserCardController.setProjectUpdateData(article);
                updateArticleModelContent.getChildren().add(updateProjectform);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            refreshArticleList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

//    @FXML
//    public void initialize() {
//
//        try {
//            List<Article> articles = serviceArticle.afficher();
//            loadArticleCards(articles);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    private void loadArticleCards(List<Article> articles) {
        int column = 0;
        int row = 1;
//        int cardSpacing = 10;
//        int rowSpacing = 10;
//        int columnSpacing = 10;
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        ArtListContainer.setVgap(verticalGap);
        ArtListContainer.setHgap(horizontalGap);
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
                if (column >= 3) {
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
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        ArtListContainer.setVgap(verticalGap);
        ArtListContainer.setHgap(horizontalGap);
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

    public void refreshArticleList() throws SQLException {
        ////        ArtListContainer.getChildren().clear(); // Nettoyer le contenu actuel
        ////
        ////        try {
        ////            List<Article> articles = serviceArticle.afficher();
        ////            loadArticles(articles);
        ////        } catch (SQLException e) {
        ////            e.printStackTrace();
        ////            // Gérer l'exception appropriée ici
        ////        }
        //        // Nettoyer le contenu actuel
        //        ArtListContainer.getChildren().clear();
        //
        //        // Charger à nouveau la liste des articles depuis la base de données
        //        List<Article> articles = serviceArticle.afficher();
        //
//        // Charger à nouveau les cartes d'articles dans le conteneur
//        loadArticleCards(articles);
        // Nettoyer le contenu actuel
        ArtListContainer.getChildren().clear();

        try {
            // Charger à nouveau la liste des articles depuis la base de données
            List<Article> articles = serviceArticle.afficher();

            // Charger à nouveau les cartes d'articles dans le conteneur
            loadArticles(articles);
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
        }
    }


    private void loadArticles(List<Article> articles) {
        // Nettoyer le conteneur actuel
        ArtListContainer.getChildren().clear();

        // Réinitialiser les valeurs de la ligne et de la colonne
        int row = 1;
        int column = 0;
        int maxColumns = 3; // Nombre maximum de colonnes par ligne
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        ArtListContainer.setVgap(verticalGap);
        ArtListContainer.setHgap(horizontalGap);
        // Parcourir chaque article et charger sa carte dans le conteneur
        for (Article article : articles) {
            try {
                // Charger la carte d'article à partir du fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/articleCardAdmin.fxml"));
                VBox articleCard = loader.load();

                // Récupérer le contrôleur de la carte d'article
                articleCardAdminController articleCardController = loader.getController();

                // Initialiser les données de l'article dans la carte d'article
                articleCardController.initializeData(article);

                // Ajouter la carte d'article au conteneur
                ArtListContainer.add(articleCard, column, row);

                // Incrémenter la colonne
                column++;

                // Vérifier si nous devons passer à la ligne suivante
                if (column >= maxColumns) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception ici, si nécessaire
            }
        }
    }
    public static int getUpdateArticleModelShow() {
        return updateArticleModelShow;
    }

    public void categorieInput(ActionEvent actionEvent) {
    }

    public void ArticlesclientsfSearchInput(KeyEvent keyEvent) throws SQLException {

        }


//
//    public void searchActivite() throws SQLException {
//        FilteredList<Article> filteredData = new FilteredList<>(FXCollections.observableArrayList(serviceArticle.afficher()), p -> true);
//        ArticlesclientsfSearchInput.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(article -> {
//                if (newValue == null || newValue.isEmpty()) {
//                    return true;
//                }
//                String titre = String.valueOf(article.getTitre_art());
//                String contenu = String.valueOf(article.getContenu_art());
//                String cat = String.valueOf(article.getCategorie_art());
//                String date = String.valueOf(article.getDate_pub_art());
//                String lowerCaseFilter = newValue.toLowerCase();
//
//                if (titre.toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (contenu.toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (cat.toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else if (date.toLowerCase().contains(lowerCaseFilter)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            });
//        });
//        SortedList<Article> sortedData = new SortedList<>(filteredData);
//        // Liaison du tri de sortedData avec le tri par défaut du conteneur d'articles
//        sortedData.comparatorProperty().bind(ArtListContainer.getChildren().toArray());
//
//// Effacement du contenu actuel du conteneur d'articles et ajout des articles triés
//        ArtListContainer.getChildren().setAll(sortedData);
//
//=
//
//
//    }
}