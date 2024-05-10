package controllers.article;

import controllers.rss.RSSReader;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class articleCardRssController {

    public VBox backArt;
    @FXML
    private Text titreArtFront;

    @FXML
    private Text contenuArtFront;

    // Méthode appelée lors de l'initialisation du contrôleur
    @FXML
    public void initialize() {
        // Charger les articles RSS
        List<RSSReader> articles = null;
        try {
            articles = RSSReader.loadArticlesFromURL("https://rss.app/feeds/v1.1/tRnP5WwFlg3QpD80.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Afficher le premier article dans l'interface utilisateur
        if (articles != null && !articles.isEmpty()) {
            RSSReader firstArticle = articles.get(0);
            titreArtFront.setText(firstArticle.getTitle());
            contenuArtFront.setText(firstArticle.getContent());
        }
    }
}
