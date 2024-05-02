package controllers;

import Entities.Article;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.ServiceArticle;
import services.ServiceCommentaireHadhemi;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.cert.PolicyNode;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.lang.System.err;

public class articleCardAdminController implements Initializable {


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

    @FXML
    private HBox ItemUpdateBtn;
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
            imgArtFront.setImage(new Image("file:///" + System.getProperty("user.dir") + "/src/main/java/uploads/" + article.getImage_art()));

        }


        ItemUpdateBtn.setOnMouseClicked(event -> {
            System.out.println("article Name: " + article.getCategorie_art());
            ListArticleAdminController.setupdateArticleModelShow(1);
            ListArticleAdminController.setArticleEmailToUpdate(article.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listArticleAdmin.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        deleteArtBtn.setId(String.valueOf(article.getId()));
        deleteArtBtn.setOnMouseClicked(event -> {
            System.out.println("ID de l'article à supprimer : " + article.getId());
            try {
                serviceArticle.supprimer(article);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listArticleAdmin.fxml"));
            try {
                Parent root = loader.load();

                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }




    @FXML
    void deleteArtBtn(MouseEvent event) {

    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void backArt(MouseEvent mouseEvent) {
    }

    public void modifierArt(MouseEvent mouseEvent) {
    }
}


