package controllers;

import Entities.CommentaireHadhemi;
import Entities.Reclamation;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class CommentaireItemAdminController {
    @FXML
    private Text contComment;

    @FXML
    private Text dateartitemDateText;

    @FXML
    private Text datecom;

    @FXML
    private Text nomusercom;

    @FXML
    private Text titreartcom;
//    public void initData(CommentaireHadhemi commentaireHadhemi) {
//
//        titreartcom.setText(commentaireHadhemi.getArticle().getTitre_art());
//        dateartitemDateText.setText(String.valueOf(commentaireHadhemi.getArticle().getDate_pub_art()));
//        nomusercom.setText(commentaireHadhemi.getNom_aut_com());
//        datecom.setText(String.valueOf(commentaireHadhemi.getDate_creation()));
//        contComment.setText(commentaireHadhemi.getContenu());
//
//    }

    public void initData(CommentaireHadhemi commentaireHadhemi) {
        titreartcom.setText(commentaireHadhemi.getArticle().getTitre_art());
        dateartitemDateText.setText(commentaireHadhemi.getArticle().getDate_pub_art().toString());
        nomusercom.setText(commentaireHadhemi.getNom_aut_com());
        datecom.setText(commentaireHadhemi.getDate_creation().toString());
        contComment.setText(commentaireHadhemi.getContenu());
    }
}
