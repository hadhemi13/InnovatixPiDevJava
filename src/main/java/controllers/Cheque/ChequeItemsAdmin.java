package controllers.Cheque;

import Entities.Cheque;
import Entities.Compte;
import controllers.Compte.ApprouverCompte;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;
import services.ServiceCompte;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChequeItemsAdmin  implements Initializable {

    @FXML
    private Label ApprouveBtnC;

    @FXML
    private ImageView Approuver;

    @FXML
    private Text Cin;

    @FXML
    private Text Email;

    @FXML
    private Text Montant;

    @FXML
    private Text NometPrenom;

    @FXML
    private Label RefusBtnC;

    @FXML
    private Text Rib;

    @FXML
    private Text benificaire;

    @FXML
    private ImageView photoCin;

    @FXML
    private Text tel;

    public void initData(Cheque i){
        ServiceCheque serviceCheque = new ServiceCheque();

        Rib.setText(String.valueOf(i.getId()));
        Cin.setText(String.valueOf(i.getCin()));
       // Image image = new Image("file:" + i.getPhoto_cin());
        //ImageView imageView = new ImageView(image);
       // photoCin.setGraphic(imageView);
        NometPrenom.setText(i.getNom_prenom());
        Email.setText(i.getEmail());
        benificaire.setText(i.getBeneficiaire());
        tel.setText(String.valueOf(i.getTelephone()));
        Montant.setText(String.valueOf(i.getMontant()));
//        ApprouveBtnC.setOnMouseClicked(mouseEvent -> {
//            Stage primaryStage = new Stage();
//            try {
//                Cheque compte = serviceCheque.afficher().get(Integer.parseInt(ApprouveBtnC.getId()));
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/HistoriqueChequeAdmin.fxml")) ;
//                Parent parent = loader.load();
//                Scene scene = new Scene(parent);
//                primaryStage.setTitle("E-Flex Bank");
//                primaryStage.setScene(scene);
//                primaryStage.show();
//                ApprouverXheque app = loader.getController();
//                ApprouverXheque.initData(compte);
//            }catch (SQLException | IOException exception)
//            {
//                throw new RuntimeException(exception);
//            }
//
//        });
//        RefusBtn.setOnMouseClicked(mouseEvent -> {
//            try {
//                serviceCompte.supprimer(Integer.parseInt(RefusBtn.getId()));
//
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        });


        }


    @FXML
    void ApprouverXheque(MouseEvent event) {

    }

    @FXML
    void RefuserCheque(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
