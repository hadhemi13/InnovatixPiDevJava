package controllers.demandeStage;

import Entities.DemandeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceDemandeStage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageParOffreController implements Initializable {


    @FXML
    private ComboBox<String> statusInput;
    @FXML
    private Button RetourS;
    @FXML
    private HBox userTableHead;

    @FXML
    private Pane content_area;

    @FXML
    private VBox userListContainer;
    private ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();
    public static List<DemandeStage> list ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceDemandeStage projectService = new ServiceDemandeStage();
//        List<DemandeStage> list = new ArrayList<>();

//
        for (DemandeStage offre : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/DemandeStage/DemandeStageItem.fxml"));
                Parent offreItem = loader.load();
                DemandeStageItemController offreStageItem = loader.getController();
                offreStageItem.initData(offre);
                userListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void statusChange(ActionEvent event) {
    }

    public void RetourBack(MouseEvent mouseEvent) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/YesserTest/CardNavBar.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
