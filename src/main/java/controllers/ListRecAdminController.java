package controllers;

import Entities.Reclamation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceReclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListRecAdminController implements Initializable {

    @FXML
    private VBox RecListContainer;

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<?> inputRectrie;

    @FXML
    private HBox recTableHead;
    @FXML
    void openListRep(MouseEvent event) {
        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listRepAdmin.fxml"));
            Pane listRepAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listRepAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceReclamation sr = new ServiceReclamation() ;
        List<Reclamation> list = new ArrayList<>();
        try {
            list = sr.afficher();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // userListContainer.getChildren().add();
//           OffreStageItem offreStageItem = new OffreStageItem();
//            userListContainer.getChildren().add(offreStageItem.initE());

        for (Reclamation reclamation : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ReclamationItemAdmin.fxml"));
                Parent offreItem = loader.load();
                ReclamationItemAdminController RecItem = loader.getController();
                RecItem.initData(reclamation);
                RecListContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
