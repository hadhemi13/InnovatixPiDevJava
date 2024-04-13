package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AjouterArticle {

    @FXML
    private Text CatArtInputError;

    @FXML
    private TextArea ContenuArt;

    @FXML
    private Text ContenuArtInputError;

    @FXML
    private ComboBox<?> addCategorie;

    @FXML
    private Button addProductBtn;

    @FXML
    private Text addpieceJBtn;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private DatePicker datePubArt;

    @FXML
    private HBox descriptionInputErrorHbox;

    @FXML
    private Spinner<?> dureeArt;

    @FXML
    private Text dureeArtInputError;

    @FXML
    private HBox etatInputErrorHbox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text imageInputError;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private Text objectifInputError;

    @FXML
    private HBox objectifInputErrorHbox;

    @FXML
    private ImageView pieceJArtInput;

    @FXML
    private HBox pointsInputErrorHbox;

    @FXML
    private Text titreInputError;

    @FXML
    private HBox titreInputErrorHbox;

    @FXML
    private TextField titre_don;

    @FXML
    void ajouter_article(MouseEvent event) {

    }

    @FXML
    void ajouter_image(MouseEvent event) {

    }

}
