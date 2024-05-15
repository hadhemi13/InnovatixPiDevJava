package controllers.Virement;

import Entities.User;
import Entities.Virement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import services.ServiceVirement;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class VirementItemsAdmin  implements  Initializable {
    public static final String ACCOUNT_SID = System.getenv("AC3e7c4fb14b4c902709bc986f10f29f5c");
    public static final String AUTH_TOKEN = System.getenv("a734dc1ae9533b42e8031e036cc45278");

    @FXML
    private Label ApprouveBtnV;

    @FXML
    private ImageView Approuver;

    @FXML
    private ImageView Refuser;


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
    private Virement virement;

    @FXML
    private ImageView photoCin;
    @FXML
    private HBox Hboxvirement;



    public void initData(Virement i){
        this.virement=i ;
        ServiceVirement serviceVirement = new ServiceVirement();
        System.out.println(i);
        Rib.setText(String.valueOf(i.getRib()));
        Cin.setText(String.valueOf(i.getCin()));
        //photoCin.setImage(i.getPhoto_cin_v());
        NometPrenom.setText(i.getNomet_prenom());
        NumTel.setText(String.valueOf(i.getPhone_number()));
        TypeVir.setText(i.getType_virement());
        Transferez.setText(i.getTransferez_a());
        NumBenif.setText(String.valueOf(i.getNum_beneficiare()));
        Montant.setText(i.getMontant());

        if ("Approuvé".equals(virement.getDecision_v()) || "Rejeté".equals(virement.getDecision_v())) {
            disableDecisionButtons();
        }
    }
//    @FXML
//    private void approuverVir(MouseEvent event) {
//        if (showConfirmationDialog("Approve", "Voullez vous Approuvez ce virement ?")) {
//            updateChequeDecision("Approuvé");
//        }
//    }


    @FXML
    private void approuverVir(MouseEvent event) {
        if (showConfirmationDialog("Approve", "Voullez vous Approuvez ce virement ?")) {
            updateVirementDecision("Approuvé");
        }
    }

    @FXML
    private void refuserVir(MouseEvent event) {
        if (showConfirmationDialog("Reject", "Voulez vous réfuser ce virement?")) {
            updateVirementDecision("Rejeté");
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
    private void updateVirementDecision(String newDecisionV) {
        try {
            virement.setDecision_v(newDecisionV);
            new ServiceVirement().modifier(virement);
            showAlert(Alert.AlertType.INFORMATION, "Success", "virement decision updated to " + newDecisionV + ".");
            disableDecisionButtons();
            applyRejectedStyle();

            if ("Approuvé".equals(newDecisionV)) {
                sendSMS(virement.getPhone_number());
            }

            ListVirementAdmin.getInstance().refreshVirementList();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to update virement decision.");
            e.printStackTrace();
        }
    }
    private void applyRejectedStyle() {
        if ("Rejeté".equals(virement.getDecision_v())) {
            Hboxvirement.getStyleClass().clear();
            Hboxvirement.getStyleClass().add("virement-rejected");
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void disableDecisionButtons() {
        ApprouveBtnV.setDisable(true);
        RefusBtnV.setDisable(true);
        Approuver.setOpacity(0.4);  // Optionally set opacity to visually indicate the button is disabled
        Refuser.setOpacity(0.4);    // Optionally set opacity to visually indicate the button is disabled
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void sendSMS(String phoneNumber) {
        try {
            Twilio.init("AC3e7c4fb14b4c902709bc986f10f29f5c", "a734dc1ae9533b42e8031e036cc45278");

            Message message = Message.creator(
                            new PhoneNumber("+21628160626"),
                            new PhoneNumber("+13156586121"),
                            "Bonjour " + ", " +
                                    "Nous sommes heureux de vous informer que votre demande de virement " +
                                    "a été approuvée avec succès. " +
                                    "Cordialement, [ EFB]")
                    .create();

            System.out.println("Message SID: " + message.getSid());
        } catch (Exception e) {
            e.printStackTrace();
            // Gérez l'erreur ici, par exemple affichez un message d'erreur ou enregistrez les détails de l'erreur dans un fichier journal.
        }
    }


}