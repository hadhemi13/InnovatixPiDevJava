package controllers.Virement;

import Entities.Compte;
import Entities.Virement;
import controllers.Compte.CompteItems;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCompte;
import services.ServiceVirement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListVirementAdmin  implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private Button retourVir;

    @FXML
    private VBox VireContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceVirement serviceVirement = new ServiceVirement();
        List<Virement> list = new ArrayList<>();
        try {
            list = serviceVirement.afficher(); // Notez le changement ici
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Virement virement : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/VirementItemsAdmin.fxml"));
                Parent offreItem = loader.load();
                VirementItemsAdmin offreStageItem = loader.getController();
                offreStageItem.initData(virement);
                VireContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void returnBackVir(MouseEvent event) {

        try {
            // Charger le fichier FXML de listArticleAdmin
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CardAdmin.fxml"));
            Pane listArticleAdminPane = loader.load();

            // Remplacer le contenu de content_area par le contenu de listArticleAdmin
            content_area.getChildren().setAll(listArticleAdminPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
