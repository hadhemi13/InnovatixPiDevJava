package controllers.demandeStage;

import Entities.DemandeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class DemandeStageItemController {


    @FXML
    private Text date;

    @FXML
    private Text numero;

    @FXML
    private Label Approuver;

    @FXML
    private Text domaine;

    @FXML
    private Label refuser;

    @FXML
    private Text Nom;

    @FXML
    private Text prenom;

    @FXML
    private Text email;

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    public void initData(DemandeStage offre) {

    }
}
