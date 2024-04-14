package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class DemandeStageController {
    @FXML
    private TextArea textLettreDem;

    @FXML
    private TextField textNomDem;

    @FXML
    private TextField textPrenomDem;

    @FXML
    private Button btnAddDem;

    @FXML
    private Button UploadCv;

    @FXML
    private Button btnClearDem;

    @FXML
    private TextField textNumeroDem;

    @FXML
    private AnchorPane addDemandePane;

    @FXML
    private WebView CvDemandeInput;

    @FXML
    private ComboBox<?> textDomaineDem;

    @FXML
    private Button btnReturn;

    @FXML
    private Label CvDemandeInputa;

    @FXML
    private DatePicker dateDem;

    @FXML
    private TextField textEmailDem;

    private File selectedCvFile;
    private String fileName;

    @FXML
    void AddDemandeStage(ActionEvent event) {

    }

    @FXML
    void clearFieldsDemande(ActionEvent event) {

    }

//    @FXML
//    void 720000(ActionEvent event) {
//
//    }

    @FXML
    void return_ListActivite(ActionEvent event) {

    }
//    private String fileName = null;
    @FXML
    void uploadCV() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir votre CV");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        selectedCvFile = fileChooser.showOpenDialog(UploadCv.getScene().getWindow());

        if (selectedCvFile != null) {
            // Charger le PDF dans la WebView
            String url = selectedCvFile.toURI().toString();
            CvDemandeInput.getEngine().load(url);

            // Générer un nom de fichier unique pour le CV
            String uniqueID = UUID.randomUUID().toString();
            String extension = ".pdf"; // L'extension est déjà spécifiée dans le filtre de l'FileChooser
            fileName = uniqueID + extension;

            // Copier le fichier PDF vers le répertoire de destination
            Path destination = Paths.get(System.getProperty("user.dir"), "src", "Images", "uploads", fileName);
            Files.copy(selectedCvFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    @FXML
    public void UploadCv(ActionEvent actionEvent) {
        try {
            uploadCV();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @FXML
//    void aeabab(ActionEvent event) {
//
//    }

}

