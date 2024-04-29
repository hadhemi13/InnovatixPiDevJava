package controllers;

import Entities.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

import javax.imageio.IIOParam;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.cert.PolicyNode;
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
    private ComboBox<String> addCategorie;

    @FXML
    private ImageView imageInput;

    @FXML
    private VBox vboxmod;
    @FXML
    private HBox PieceJInputErrorHbox;

    @FXML
    private HBox titreInputErrorHbox;
    @FXML
    private HBox categorieInputErrorHbox;

    @FXML
    private Text titreInputError;
    @FXML
    private ImageView pieceJArtInput;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private HBox ContenuInputErrorHbox;
    @FXML
    private Text imageInputError;
    private Article article;
    private articleCardAdminController articleCardAdminController;
    private ListArticleAdminController listArticleController;

    private ServiceArticle serviceArticle;
    @FXML
    private Pane content_area;
    public void setListArticleController(ListArticleAdminController listArticleController) {
        this.listArticleController = listArticleController;
    }
    public void setarticleCardAdminController(articleCardAdminController articleCardAdminController) {
        this.articleCardAdminController = articleCardAdminController;
    }

    public void initData(Article article) {
        this.article = article;
        initializeArticleFields();
        pieceJArtInput.setOnMouseClicked(this::openArticleImage);

    }
    private void openArticleImage(MouseEvent mouseEvent) {
        Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "uploadsPdfH", article.getPiecejointe_art());

        try {
            File file = destination.toFile();
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("File not found: " + destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeArticleFields() {
        if (article != null) {
            titreArt.setText(article.getTitre_art());
            ContenuArt.setText(article.getContenu_art());
            addCategorie.setValue(article.getCategorie_art());
         //    Assurez-vous que l'image de l'article n'est pas vide
            if (article.getImage_art() != null && !article.getImage_art().isEmpty()) {
                Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "uploads", article.getImage_art());
                if (Files.exists(destination)) {
                    try {
                        Image image = new Image(destination.toUri().toString());
                        imageInput.setImage(image);
                    } catch (Exception e) {
                        System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
                        // Gérer l'erreur de chargement de l'image ici
                    }
                } else {
                    System.err.println("Le fichier image n'existe pas : " + destination);
                    // Gérer l'absence du fichier image ici
                }
            }
        }
        }

@FXML
void ModifierArt(MouseEvent mouseEvent) {
    try {

        if (article != null) {
            // Mettre à jour les données de l'article avec les nouvelles valeurs
            article.setTitre_art(titreArt.getText());
            article.setContenu_art(ContenuArt.getText());
            article.setDate_pub_art(LocalDateTime.now());
            article.setCategorie_art(addCategorie.getValue());
            article.setDuree_art(2);
            // Assurez-vous que l'image de l'article n'est pas vide
            if (imageInput.getImage() != null) {
                article.setImage_art(imageInput.getImage().getUrl());
            }

            // Appeler la méthode de service pour effectuer la mise à jour de l'article dans la base de données
            ServiceArticle serviceArticle = new ServiceArticle();
            serviceArticle.modifier(article);


            // Fermer la fenêtre de modification d'article
            Stage stage = (Stage) titreArt.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listArticleAdmin.fxml"));
            try {
                Parent root = loader.load();

                Pane contentArea = (Pane) ((Node) mouseEvent.getSource()).getScene().lookup("#content_area");

                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }} catch (SQLException e) {
        throw new RuntimeException(e);
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
        imageInputErrorHbox.setVisible(false);
        categorieInputErrorHbox.setVisible(false);
        ContenuInputErrorHbox.setVisible(false);
        PieceJInputErrorHbox.setVisible(false);
        titreInputErrorHbox.setVisible(false);
        ObservableList<String> categories = FXCollections.observableArrayList(
                "Développement durable",
                "Finance",
                "Banque",
                "Crédits"
        );
        addCategorie.setItems(categories);

        initializeArticleFields();
    }


    public void initListArticleController(ListArticleAdminController listArticleController) {
        this.listArticleController = listArticleController;
    }

    }


