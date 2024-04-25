package controllers;

import Entities.CommentaireHadhemi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import services.ServiceArticle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CommentaireItemAdminController implements Initializable {

    @FXML
    private Text contComment;

    @FXML
    private Text dateartitemDateText;

    @FXML
    private Text datecom;

    @FXML
    private ImageView deleteComment;

    @FXML
    private Text nomusercom;

    @FXML
    private Text titreartcom;
public static CommentaireHadhemi comment ;
    @FXML
    void deleteComment(MouseEvent event) {
        try {
            if (comment != null) {
                ServiceArticle sa = new ServiceArticle();
                sa.supprimer(comment.getArticle());
                // Stage stage = (Stage) deleteArt.getScene().getWindow();
                // stage.close();
            } else {
                // Affichez un message d'erreur ou faites une action appropriée si l'article est null
                System.err.println("L'article est null. Impossible de le supprimer.");
            }
        } catch (SQLException e) {
            // Gérer l'exception SQLException ici
            e.printStackTrace();}

    }



    public void initData(CommentaireHadhemi commentaireHadhemi) {
        titreartcom.setText(commentaireHadhemi.getArticle().getTitre_art());
        dateartitemDateText.setText(String.valueOf(commentaireHadhemi.getArticle().getDate_pub_art()));
        nomusercom.setText(commentaireHadhemi.getNom_aut_com());
        datecom.setText(String.valueOf(commentaireHadhemi.getDate_creation()));
        contComment.setText(commentaireHadhemi.getContenu());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comment=this.comment;
    }
}




