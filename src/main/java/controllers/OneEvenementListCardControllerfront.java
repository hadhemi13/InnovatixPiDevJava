package controllers;

import Entities.Evenement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.IService;
import services.ServiceEvenement;

import java.io.IOException;

public class OneEvenementListCardControllerfront {

    @FXML
    private ImageView img;

    @FXML
    private HBox deleteEvenement;

    @FXML
    private HBox editEvenement;

    @FXML
    private Text productName;


    @FXML
    private Text descfx;

    @FXML
    private Text pricefx;
    @FXML
    private Text pricefx1;

    @FXML
    private Text offrefx;
    @FXML
    private Text Datefx;

    @FXML
    private Text lieufx;
    @FXML
    private Text stockProduit;
    @FXML
    private HBox qrCodeEvenement;
    @FXML
    private HBox offerEvenement;
    @FXML
    private HBox priceHbox;
    IService EvenementService = new ServiceEvenement();
    public void setEvenementData(Evenement evenement) {
        Image image = new Image(
                getClass().getResource("/assets/ProductUploads/" + evenement.getImg()).toExternalForm());
        img.setImage(image);
        productName.setText(evenement.getNom());
        descfx.setText(evenement.getDescription());
        lieufx.setText(evenement.getLieu());
        pricefx.setText(String.valueOf(evenement.getPrix()));
        pricefx1.setText(String.valueOf(evenement.getPrix()));
        offrefx.setText(String.valueOf(evenement.getRemise()));
        Datefx.setText(String.valueOf(evenement.getDateDebut()));

    }


}
