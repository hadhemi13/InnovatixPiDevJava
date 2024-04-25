package controllers.Virement;

import Entities.Compte;
import Entities.Virement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import services.ServiceCompte;
import services.ServiceVirement;

public class VirementItemsAdmin {

    @FXML
    private Label ApprouveBtnV;

    @FXML
    private ImageView Approuver;

    @FXML
    private Text Cin;

    @FXML
    private Text Montant;

    @FXML
    private Text NometPrenom;

    @FXML
    private Text NumBenif;

    @FXML
    private Text NumTel;

    @FXML
    private Label RefusBtnV;

    @FXML
    private Text Rib;

    @FXML
    private Text Transferez;

    @FXML
    private Text TypeVir;

    @FXML
    private ImageView photoCin;
    public void initData(Virement i){
        ServiceVirement serviceVirement = new ServiceVirement();

        Rib.setText(String.valueOf(i.getRib()));
        Cin.setText(String.valueOf(i.getCin()));
        //photoCin.setImage(i.getPhoto_cin_v());
        NometPrenom.setText(i.getNomet_prenom());
        NumTel.setText(String.valueOf(i.getPhone_number()));
        TypeVir.setText(i.getType_virement());
        Transferez.setText(i.getTransferez_a());
        NumBenif.setText(String.valueOf(i.getNum_beneficiare()));
        Montant.setText(i.getMontant());}

    @FXML
    void approuverVir(MouseEvent event) {

    }

    @FXML
    void refuserVir(MouseEvent event) {

    }

}
