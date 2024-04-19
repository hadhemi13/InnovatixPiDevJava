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
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ModifierArticleController implements Initializable {

    public Button ModifierArtBtn;
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
//
//
//    private void initializeArticleFields() {
//        if (article != null) {
//            titreArt.setText(article.getTitre_art());
//            ContenuArt.setText(article.getContenu_art());
//            datePubArt.setValue(article.getDate_pub_art().toLocalDate());
//            addCategorie.setValue(article.getCategorie_art());
//            SpinnerValueFactory<Integer> valueFactory = dureeArt.getValueFactory();
//            if (valueFactory != null) {
//                valueFactory.setValue(article.getDuree_art());
//            }
//            Image image = new Image(article.getImage_art());
//            imageInput.setImage(image);
//        }
//    }


//    private void loadArticleData(int articleId) {
//        // Utilisez le service pour récupérer les détails de l'article à partir de la base de données
//        article = this.article;
//    }
//
//    // Mettre à jour la méthode initializeArticleFields() pour charger les données de l'article
//    private void initializeArticleFields(int articleId) {
//        loadArticleData(articleId); // Charger les données de l'article depuis la base de données
//        // Reste du code inchangé
//    }
//
//    // Mettre à jour la méthode ModifierArt(MouseEvent mouseEvent) pour effectuer la mise à jour de l'article
//    @FXML
//    public void ModifierArt(MouseEvent mouseEvent) {
//        try {
//            // Mettre à jour les valeurs de l'article avec les nouvelles valeurs saisies par l'utilisateur
//            article.setTitre_art(titreArt.getText());
//            article.setContenu_art(ContenuArt.getText());
//            article.setDate_pub_art(LocalDateTime.of(datePubArt.getValue(), LocalTime.MIDNIGHT));
//            article.setCategorie_art(addCategorie.getValue());
//            article.setDuree_art(dureeArt.getValue());
//            article.setImage_art(imageInput.getImage().getUrl());
//
//            // Appeler la méthode de service pour effectuer la mise à jour de l'article dans la base de données
//            serviceArticle.modifier(article);
//
//            // Fermer la fenêtre après la mise à jour
//            Stage stage = (Stage) titreArt.getScene().getWindow();
//            stage.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Gérer l'exception appropriée ici
//        }
//    }
//public void initData(Article article) {
//    this.article = article;
//    initializeArticleFields();
//}
//
//    private void initializeArticleFields() {
//        if (article != null) {
//            titreArt.setText(article.getTitre_art());
//            ContenuArt.setText(article.getContenu_art());
//            datePubArt.setValue(article.getDate_pub_art().toLocalDate());
//            addCategorie.setValue(article.getCategorie_art());
//            SpinnerValueFactory<Integer> valueFactory = dureeArt.getValueFactory();
//            if (valueFactory != null) {
//                valueFactory.setValue(article.getDuree_art());
//            }
//            Image image = new Image(article.getImage_art());
//            imageInput.setImage(image);
//        }
//    }

    public void initData(Article article) {
        this.article = article;
        initializeArticleFields();
    }

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
            // Assurez-vous que l'image de l'article n'est pas vide
            if (article.getImage_art() != null && !article.getImage_art().isEmpty()) {
                Image image = new Image(article.getImage_art());
                imageInput.setImage(image);
            }
        }
    }

    @FXML
    void ModifierArt(MouseEvent mouseEvent) {
        try {
            if (article != null) {
                article.setTitre_art(titreArt.getText());
                article.setContenu_art(ContenuArt.getText());
                article.setDate_pub_art(LocalDateTime.of(datePubArt.getValue(), LocalTime.MIDNIGHT));
                article.setCategorie_art(addCategorie.getValue());
                article.setDuree_art(dureeArt.getValue());
                // Assurez-vous que l'image de l'article n'est pas vide
                if (imageInput.getImage() != null) {
                    article.setImage_art(imageInput.getImage().getUrl());
                }
                // Appeler la méthode de service pour effectuer la mise à jour de l'article dans la base de données
                ServiceArticle serviceArticle = new ServiceArticle();
                serviceArticle.modifier(article);
//                ListArticleAdminController listArticleController = ListArticleAdminController.loadArticles();

                // Fermez la fenêtre de modification d'article
                Stage stage = (Stage) titreArt.getScene().getWindow();
                stage.close();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception appropriée ici
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
       // initializeArticleFields();
    }
//
//    public void ModifierArt(MouseEvent mouseEvent) throws SQLException {
//        ServiceArticle serviceArticle = new ServiceArticle() ;
//        if (article != null) {
//            article.setTitre_art(titreArt.getText());
//            article.setContenu_art(ContenuArt.getText());
//            article.setDate_pub_art(LocalDateTime.of(datePubArt.getValue(), LocalDate.now().atStartOfDay().toLocalTime()));
//            article.setCategorie_art(addCategorie.getValue());
//            article.setDuree_art(dureeArt.getValue());
//            article.setImage_art(imageInput.getImage().getUrl());
//            serviceArticle.modifier(article);
//        }
//    }

}
