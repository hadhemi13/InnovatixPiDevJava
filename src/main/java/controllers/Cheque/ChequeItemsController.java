package controllers;

import Entities.Cheque;
import Entities.Virement;
import controllers.Cheque.DemandeChequeListClient;
import controllers.Cheque.ModifierCheque;
import controllers.Virement.DemandeVirementListClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCheque;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.awt.Desktop;

public class ChequeItemsController {

    @FXML
    private Text date;

    @FXML
    private Text userItemEmail;

    @FXML
    private Text personne;

    @FXML
    private Text montant;

    @FXML
    private HBox Pdf;

    @FXML
    private Text userItemStateText;

    @FXML
    private Text userItemTel;

    @FXML
    private Text hhh;

    @FXML
    private HBox userItemUpdateBtn;

    @FXML
    private Text userItemRole;

    @FXML
    private Text productName;

    @FXML
    private Text userItemName;

    @FXML
    private HBox userItemStateBtn;

    @FXML
    private Node pdf;

    public void initData(Cheque i) {
        ServiceCheque serviceCheque = new ServiceCheque();

        userItemUpdateBtn.setId(String.valueOf(i.getId()));

        userItemStateBtn.setId(String.valueOf(i.getId()));

        userItemEmail.setText(String.valueOf(i.getCin()));
        userItemName.setText(String.valueOf(i.getCompte_id()));
        userItemTel.setText(i.getNom_prenom());
        userItemRole.setText(i.getEmail());
        userItemStateText.setText(i.getDecision());
        date.setText(String.valueOf(i.getDate()));
        hhh.setText(String.valueOf(i.getTelephone()));
        personne.setText(i.getBeneficiaire().toString());
        montant.setText(String.valueOf(i.getMontant()));

        userItemUpdateBtn.setOnMouseClicked(event -> {
            if (i.getDecision().equals("Approuvé")) {
                userItemUpdateBtn.setDisable(true);
                showAlert(Alert.AlertType.ERROR, "Demande Approuvée", "La demande de Chéque a déjà été approuvée.");
            } else {
                loadUpdateCard(i);
                System.out.println("Cheque Name: " + i.getNom_prenom());
                DemandeChequeListClient.setupdateChequeModelShow(1);
                DemandeChequeListClient.setchequeEmailToUpdate(i.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
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

        userItemStateBtn.setOnMouseClicked(event -> {
            System.out.println("Cheque Name: " + i.getNom_prenom());
            try {
                serviceCheque.supprimer(Integer.parseInt(userItemStateBtn.getId()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        pdf.setOnMouseClicked(event -> {
            if (i != null) {
                Document document = new Document();
                try {
                    String pdfFileName = "cheque.pdf";
                    String relativePath = "src/main/resources/pdf/";
                    String outputPath = System.getProperty("user.dir") + "/" + relativePath + pdfFileName;
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
                    document.open();
                    Rectangle rectangle = new Rectangle(document.getPageSize());
                    rectangle.setBorder(Rectangle.BOX);
                    rectangle.setBorderWidth(2);
                    rectangle.setBorderColor(new BaseColor(107, 175, 84));
                    PdfContentByte canvas = writer.getDirectContent();
                    canvas.rectangle(rectangle);

                    String userDir = System.getProperty("user.dir");
                    String imagePath = userDir + "/src/main/resources/img/logo.png";
                    com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(imagePath);
                    logo.scaleToFit(100, 100);
                    logo.setAlignment(Element.ALIGN_LEFT);
                    document.add(logo);

                    Paragraph title = new Paragraph("Demande de Chèque", new Font(Font.FontFamily.HELVETICA, 32, Font.BOLD, new BaseColor(107, 175, 84)));
                    title.setAlignment(Element.ALIGN_CENTER);
                    document.add(title);

                    LineSeparator line = new LineSeparator();
                    line.setLineColor(new BaseColor(107, 175, 84));
                    line.setLineWidth(2f);
                    line.setPercentage(100f);
                    document.add(line);

                    document.add(Chunk.NEWLINE);

                    Font detailsFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
                    document.add(new Paragraph("Voici les Détails du Chéque :  ", detailsFont));
                    document.add(new Paragraph("Nom et Prénom: " + userItemTel.getText(), detailsFont));
                    document.add(new Paragraph("Email: " + userItemRole.getText(), detailsFont));
                    document.add(new Paragraph("CIN: " + userItemEmail.getText(), detailsFont));
                    document.add(new Paragraph("Bénéficiaire: " + personne.getText(), detailsFont));
                    document.add(new Paragraph("Montant: " + montant.getText(), detailsFont));
                    document.add(new Paragraph("Téléphone: " + hhh.getText(), detailsFont));
                    document.add(new Paragraph("Date: " + date.getText(), detailsFont));

                    document.add(Chunk.NEWLINE);

                    document.close();
                    System.out.println("PDF enregistré avec succès !");

                    File pdfFile = new File(outputPath);
                    Desktop.getDesktop().open(pdfFile);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Erreur lors de la création ou de l'ouverture du PDF.");
                }
            } else {
                System.err.println("Le Chèque n'est pas initialisé.");
            }
        });
    }

    private void loadUpdateCard(Cheque i) {
        userItemUpdateBtn.setOnMouseClicked(event -> {
            DemandeChequeListClient.setupdateChequeModelShow(1);
            DemandeChequeListClient.setchequeEmailToUpdate(i.getId());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeChequeListClient.fxml"));
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void UpdateCheque(MouseEvent mouseEvent) {
    }

    public void DeleteCheque(MouseEvent mouseEvent) {
    }

    public void genererPDF(MouseEvent mouseEvent) {
    }
}
