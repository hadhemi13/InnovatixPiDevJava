package controllers.reclamation;

import Entities.actualites.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import services.ServiceReclamation;

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

public class AjouterReclamationController implements Initializable {

    @FXML
    private Text ContenuArtInputError;

    @FXML
    private VBox content_area;

    @FXML
    private HBox ObjetHboxErreur;

    @FXML
    private Button addRecBtn;

    @FXML
    private Text addpieceJBtn;

    @FXML
    private HBox contenuInputErrorHbox;

    @FXML
    private TextArea contenuRec;

    @FXML
    private Text depInputError;

    @FXML
    private HBox depRecErrorHbox;

    @FXML
    private ComboBox<String> departementRec;

    @FXML
    private TextField objetRec;

    @FXML
    private ImageView pieceJArtInput;

    @FXML
    private HBox pieceJInputErrorHbox;

    @FXML
    private Text piecejointeInputError;

    @FXML
    private Text titreInputError;
    private String pdfName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        depRecErrorHbox.setVisible(false);
        contenuInputErrorHbox.setVisible(false);
        ObjetHboxErreur.setVisible(false);
        pieceJInputErrorHbox.setVisible(false);
        ObservableList<String> departement = FXCollections.observableArrayList(
                "RH",
                "Finance",
                "Crédits",
                "Autres"

        );
        departementRec.setItems(departement);


    }
    @FXML


    void ajouter_reclamation(MouseEvent event) throws SQLException, IOException {
        String nom = "hadhemi";
        String adresse = "mahmoud";

        ServiceReclamation sr = new ServiceReclamation();

        if (contenuRec.getText().isEmpty()) {
            contenuInputErrorHbox.setVisible(true);
            return;
        }

        if (departementRec.getSelectionModel().isEmpty()) {
            depRecErrorHbox.setVisible(true);
            return;
        }

        if (objetRec.getText().isEmpty()) {
            ObjetHboxErreur.setVisible(true);
            return;
        }
        LocalDateTime dateTime = LocalDateTime.now();

        String selectedDepartment = departementRec.getSelectionModel().getSelectedItem();
        String pieceJArt = pdfName;

        String statut ="En cours de traitement";
        Reclamation reclamation = new Reclamation( objetRec.getText(), contenuRec.getText(), adresse,nom, selectedDepartment,statut, pieceJArt, dateTime);
                sr.ajouter(reclamation);
        if (sr.ajouter(reclamation)) {


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/reclamation/listeRecClient.fxml"));
            Pane listArtAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArtAdminPane);

        }
    }


    public void addpieceJBtn(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier PDF");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        File selectedPDFFile = fileChooser.showOpenDialog(pieceJArtInput.getScene().getWindow());
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
        }}
}
