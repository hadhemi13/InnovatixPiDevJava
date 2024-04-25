package controllers;

import Entities.CommentaireHadhemi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.ServiceArticle;
import services.ServiceCommentaireHadhemi;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CommentaireItemAdminController{

    @FXML
    private Text contComment;
    @FXML
    private Text nomusercom;
    @FXML
    private Text dateartitemDateText;


    @FXML
    private Text datecom;

    @FXML
    private ImageView deleteComment;

    @FXML
    private Text titreartcom;
    @FXML
    void deleteComment(MouseEvent event) {
//        try {
//            if (commentaireHadhemi != null) {
//                ServiceArticle sa = new ServiceArticle();
//                sa.supprimer(comment.getArticle());
//                // Stage stage = (Stage) deleteArt.getScene().getWindow();
//                // stage.close();
//            } else {
//                // Affichez un message d'erreur ou faites une action appropriée si l'article est null
//                System.err.println("L'article est null. Impossible de le supprimer.");
//            }
//        } catch (SQLException e) {
//            // Gérer l'exception SQLException ici
//            e.printStackTrace();}

    }



    public void initData(CommentaireHadhemi commentaireHadhemi) {
        ServiceCommentaireHadhemi serviceCommentaireHadhemi = new ServiceCommentaireHadhemi();
        contComment.setText(commentaireHadhemi.getContenu());
        datecom.setText(String.valueOf(commentaireHadhemi.getDate_creation()));
        nomusercom.setText(commentaireHadhemi.getNom_aut_com());
        titreartcom.setText(commentaireHadhemi.getArticle().getTitre_art());
        dateartitemDateText.setText(String.valueOf(commentaireHadhemi.getArticle().getDate_pub_art()));
deleteComment.setId(String.valueOf(commentaireHadhemi.getId()));
        deleteComment.setOnMouseClicked(event -> {
            System.out.println("ID du commentaire à supprimer : " + commentaireHadhemi.getId());
            try {
                serviceCommentaireHadhemi.supprimer(commentaireHadhemi);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listCommentAdmin.fxml"));
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
    }