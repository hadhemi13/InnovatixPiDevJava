package controllers;

import Entities.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
    private Spinner<Integer> dureeArt;

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
    private ImageView imageInput;
    @FXML
    private TextField titreInput;

    @FXML
    private Text titreInputError;

    @FXML
    private HBox titreInputErrorHbox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageInputErrorHbox.setVisible(false);
        categorieErrorHbox.setVisible(false);
        ContenuHboxErreur.setVisible(false);
        pieceJInputErrorHbox.setVisible(false);
        titreInputErrorHbox.setVisible(false);
        dureeInputErrorHbox.setVisible(false);
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1); // Valeur initiale, valeur maximale, valeur minimale
        dureeArt.setValueFactory(valueFactory);

        // Autoriser la saisie directe des valeurs dans le Spinner
        dureeArt.setEditable(true);

        // Limiter la saisie à des nombres entiers
        dureeArt.getEditor().setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().matches("\\d*")) {
                return c;
            } else {
                return null;
            }
        }));
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
        String nom = "hadhemi";
        String adresse ="mahmoud";
        ServiceArticle sa = new ServiceArticle();
        if (ContenuArt.getText().isEmpty()) {

            ContenuHboxErreur.setVisible(true);
            return;
        }
        if (categoriechoice.getSelectionModel().isEmpty()) {
            categorieErrorHbox.setVisible(true);
            return;
        }

        if (dureeArt.getValue() == null) {
            dureeInputErrorHbox.setVisible(true);
            return;
        }

        if (titreInput.getText().isEmpty()) {
            titreInputErrorHbox.setVisible(true);
            return;
        }
        LocalDate selectedDate = datePubArt.getValue();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.of(selectedDate, currentTime);
        String image = "admin";
        String pieceJArt= "admin";
        String selectedCategory = categoriechoice.getSelectionModel().getSelectedItem();
        Article article = new Article(nom, adresse,dateTime,(Integer) dureeArt.getValue(),selectedCategory,titreInput.getText(),ContenuArt.getText(),pieceJArt,image);
        sa.ajouter(article);


    }




}
