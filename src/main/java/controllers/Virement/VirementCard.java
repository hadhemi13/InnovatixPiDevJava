package controllers.Virement;

import Entities.Cheque;
import Entities.Virement;
import controllers.Cheque.ModifierCheque;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VirementCard  implements Initializable {

    @FXML
    private Text DesicionItems;

    @FXML
    private Text MontantItems;

    @FXML
    private Text NomPrenomItems;

    @FXML
    private Text NumBenefItems;

    @FXML
    private Text RibItems;

    @FXML
    private Text TelItems;

    @FXML
    private Text TypeItems;

    @FXML
    private Text benfItems;

    @FXML
    private Text cinItems;

    @FXML
    private HBox editItemsBtn;
    @FXML
    private Text productName;

    @FXML
    private HBox supprItems;
    private Virement virement;


    // Ne rien faire dans l'initialisation par défaut

    public void initData(Virement virement) {
        this.virement = virement;
        RibItems.setText(String.valueOf(virement.getRib()));
        cinItems.setText(String.valueOf(virement.getCin()));
        NomPrenomItems.setText(virement.getNomet_prenom());
        TelItems.setText(virement.getPhone_number());
        benfItems.setText(virement.getTransferez_a());
        NumBenefItems.setText(String.valueOf(virement.getNum_beneficiare()));
        MontantItems.setText(String.valueOf(virement.getMontant()));
        DesicionItems.setText(virement.getDecision_v().toString());
    }
    @FXML
    void DeleteVirement(MouseEvent event) {

    }

    @FXML
    void updateVirement(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
