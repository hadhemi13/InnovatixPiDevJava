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
import java.util.ArrayList;
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

//    public void initData(OffreDeStage i) {
//        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
//
//        // Vérification de null pour les mots clés
//        String motsClesText = i.getMotsCles() != null ? i.getMotsCles().toString() : "N/A";
//        motsCles.setText(motsClesText);
//
//        titre.setText(i.getTitle());
//        domaine.setText(i.getDomaine());
//        description.setText(i.getDescription());
//
//        // Vérification de null pour le langage
//        String langageText = i.getLanguage() != null ? i.getLanguage().toString() : "N/A";
//        language.setText(langageText);
//
//        type.setText(i.getTypeOffre());
//        experience.setText(i.getExperience());
//
//        // Vérification de null pour le niveau
//        String niveauText = i.getNiveau() != null ? i.getNiveau().toString() : "N/A";
//        niveau.setText(niveauText);
//
//        exigence.setText(i.getExigenceOffre());
//        poste.setText(String.valueOf(i.getPostePropose()));
//    }
    public void initE(){
        titre.setText("Titre");
        description.setText("Description");

    }
public void initData(OffreDeStage i) {

    ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();


    // Vérification de null pour les mots clés
    String motsClesText = i.getMotsCles() != null ? i.getMotsCles().toString() : "N/A";
    motsCles.setText(motsClesText);

    // Limiter la longueur du titre à 30 caractères
    String titreText = i.getTitle() != null && i.getTitle().length() > 30 ? i.getTitle().substring(0, 30) : i.getTitle();
    titre.setText(titreText);

    // Limiter la longueur du domaine à 30 caractères
    String domaineText = i.getDomaine() != null && i.getDomaine().length() > 30 ? i.getDomaine().substring(0, 30) : i.getDomaine();
    domaine.setText(domaineText);

    // Limiter la longueur de la description à 30 caractères
    String descriptionText = i.getDescription() != null && i.getDescription().length() > 30 ? i.getDescription().substring(0, 30) : i.getDescription();
    description.setText(descriptionText);

    // Vérification de null pour le langage
    String langageText = i.getLanguage() != null ? i.getLanguage().toString() : "N/A";
    language.setText(langageText);

    // Limiter la longueur du type à 30 caractères
    String typeText = i.getTypeOffre() != null && i.getTypeOffre().length() > 30 ? i.getTypeOffre().substring(0, 30) : i.getTypeOffre();
    type.setText(typeText);

    // Limiter la longueur de l'expérience à 30 caractères
    String experienceText = i.getExperience() != null && i.getExperience().length() > 30 ? i.getExperience().substring(0, 30) : i.getExperience();
    experience.setText(experienceText);

    // Vérification de null pour le niveau
    String niveauText = i.getNiveau() != null ? i.getNiveau().toString() : "N/A";
    niveau.setText(niveauText);

    // Limiter la longueur de l'exigence à 30 caractères
    String exigenceText = i.getExigenceOffre() != null && i.getExigenceOffre().length() > 30 ? i.getExigenceOffre().substring(0, 30) : i.getExigenceOffre();
    exigence.setText(exigenceText);

    // Limiter la longueur du poste à 30 caractères
    String posteText = String.valueOf(i.getPostePropose());
// Limiter la longueur du poste à 30 caractères si la longueur est supérieure à 30
    posteText = posteText.length() > 30 ? posteText.substring(0, 30) : posteText;
    poste.setText(posteText);
}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ne rien faire dans l'initialisation par défaut


    }
}
