package controllers.article;

import Entities.actualites.Article;
import controllers.article.ListArticleAdminController;
import controllers.article.UpdateArtcileCardController;
import controllers.article.articleCardAdminController;
import controllers.rss.RSSController;
import controllers.rss.RSSReader;
import controllers.rss.rssFeed;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;


import Entities.actualites.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import services.ServiceArticle;
import controllers.rss.RSSReader;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class FrontControlleur implements Initializable {


    public AnchorPane Empty;
    public BorderPane borderPost;
    public Label contenuArt;
    public Label categorieart;
    public Button titreArt;
    public Label userNom;
    public ImageView userImg;
    public Button dateArt;
    public ImageView imageP;
    public Button newtP;
    public Button PreviousP;
    public Button ajoutPP;
    @FXML
    private GridPane ArtListContainer;
    @FXML
    private VBox Rss;
    private List<RSSReader> articlesRss;

    private int currentArticleIndex = 0;

    private Timeline timeline;
    private final ServiceArticle serviceArticle = new ServiceArticle();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("yesser");
        try {
            // Call the method to load articles
            loadArticles();
            System.out.println("yesser load");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            // Handle any exceptions here, such as displaying an error message
        }
        // Nettoyer le contenu actuel
        ArtListContainer.getChildren().clear();
        System.out.println("yesser children");

        // Récupérez la liste des articles à partir du service ou du gestionnaire de données
        List<Article> articles = new ArrayList<>(); // Par exemple
        try {
            articles = serviceArticle.afficher();
            System.out.println(articles);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Créer un ScrollPane pour permettre le défilement des articles
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true); // Ajuster la largeur du ScrollPane à celle de la GridPane
        scrollPane.setContent(ArtListContainer);

        // Ajouter le ScrollPane à la vue parent
        // Assurez-vous que ArtListContainer est déjà ajouté à une vue parent dans votre scène FXML
        // Si ce n'est pas le cas, ajoutez d'abord ArtListContainer à la vue parent dans votre FXML
        // Ensuite, vous pouvez ajouter le ScrollPane à la même vue parent
        // Par exemple, si ArtListContainer est déjà ajouté à une VBox nommée container dans votre FXML :
        // container.getChildren().add(scrollPane);

        // Ajoutez chaque article au GridPane
        int row = 1;
        int column = 0;
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        ArtListContainer.setVgap(verticalGap);
        ArtListContainer.setHgap(horizontalGap);

        for (Article article : articles) {
            System.out.println("loadArticles" + article.getId());
            // Charger la carte d'article à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
            Parent articleCardParent = null;
            try {
                articleCardParent = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Initialisez les données de l'article dans le contrôleur de carte d'article
            articleCardClientController articleCardController = loader.getController();
            articleCardController.initializeData(article);

            // Ajoutez la carte d'article au GridPane
            ArtListContainer.add(articleCardParent, column, row);

            // Incrémentez la colonne et passez à la ligne suivante si nécessaire
            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }
//        try {
//            // Charger les détails de l'article sélectionné
//            List<rssFeed> articlesRss = rssFeed.loadArticlesFromURL("https://rss.app/feeds/v1.1/tyV0IvNTdIOilauf.json");
//            for (rssFeed rssFeed : articlesRss){
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleRssCard.fxml"));
//
//                Parent offreItem = loader.load();
//                System.out.println(articlesRss);
//                RSSController offreStageItem = loader.getController();
//                offreStageItem.initData(rssFeed);
//                Rss.getChildren().add(offreItem);
//            }
//            // Rechercher l'article correspondant au titre sélectionné
////            rssFeed selectedArticle = articlesRss.stream()
////                    .filter(article -> article.getTitle().equals(newValue))
////                    .findFirst()
////                    .orElse(null);
//
//            // Afficher les détails de l'article sélectionné
////            if (selectedArticle != null) {
////                textTitre.setText(selectedArticle.getTitle());
////                textcontent.setText(selectedArticle.getContent());
////                afficherImage(selectedArticle.getImageUrl());
////            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        try {
            articlesRss = RSSReader.loadArticlesFromURL("https://rss.app/feeds/v1.1/t9AIvL6SZLnwsI91.json");
            if (!articles.isEmpty()) {
                displayArticle(articlesRss.get(currentArticleIndex)); // Appel de la méthode avec un objet de type RSSReader
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // Gérer IOException de manière appropriée
        }



        // Initialize and start the timeline for automatic article display
        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> nextArticle()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    @FXML
    void Go_New_Post() {
        nextArticle();
    }

    @FXML
    void PreviousPost() {
        if (currentArticleIndex > 0) {
            currentArticleIndex--;
        } else {
            currentArticleIndex = articlesRss.size() - 1;
        }
        displayArticle(articlesRss.get(currentArticleIndex));
    }

    @FXML
    void nextPost() {
        nextArticle();
    }

    private void nextArticle() {
        if (currentArticleIndex < articlesRss.size() - 1) {
            currentArticleIndex++;
        } else {
            currentArticleIndex = 0;
        }
        displayArticle(articlesRss.get(currentArticleIndex));
    }

    private void displayArticle(RSSReader article) {
        titreArt.setText(article.getTitle());
        dateArt.setText(article.getDatePublished());
        String wrappedContent = wrapText(article.getContent(), 60);
        contenuArt.setText(wrappedContent);
        categorieart.setText(article.getAuthor());

        if (!article.getImageUrl().isEmpty()) {
            try {
                Image image = new Image(article.getImageUrl());
                imageP.setImage(image);
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
            }
        }
    }

    // Méthode pour envelopper le texte avec un retour à la ligne chaque 50 caractères
    private String wrapText(String text, int wrapLength) {
        StringBuilder sb = new StringBuilder(text);
        int i = 0;
        while ((i = sb.indexOf(" ", i + wrapLength)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        return sb.toString();
    }

    public void searchProduct(KeyEvent keyEvent) {
    }

    public void SearchByImage(MouseEvent mouseEvent) {
    }

    public void getPromotionalItems(MouseEvent mouseEvent) {
    }

    public void go_details_Posts(ActionEvent actionEvent) {
    }

    public void returnbackarticle(MouseEvent mouseEvent) {


    }





    private void loadArticleCards(List<Article> articles) {
        int column = 0;
        int row = 1;

        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        ArtListContainer.setVgap(verticalGap);
        ArtListContainer.setHgap(horizontalGap);
        for (Article article : articles) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
                VBox articleCard = fxmlLoader.load();

                // Get the controller
                articleCardClientController articleCardController = fxmlLoader.getController();

                // Set article data
                articleCardController.initializeData(article);

                // Add the article card to the grid pane
                ArtListContainer.add(articleCard, column, row);

                // Increment row and column
                column++;
                if (column >= 4) {
                    column = 0;
                    row++;
                }

            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception appropriée ici, par exemple afficher un message d'erreur à l'utilisateur ou journaliser l'erreur
            }
        }
    }

//
//    public void loadArticles() throws IOException, SQLException {
//        ArtListContainer.getChildren().clear(); // Nettoyer le contenu actuel
//
//        // Récupérez la liste des articles à partir du service ou du gestionnaire de données
//        List<Article> articles = serviceArticle.getAllArticles(); // Par exemple
//        System.out.println(serviceArticle);
//        // Ajoutez chaque article au GridPane
//        int row = 1;
//        // Espacement entre les cartes
//        double verticalGap = 18; // Environ 9 mm
//        double horizontalGap = 50; // Environ 9 mm
//        // Espacement entre le GridPane et les cartes
//        double margin = 5; // Environ 10 mm
//        // Définir l'espacement vertical et horizontal
//        ArtListContainer.setVgap(verticalGap);
//        ArtListContainer.setHgap(horizontalGap);
//        for (Article article : articles) {
//            // Créez un contrôleur de carte d'article pour chaque article
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
//            Parent articleCardParent = loader.load();
//
//            // Initialisez les données de l'article dans le contrôleur de carte d'article
//            articleCardClientController articleCardController = loader.getController();
//            articleCardController.initializeData(article);
//
//            // Ajoutez la carte d'article au GridPane
//            ArtListContainer.addRow(row++, articleCardParent);
//
//        }
//    }


    public void loadArticles() throws IOException, SQLException {

        // Nettoyer le contenu actuel
        ArtListContainer.getChildren().clear();

        // Récupérez la liste des articles à partir du service ou du gestionnaire de données
        List<Article> articles = serviceArticle.getAllArticles(); // Par exemple

        // Créer un ScrollPane pour permettre le défilement des articles
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true); // Ajuster la largeur du ScrollPane à celle de la GridPane
        scrollPane.setContent(ArtListContainer);

        // Ajouter le ScrollPane à la vue parent
        // Assurez-vous que ArtListContainer est déjà ajouté à une vue parent dans votre scène FXML
        // Si ce n'est pas le cas, ajoutez d'abord ArtListContainer à la vue parent dans votre FXML
        // Ensuite, vous pouvez ajouter le ScrollPane à la même vue parent
        // Par exemple, si ArtListContainer est déjà ajouté à une VBox nommée container dans votre FXML :
        // container.getChildren().add(scrollPane);

        // Ajoutez chaque article au GridPane
        int row = 1;
        int column = 0;
        // Espacement entre les cartes
        double verticalGap = 18; // Environ 9 mm
        double horizontalGap = 50; // Environ 9 mm
        // Espacement entre le GridPane et les cartes
        double margin = 5; // Environ 10 mm
        // Définir l'espacement vertical et horizontal
        ArtListContainer.setVgap(verticalGap);
        ArtListContainer.setHgap(horizontalGap);

        for (Article article : articles) {
            System.out.println("loadArticles"+article.getId());
            // Charger la carte d'article à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
            Parent articleCardParent = loader.load();

            // Initialisez les données de l'article dans le contrôleur de carte d'article
            articleCardClientController articleCardController = loader.getController();
            articleCardController.initializeData(article);

            // Ajoutez la carte d'article au GridPane
            ArtListContainer.add(articleCardParent, column, row);

            // Incrémentez la colonne et passez à la ligne suivante si nécessaire
            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }
    }

    public void refreshArticleList() throws SQLException {


        // Nettoyer le contenu actuel
        ArtListContainer.getChildren().clear();

        try {
            // Charger à nouveau la liste des articles depuis la base de données
            List<Article> articles = serviceArticle.afficher();
            System.out.println("refresh" + articles);
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
                System.out.println("loadArticles" + article.getId());
                // Charger la carte d'article à partir du fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/article/articleCardClient.fxml"));
                VBox articleCard = loader.load();

                // Récupérer le contrôleur de la carte d'article
                articleCardClientController articleCardController = loader.getController();

                // Initialiser les données de l'article dans la carte d'article
                articleCardController.initializeData(article);

                // Ajouter la carte d'article au conteneur
                ArtListContainer.add(articleCard, column, row);

                // Incrémenter la colonne
                column++;

                // Vérifier si nous devons passer à la ligne suivante
                if (column >= 4) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Gérer l'exception ici, si nécessaire
            }
        }
    }




    public void nextPost(ActionEvent actionEvent) {
    }

    public void PreviousPost(ActionEvent actionEvent) {
    }

    public void Go_New_Post(ActionEvent actionEvent) {
    }
}