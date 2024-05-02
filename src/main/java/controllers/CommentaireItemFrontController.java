package controllers;

import Entities.CommentaireHadhemi;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.ServiceCommentaireHadhemi;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;



public class CommentaireItemFrontController {


    @FXML
    private Text commentAff;

    @FXML
    private HBox commenterHbox;

    @FXML
    private Text nomCom;


    /////////////////////Ajb////////////////////////////
    public void initData(CommentaireHadhemi commentaireHadhemi) {
        ServiceCommentaireHadhemi serviceCommentaireHadhemi = new ServiceCommentaireHadhemi();
        commentAff.setText(commentaireHadhemi.getContenu());
        nomCom.setText(commentaireHadhemi.getNom_aut_com());
        System.out.println("contenue du commmmmmm"+commentAff);



    }
/////////////////////Ajb////////////////////////////


}
