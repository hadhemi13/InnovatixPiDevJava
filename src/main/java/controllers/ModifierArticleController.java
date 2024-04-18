package controllers;

import Entities.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceArticle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ModifierArticleController implements Initializable {

    @FXML
    private TextField titreArt;

    @FXML
    private TextArea ContenuArt;

    @FXML
    private DatePicker datePubArt;

    @FXML
    private ComboBox<String> addCategorie;

    @FXML
    private Spinner<Integer> dureeArt;

    @FXML
    private ImageView imageInput;

    private Article article;

    private ServiceArticle serviceArticle;

    private void initializeArticleFields() {
        if (article != null) {
            titreArt.setText(article.getTitre_art());
            ContenuArt.setText(article.getContenu_art());
            datePubArt.setValue(article.getDate_pub_art().toLocalDate());
            addCategorie.setValue(article.getCategorie_art());
            SpinnerValueFactory<Integer> valueFactory = dureeArt.getValueFactory();
            if (valueFactory != null) {
                valueFactory.setValue(article.getDuree_art());
            }
            Image image = new Image(article.getImage_art());
            imageInput.setImage(image);
        }
    }

    @FXML
    void ajouter_image(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);
        }
    }


    @FXML
    void returnbackarticle(MouseEvent event) {
        Stage stage = (Stage) titreArt.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialiser la liste des catégories
        ObservableList<String> categories = FXCollections.observableArrayList(
                "Développement durable",
                "Finance",
                "Banque",
                "Crédits"
        );
        addCategorie.setItems(categories);

        // Initialiser le Spinner de durée
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0); // Valeur initiale, valeur maximale, valeur minimale
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

        // Initialiser les champs de l'article
        initializeArticleFields();
    }

    public void ModifierArt(MouseEvent mouseEvent) throws SQLException {
        ServiceArticle serviceArticle = new ServiceArticle() ;
        if (article != null) {
            article.setTitre_art(titreArt.getText());
            article.setContenu_art(ContenuArt.getText());
            article.setDate_pub_art(LocalDateTime.of(datePubArt.getValue(), LocalDate.now().atStartOfDay().toLocalTime()));
            article.setCategorie_art(addCategorie.getValue());
            article.setDuree_art(dureeArt.getValue());
            article.setImage_art(imageInput.getImage().getUrl());
            serviceArticle.modifier(article);
        }
    }
}
