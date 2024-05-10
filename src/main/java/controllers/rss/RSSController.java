package controllers.rss;


    import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import controllers.rss.rssFeed;

import java.io.IOException;
import java.util.List;

    public class RSSController {
        public Text textTitre;
        public ScrollPane contentArticle;
        public Text textcontent;
        @FXML
        private ListView<String> articleListView;

        @FXML
        private ImageView articleImageView;



        @FXML
        public void initialize() {
            // Autoriser la sélection simple d'un seul article dans la ListView
            articleListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

            List<String> articleTitles = null;
            try {
                articleTitles = rssFeed.loadArticleTitlesFromURL("https://rss.app/feeds/v1.1/t9AIvL6SZLnwsI91.json");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Afficher les titres des articles dans la ListView
            if (articleTitles != null && !articleTitles.isEmpty()) {
                articleListView.getItems().addAll(articleTitles);
            }

            // Ajouter un gestionnaire d'événements pour détecter la sélection d'un article
            articleListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    try {
                        // Charger les détails de l'article sélectionné
                        List<rssFeed> articles = rssFeed.loadArticlesFromURL("https://rss.app/feeds/v1.1/tyV0IvNTdIOilauf.json");

                        // Rechercher l'article correspondant au titre sélectionné
                        rssFeed selectedArticle = articles.stream()
                                .filter(article -> article.getTitle().equals(newValue))
                                .findFirst()
                                .orElse(null);

                        // Afficher les détails de l'article sélectionné
                        if (selectedArticle != null) {
                            textTitre.setText(selectedArticle.getTitle());
                            textcontent.setText(selectedArticle.getContent());
                            afficherImage(selectedArticle.getImageUrl());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        private void afficherImage(String imageUrl) {
            if (imageUrl != null && !imageUrl.isEmpty()) {
                try {
                    // Charger l'image à partir de l'URL et l'afficher dans l'ImageView
                    Image image = new Image(imageUrl);
                    articleImageView.setImage(image);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Réinitialiser l'image si l'URL est vide ou nulle
                articleImageView.setImage(null);
            }
        }

}
