package controllers.Compte;

import Entities.Cheque;
import Entities.Compte;
import controllers.Cheque.ListeChequeAdmin;
import controllers.Cheque.ModifierCheque;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;
import services.ServiceCompte;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class CompteItems implements Initializable {

    @FXML
    private Label ApprouveBtn;

    @FXML
    private ImageView ApprouverC;

    @FXML
    private Text DateNaiss;

    @FXML
    private Text Email;

    @FXML
    private Text Nom;

    @FXML
    private Text NumCin;

    @FXML
    private Text Numtel;

    @FXML
    private Text Prenom;

    @FXML
    private Text Professon;

    @FXML
    private Label RefusBtn;

    @FXML
    private Text TypeCin;

    @FXML
    private Text TypeCompte;
    @FXML
    private Compte compte;
    @FXML
    private  ImageView reffuserC;
    @FXML
    private HBox HboxCompte;


    public void initData(Compte i){
        this.compte= i ;
        ServiceCompte serviceCompte = new ServiceCompte();

        Email.setText(i.getEmail());
        Nom.setText(i.getNom());
        Prenom.setText(i.getPrenom());
        TypeCin.setText(i.getType_cin());
        NumCin.setText(String.valueOf(i.getCin()));
        DateNaiss.setText(String.valueOf(i.getDate_naissance()));
        Professon.setText(i.getProffesion());
        Numtel.setText(String.valueOf(i.getNumero_telephone()));
        TypeCompte.setText(i.getType_compte());
   //     ApprouveBtn.setOnMouseClicked(mouseEvent -> {
 //           Stage primaryStage = new Stage();
 //           try {
 //               Compte compte = serviceCompte.afficher().get(Integer.parseInt(ApprouveBtn.getId()));
 //               FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Historique.fxml")) ;
 //               Parent parent = loader.load();
 //               Scene scene = new Scene(parent);
 //               primaryStage.setTitle("E-Flex Bank");
 //               primaryStage.setScene(scene);
 //               primaryStage.show();
 //               ApprouverCompte approuverCompte = loader.getController();
 //               ApprouverCompte.initData(compte);
 //           }catch (SQLException | IOException exception)
 //           {
 //               throw new RuntimeException(exception);
 //           }
//
  //      });
  //      RefusBtn.setOnMouseClicked(mouseEvent -> {
   //         try {
    //            serviceCompte.supprimer(Integer.parseInt(RefusBtn.getId()));
//
 //           } catch (SQLException e) {
 //               throw new RuntimeException(e);
  //          }
   //     });
        if ("Approuvé".equals(compte.getStatut()) || "Rejeté".equals(compte.getStatut())) {
            disableStatutButtons();
        }



    }
    public void ApprouverCompte(MouseEvent mouseEvent) {
        if (showConfirmationDialog("Approve", "Voullez vous Approuvez ce compte Bancaire ?")) {
            updateCompteStatut("Approuvé");
        }
    }

    public void RefuserCompte(MouseEvent mouseEvent) {
        if (showConfirmationDialog("Reject", "Voulez vous réfuser ce compte bancaire?")) {
            updateCompteStatut("Réfusser" );
        }
    }

    private boolean showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title + " Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(content);

        Optional<ButtonType> action = alert.showAndWait();
        return action.isPresent() && action.get() == ButtonType.OK;
    }

    private void updateCompteStatut(String newStatut) {
        try {
            compte.setStatut(newStatut);
            new ServiceCompte().modifier(compte);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Compte statut updated to " + newStatut + ".");
            disableStatutButtons();
            applyRejectedStyle();

            ListeCompteAdmin.getInstance().refreshCompteList();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update compte decision.");
            e.printStackTrace();
        }
    }
    private void disableStatutButtons() {
        ApprouveBtn.setDisable(true);
        RefusBtn.setDisable(true);
        ApprouverC.setOpacity(0.4);  // Optionally set opacity to visually indicate the button is disabled
        reffuserC.setOpacity(0.4);    // Optionally set opacity to visually indicate the button is disabled
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void applyRejectedStyle() {
        if ("Rejeté".equals(compte.getStatut())) {
            HboxCompte.getStyleClass().clear();
            HboxCompte.getStyleClass().add("compte-rejected");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }






}
