package controllers;

import Entities.Evenement;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
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

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class OneEvenementListCardController {

    @FXML
    private ImageView img;

    @FXML
    private Text offrefx;
    @FXML
    private HBox deleteEvenement;

    @FXML
    private HBox editEvenement;
    @FXML
    private HBox showEvenement;

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
    private HBox offerEvent;

    @FXML
    private HBox offerEvenement;


    @FXML
    private Text priceAfterOffer;
    @FXML
    private HBox priceHbox;
    @FXML
    private HBox priceAfterOfferHbox;

    @FXML
    private Text priceBeforeOffer;
    @FXML
    private TextField nameInput;



    public void setEvenementData(Evenement evenement) {




        float prixApresOffre = 0;
        IService EvenementService = new ServiceEvenement();

        Image image = new Image(
                getClass().getResource("/assets/ProductUploads/" + evenement.getImg()).toExternalForm());
        img.setImage(image);
        productName.setText(evenement.getNom());
        descfx.setText(evenement.getDescription());
        lieufx.setText(evenement.getLieu());
        offrefx.setText(String.valueOf(evenement.getRemise()));
        System.out.println(evenement.getRemise());
        if (evenement.getRemise() == 0) {
            System.out.println(" test");
            System.out.println(evenement.getRemise());
            priceAfterOfferHbox.setVisible(false);
            priceHbox.setVisible(true);
            pricefx.setText("" + evenement.getPrix());
        } else {
            System.out.println("offer is working!!!");
            priceHbox.setVisible(false);
            priceAfterOfferHbox.setVisible(true);
            priceBeforeOffer.setText("" + evenement.getPrix());
            prixApresOffre = (float) (evenement.getPrix()
                    - (evenement.getPrix() * evenement.getRemise() / 100.0));
            String prixApresOffreStr = String.format("%.1f", prixApresOffre);
            priceAfterOffer.setText(prixApresOffreStr);
        }
       // pricefx.setText(String.valueOf(evenement.getPrix()));

        deleteEvenement.setId(String.valueOf(evenement.getId()));

        deleteEvenement.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Supprimer l'événement");
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cet événement?");


            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("/assets/style/alerts.css").toExternalForm());
            dialogPane.getStyleClass().add("my-dialog");

            ButtonType ouiButton = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
            ButtonType nonButton = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(ouiButton, nonButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ouiButton) {
                System.out.println("ID du Evenement à supprimer : " + evenement.getId());
                try {
                    EvenementService.supprimer(evenement.getId());
                    TrayNotificationAlert.notif("Événement", "Événement supprimé avec succès.",
                            NotificationType.SUCCESS, AnimationType.POPUP, Duration.millis(2500));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/EvenementsList.fxml"));
                try {
                    Parent root = loader.load();

                    Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                    contentArea.getChildren().clear();
                    contentArea.getChildren().add(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        offerEvent.setId(String.valueOf(evenement.getId()));

        offerEvent.setOnMouseClicked(event -> {
            System.out.println("ID du evenement à créer une offre : " + evenement.getId());
            Evenement.setIdEvenement(evenement.getId());
            HBox offreModel = (HBox) ((Node) event.getSource()).getScene().lookup("#offreModel");
            offreModel.setVisible(true);
        });

        editEvenement.setId(String.valueOf(evenement.getId()));
        editEvenement.setOnMouseClicked(event -> {
            System.out.println("ID du Evenement à modifier : " + evenement.getId());
            Evenement.setIdEvenement(evenement.getId());
            Evenement.actionTest = 1;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/AddEvenement.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        showEvenement.setId(String.valueOf(evenement.getId()));
        showEvenement.setOnMouseClicked(event -> {
            System.out.println("ID du Evenement : " + evenement.getId());
            Evenement.setIdEvenement(evenement.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/project/ShowEvenementCard.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        editEvenement.setId(String.valueOf(evenement.getId()));
        qrCodeEvenement.setOnMouseClicked(event -> {
            System.out.println("ID du Evenement à générer qr Code : " + evenement.getId());
            Evenement.setIdEvenement(evenement.getId());
            StringBuilder builder = new StringBuilder();
            builder.append(" ID de l'événement: ").append(evenement.getId()).append("\n");
            builder.append("Nom de l'événement: ").append(evenement.getNom()).append("\n");
            builder.append("Description de l'événement: ").append(evenement.getDescription()).append("\n");
            builder.append("Prix de l'événement: ").append(evenement.getPrix()).append("\n");
            builder.append("Lieu de l'événement: ").append(evenement.getLieu()).append("\n");
            String text = builder.toString();
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix;
            try {
                bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);
                 BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                ImageView qrCodeImg = (ImageView) ((Node) event.getSource()).getScene().lookup("#qrCodeImg");
                qrCodeImg.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
                HBox qrCodeImgModel = (HBox) ((Node) event.getSource()).getScene().lookup("#qrCodeImgModel");
                qrCodeImgModel.setVisible(true);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        });
    }
}
