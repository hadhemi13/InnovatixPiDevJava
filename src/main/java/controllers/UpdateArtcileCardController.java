package controllers;

 import Entities.Article;
 import javafx.collections.FXCollections;
 import javafx.collections.ObservableList;
 import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
 import javafx.scene.control.ComboBox;
 import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
 import javafx.scene.text.Text;
 import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
 import services.ServiceArticle;


 import java.awt.*;
 import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.UUID;

public class UpdateArtcileCardController implements Initializable {
    @FXML
    private TextArea ContenuArt;

    @FXML
    private Text addpieceJBtn;

    @FXML
    private HBox choose_photoBtn;

    @FXML
    private ImageView imageInput;

    @FXML
    private ImageView pieceJArtInput;

    @FXML
    private TextField titreArt;

    @FXML
    private Button update_projectBtn;


    @FXML
    private TextField nomProjetInput;

    @FXML
    private TextField categorieInput;

    @FXML
    private TextArea descriptionProjetInput;

    @FXML
    private TextField budgetProjetInput;

    @FXML
    private TextField dateCreationInput;

    @FXML
    private TextField dureeProjetInput;

    @FXML
    private TextField statutProjetInput;


    Article articleToUpdate;
    private File selectedImageFile;
    private String imageName;
    private static String FxmlToLoad;

    @FXML
    private ComboBox<String> addCategorie;

    public static String getFxmlToLoad() {
        return FxmlToLoad;
    }
    private String pdfName;


    public static void setFxmlToLoad(String FxmlToLoad) {
        UpdateArtcileCardController.FxmlToLoad = FxmlToLoad;
    }

    @FXML
    void ajouter_image(MouseEvent mouseEvent) throws IOException {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Choisir une image");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
//        );
//        File selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
//        if (selectedImageFile != null) {
//            Image image = new Image(selectedImageFile.toURI().toString());
//            imageInput.setImage(image);
//        }
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

    @FXML
    void updateProject(MouseEvent event) {
        ServiceArticle serviceArticle = new ServiceArticle();
        articleToUpdate.setTitre_art(titreArt.getText());
        articleToUpdate.setContenu_art(ContenuArt.getText());
        articleToUpdate.setDate_pub_art(LocalDateTime.now());
        articleToUpdate.setCategorie_art(addCategorie.getValue());
        articleToUpdate.setPiecejointe_art(pdfName);
        articleToUpdate.setDuree_art(2);
         articleToUpdate.setImage_art( imageName );


        try {
            serviceArticle.modifier(articleToUpdate);
            ListArticleAdminController.setupdateArticleModelShow(0);
            ListArticleAdminController.setShowArticleModelShow(0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listArticleAdmin.fxml"));
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setProjectUpdateData(Article article) {
        articleToUpdate = article;
        titreArt.setText(article.getTitre_art());
        ContenuArt.setText(article.getContenu_art());
        addCategorie.setValue(article.getCategorie_art());

        Image image = new Image("file:///" + System.getProperty("user.dir") + "/src/main/java/uploads/" + article.getImage_art());
        imageInput.setImage(image);
        imageName = article.getImage_art();
    }
//    public void setProjectShowData(Project project) {
//        projectToUpdate = project;
//        nomProjetInput.setText(project.getNomProjet());
//        descriptionProjetInput.setText(project.getDescriptionProjet());
//        categorieInput.setText(project.getCategorie());
//        dureeProjetInput.setText(String.valueOf(project.getDureeProjet()));
//        budgetProjetInput.setText(String.valueOf(project.getBudgetProjet()));
//        statutProjetInput.setText(String.valueOf(project.getStatutProjet()));
//        dateCreationInput.setText(String.valueOf(project.getDateCreation()));
//
//        Image image = new Image(getClass().getResource("/assets/projectUploads/" + project.getImg()).toExternalForm());
//        imageInput.setImage(image);
//        imageName = project.getImg();
//    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        imageInputErrorHbox.setVisible(false);
//        categorieInputErrorHbox.setVisible(false);
//        ContenuInputErrorHbox.setVisible(false);
//        PieceJInputErrorHbox.setVisible(false);
//        titreInputErrorHbox.setVisible(false);
        // Initialiser la liste des catégories
        ObservableList<String> categories = FXCollections.observableArrayList(
                "Développement durable",
                "Finance",
                "Banque",
                "Crédits"
        );
        addCategorie.setItems(categories);
        pieceJArtInput.setOnMouseClicked(this::openArticleImage);


    }
    private void openArticleImage(MouseEvent mouseEvent) {

        Path destination = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "uploadsPdfH", articleToUpdate.getPiecejointe_art());

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

    public void ajouterPiece(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier PDF");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File selectedPDFFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedPDFFile != null) {
            // Générer un nom de fichier unique pour le PDF
            String uniqueID = UUID.randomUUID().toString();
            String extension = ".pdf";
            pdfName = uniqueID + extension; // Mettre à jour la variable de classe pdfName

            // Définir le répertoire de destination pour les PDF téléchargés
            String destinationFolder = "C:\\Users\\HP\\Desktop\\InnovatixPiDevJava\\src\\main\\java\\uploadsPdfH"; // Chemin absolu du répertoire de destination

            // Créer le chemin de destination pour le PDF
            Path destination = Paths.get(destinationFolder, pdfName);

            try {
                // Copier le fichier sélectionné vers le dossier de destination
                Files.copy(selectedPDFFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                // Gérer les erreurs de copie
                e.printStackTrace();
            }
        }
    }

    public void PieceJArtInput(MouseEvent mouseEvent) {
    }
}