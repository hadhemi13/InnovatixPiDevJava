package controllers.offreDeStage;

import Entities.DemandeStage;
import Entities.OffreDeStage;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditOffreController implements Initializable {

    private static String fxmlToLoad;
    public static String getFxmlToLoad() {
        return fxmlToLoad;
    }

    public static void setFxmlToLoad(String FxmlToLoad) {
        EditOffreController.fxmlToLoad = FxmlToLoad;
    }
    public void initData(OffreDeStage demande) {
        // Initialiser les champs de l'interface utilisateur avec les données de la demande
        // Par exemple :
//        NomDemInput.setText(demande.getNom());
//        PrenomDemInput.setText(demande.getPrenom());
        // Autres initialisations...

    }
    public static void setProjectUpdateData(OffreDeStage project) {
//        projectToUpdate = project;

//        Image image = new Image(getClass().getResource("/assets/projectUploads/" + project.getImg()).toExternalForm());
//        imageInput.setImage(image);
//        imageName = project.getImg();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
