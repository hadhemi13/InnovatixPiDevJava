package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AjouterReponseAdminController implements Initializable {

    @FXML
    private Text addpieceJBtn;

    @FXML
    private TextArea contenu;

    @FXML
    private Text contenuInputError;

    @FXML
    private HBox contenuInputErrorBox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Button reponseBtn;

    @FXML
    void addHistory(MouseEvent event) {

    }

    @FXML
    void ajouter_image(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
