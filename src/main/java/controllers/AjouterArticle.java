package controllers;

import Entities.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import services.ServiceArticle;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AjouterArticle implements Initializable {
    @FXML
    private Text CatArtInputError;

    @FXML
    private TextArea ContenuArt;

    @FXML
    private Text ContenuArtInputError;

    @FXML
    private HBox ContenuHboxErreur;

    @FXML
    private Button addArticleBtn;

    @FXML
    private Text addpieceJBtn;

    @FXML
    private HBox categorieErrorHbox;

    @FXML
    private ComboBox<String> categoriechoice;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private DatePicker datePubArt;

    @FXML
    private Spinner<?> dureeArt;

    @FXML
    private Text dureeArtInputError;

    @FXML
    private Text imageInputError;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private ImageView pieceJArtInput;

    @FXML
    private HBox pieceJInputErrorHbox;

    @FXML
    private Text piecejointeInputError;

    @FXML
    private HBox dureeInputErrorHbox;

    @FXML
    private TextField titreInput;

    @FXML
    private Text titreInputError;

    @FXML
    private HBox titreInputErrorHbox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ContenuHboxErreur.setVisible(false);
        categorieErrorHbox.setVisible(false);
        imageInputErrorHbox.setVisible(false);
        pieceJInputErrorHbox.setVisible(false);
        dureeInputErrorHbox.setVisible(false);
        titreInputErrorHbox.setVisible(false);
        ObservableList<String> categories = FXCollections.observableArrayList(
                "Développement durable",
                "Finance",
                "Banque",
                "Crédits"
        );
        categoriechoice.setItems(categories);


    }

    @FXML
    void ajouter_article(MouseEvent event) throws SQLException {
        ServiceArticle sa = new ServiceArticle();
        if (ContenuArt.getText().isEmpty()) {

            ContenuHboxErreur.setVisible(true);
        }
        if (categoriechoice.getSelectionModel().isEmpty()) {
            categorieErrorHbox.setVisible(true);
        }

        if (dureeArt.getValue() == null) {
            dureeInputErrorHbox.setVisible(true);
        }

        if (titreInput.getText().isEmpty()) {
            titreInputErrorHbox.setVisible(true);
        }
        LocalDate selectedDate = datePubArt.getValue();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.of(selectedDate, currentTime);

        String selectedCategory = categoriechoice.getSelectionModel().getSelectedItem();
        Article article = new Article(dateTime,(Integer) dureeArt.getValue(),selectedCategory,titreInput.getText(),ContenuArt.getText());
        sa.ajouter(article);


    }




}
