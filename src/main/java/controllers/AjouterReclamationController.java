package controllers;

import Entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.ServiceReclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AjouterReclamationController implements Initializable {

    @FXML
    private Text ContenuArtInputError;

    @FXML
    private VBox content_area;

    @FXML
    private HBox ObjetHboxErreur;

    @FXML
    private Button addRecBtn;

    @FXML
    private Text addpieceJBtn;

    @FXML
    private HBox contenuInputErrorHbox;

    @FXML
    private TextArea contenuRec;

    @FXML
    private Text depInputError;

    @FXML
    private HBox depRecErrorHbox;

    @FXML
    private ComboBox<String> departementRec;

    @FXML
    private TextField objetRec;

    @FXML
    private ImageView pieceJArtInput;

    @FXML
    private HBox pieceJInputErrorHbox;

    @FXML
    private Text piecejointeInputError;

    @FXML
    private Text titreInputError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        depRecErrorHbox.setVisible(false);
        contenuInputErrorHbox.setVisible(false);
        ObjetHboxErreur.setVisible(false);
        pieceJInputErrorHbox.setVisible(false);
        ObservableList<String> departement = FXCollections.observableArrayList(
                "RH",
                "Finance",
                "Crédits",
                "Autres"

        );
        departementRec.setItems(departement);


    }
    @FXML


    void ajouter_reclamation(MouseEvent event) throws SQLException, IOException {
        String nom = "hadhemi";
        String adresse = "mahmoud";

        ServiceReclamation sr = new ServiceReclamation();

        if (contenuRec.getText().isEmpty()) {
            contenuInputErrorHbox.setVisible(true);
            return;
        }

        if (departementRec.getSelectionModel().isEmpty()) {
            depRecErrorHbox.setVisible(true);
            return;
        }

        if (objetRec.getText().isEmpty()) {
            ObjetHboxErreur.setVisible(true);
            return;
        }
        LocalDateTime dateTime = LocalDateTime.now();
        String pieceJointe = "admin";

        String selectedDepartment = departementRec.getSelectionModel().getSelectedItem();

        String statut ="En cours de traitement";
        Reclamation reclamation = new Reclamation( objetRec.getText(), contenuRec.getText(), adresse,nom, selectedDepartment,statut, pieceJointe, dateTime);
                sr.ajouter(reclamation);
        if (sr.ajouter(reclamation)) {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listeRecClient.fxml"));
            Pane listArtAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArtAdminPane);

        }
    }

}
