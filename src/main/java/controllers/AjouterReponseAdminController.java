package controllers;

import Entities.Reclamation;
import Entities.Reponse;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import services.ServiceReponse;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class AjouterReponseAdminController implements Initializable {

    @FXML
    private Text addpieceJBtn;

    @FXML
    private TextArea contenuInput;

    @FXML
    private Text contenuInputError;

    @FXML
    private HBox contenuInputErrorBox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text pieceInputError;

    @FXML
    private HBox pieceInputErrorBox;

    @FXML
    private Button reponseBtn;
    private Reclamation reclamation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contenuInputErrorBox.setVisible(false);
        pieceInputErrorBox.setVisible(false);


    }
    @FXML
    void ajouter_image(MouseEvent event) {

    }


    @FXML
    void addReponse(MouseEvent event) {
        int newId = ReclamationItemAdminController.idAn;
        ServiceReponse sr = new ServiceReponse();
        if (contenuInput.getText().isEmpty()) {
            contenuInputErrorBox.setVisible(true);
            return;
        }
        if (addpieceJBtn.getText().isEmpty()) {
            pieceInputErrorBox.setVisible(true);
            return;
        }
        LocalDateTime dateTime = LocalDateTime.now();
        String adresse = "mahmoud";
        String pieceJointe = "admin";
        Reponse reponse = new Reponse(newId,adresse,dateTime,contenuInput.getText(),pieceJointe);
        try {
            sr.ajouter(reponse);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void initDataRec(Reclamation reclamation) {
        this.reclamation = reclamation;
        // Autres initialisations si nécessaire
    }

}