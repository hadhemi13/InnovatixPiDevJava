package controllers.Cheque;

import Entities.Cheque;
import Entities.Compte;
import controllers.Compte.CompteItems;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeChequeAdmin implements Initializable {

    @FXML
    private VBox ChequeContainer;
    @FXML
    private Pane content_area;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCheque serviceCheque = new ServiceCheque();
        List<Cheque> list = new ArrayList<>();
        try {
            list = serviceCheque.afficher(); // Notez le changement ici
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (Cheque cheque : list) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ChequeItemsAdmin.fxml"));
                Parent offreItem = loader.load();
                ChequeItemsAdmin offreStageItem = loader.getController();
                offreStageItem.initData(cheque);
                ChequeContainer.getChildren().add(offreItem);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void returnbackCardCH(MouseEvent mouseEvent) {
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
