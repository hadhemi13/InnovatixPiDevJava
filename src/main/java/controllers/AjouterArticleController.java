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
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.UUID;

public class AjouterArticleController implements Initializable {
    @FXML
    private Text CatArtInputError;
    private  File selectedImageFile;
    private String imageName = null ;
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

        if (champsVides) {
            return;
        }

        LocalDateTime dateTime = LocalDateTime.now();
        int dureeArt=4;
        String img = imageName;
        String pieceJArt= "admin";
        String selectedCategory = categoriechoice.getSelectionModel().getSelectedItem();
        Article article = new Article(nom, adresse,dateTime,(Integer) dureeArt,selectedCategory,titreInput.getText(),ContenuArt.getText(),pieceJArt,img);
        boolean ajoutReussi = sa.ajouter(article);

        if (ajoutReussi) {

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
    void importerImage() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "uploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        }
    }
    public String getImageName() {
        return imageName;
    }

    public void ajouterPiece(MouseEvent mouseEvent) {


    }
}