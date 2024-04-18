package controllers;

import Entities.Article;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class articleCardAdminController {

    @FXML
    private HBox ArticleDetails;

    @FXML
    private Text contenuArtFront;

    @FXML
    private Text datepubArt;

    @FXML
    private ImageView imgArtFront;

    @FXML
    private Text titreArtFront;
    @FXML
    private VBox backArt;
    @FXML
    private StackPane stackPane;

    @FXML
    private HBox voirPlusArtBtn;
    public void initializeData(Article article) {


        titreArtFront.setText(article.getTitre_art());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = article.getDate_pub_art().format(formatter);
        datepubArt.setText(formattedDate);
        contenuArtFront.setText(article.getContenu_art());

    }
    private Article article; // Article associé à cette carte d'article

    public void setArticle(Article article) {
        this.article = article;
        // Remplir les éléments graphiques avec les données de l'article
        if (article != null) {
            // Assurez-vous que l'image de l'article n'est pas vide
//            if (article.getImage_art() != null && !article.getImage_art().isEmpty()) {
//                imgArtFront.setImage(new Image(article.getImage_art()));
//            }
            titreArtFront.setText(article.getTitre_art());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = article.getDate_pub_art().format(formatter);
            datepubArt.setText(formattedDate);
            contenuArtFront.setText(article.getContenu_art());
        }
    }
    @FXML
    void modifierArt(MouseEvent event) throws IOException {
//        // Charger la vue FXML du formulaire de modification d'article
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/modifierArticle.fxml"));
//        Parent editArticleFormParent = loader.load();
//
//        // Créer une nouvelle scène
//        Scene editArticleFormScene = new Scene(editArticleFormParent);
//
//        // Obtenir la scène actuelle
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        // Créer une nouvelle fenêtre modale
//        Stage modalStage = new Stage();
//        modalStage.initModality(Modality.WINDOW_MODAL);
//        modalStage.initOwner(stage);
//        modalStage.setScene(editArticleFormScene);
//        modalStage.showAndWait();
        // Charger la vue FXML de la boîte de dialogue modale personnalisée



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/modifierArticle.fxml"));
        Parent editArticlePopupParent = loader.load();

        // Créer une nouvelle scène
        Scene editArticlePopupScene = new Scene(editArticlePopupParent);

        // Obtenir la scène actuelle
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Créer une nouvelle fenêtre modale
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.WINDOW_MODAL);
        modalStage.initOwner(stage);
        modalStage.initStyle(StageStyle.TRANSPARENT); // Définir le style de la fenêtre comme transparent
        modalStage.setScene(editArticlePopupScene);
        modalStage.showAndWait();


   }
}


