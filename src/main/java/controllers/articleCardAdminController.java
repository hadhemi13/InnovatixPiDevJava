package controllers;

import Entities.Article;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

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
    private HBox voirPlusArtBtn;
    public void initializeData(Article article) {
        String imagePath = "@../imagesAct/admin.png";
        imgArtFront.setImage(new Image(imagePath));
        titreArtFront.setText(article.getTitre_art());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = article.getDate_pub_art().format(formatter);
        datepubArt.setText(formattedDate);
        contenuArtFront.setText(article.getContenu_art());
    }

}
