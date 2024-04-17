package controllers.Virement;

import Entities.Cheque;
import Entities.Virement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import services.ServiceCheque;
import services.ServiceVirement;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class AjouterVirementCard {

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
    private Button add_new_VirementtBtn;

    @FXML
    private TextField benef;

    @FXML
    private Text benefInputError;

    @FXML
    private HBox benefInputErrorHbox;


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
    private Text typeInputError;
    @FXML
    private HBox typeInputErrorHbox;

    @FXML
    private Text transferezInputError;

    @FXML
    private HBox transferezInputErrorHbox;

    @FXML
    private ComboBox<String> type;

    @FXML
    void ajouterVirement(MouseEvent event)  throws SQLException {
        ServiceVirement sv = new ServiceVirement();
        if (Cin.getText().isEmpty()) {
            cinInputErrorHbox.setVisible(true);
            if (NometPrenom.getText().isEmpty()) {
                NometPrenomInputErrorHbox.setVisible(true);
                if (Num.getText().isEmpty()) {
                    NumInputErrorHbox.setVisible(true);
                    if (type.getSelectionModel().isEmpty()) {
                        typeInputErrorHbox.setVisible(true);
                        if (montant.getText().isEmpty()) {
                            montantInputErrorHbox.setVisible(true);
                            if (transferez.getText().isEmpty()) {
                                transferezInputErrorHbox.setVisible(true);
                                if(benef.getText().isEmpty()){
                                    benefInputErrorHbox.setVisible(true);
                            }
                        }
                    }
                } else {
                    if (type.getSelectionModel().isEmpty()) {
                        typeInputErrorHbox.setVisible(true);
                        if (NometPrenom.getText().isEmpty()) {
                            NometPrenomInputErrorHbox.setVisible(true);
                            if (montant.getText().isEmpty()) {
                                montantInputErrorHbox.setVisible(true);
                                if (Num.getText().isEmpty()) {
                                    NumInputErrorHbox.setVisible(true);
                                }
                            }
                        }

                    } else {
                        if (transferez.getText().isEmpty()) {
                            transferezInputErrorHbox.setVisible(true);
                            if (montant.getText().isEmpty()) {
                                montantInputErrorHbox.setVisible(true);
                                if (Num.getText().isEmpty()) {
                                    NumInputErrorHbox.setVisible(true);
                                }
                            } else {
                                if (montant.getText().isEmpty()) {
                                    montantInputErrorHbox.setVisible(true);
                                    if (Num.getText().isEmpty()) {
                                        NumInputErrorHbox.setVisible(true);
                                    } else {
                                        if (Num.getText().isEmpty()) {
                                            NumInputErrorHbox.setVisible(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }else {
            if (!Cin.getText().isEmpty()) {
                cinInputErrorHbox.setVisible(false);
                if (!NometPrenom.getText().isEmpty()) {
                    NometPrenomInputErrorHbox.setVisible(false);
                    if (!type.getSelectionModel().isEmpty()) {
                        typeInputErrorHbox.setVisible(false);
                        if (!transferez.getText().isEmpty()) {
                            transferezInputErrorHbox.setVisible(false);
                            if (!montant.getText().isEmpty()) {
                                montantInputErrorHbox.setVisible(false);
                                if (!Num.getText().isEmpty()) {
                                    NumInputErrorHbox.setVisible(false);

                                }
                            }
                        }
                    }
                }
            }



        }
        // Select for combo
        SingleSelectionModel<String> selectionModel = type.getSelectionModel();

        String selectedtype = selectionModel.getSelectedItem();
        // Create a new instance of cheque from View
        String image="admin";
        Integer Rib = 345678644;
        String telText = Num.getText();
        Integer aa = Integer.parseInt(telText);
        String cin = Cin.getText();
        Integer Cin = Integer.parseInt(cin);
        String Nom = NometPrenom.getText();
        String typee = type.getValue();
            Double montantn = Double.valueOf(montant.getText());
        String email = transferez.getText();
        String decisionV = "Encours";
        Virement virement = new Virement(typee,montant,aa,transferez,Cin,Nom,image,decisionV);



// Convertir le texte en un entier
        // int telInteger = Integer.parseInt(telText);

            ServiceVirement ServiceVirement = new ServiceVirement();
        ServiceVirement.ajouter(virement);

    }

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageInputErrorHbox.setVisible(false);
        NumInputErrorHbox.setVisible(false);
        montantInputErrorHbox.setVisible(false);
        transferezInputErrorHbox.setVisible(false);
        cinInputErrorHbox.setVisible(false);
        NometPrenomInputErrorHbox.setVisible(false);
        typeInputErrorHbox.setVisible(false);
        benefInputErrorHbox.setVisible(false);

        ObservableList<String> types = FXCollections.observableArrayList(
                "Personne",
                "VEcoresponsabilité"
        );

        type.setItems(types);


    }

    @FXML
    void ajouter_imageV(MouseEvent event) {

    }

    public void UpdateVirement(MouseEvent mouseEvent) {
    }
}
