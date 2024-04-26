package controllers;

import Entities.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceArticle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AjouterArticleController implements Initializable {
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
    private ImageView imageInput;

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
    private TextField titreInput;

    @FXML
    private Text titreInputError;

    @FXML
    private HBox titreInputErrorHbox;
    @FXML
    private Pane content_area;

    @FXML
    private ScrollPane scrollPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageInputErrorHbox.setVisible(false);
        categorieErrorHbox.setVisible(false);
        ContenuHboxErreur.setVisible(false);
        pieceJInputErrorHbox.setVisible(false);
        titreInputErrorHbox.setVisible(false);
//        SpinnerValueFactory<Integer> valueFactory =
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0); // Valeur initiale, valeur maximale, valeur minimale
//        dureeArt.setValueFactory(valueFactory);
//
//        // Autoriser la saisie directe des valeurs dans le Spinner
//        dureeArt.setEditable(true);
//
//        // Limiter la saisie à des nombres entiers
//        dureeArt.getEditor().setTextFormatter(new TextFormatter<>(c -> {
//            if (c.getControlNewText().matches("\\d*")) {
//                return c;
//            } else {
//                return null;
//            }
//        }));
        ObservableList<String> categories = FXCollections.observableArrayList(
                "Développement durable",
                "Finance",
                "Banque",
                "Crédits"
        );
        categoriechoice.setItems(categories);


    }

    @FXML
    void ajouter_article(MouseEvent event) throws SQLException, IOException {
        String nom = "hadhemi";
        String adresse = "mahmoud";
        ServiceArticle sa = new ServiceArticle();
        boolean champsVides = false;
        if (ContenuArt.getText().isEmpty()) {
            ContenuHboxErreur.setVisible(true);
            champsVides = true;
        } else {
            ContenuHboxErreur.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }

        if (categoriechoice.getSelectionModel().isEmpty()) {
            categorieErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            categorieErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }
        if (imageInput.getImage() == null) {
            imageInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            imageInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }

        if (titreInput.getText().isEmpty()) {
            titreInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            titreInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }
        String titre = titreInput.getText();
        if (!Character.isUpperCase(titre.charAt(0))) {
            titreInputErrorHbox.getChildren().setAll(new Text("Le titre doit commencer par une majuscule."));
            titreInputErrorHbox.setVisible(true);
            champsVides = true; // Mettre à jour champsVides pour indiquer qu'il y a une erreur
        } else {
            titreInputErrorHbox.setVisible(false); // Masquer le message d'erreur si le champ est rempli
        }
//        if (datePubArt.getValue() == null) {
//            DateHboxErreur.setVisible(true);
//            champsVides = true; // Mettre à jour champsVides pour indiquer qu'il y a une erreur
//        } else {
//            if (datePubArt.getValue().isBefore(LocalDate.now())) {
//                DateHboxErreur.getChildren().setAll(new Text("Veuillez sélectionner une date de publication valide."));
//                DateHboxErreur.setVisible(true);
//                champsVides = true; // Mettre à jour champsVides pour indiquer qu'il y a une erreur
//            } else {
//                DateHboxErreur.setVisible(false); // Masquer le message d'erreur si la date est valide
//            }
//        }

        // Si au moins un champ est vide, afficher les messages d'erreur
        if (champsVides) {
            return;
        }
//        LocalDate selectedDate = datePubArt.getValue();
//        LocalTime currentTime = LocalTime.now();
//        LocalDateTime dateTime = LocalDateTime.of(selectedDate, currentTime);
        LocalDateTime dateTime = LocalDateTime.now();
        int dureeArt=4;
        String image = imageInput.getImage().getUrl();
        String pieceJArt= "admin";
        String selectedCategory = categoriechoice.getSelectionModel().getSelectedItem();
        Article article = new Article(nom, adresse,dateTime,(Integer) dureeArt,selectedCategory,titreInput.getText(),ContenuArt.getText(),pieceJArt,image);
        sa.ajouter(article);
        if (sa.ajouter(article)) {


            // Redirection vers la liste des articles
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListArticleAdmin.fxml"));
//            Parent root = loader.load();
//            ListArticleAdminController controller = loader.getController();
//            controller.initData(); // Méthode pour rafraîchir la liste des articles si nécessaire
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListArticleAdmin.fxml"));
            Pane listArtAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArtAdminPane);

        }

    }
    @FXML
    void returnbackarticle(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listArticleAdmin.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void importerImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedImageFile = fileChooser.showOpenDialog(choose_photoBtn.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);
        }
    }
//@FXML
//private void importerImage() {
//    FileChooser fileChooser = new FileChooser();
//    fileChooser.setTitle("Choisir une image");
//    fileChooser.getExtensionFilters().addAll(
//            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
//    );
//    File selectedImageFile = fileChooser.showOpenDialog(choose_photoBtn.getScene().getWindow());
//    if (selectedImageFile != null) {
//        try {
//            // Définir le chemin de destination du dossier "uploads"
//            String destinationFolder = "C:\\Users\\HP\\Desktop\\InnovatixPiDevJava\\src\\main\\resources\\uploads";
//            // Créer un objet Path pour le dossier de destination
//            Path destinationFolderPath = Paths.get(destinationFolder);
//            // Vérifier si le dossier de destination existe, sinon le créer
//            if (!Files.exists(destinationFolderPath)) {
//                Files.createDirectories(destinationFolderPath);
//            }
//            // Définir le chemin de destination du fichier image dans le dossier "uploads"
//            String destinationFilePath = destinationFolder + File.separator + selectedImageFile.getName();
//            // Copier le fichier sélectionné vers le dossier de destination
//            Files.copy(selectedImageFile.toPath(), Paths.get(destinationFilePath));
//            // Afficher l'image importée dans l'interface utilisateur
//            Image image = new Image("file:" + destinationFilePath);
//            imageInput.setImage(image);
//        } catch (IOException e) {
//            e.printStackTrace();
//            // Gérer les exceptions liées à la copie du fichier
//        }
//    }}
    public void ajouterPiece(MouseEvent mouseEvent) {


            }
    }

