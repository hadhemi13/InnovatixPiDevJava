package controllers.Cheque;

import Entities.Cheque;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ChequeItems implements Initializable {

    @FXML
    private Text userItemEmail;

    @FXML
    private Text userItemName;

    @FXML
    private Text userItemRole;

    @FXML
    private Label userItemStateBtn;

    @FXML
    private ImageView userItemStateBtnImg;

    @FXML
    private Label userItemStateLabel;

    @FXML
    private Text userItemStateText;

    @FXML
    private Text userItemTel;

    @FXML
    private Label userItemUpdateBtn;

    @FXML
    private ImageView userItemUpdateBtnImg;
    @FXML
    private Text date;

    @FXML
    private Text hhh;

    @FXML
    private Text personne;


    public void initData(Cheque i) {
        userItemEmail.setText(i.getEmail().toString());
        userItemName.setText(i.getNom_prenom());
        userItemTel.setText(String.valueOf(i.getTelephone()));
        userItemRole.setText(i.getEmail());
        userItemStateText.setText(i.getBeneficiaire());
        date.setText(i.getPhoto_cin());
        hhh.setText(String.valueOf(i.getMontant()));
        personne.setText(i.getDecision().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ne rien faire dans l'initialisation par défaut


    }


}
