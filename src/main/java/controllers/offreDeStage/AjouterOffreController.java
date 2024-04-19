package controllers.offreDeStage;

import Entities.OffreDeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import services.ServiceOffreDeStage;

import java.sql.SQLException;
import java.util.Optional;

public class AjouterOffreController {

    @FXML
    private DatePicker date;

    @FXML
    private TextField titreInput;

    @FXML
    private TextField motsCles;

    @FXML
    private TextField typeOffre;

    @FXML
    private Button upload;

    @FXML
    private TextField description;

    @FXML
    private TextField language;

    @FXML
    private TextField experience;

    @FXML
    private TextField niveau;

    @FXML
    private TextField pfeBook;

    @FXML
    private TextField domaineInput;

    @FXML
    private TextField Exigence;

    @FXML
    private TextField postePropose;

    @FXML
    private AnchorPane content_area;

    @FXML
    private Button ajouterOffre;
    @FXML
    void ajouterOffre(ActionEvent event) throws SQLException {
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
        OffreDeStage stage = new OffreDeStage(titreInput.getText(),domaineInput.getText(),typeOffre.getText(),Integer.parseInt(postePropose.getText()),experience.getText(),description.getText(),Exigence.getText());
        serviceOffreDeStage.ajouter(stage);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information manquante");
        alert.setHeaderText(null);
        alert.setContentText("Toutes les champs sont vides.");
        Optional<ButtonType> option = alert.showAndWait();
    }

    @FXML
    void upload(ActionEvent event) {

    }

}
