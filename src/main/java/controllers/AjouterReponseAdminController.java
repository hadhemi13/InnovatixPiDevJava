package controllers;

import Entities.Reclamation;
import Entities.Reponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceReponse;

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

public class AjouterReponseAdminController implements Initializable {

    @FXML
    private Text addpieceJBtn;

    @FXML
    private TextArea contenuInput;

    @FXML
    private Text contenuInputError;

    @FXML
    private HBox contenuInputErrorBox;

    @FXML
    private ImageView imageInput;

    @FXML
    private Text pieceInputError;

    @FXML
    private HBox pieceInputErrorBox;

    @FXML
    private Button reponseBtn;
    private Reclamation reclamation;
    private String pdfName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contenuInputErrorBox.setVisible(false);
        pieceInputErrorBox.setVisible(false);


    }
    @FXML
    void ajouter_image(MouseEvent event) {
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


    @FXML
    void addReponse(MouseEvent event) {

        int newId = ReclamationItemAdminController.idAn;

        ServiceReponse sr = new ServiceReponse();
        if (contenuInput.getText().isEmpty()) {
            contenuInputErrorBox.setVisible(true);
            return;
        }
        if (addpieceJBtn.getText().isEmpty()) {
            pieceInputErrorBox.setVisible(true);
            return;
        }
        LocalDateTime dateTime = LocalDateTime.now();
        String adresse = "mahmoud";
        String pieceJointe = pdfName;
        Reponse reponse = new Reponse(newId, adresse, dateTime, contenuInput.getText(), pieceJointe);
        try {
            sr.ajouter(reponse);
            reclamation.setStatut_rec("Traitée");

            // Fermeture de la fenêtre d'ajout de réponse
            ((Stage) contenuInput.getScene().getWindow()).close(); // Assurez-vous d'importer javafx.stage.Stage

            // Ouvrir la liste des réponses dans la fenêtre parent
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listRepAdmin.fxml"));
            Parent listRepAdminPane = loader.load();
            ListRepAdminController listRepController = loader.getController();
            // Initialiser la liste des réponses si nécessaire
            // Par exemple, listRepController.initDataRep(reponse);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(listRepAdminPane));
            stage.show();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initDataRec(Reclamation reclamation) {
        this.reclamation = reclamation;
        // Autres initialisations si nécessaire
    }

}