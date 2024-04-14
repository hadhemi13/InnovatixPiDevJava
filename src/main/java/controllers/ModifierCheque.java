package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ModifierCheque {

    @FXML
    private TextField Cin;

    @FXML
    private TextField Email;

    @FXML
    private Text EmailInputError;

    @FXML
    private HBox EmailInputErrorHbox;

    @FXML
    private TextField NometPrenom;

    @FXML
    private Text NometPrenomInputError;

    @FXML
    private HBox NometPrenomInputErrorHbox;

    @FXML
    private TextField RIB;

    @FXML
    private ComboBox<?> beneficiaire;

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
    private DatePicker date;

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
    private TextField tel;

    @FXML
    private Text telInputError;

    @FXML
    private HBox telInputErrorHbox;

    @FXML
    private Button update_chequetBtn;

    @FXML
    void ajouter_image(MouseEvent event) {

    }

    @FXML
    void updateCheque(MouseEvent event) {

    }

    @FXML
    void updatecheque(ActionEvent event) {

    }

}
