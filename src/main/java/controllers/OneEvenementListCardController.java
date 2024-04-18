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
import javafx.util.Duration;
import services.IService;
import services.ServiceEvenement;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.IOException;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author ALI
 */
public class OneEvenementListCardController {

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
    private Text lieufx;

    @FXML
    private Text stockProduit;
    @FXML
    private HBox qrCodeEvenement;

    @FXML
    private HBox offerEvenement;



    @FXML
    private HBox priceHbox;

    public void setEvenementData(Evenement evenement) {
        float prixApresOffre = 0;
         IService EvenementService = new ServiceEvenement();

        Image image = new Image(
                getClass().getResource("/assets/ProductUploads/" + evenement.getImg()).toExternalForm());
        img.setImage(image);

        productName.setText(evenement.getNom());
        descfx.setText(evenement.getDescription());
        lieufx.setText(evenement.getLieu());
        pricefx.setText(String.valueOf(evenement.getPrix()));


        // deleteEvenement btn click
        deleteEvenement.setId(String.valueOf(evenement.getId()));

        deleteEvenement.setOnMouseClicked(event -> {
            System.out.println("ID du Evenement à supprimer : " + evenement.getId());
            try {
                EvenementService.supprimer(evenement.getId());
                TrayNotificationAlert.notif("Evenement", "Evenement deleted successfully.",
                        NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));
            } catch (SQLException e) {
                e.printStackTrace();
            }
//             supprimer le contenu de la liste et afficher la nouvelle liste(apres
//             supprimer)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/EvenementsList.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // end
        });
        // END deleteEvenement btn click

        // editEvenement btn click
       editEvenement.setId(String.valueOf(evenement.getId()));

        editEvenement.setOnMouseClicked(event -> {
            System.out.println("ID du Evenement à modifier : " + evenement.getId());
          //  Collecte.setIdEvenement(evenement.getId());

        //    Collecte.actionTest = 1; // pour afficher le bouton update

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AddEvenement.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de AddProduct.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        // END editEvenement btn click

        // qrCodeEvenement btn click
   //     editEvenement.setId(String.valueOf(evenement.getId()));

//        qrCodeEvenement.setOnMouseClicked(event -> {
//            System.out.println("ID du Evenement à générer qr Code : " + evenement.getId());
//        //    Evenement.setIdEvenement(evenement.getId());
//
//            String text = "Product ID: " + evenement.getId() + "\nProduct Name: " + evenement.getNom()
//                    + "\nProduct Description: " + evenement.getDescription() + "\nProduct Price: "
//                    + evenement.getPrix() + "\nProduct Points: " + evenement.getLieu();
//             // Créer un objet QRCodeWriter pour générer le QR code
//            QRCodeWriter qrCodeWriter = new QRCodeWriter();
//            // Générer la matrice de bits du QR code à partir du texte saisi
//            BitMatrix bitMatrix;
//            try {
//                bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
//                // Convertir la matrice de bits en image BufferedImage
//                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
//                // Enregistrer l'image en format PNG
//                // File outputFile = new File("qrcode.png");
//                // ImageIO.write(bufferedImage, "png", outputFile);
//                // Afficher l'image dans l'interface utilisateur
//
//                ImageView qrCodeImg = (ImageView) ((Node) event.getSource()).getScene().lookup("#qrCodeImg");
//                qrCodeImg.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
//
//                HBox qrCodeImgModel = (HBox) ((Node) event.getSource()).getScene().lookup("#qrCodeImgModel");
//                qrCodeImgModel.setVisible(true);
//            } catch (WriterException e) {
//                e.printStackTrace();
//            }
//
//        });
        // END qrCodeEvenement btn click

        // offreEvenement btn click
      //  offerEvenement.setId(String.valueOf(Evenement.getId()));

//        offerEvenement.setOnMouseClicked(event -> {
//            System.out.println("ID du Evenement à créer une offre : " + Evenement.getId());
//            Collecte.setIdEvenement(Evenement.getId());
//
//            HBox offreModel = (HBox) ((Node) event.getSource()).getScene().lookup("#offreModel");
//            offreModel.setVisible(true);
//
//        });
        // END offreEvenement btn click

    }


}
