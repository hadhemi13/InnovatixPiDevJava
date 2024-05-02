package controllers;

import Entities.Article;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ServiceArticle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class articleCardClientController implements Initializable {

    @FXML
    private HBox action;

    @FXML
    private VBox backArt;

    @FXML
    private Text contenuArtFront;

    @FXML
    private Text datepubArt;

    @FXML
    private HBox deleteArtBtn;

    @FXML
    private HBox editArt;

    @FXML
    private ImageView imgArtFront;

    @FXML
    private HBox open_productDetails;

    @FXML
    private Text titreArtFront;

    @FXML
    private HBox viewdetailArt;

    private Article article;
    private ListArticleAdminController listArticleController;

    public void setListArticleController(ListArticleAdminController listArticleController) {
        this.listArticleController = listArticleController;
    }

    public void initializeData(Article article) {
        ServiceArticle serviceArticle = new ServiceArticle();
        if (article != null) {
            titreArtFront.setText(article.getTitre_art());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = article.getDate_pub_art().format(formatter);
            datepubArt.setText(formattedDate);
            contenuArtFront.setText(article.getContenu_art());
            viewdetailArt.setId(String.valueOf(article.getId()));
            viewdetailArt.setOnMouseClicked(event -> {
                try {
                    // Charger le fichier FXML du formulaire de modification d'article
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/commentArticle.fxml"));
                    Parent editArticlePopupParent = loader.load();

                    // Récupérer le contrôleur du formulaire de modification d'article
                    CommentArticleController comment = loader.getController();

                    // Passer l'article à modifier au contrôleur
                    comment.setData(article);

                    // Créer une nouvelle fenêtre modale pour le formulaire de modification
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Commenter Article");
                    stage.setScene(new Scene(editArticlePopupParent));
                    stage.showAndWait(); // Attendre que la fenêtre se ferme avant de continuer

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void viewdetailArt(MouseEvent mouseEvent) {
    }

}
