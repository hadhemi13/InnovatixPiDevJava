package controllers.offreDeStage;

import Entities.OffreDeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import services.ServiceOffreDeStage;

import java.net.URL;
import java.util.ResourceBundle;

public class OffreStageItem implements Initializable {

    @FXML
    private Text motsCles;

    @FXML
    private Text titre;

    @FXML
    private Text domaine;

    @FXML
    private Text description;

    @FXML
    private Text language;

    @FXML
    private Text type;

    @FXML
    private Text experience;

    @FXML
    private Text niveau;

    @FXML
    private Button ListeDesDemandes;

    @FXML
    private Button OffreUpdate;

    @FXML
    private Text exigence;

    @FXML
    private Button OffreDetails;

    @FXML
    private Text poste;

    @FXML
    void ListeDesDemandes(ActionEvent event) {

    }

    @FXML
    void OffreUpdate(ActionEvent event) {

    }

    @FXML
    void OffreDetails(ActionEvent event) {

    }

    public void initData(OffreDeStage i) {
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
//        Rectangle clip = new Rectangle();
//        clip.setArcWidth(100);
//        clip.setArcHeight(100);
        motsCles.setText(i.getMotsCles().toString());
        titre.setText(i.getTitle());
        domaine.setText(i.getDomaine());
        description.setText(i.getDescription());
        language.setText(i.getLanguage().toString());
        type.setText(i.getTypeOffre());
        experience.setText(i.getExperience());
        niveau.setText(i.getNiveau().toString());
        exigence.setText(i.getExigenceOffre());
        poste.setText(String.valueOf(i.getPostePropose()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ne rien faire dans l'initialisation par défaut


    }
}
