package controllers.Cheque;

import Entities.Cheque;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ServiceCheque;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChequeItems implements Initializable {

    @FXML
    private Text userItemEmail;

    @FXML
    private HBox userItemStateBtn;
    @FXML
    private GridPane gridPane;

    private Cheque cheque;
    @FXML
    private VBox content_area;

    @FXML
    private Text userItemName;
    @FXML
    private HBox userItemUpdateBtn;

    @FXML
    private Text userItemStateText;

    @FXML
    private Text userItemRole;


    @FXML
    private ImageView userItemStateBtnImg;

    @FXML
    private Label userItemStateLabel;


    @FXML
    private Text userItemTel;
    @FXML
    private ImageView userItemUpdateBtnImg;
    @FXML
    private Text date;

    @FXML
    private Text hhh;

    @FXML
    private Text personne;


//    public void initData(Cheque i) {
//        ServiceCheque serviceCheque= new ServiceCheque();
//
//        userItemEmail.setText(i.getEmail().toString());
//        userItemName.setText(i.getNom_prenom());
//        userItemTel.setText(String.valueOf(i.getTelephone()));
//        userItemRole.setText(i.getEmail());
//        userItemStateText.setText(i.getBeneficiaire());
//        date.setText(i.getPhoto_cin());
//        hhh.setText(String.valueOf(i.getMontant()));
//        personne.setText(i.getDecision().toString());
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userItemUpdateBtn.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ModifierCheque.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ModifierCheque modifierCheque = loader.getController();
            modifierCheque.initData(cheque); // Passer l'article sélectionné
            Stage stage = new Stage();
            stage.setScene(new Scene(root)); // Cette ligne cause l'erreur
            stage.show();
        });
    }

        // Ne rien faire dans l'initialisation par défaut
    public void initData(Cheque i) {
        this.cheque=i;
        ServiceCheque serviceCheque = new ServiceCheque();

        userItemEmail.setText(String.valueOf(i.getCin()));
        userItemName.setText(String.valueOf(i.getId()));
        userItemTel.setText(i.getNom_prenom());
        userItemRole.setText(i.getEmail());
        userItemStateText.setText(i.getDecision());
        date.setText(String.valueOf(i.getDate()));
        hhh.setText(String.valueOf(i.getTelephone()));
        personne.setText(i.getBeneficiaire().toString());

    }

    @FXML
    public void DeleteCheque(MouseEvent mouseEvent) {
        this.cheque=cheque;
        System.out.println(cheque);
        try {
            if (cheque != null) {
                ServiceCheque sv = new ServiceCheque();
                //int chequeId = cheque.getId(); // Supposons que la méthode getId() renvoie l'ID du chèque
                sv.supprimer(33); // Appel de la méthode supprimer avec l'ID du chèque
                Stage stage = (Stage) userItemStateBtn.getScene().getWindow(); // Correction pour obtenir la fenêtre parente
                stage.close();
            } else {
                // Affichez un message d'erreur ou faites une action appropriée si cheque est null
                System.err.println("Le Chèque est null. Impossible de le supprimer.");
            }
        } catch (SQLException e) {
            // Gérer l'exception SQLException ici
            e.printStackTrace();
            // Vous pouvez également afficher un message d'erreur à l'utilisateur ici
        }
    }


//    @FXML
//    public void UpdateCheque(MouseEvent mouseEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ModifierCheque.fxml"));
//        Parent editCheque = loader.load();
//
//        ModifierCheque modifierCheque = loader.getController();
//        modifierCheque.initData(cheque);
//
  //      Stage stage = new Stage();
    //    stage.initModality(Modality.WINDOW_MODAL);
     //   stage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
      //  stage.setScene(new Scene(editCheque));
       // stage.showAndWait();
    //}

    @FXML
    public void UpdateCheque(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ModifierCheque.fxml"));
        Parent editCheque = loader.load();

        ModifierCheque modifierCheque = loader.getController();
        modifierCheque.initData(cheque);

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) mouseEvent.getSource()).getScene().getWindow());
        stage.setScene(new Scene(editCheque));
        stage.showAndWait();
    }


}
