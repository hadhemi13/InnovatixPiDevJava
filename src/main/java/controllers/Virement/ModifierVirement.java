package controllers.Virement;

import Entities.Virement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ModifierVirement {

    @FXML
    private TextField Cin;

    @FXML
    private TextField NometPrenom;

    @FXML
    private Text NometPrenomInputError;

    @FXML
    private HBox NometPrenomInputErrorHbox;

    @FXML
    private TextField Num;

    @FXML
    private Text NumInputError;

    @FXML
    private HBox NumInputErrorHbox;

    @FXML
    private TextField RIB;

    @FXML
    private TextField benef;

    @FXML
    private Text benefInputError;

    @FXML
    private HBox benefInputErrorHbox;

    @FXML
    private Text beneficiaireInputError;

    @FXML
    private HBox beneficiaireInputErrorHbox;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private Text cinInputError;

    @FXML
    private HBox cinInputErrorHbox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text imageInputError;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private TextField montant;

    @FXML
    private Text montantInputError;

    @FXML
    private HBox montantInputErrorHbox;

    @FXML
    private TextField transferez;

    @FXML
    private Text transferezInputError;

    @FXML
    private HBox transferezInputErrorHbox;

    @FXML
    private ComboBox<String> type;

    @FXML
    private Button update_VirementtBtn;

    public void initData(Virement virement){
        Cin.setText(String.valueOf(virement.getCin()));
        NometPrenom.setText(virement.getNomet_prenom());

    }

    @FXML
    void ajouter_imageV(MouseEvent event) {

    }

    @FXML
    void modifiervirement(MouseEvent event) {

    }

    @FXML
    void updatevirement(ActionEvent event) {

    }

}
