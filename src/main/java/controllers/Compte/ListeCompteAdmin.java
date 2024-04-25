package controllers.Compte;

import Entities.Cheque;
import Entities.Compte;
import controllers.ChequeItemsController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCheque;
import services.ServiceCompte;

import java.io.IOException;
import java.net.URL;
import java.security.cert.PolicyNode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeCompteAdmin  implements Initializable {

    @FXML
    private Pane content_area;
    @FXML
    private VBox CompteContainer;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCompte serviceCompte = new ServiceCompte();
        List<Compte> list = new ArrayList<>();
        try {
            list = serviceCompte.afficher(); // Notez le changement ici
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Compte compte : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CompteItems.fxml"));
                Parent offreItem = loader.load();
                CompteItems offreStageItem = loader.getController();
                offreStageItem.initData(compte);
                CompteContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void returnbackCard(MouseEvent mouseEvent) {
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
